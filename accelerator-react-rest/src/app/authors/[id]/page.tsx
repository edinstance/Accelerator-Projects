"use client";

import BookTable from "@/src/components/Tables/BookTable";
import { useEffect, useState } from "react";

function IndividualAuthorsPage({ params }: { params: { id: number } }) {
  const [authorBooks, setAuthorBooks] = useState<Book[]>([]);
  const [currentAuthor, setCurrentAuthor] = useState<Author | null>(null);

  useEffect(() => {
    const FetchBooks = async () => {
      try {
        const response = await fetch(
          `/api/getBooksByAuthorId?authorId=${params.id}`,
          { cache: "no-store" },
        );
        const data = await response.json();
        const filteredData = data.filter(
          (item: { released: boolean }) => item.released,
        );
        setAuthorBooks(filteredData);
      } catch (error) {
        console.error("Error fetching books:", error);
      }
    };
    const fetchCurrentAuthor = async () => {
      try {
        const response = await fetch(
          `/api/getAuthorById?authorId=${params.id}`,
          {
            cache: "no-store",
          },
        );
        const data = await response.json();
        setCurrentAuthor(data);
      } catch (error) {
        console.error("Error fetching author:", error);
      }
    };

    FetchBooks();
    fetchCurrentAuthor();
  }, [params.id]);

  return (
    <main className="flex min-h-screen flex-col bg-white p-44 dark:bg-black">
      <div className="pb-16">
        <div className="space-y-4">
          <p className="text-2xl text-black dark:text-white">
            {currentAuthor?.name}
          </p>
          <p className="text-md text-black dark:text-white">
            {currentAuthor?.description}
          </p>
        </div>
      </div>

      <BookTable books={authorBooks} showReleaseStatus={true}></BookTable>
    </main>
  );
}

export default IndividualAuthorsPage;
