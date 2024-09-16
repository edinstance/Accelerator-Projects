type Book = {
  bookId: number;
  title: string;
  author: Omit<Author, "books">;
  isbn: string;
  released: boolean;
};
