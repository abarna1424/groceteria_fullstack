import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GroceteriaService {
  url: string = 'http://localhost:8080';


  //CATEGORY SECTION
  category: any=[{
    name: "VEGETABLES", value: 0
  }, 
  {
    name: "FRUITS" , value: 1
  },
  {
    name: "DAIRYPRODUCTS", value: 2
  },
  {
    name: "MEAT", value: 3
  },
  {
    name: "GRAINS AND OILS", value: 4
  },
  {
    name: "SPICES AND SEASONINGS", value: 5
  },
  {
    name: "BAKING INGREDIENTS", value: 6
  },
  {
    name: "CONDIMENTS", value: 7
  },
  {
    name: "SNACKS", value: 8
  },
  {
    name: "SKINCARE", value: 9 
  }]

  constructor(
    private http: HttpClient,
    private route: Router
  ) { }


  /*customer Registeration*/
  signUp(body: any):Observable<any>{
    return this.http.post(this.url + "/users/register", body);
  }

  /*customer login*/
  userSignIn(body: any): Observable<any>{
    return this.http.post(this.url + "/users/api/login", body);
  }

  // once we logged in that time we are storing customer id into token
  storeUserAuthorization(token: string): void {
    localStorage.setItem("token", token);
  }

  getUserAuthorization(): any {
    const token = localStorage.getItem("token");
    return token;
  }

  storeUserName(name: string): void {
    localStorage.setItem("userName", name);
  }

  getUserName(): any {
    const name = localStorage.getItem('userName');
    return name;
  }

  userLogout(): void {
    localStorage.clear();
    this.route.navigate(['']);
  }


   //admin login
   adminSignIn(body: any): Observable<any> {
    // return this.http.post(this.url + "/api/admin/login", body);
    return this.http.post(this.url + "/users/login", body);
  }
  storeAdminAuthorization(token: string): void {
    localStorage.setItem("admin", token);
  }
  getAdminAuthorization(): any {
    const token = localStorage.getItem("admin");
    return token;
  }

  storeAdminUserName(name: string): void {
    localStorage.setItem("adminName", name);
  }

  getAdminName(): any {
    const name = localStorage.getItem("adminName");
    return name;
  }

  adminLogout(): void {
    localStorage.clear();
    this.route.navigate(['/']);
  }

  // this is to get username in admin.home.html part via admin.home.ts
  isAdminLoginPresent(): void {
    if (this.getAdminAuthorization() === null) {
      this.route.navigate(['/admin-login']);
    }
  }
  isUserLoginPresent(): void {
    if (this.getUserAuthorization() === null) {
      this.route.navigate(['/user-login']);
    }
  }


  //user-role
  storeUserRole(role: string): void {
    localStorage.setItem("role", role);
  }

  getUserRole(): any {
    const role = localStorage.getItem("role");
    return role;
  }


  //METHODS 
  //@RequestMapping("/item")
  //@PostMapping("/additems")
  addItem(body: any): Observable<any>{
    return this.http.post(this.url + "/item/additems", body);
  }
  //@GetMapping("/all")
  getItemlist(): Observable<any>{
    return this.http.get(this.url + "/item");
  }

  //@DeleteMapping("{itemId}")
  deleteItem(id: any): Observable<any>{
    return this.http.delete(this.url + "/item/" +id);
  }

  //@GetMapping("/items/{itemId}")
  getItemById(id: any): Observable<any>{
    return this.http.get(this.url + "/item/items/"+id);
  }

  //@PutMapping("{itemId}")
  editItem(body: any, id: any): Observable<any>{
    return this.http.put(this.url + "/item/" +id, body);
  }


  //CART PART
  //@RequestMapping("/cart")
  //@PostMapping("{userId}/{itemId}")
  addToCart(body: any, pid: any, cid: any): Observable<any>{
    return this.http.post(this.url+"/cart/"+cid+"/"+pid,body);
  }


  //@RequestMapping("/users")
  //@GetMapping("user/{id}")
  getUserById(id:any):Observable<any> {
    return this.http.get(this.url + "/users/user/"+id);
  }
  
  //@GetMapping("/list")
  cartList():Observable<any>{
    return this.http.get(this.url+"/cart/list");
  }

  //@RequestMapping("/orders")
  //@PostMapping("/{userId}/{cartId}")
  placeOrder(cid:any,cartid:any,body:any):Observable<any> {
    return this.http.post(this.url + "/orders/"+cid+"/"+cartid, body);
  }

  //@DeleteMapping("/{cartId}")
  deleteCart(id :any):Observable<any> {
    return this.http.delete(`${this.url}/cart/${id}`);
  }

  orderList(id:any):Observable<any>{
    return this.http.get(this.url+"/orders/"+id);
  }

  getCategoryList(): any {
    return this.category;
  }

  //PAYMENT PART
  //@RequestMapping("/payment")
  //@PostMapping("{orderId}/{userId}")
  addPayment(body:any,orderid:any,cid:any):Observable<any> {
    return this.http.post(this.url + "/payment/"+orderid+"/"+cid, body);
  }

  //@PostMapping("/forgotpassword")
  forgotPassword(body: any):Observable<any> {
    return this.http.post(this.url + "/users/forgotpassword", body);
  }

  updateUserInformation(body: any):Observable<any> {
    return this.http.put(this.url + "/users/user/"+body?.userId, body);
  }

  changePassword(uid: any,password:any):Observable<any> {
    return this.http.post(this.url + "/users/"+uid+"/"+password,{});
  }

  //@GetMapping("/items/{itemId}")
  getItemByCategory(cid: any, offset: any, limit: any):Observable<any>{
    return this.http.get(this.url+"/item/" + cid + "/"+ offset + "/" + limit);
  }
  
  getAllItems(offset: any, limit: any):Observable<any>{
    return this.http.get(this.url+"/item/" +  offset + "/" + limit);
  }

  getAllorderList():Observable<any>{
    return this.http.get(this.url+"/orders");
  }

  //@PostMapping("/addOrder/{userId}")
  placeOrderItem(cid:any, body:any):Observable<any>{
    return this.http.post(this.url + "/orders/addOrder/"+cid, body);
  }

  //@GetMapping({"/createTransaction/{amount}"})
  addPaymentOfOrder(amount: any):Observable<any> {
    return this.http.get(this.url + "/orders/createTransaction/"+amount);
  }

  //@RequestMapping("/item")
  //@GetMapping("/ItemSearch/{keyword}/{pageNo}/{pageSize}")
  searchItemByName(keyword: any, pageNo: any, pageSize: any):Observable<any> {
    return this.http.get(this.url + `/item/ItemSearch/${keyword}/${pageNo}/${pageSize}`);
  }

  deleteCartByQuanity(id :any, quantity: any):Observable<any> {
    return this.http.delete(`${this.url}/cart/${id}/${quantity}`);
  }
}
