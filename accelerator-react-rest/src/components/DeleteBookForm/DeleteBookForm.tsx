"use client";

import { Button } from "../Button/Button";
import { FormEvent, useContext, useState } from "react";
import { Popover } from "@headlessui/react";
import deleteBook from "@/src/actions/deleteBook";
import { BookContext } from "@/src/context/BookContext";

export default function DeleteBookForm() {
  const [bookId, setBookId] = useState("");
  const [isPopoverOpen, setIsPopoverOpen] = useState(false);

  const { fetchBooks, books } = useContext(BookContext);
  const handleDelete = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (bookId) {
      await deleteBook(bookId);
      fetchBooks();
      setIsPopoverOpen(true);
      setTimeout(() => {
        setIsPopoverOpen(false);
        setBookId("");
      }, 3000);
    }
  };

  return (
    <div className="relative space-y-4">
      <form onSubmit={handleDelete}>
        <div className="flex flex-row space-x-4">
          <input
            type="text"
            className="rounded-md bg-gray-500 px-4"
            placeholder="ID to delete"
            value={bookId}
            onChange={(e) => setBookId(e.target.value)}
          />
          <Button type="submit">Delete Book</Button>
        </div>
      </form>

      {isPopoverOpen && (
        <Popover
          data-testid="delete-book-popover"
          className="absolute w-fit max-w-sm gap-4 rounded-md bg-green-500 p-4 text-white"
        >
          <div>Book deleted successfully</div>
        </Popover>
      )}
    </div>
  );
}
