import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IPageable } from '../../../shared/models/IPageable';
import { IUser } from '../../user/models/IUser';
import { ENVIRONMENT } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class RegisterService {
  private http = inject(HttpClient)

  baseUrl = ENVIRONMENT.apiUrl;
  allurl = `${this.baseUrl}/auth/inscription`;

  createUser(user : IUser) : Observable<IUser> {
     return this.http.post<IUser>(`${this.allurl}`, user);
  }
}
