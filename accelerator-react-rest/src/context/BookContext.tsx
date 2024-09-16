import React, { createContext, useState, useContext, useEffect } from "react";

const BookContext = createContext<{
  books: Book[];
  fetchBooks: () => Promise<void>;
}>({
  books: [],
  fetchBooks: async () => {},
});

const BookProvider = ({ children }: { children: React.ReactNode }) => {
  const [books, setBooks] = useState<Book[]>([]);

  const fetchBooks = async () => {
    try {
      const response = await fetch("/api/getAllBooks", { cache: "no-store" });
      const data = await response.json();
      setBooks(data);
    } catch (error) {
      console.error("Error fetching books:", error);
    }
  };

  useEffect(() => {
    fetchBooks();
    const intervalId = setInterval(fetchBooks, 60000); // Fetch books every minute
    return () => clearInterval(intervalId);
  }, []);

  return (
    <BookContext.Provider value={{ books, fetchBooks }}>
      {children}
    </BookContext.Provider>
  );
};

export { BookProvider, BookContext };
