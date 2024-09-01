export interface ArticleRequestDTO {
  title: string;
  content: string;
  userId: number;
  publicationDate: string; // Use string for date, as it will be serialized to ISO 8601 format
}
