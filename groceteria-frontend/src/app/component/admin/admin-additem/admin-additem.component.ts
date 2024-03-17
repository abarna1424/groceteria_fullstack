import { Component } from '@angular/core';
import { GroceteriaService } from '../../../groceteria.service';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Item } from '../../model/item.model';

@Component({
  selector: 'app-admin-additem',
  templateUrl: './admin-additem.component.html',
  styleUrl: './admin-additem.component.css'
})
export class AdminAdditemComponent {

  itemName: string = '';
  image: string = '';
  description: string = '';
  mrpPrice: number = 0;
  quantity: number = 0;
  isEdit: boolean =false;
  itemId: any;
  getCategoryList: any[] = [];
  category: number = 0;

  constructor(

    private gservice: GroceteriaService,
    private router: Router,
    private activateRouter: ActivatedRoute
  ){
    this.activateRouter.queryParams.subscribe((params: any)=>{
      if(params?.id){
        this.isEdit = true;
        this.gservice.getItemById(params?.id).pipe(take(1)).subscribe((res: any)=> {
          if(!!res && res?.itemId){

            const item :Item=res;
            console.log('>>>>',item);
            this.itemName= item?.itemName;
            this.description= item?.description;
            this.image= item?.image;
            this.mrpPrice=item?.mrpPrice;
            this.quantity=item?.quantity;
            this.itemId=item?.itemId;
            const categoryName=this.getCategoryList.find((cate: any)=> cate?.name.toString() === item?.category)?.value;
            this.category = categoryName;
          }
          console.log(res);
        });
      }
    })

  }

  ngOnInit(): void{
    this.gservice.isUserLoginPresent();
    this.getCategoryList = this.gservice.getCategoryList();
  }

  onAddItem(): void{

    if(this.itemName === ''){
      alert("Item name is required");
      return;
    }

    if(this.description === ''){
      alert("description is required");
      return;
    }

    if(this.image === ''){
      alert("image should not be blank");
      return;
    }

    console.log("******MRP price", this.mrpPrice);
    if(this.mrpPrice === 0 || this.mrpPrice === null){
      alert("MRP price should not be zero/blank");
      return;
    }

    if(this.quantity === 0|| this.quantity===null || this.quantity<0){
      alert("Quantity should not be zero/blank and negative");
      return;
    }


    const body: any ={
      itemName: this.itemName,
      image: this.image,
      description: this.description,
      mrpPrice:this.mrpPrice,
      quantity:this.quantity,
      category:this.category
    }

    if(this.isEdit){
      console.log("=======>", body);
    this.gservice.editItem(body,this.itemId).pipe(take(1)).subscribe((res: any) => {
      console.log("*****", res);
      if (res && res?.itemId) {
        alert("Item updated sucessfully");
        this.router.navigate(["/admin/itemlist"]);
      }
    }, err => {
      console.log("Error  ", err);
      alert("Something going wrong!! Please try again");
    })
    }else{
      console.log("=======>", body);
      this.gservice.addItem(body).pipe(take(1)).subscribe((res: any) => {
        console.log("*****", res);
        if (res && res?.itemId) {
          alert("Item added sucessfully");
          this.router.navigate(["/admin/itemlist"]);
        }
      }, err => {
        console.log("Error  ", err);
        alert("Something going wrong!! Please try again");
      })
    }

  }
}
