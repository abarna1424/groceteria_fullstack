import { Component } from '@angular/core';
import { NavigationStart, Router } from '@angular/router';
import { GroceteriaService } from './groceteria.service';
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-groceteria';
  isLoggedIn: boolean = false;
  isAdminLoggedIn: boolean = false;

  
  constructor(
    private route:Router,
    private gservice: GroceteriaService)
    {
      this.route.events.pipe(
        filter(event => event instanceof NavigationStart)
      ).subscribe((event: any) => {
        if (this.gservice.getUserRole() !== null && this.gservice.getUserRole() === "customer") {
          setTimeout(() => {
            this.isLoggedIn = true;
            this.isAdminLoggedIn = false;
          }, 100);
        } else {
          console.log('>>>>>>', this.gservice.getUserRole());
          if (this.gservice.getUserRole() !== null && this.gservice.getUserRole() === "admin") {
            setTimeout(() => {
              console.log("11111111111");
              this.isAdminLoggedIn = true;
              this.isLoggedIn = false;
            }, 100);
          } {
            setTimeout(() => {
              console.log("222222222");
              this.isLoggedIn = false;
              this.isAdminLoggedIn = false;
            }, 1);
          }
        }
      });
}


}
