"use server";

import { env } from "@/src/env/server";

async function deleteBook(bookId: string) {
  try {
    const response = await fetch(
      `${env.BACKEND_URL}/api/v1/books/deleteBookById?bookId=${parseInt(bookId)}`,
      {
        method: "DELETE",
      },
    );
  } catch (error) {
    console.error("Error in fetch:", error);
  }
}

export default deleteBook;
