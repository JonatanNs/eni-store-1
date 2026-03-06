import { Component, inject, OnInit } from '@angular/core';
import { HomeService } from '../../services/home.service';
import { IArticle } from '../../../../shared/models/IArticle';
import { IPageable } from '../../../../shared/models/IPageable';
import { ArticleList } from '../../components/article-list/article-list';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home-page',
  imports: [ArticleList, CommonModule],
  standalone : true,
  templateUrl: './home.page.html',
  styleUrl: './home.page.scss',
})
export class HomePage {

  private homeService = inject(HomeService);

  //page : number = 1;
  //perPage : number = 5;

  articles$: Observable<IPageable<IArticle>> = this.homeService.getArticles(1, 10);
}
