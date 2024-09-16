"use client";
import BookTable from "@/src/components/Tables/BookTable";
import { BookContext, BookProvider } from "@/src/context/BookContext";
import { useContext } from "react";

function BooksPage() {
  return (
    <main className="flex min-h-screen flex-col bg-white p-44 dark:bg-black">
      <BookProvider>
        <div className="pb-16">
          <div className="space-y-4">
            <p className="text-2xl text-black dark:text-white">Book Library</p>
            <p className="text-md text-black dark:text-white">
              This is a list of books that are available in the library and
              their details. You can view an author&apos;s books by clicking the
              view button in the relevant row.
            </p>
          </div>
        </div>

        <BookTable showAuthorDetails={true}></BookTable>
      </BookProvider>
    </main>
  );
}

export default BooksPage;
