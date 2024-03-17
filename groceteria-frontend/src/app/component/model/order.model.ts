import { Item } from "./item.model";


export interface Order{
    mrpPrice : number;
    orderId : number;
    orderStatus : string;
    paymentStatus : string;
    quantity : number;
    totalPrice : number;
    orderDate : string;
    itemName : string;
    image: string;
    item :  Array<Item>;
}