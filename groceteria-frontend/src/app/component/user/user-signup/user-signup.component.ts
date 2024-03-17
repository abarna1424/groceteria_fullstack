import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GroceteriaService } from '../../../groceteria.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-user-signup',
  templateUrl: './user-signup.component.html',
  styleUrl: './user-signup.component.css'
})
export class UserSignupComponent implements OnInit {

  firstName: string = "";
  lastName: string = "";
  dob: string = "";
  gender: string = "female";
  emailId: string = "";
  password: string = "";
  phone: string = "";
  district: string = "";
  state: string = "";
  address: string = "";
  zipcode: string = "";



  constructor(
    private router: Router,
    private datePipe: DatePipe,
    private gservice: GroceteriaService
  ) {

  }
  ngOnInit(): void {

  }
  setDOB(ev: any): void {
    const date: any = this.datePipe.transform(ev?.value, 'yyyy-MM-dd');
    this.dob = date;
  }

  signup(): void {
    if (this.firstName === '' || this.firstName.length < 3) {
      alert('FirstName must contain atleast 3 characters');
      return;
    }
    if (this.lastName === '' || this.lastName.length < 3) {
      alert('LastName must contain atleast 3 characters');
      return;
    }
    if (this.phone === '' || this.phone.length < 10 || this.phone.length > 10) {
      alert('Invalid contact number');
      return;
    }
    const pattern = /^[6789][0-9]{9}$/;
    if (!pattern.test(this.phone)) {
      alert('Invalid mobile number.');
      return;
    }
    if (this.district === '' || this.district.length < 3) {
      alert('District must contain atleast 3 characters');
      return;
    }
    if (this.state === '' || this.state.length < 3) {
      alert('State must contain atleast 3 characters');
      return;
    }
    if (this.zipcode === '' || this.zipcode.length < 6) {
      alert('Zipcode must contain atleast 6 characters');
      return;
    }

    const body: any = {
      firstName: this.firstName,
      lastName: this.lastName,
      dateOfBirth: this.dob,
      phoneNumber: this.phone,
      gender: this.gender,
      emailId: this.emailId,
      password: this.password,
      state: this.state,
      district: this.district,
      address: this.address,
      zipcode: this.zipcode,
     role:"customer"
      //  role:"admin"
    }
    console.log("=======>",body);
    this.gservice.signUp(body).pipe(take(1)).subscribe((res :any) => {
      console.log("*****",res);
      if(res && res?.userId){
        alert("Registration sucessful");
        this.router.navigate(["/user-login"]);
      }
    }, err =>{
        console.log("Error  ", err);
        if (err && err?.error === 'Oops duplicate Entry of the data !') {
          alert("Email address registered already, please go to login.");
        } else {
          alert("Something going wrong..pls try again");
        }
    })

  }

}
