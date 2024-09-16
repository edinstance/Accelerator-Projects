"use client";
import { BookContext } from "@/src/context/BookContext";
import { Button } from "../Button/Button";
import { useContext } from "react";

function BookTable({
  books,
  showAuthorDetails = false,
  showReleaseStatus = false,
  showBookId = false,
}: {
  books?: Book[];
  showAuthorDetails?: boolean;
  showReleaseStatus?: boolean;
  showBookId?: boolean;
}) {
  const contextBooks = useContext(BookContext).books;
  const booksToDisplay = books || contextBooks;

  return (
    <div className="flex h-full min-w-full flex-grow flex-col overflow-scroll border-2 border-black text-center dark:border-white">
      {booksToDisplay.length > 0 ? (
        <table className="min-w-full" data-testid="book-table">
          <thead className="">
            <tr>
              {showBookId ? (
                <th className="text-md px-4 py-2 text-black dark:text-white">
                  Book ID
                </th>
              ) : (
                <th className="text-md px-4 py-2 text-black dark:text-white">
                  Index
                </th>
              )}
              <th className="text-md px-4 py-2 text-black dark:text-white">
                Book Title
              </th>
              <th className="text-md px-4 py-2 text-black dark:text-white">
                Book ISBN
              </th>
              {showReleaseStatus && (
                <th className="text-md px-4 py-2 text-black dark:text-white">
                  Book Status
                </th>
              )}
              {showAuthorDetails && (
                <>
                  <th className="text-md px-4 py-2 text-black dark:text-white">
                    Author Name
                  </th>
                  <th className="text-md px-4 py-2 text-black dark:text-white">
                    Author Details
                  </th>
                </>
              )}
            </tr>
          </thead>
          <tbody>
            {booksToDisplay.map((book, index) => (
              <tr key={book.bookId} className="">
                <td className="text-md px-4 py-2 text-black dark:text-white">
                  {showBookId ? book.bookId : index + 1}
                </td>
                <td className="text-md px-4 py-2 text-black dark:text-white">
                  {book.title}
                </td>
                <td className="text-md px-4 py-2 text-black dark:text-white">
                  {book.isbn}
                </td>
                {showReleaseStatus && (
                  <td className="text-md px-4 py-2 text-black dark:text-white">
                    {book.released.valueOf() ? "Released" : "Not Released"}
                  </td>
                )}

                {showAuthorDetails && (
                  <>
                    <td className="text-md px-4 py-2 text-black dark:text-white">
                      {book.author.name}
                    </td>
                    <td className="text-md px-4 py-2 text-black dark:text-white">
                      <Button href={"/authors/" + book.author.authorId}>
                        View
                      </Button>
                    </td>
                  </>
                )}
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <div>
          <p className="text-xl text-black dark:text-white">
            No Books found, please add one.
          </p>
        </div>
      )}
    </div>
  );
}

export default BookTable;
