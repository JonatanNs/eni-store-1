import { Component, Input } from '@angular/core';
import { IArticle } from '../../../../shared/models/IArticle';
import { ArticleCard } from '../article-card/article-card';
import { IPageable } from '../../../../shared/models/IPageable';

@Component({
  selector: 'app-article-list',
  standalone : true,
  imports: [ArticleCard],
  templateUrl: './article-list.html',
  styleUrl: './article-list.scss',
})
export class ArticleList {
  @Input({ required: true }) articlesList! : IPageable<IArticle>;
}
