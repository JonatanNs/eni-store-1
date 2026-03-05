import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ENVIRONMENT } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { IArticle } from '../../../shared/models/IArticle';
import { IPageable } from '../../../shared/models/IPageable';

@Injectable({
  providedIn: 'root',
})
export class HomeService {

  private http = inject(HttpClient);
  
  jsonServer_url = ENVIRONMENT.apiUrl;
  newsUrl = `${this.jsonServer_url}/articles`;

  getArticles(page : number, perPage : number) : Observable<IPageable<IArticle>>{
    return this.http.get<IPageable<IArticle>>(`${this.newsUrl}?_page=${page}&_per_page=${perPage}`);
  }
}

