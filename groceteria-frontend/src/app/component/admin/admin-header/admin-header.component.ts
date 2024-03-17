import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { GroceteriaService } from '../../../groceteria.service';
import { filter } from 'rxjs';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrl: './admin-header.component.css'
})
export class AdminHeaderComponent implements OnInit {
  url: string = '';
  userName: string = '';

  constructor(
    private route:Router,
    private gservice:GroceteriaService,
    private changeDetector: ChangeDetectorRef
  ){

    if(this.gservice.getUserName() !== null){
      this.userName = this.gservice.getUserName();
    }

  }
  ngOnInit(): void{
    this.route.events.pipe(
      filter(event => event instanceof NavigationStart)
    ).subscribe((event: any) => {
      this.url = event?.url;
    });
  }

  gotourl(link: string): void{
    if(link === '/admin/logout'){
      this.gservice.userLogout();
      return;
    }
    this.route.navigate([link]);
  }


}
