export interface IApiResponse<T> {
  code: string;
  timestamp: string;
  message: string;
  data: {
    content: T[];
  };
}