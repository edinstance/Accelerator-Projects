"use server";

import { env } from "@/src/env/server";

async function addAuthor({ authorName }: { authorName: string }) {
  try {
    const response = await fetch(
      `${env.BACKEND_URL}/api/v1/authors/addAuthor`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: authorName,
          books: [],
        }),
      },
    );
  } catch (error) {
    console.error("Error in fetch:", error);
  }
}

export default addAuthor;
