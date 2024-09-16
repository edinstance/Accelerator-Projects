"use server";

import { env } from "@/src/env/server";

async function addBook({
  bookTitle,
  selectedAuthor,
  bookISBN,
}: {
  bookTitle: string;
  selectedAuthor: Author;
  bookISBN: string;
}) {
  try {
    const response = await fetch(`${env.BACKEND_URL}/api/v1/books/addBook`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        title: bookTitle,
        author: selectedAuthor,
        released: true,
        isbn: bookISBN,
      }),
    });
  } catch (error) {
    console.error("Error in fetch:", error);
  }
}

export default addBook;
