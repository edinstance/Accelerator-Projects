type Author = {
  authorId: number;
  name: string;
  description: string;
  books: Omit<Book, "author">[];
};
