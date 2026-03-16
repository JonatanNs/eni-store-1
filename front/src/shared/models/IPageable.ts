export interface IPageable<T> {
  code: string;
  timestamp: string;
  message: string;
  data: {
    content: T[];
  };
  first: number;
  prev: number | null;
  next: number | null;
  last: number;
  pages: number;
  items: number;
}