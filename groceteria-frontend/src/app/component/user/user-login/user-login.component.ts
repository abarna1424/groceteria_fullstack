import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GroceteriaService } from '../../../groceteria.service';
import { FormBuilder } from '@angular/forms';
import { take } from 'rxjs';



@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent implements OnInit {
  errormessage: string = '';
  emailId: string = '';
  password: string = '';
  errormessagep: string = '';

  constructor(
    private route: Router,
    private gservice: GroceteriaService,
    private fb: FormBuilder) {

  }
  ngOnInit(): void { }

  login(): void {
    console.log('CCCCCCCCC')
    if (this.emailId === " " || this.emailId === undefined) {
      this.errormessage = "Email address is blank";
      return;
    }

    this.errormessage = "";

    if (this.password === " " || this.password === undefined) {
      this.errormessagep = "Password is blank";
      return;
    }
    this.errormessagep = '';


    const body: any = {"emailId":this.emailId, "password":this.password }
    this.gservice.userSignIn(body).pipe(take(1)).subscribe((res: any) => {
      console.log("*******",res);
      if (res && res?.userId) {
        alert("Login sucessful");
        if (res?.role) {
          this.gservice.storeUserRole(res?.role);
        }
        this.gservice.storeUserAuthorization(res?.userId);
        let userName = '';
        if (res?.firstName) {
          userName+=res?.firstName;
        }
        if (res?.lastName){
          userName+=' ' + res?.lastName;
        }

        this.gservice.storeUserName(userName);
        console.log('@@@@@',userName);
        console.log('>>>>>>', res?.role);
        if (res?.role === 'admin') {
          this.route.navigate(['/admin/home']);
        } else {
          this.route.navigate(['/user/home']);
        }
      }
    }, err => {
      console.log("Error ", err);
      console.log(">>> ", err);
      if (err?.error && err?.error.startsWith("Customer  not found with")) {
        alert("Customer email/password is invalid");
      }
      else {
        alert("Something going wrong in login! pls try again");
      }
    }
    )}

  //   this.gservice.userSignIn(body).pipe(take(1)).subscribe((res: any) => {
  //     // Successful response
  //     if (res && res?.userId) {
  //       alert("Login successful");
  //       if (res?.role) {
  //         this.gservice.storeUserRole(res?.role);
  //       }
  //       this.gservice.storeUserAuthorization(res?.userId);
  //       let userName = '';
  //       if (res?.firstName) {
  //         userName += res?.firstName;
  //       }
  //       if (res?.lastName) {
  //         userName += ' ' + res?.lastName;
  //       }
  //       this.gservice.storeUserName(userName);
  //       console.log('>>>>>>', res?.role);
  //       if (res?.role === 'admin') {
  //         this.route.navigate(['/admin/home']);
  //       } else {
  //         this.route.navigate(['/user/home']);
  //       }
  //     }
  //   }, err => {
  //     // Error handling
  //     console.log("Error: ", err);
  //     if (err.status === 400) {
  //       // Bad request error (validation error)
  //       if (err?.error && err?.error.startsWith("Customer not found with")) {
  //         alert("Customer email/password is invalid");
  //       } else {
  //         alert("Invalid request. Please check your input and try again.");
  //       }
  //     } else {
  //       // Other server errors
  //       alert("Something went wrong in login. Please try again later.");
  //     }
  //   })
  // }





  routeToNewUser(): void {
    this.route.navigate(["/user-signup"]);
  }
  routeToForgotPassword(): void {
    this.route.navigate(["/forgot-password"]);
  }
}





