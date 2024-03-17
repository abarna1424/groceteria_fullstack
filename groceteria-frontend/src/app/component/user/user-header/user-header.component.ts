import { Component, OnInit } from '@angular/core';
import { GroceteriaService } from '../../../groceteria.service';
import { NavigationStart, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.css']
})
export class UserHeaderComponent implements OnInit {

  url: string = "/user/home";
  userName: string = '';
  
  constructor(
    private gservice : GroceteriaService,
    private router:Router
  ){
    if(this.gservice.getUserName() ! == null){
      this.userName = this.gservice.getUserName();
    }
  }

  ngOnInit(): void{
    this.router.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event:any) => {
      this.url = event?.url;
    });
  }

  routerToLink(link: string):void{
    if(link === '/user/logout'){
      this.gservice.userLogout();
      return;
    }
    this.router.navigate([link]);
  }

}
