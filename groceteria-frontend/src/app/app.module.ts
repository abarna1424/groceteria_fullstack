import { NgModule } from '@angular/core';
import { BrowserModule} from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutUsComponent } from './component/about-us/about-us.component';
import { AppHeaderComponent } from './component/app-header/app-header.component';
import { ChangePasswordComponent } from './component/change-password/change-password.component';
import { ContactUsComponent } from './component/contact-us/contact-us.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { HomeComponent } from './component/home/home.component';
import { PagingComponent } from './component/paging/paging.component';
import { AdminAdditemComponent } from './component/admin/admin-additem/admin-additem.component';
import { AdminHeaderComponent } from './component/admin/admin-header/admin-header.component';
import { AdminHomeComponent } from './component/admin/admin-home/admin-home.component';
import { AdminItemlistComponent } from './component/admin/admin-itemlist/admin-itemlist.component';
import { AdminOrderlistComponent } from './component/admin/admin-orderlist/admin-orderlist.component';
import { UserCartComponent } from './component/user/user-cart/user-cart.component';
import { UserHeaderComponent } from './component/user/user-header/user-header.component';
import { UserHomeComponent } from './component/user/user-home/user-home.component';
import { UserLoginComponent } from './component/user/user-login/user-login.component';
import { UserOrderComponent } from './component/user/user-order/user-order.component';
import { UserOrderHistoryComponent } from './component/user/user-order-history/user-order-history.component';
import { UserSignupComponent } from './component/user/user-signup/user-signup.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import {MatIconModule} from '@angular/material/icon';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { MatDialogModule } from '@angular/material/dialog';
import {MatMenuModule} from '@angular/material/menu';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    AboutUsComponent,
    AppHeaderComponent,
    ChangePasswordComponent,
    ContactUsComponent,
    ForgotPasswordComponent,
    HomeComponent,
    PagingComponent,
    AdminAdditemComponent,
    AdminHeaderComponent,
    AdminHomeComponent,
    AdminItemlistComponent,
    AdminOrderlistComponent,
    UserCartComponent,
    UserHeaderComponent,
    UserHomeComponent,
    UserLoginComponent,
    UserOrderComponent,
    UserOrderHistoryComponent,
    UserSignupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatIconModule,
    MatSnackBarModule,
    MatButtonToggleModule,
    MatDialogModule,
    MatMenuModule,
    MatNativeDateModule,
    MatRippleModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    BrowserAnimationsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]

  // providers: [
  //   provideClientHydration()
  // ],
  // bootstrap: [AppComponent]
})
export class AppModule { }
