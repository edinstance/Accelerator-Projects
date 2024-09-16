"use client";
import AuthorTable from "@/src/components/Tables/AuthorTable";
import { useEffect, useState } from "react";

function AuthorsPage() {
  const [allAuthors, setAllAuthors] = useState<Author[]>([]);

  useEffect(() => {
    const fetchAuthors = async () => {
      try {
        const response = await fetch(`/api/getAllAuthors`, {
          cache: "no-store",
        });
        const data = await response.json();
        setAllAuthors(data);
      } catch (error) {
        console.error("Error fetching books:", error);
      }
    };

    fetchAuthors();
  }, []);

  return (
    <main className="flex min-h-screen flex-col bg-white p-44 dark:bg-black">
      <div className="pb-16">
        <div className="space-y-4">
          <p className="text-2xl text-black dark:text-white">Author Library</p>
          <p className="text-md text-black dark:text-white">
            This is a list of all of the authors that are have a book in the
            library and their description. You can view more details about an
            author by clicking the more button in the relevant row.
          </p>
        </div>
      </div>

      <AuthorTable authors={allAuthors}></AuthorTable>
    </main>
  );
}

export default AuthorsPage;
