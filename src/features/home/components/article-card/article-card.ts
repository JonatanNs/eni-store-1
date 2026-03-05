import { Component, Input } from '@angular/core';
import { IArticle } from '../../../../shared/models/IArticle';

@Component({
  selector: 'app-article-card',
  imports: [],
   standalone : true,
  templateUrl: './article-card.html',
  styleUrl: './article-card.scss',
})
export class ArticleCard {
  @Input({required : true}) article!: IArticle;
}
