import { Component, OnInit } from '@angular/core';
import { GroceteriaService } from '../../../groceteria.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit{

  userName: string = '';
  constructor(
    private gservice: GroceteriaService
  ) {
    if (this.gservice.getUserName() !== null) {
      this.userName = this.gservice.getUserName();
      console.log("*******",this.userName);
    }
    this.gservice.isUserLoginPresent();
  }

  ngOnInit(): void {
  }
}
