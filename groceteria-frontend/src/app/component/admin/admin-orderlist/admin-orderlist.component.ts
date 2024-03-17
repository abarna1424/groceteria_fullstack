import { Component } from '@angular/core';
import { Order } from '../../model/order.model';
import { GroceteriaService } from '../../../groceteria.service';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { take } from 'rxjs';

@Component({
  selector: 'app-admin-orderlist',
  templateUrl: './admin-orderlist.component.html',
  styleUrl: './admin-orderlist.component.css'
})
export class AdminOrderlistComponent {

  orderList: Order[] = [];
  tempOrderList: Order[] = [];
  today = new Date();
  constructor(
    private gservice: GroceteriaService,
    private router: Router,
    private datePipe: DatePipe
  ){
    this.gservice.isUserLoginPresent();
  }

  ngOnInit(): void{
    this.getOrderList();
  }

  getOrderList(): void{
    this.gservice.getAllorderList().pipe(take(1)).subscribe(
      (res: any) => {
        if(!!res && Array.isArray(res)){
          this.orderList = res;
          this.tempOrderList = res;
        }
      }, err => {
        console.log("Error");
      }
    );
  }

  getDate(d: string | undefined): any{
    let ans: any;
    if(!!d && d !== null){
      ans = this.datePipe.transform(d, "shortDate") || null;
    }
    return ans;
  }

  changeDate(ev: any): void{
    const date: string = this.datePipe.transform(ev?.value, 'yyyy-MM-dd')?.toString() || '';
    this.orderList= this.tempOrderList.filter((item: Order) => new Date(item?.orderDate).getTime() === new Date(date).getTime() );
  
  }

}
