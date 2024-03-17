import { Component } from '@angular/core';
import { Cart } from '../../model/cart.model';
import { GroceteriaService } from '../../../groceteria.service';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { forkJoin, take } from 'rxjs';
import { Item } from '../../model/item.model';
import * as _ from "lodash";

@Component({
  selector: 'app-user-cart',
  templateUrl: './user-cart.component.html',
  styleUrl: './user-cart.component.css'
})
export class UserCartComponent {

  cartList: Cart[] = [];
  cartListBackup : Cart[] = [];
  grandTotal:  number = 0;
  user: any = {};


  constructor(
    private gservice: GroceteriaService,
    private router: Router,
    private datePipe: DatePipe
  ){
    this.gservice.isUserLoginPresent();
    this.getCartList();
    this.getUserDetail();
  }

  ngOnInit(): void{

  }

  getCartList(): void{
    this.gservice.cartList().pipe(take(1)).subscribe(
      (res: any) => {
        console.log("****", res);
        if(!!res && Array.isArray(res)){ 
        const userFilter = res.filter((item: Cart) => item?.user?.userId === parseInt(this.gservice.getUserAuthorization()));
        console.log("user filter:::::::", userFilter);
        this.cartList = userFilter;
        // this.cartListBackup =  _.cloneDeep(userFilter);
        this.cartListBackup =  Object.assign(userFilter);
        if(this.cartList.length > 0){
          this.cartList.map((item: Cart) => {
            this.grandTotal += (item?.mrpPrice * item?.quantity);
          })
        }
      }
      }, err => {
        console.log("error");
      }
    );
  }


  getTotal(quantity: number = 0, mrpPrice: number = 0):number{
    return quantity * mrpPrice;
  }

  placeOrder(): void{
    let totalPrice: number = 0;
    const deleteCartReq: any[] = [];
    const grocetItems: Array<Item> = [];
    this.cartList.forEach((item: Cart) => {
      grocetItems.push(item?. item);
      totalPrice += (item?.mrpPrice * item?.quantity);
      deleteCartReq.push(this.gservice.deleteCart(item?.cartId));
    });

    console.log('>>>>>>>>', totalPrice)
    const body: any = {
      totalPrice: totalPrice,
      orderStatus: "success",
      paymentStatus: "success",
      orderDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
      user: this.user,
      itemName: 'xxxxx',
      image: 'xxxxx',
      item: grocetItems
    };

    this.gservice.placeOrderItem(this.user?.userId, body).pipe(take(1)).subscribe((res: any) => {
      console.log('>>>>>>>', res);
      forkJoin(deleteCartReq).pipe(take(1)).subscribe();
      alert("place order Successfully");
      this.router.navigate(["/user/order"]);
    });
  }


  getUserDetail(): void{
    const cid = this.gservice.getUserAuthorization();
    this.gservice.getUserById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        // console.log("user***",res);
        if(!!res && res?.userId){
          this.user = res;
        }
      }, err => {
        console.log("Err");
      }
    );
  }


  deleteCart(cart:Cart, showAlert: boolean = true):void{
    this.gservice.deleteCartByQuanity(cart?.cartId, cart?.quantity).pipe(take(1)).subscribe(
      (res: any) => {
        if (showAlert) {
          alert("Item deleted sucessfully");
        }
        this.getCartList();
      }, err => {
        console.log("Err");
      }
    )
  }

  onIncreaseQunatity(cart: Cart): void{

    const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
    const indexBackup =  this.cartListBackup.findIndex((item: Cart) => item.cartId === cart?.cartId);
    const qty = cart.quantity + 1;
    console.log(this.cartListBackup[indexBackup].quantity, '>>>>>>', (cart.item?.quantity))
    if(qty > (cart.item?.quantity + this.cartListBackup[indexBackup].quantity)){
      alert('Added quantity should not greater than available quantity');
      return;
    }
    this.cartList[index].quantity =qty;
    this.updateGrantTotal();
}

onDecreaseQunatity(cart: Cart): void {
  const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
  const qty = cart.quantity - 1;
  if (qty === 0) {
    this.deleteCart(cart,false);
  }
  this.cartList[index].quantity = qty;
  this.updateGrantTotal();
}

updateGrantTotal(): void {
  let total = 0;
  this.cartList.map((item: Cart) => {
    total+= (item?.mrpPrice * item?.quantity);
    
   
  })
  this.grandTotal = total;
  console.log("+++++++", this.grandTotal);
}

}
