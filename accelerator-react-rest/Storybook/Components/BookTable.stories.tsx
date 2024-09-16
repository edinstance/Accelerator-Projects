import React from "react";
import { Meta, StoryFn } from "@storybook/react";

import { BookContext, BookProvider } from "@/src/context/BookContext";
import { Books } from "../__fixtures__/Book.fixtures";
import BookTable from "@/src/components/Tables/BookTable";

export default {
  title: "Components/BookTable",
  component: BookTable,
} as Meta;

export const Empty: StoryFn<typeof BookTable> = () => {
  return (
    <div className="bg-white dark:bg-black">
      <BookProvider>
        <BookTable></BookTable>
      </BookProvider>
    </div>
  );
};

const MockBookProvider = ({
  children,
  books,
  fetchBooks,
}: {
  children: React.ReactNode;
  books: Book[];
  fetchBooks: () => Promise<void>;
}) => {
  const value = { books, fetchBooks };
  return <BookContext.Provider value={value}>{children}</BookContext.Provider>;
};

export const WithBookData: StoryFn<typeof BookTable> = () => {
  return (
    <div className="bg-white dark:bg-black">
      <MockBookProvider
        books={Books}
        fetchBooks={async () => console.log("Fetch Book")}
      >
        <BookTable></BookTable>
      </MockBookProvider>
    </div>
  );
};
export const WithBookAndAuthorData: StoryFn<typeof BookTable> = () => {
  return (
    <div className="bg-white dark:bg-black">
      <MockBookProvider
        books={Books}
        fetchBooks={async () => console.log("Fetch Book")}
      >
        <BookTable showAuthorDetails={true}></BookTable>
      </MockBookProvider>
    </div>
  );
};
