import { Component } from '@angular/core';
import { AccountService } from '../../services/account.service';
import { TokenStorageService } from '../../services/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {

  constructor(private userService: AccountService, private router: Router) { }

  username!: '';
  password!: '';



  login() {
    this.userService.login(this.username, this.password).subscribe(
        (response) => {
           this.router.navigate(['/product']);
        },
        (error) => {
            alert('Login failed' + error);
        }
    );
}

}
