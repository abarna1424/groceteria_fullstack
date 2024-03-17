import { Component, Inject } from '@angular/core';
import { Order } from '../../model/order.model';
import { Item } from '../../model/item.model';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-user-order-history',
  templateUrl: './user-order-history.component.html',
  styleUrl: './user-order-history.component.css'
})
export class UserOrderHistoryComponent {

  order: Order| undefined;
  item: Array<Item> = [];

  constructor(
    public dialogRef: MatDialogRef<UserOrderHistoryComponent>,
    @Inject(MAT_DIALOG_DATA)public data: any
  ){

    console.log('>>>', data);
    if(!!data && data?.orderId){
      this.order = data;
      if(this.order?.item && this.order?.item.length > 0){
        this.item = this.order?.item;
      }
    }

  }

  closeDialog(): void{
    this.dialogRef.close();
  }


}
