import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, catchError, tap } from 'rxjs';
import { UserLogin } from '../models/user-login.interface';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})

export class AccountService {
  private authTokenKey = 'authToken';
  private name = 'name';
  private role = 'role';

  constructor(private http: HttpClient, private router: Router) { }
  
  login(username: string, password: string): Observable<UserLogin> {
    return this.http.post<UserLogin>(`${environment.apiBaseUrl}/auth/login`, {
        username: username,
        password: password,
    }, {
        headers: {
            'Content-Type': 'application/json',
        },
    }).pipe(
        tap(response => {
            localStorage.setItem(this.authTokenKey, response.token);
            localStorage.setItem(this.name, response.name);
            localStorage.setItem(this.role, response.role);
            this.router.navigate(['/product']); 
        }),
        catchError(error => {
            throw error;
        })
    );
}
  logout(): void {
    localStorage.removeItem(this.authTokenKey);
    this.router.navigate(['/login']);
}

isLoggedIn() {
    return !!localStorage.getItem(this.authTokenKey);
}
}
