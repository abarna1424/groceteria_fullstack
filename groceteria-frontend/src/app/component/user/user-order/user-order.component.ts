import { Component, NgZone, OnInit } from '@angular/core';
import { Order } from '../../model/order.model';
import { GroceteriaService } from '../../../groceteria.service';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { UserOrderHistoryComponent } from '../user-order-history/user-order-history.component';
import { take } from 'rxjs';

declare var Razorpay: any;

@Component({
  selector: 'app-user-order',
  templateUrl: './user-order.component.html',
  styleUrl: './user-order.component.css'
})
export class UserOrderComponent implements OnInit{

  orderList: Order[] = [];
  selectedOrder: Order | undefined;
  user: any = {};
  constructor(
    private gservice: GroceteriaService,
    private router: Router,
    private datePipe: DatePipe,
    private dialog: MatDialog,
    private ngZone: NgZone
  ) {
    this.gservice.isUserLoginPresent();
  }

  ngOnInit(): void {
    this.getUserDetail();
    this.getOrderList();
  }

  getUserDetail(): void {
    const cid = this.gservice.getUserAuthorization();
    this.gservice.getUserById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("User*****", res);
        if (!!res && res?.userId) {
          this.user = res;
        }
      }, err => {
        console.log("Err");
      }
    );
  }

  getOrderList(): void {
    this.gservice.orderList(this.gservice.getUserAuthorization()).pipe(take(1)).subscribe(
      (res: any) => {
        if (!!res && Array.isArray(res)) {
          this.orderList = res;
          console.log('>>>>>>>>##>>>', this.orderList)
        }
      }, err => {
        console.log("Error");
      }
    );
  }

  getDate(d: string | undefined): any {

    let ans: any;
    console.log('$$$', d);
    if (!!d && d !== null) {
      ans = this.datePipe.transform(d, "shortDate") || null;
    }
    return ans;
  }

  addPayment(order: Order): void {

    this.gservice.addPaymentOfOrder(order?.totalPrice).pipe(take(1)).subscribe((res: any) => {
      console.log('>>>>12333', res);
      console.log('****');
      if (res && res?.orderId) {
        this.openTransactionModel(res);
        this.selectedOrder = order;
      }

    }, error => {
      console.log("Error => ", error);
    })
  }

  openHistory(order: Order): void {
    console.log('>>>>>>>', order);
    console.log('========');
    const dialogRef = this.dialog.open(UserOrderHistoryComponent, {
      data: order,
      maxWidth: '100vw',
      maxHeight: '100vh',
      height: '80%',
      width: '80%'

    });
  }



  openTransactionModel(response: any) {
    var options = {
      order_id: response.orderId,
      key: response.key,
      amount: response.amount,
      currency: response.currency,
      name: 'Groceteria',
      description: 'Payment of Groceteria',
      image: 'https://m.media-amazon.com/images/I/51Kvj6axeAL.png',
      handler: (response: any) => {
        console.log('####', response);
        if (response != null && response.razorpay_payment_id != null) {
          this.processResponse(response);
        } else {
          alert("Payment failed..")
        }

      },
      prefill: {
        name: 'LPY',
        email: 'LPY@GMAIL.COM',
        contact: '9004380734'
      },
      notes: {
        address: 'Groceteria'
      },
      theme: {
        color: '#F37254'
      }
    };

    var razorPayObject = new Razorpay(options);
    razorPayObject.open();
  }



  processResponse(resp: any) {

    console.log('>>>>>>>>>>>>>>>>>>>>>>>>>>', resp);
    if (resp && resp?.razorpay_order_id && resp?.razorpay_payment_id && this.selectedOrder) {
      const body: any = {
        totalPrice: this.selectedOrder?.totalPrice,
        orderId: this.selectedOrder?.orderId,

        PaidDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd')?.toString(),
        paidAmount: this.selectedOrder?.totalPrice,
        user: this.user


      };
      console.log("$$$$$$$", body);
      this.gservice.addPayment(body, this.selectedOrder?.orderId, this.user?.userId).pipe(take(1)).subscribe(
        (res: any) => {
          console.log("*********", res);
          if (res && res?.paymentId) {
            alert("Payment done successfully");
            this.ngZone.run(() => {
              this.getOrderList();
            });

          }
        }, err => {
          console.log("error");
        }
      )
    }
  }




}
