import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { AboutUsComponent } from './component/about-us/about-us.component';
import { ContactUsComponent } from './component/contact-us/contact-us.component';
import { UserLoginComponent } from './component/user/user-login/user-login.component';
import { UserSignupComponent } from './component/user/user-signup/user-signup.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './component/change-password/change-password.component';
import { AdminHomeComponent } from './component/admin/admin-home/admin-home.component';
import { AdminAdditemComponent } from './component/admin/admin-additem/admin-additem.component';
import { AdminHeaderComponent } from './component/admin/admin-header/admin-header.component';
import { AdminOrderlistComponent } from './component/admin/admin-orderlist/admin-orderlist.component';
import { AdminItemlistComponent } from './component/admin/admin-itemlist/admin-itemlist.component';
import { UserHeaderComponent } from './component/user/user-header/user-header.component';
import { UserCartComponent } from './component/user/user-cart/user-cart.component';
import { UserOrderComponent } from './component/user/user-order/user-order.component';
import { UserHomeComponent } from './component/user/user-home/user-home.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path: 'home', component:HomeComponent},
  {path: 'about-us',component:AboutUsComponent},
  {path: 'contact-us',component: ContactUsComponent},
  {path: 'user-login',component:UserLoginComponent},
  {path: 'user-signup',component:UserSignupComponent},
  {path: 'forgot-password', component:ForgotPasswordComponent},
  {path: 'change-password',component:ChangePasswordComponent},


  {path:'admin',children:[

    {path:'home',component:AdminHomeComponent},
    {path:'additem',component:AdminAdditemComponent},
    {path:'header',component:AdminHeaderComponent},
    {path:'order-list',component:AdminOrderlistComponent},
    {path:'itemlist',component:AdminItemlistComponent}
  ]},

  {path:'user',children:[
    {path:'home',component:UserHomeComponent},
    {path:'cart',component:UserCartComponent},
    {path:'order',component:UserOrderComponent},
    {path:'login',component:UserLoginComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
