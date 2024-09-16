"use client";
import { Button } from "../Button/Button";

function AuthorTable({ authors }: { authors: Author[] }) {
  return (
    <div className="flex h-full min-w-full flex-grow flex-col overflow-scroll border-2 border-black text-center dark:border-white">
      {authors.length > 0 ? (
        <table className="min-w-full" data-testid="book-table">
          <thead className="">
            <tr>
              <th className="text-md px-4 py-2 text-black dark:text-white">
                Index
              </th>
              <th className="text-md px-4 py-2 text-left text-black dark:text-white">
                Author Name
              </th>
              <th className="text-md px-4 py-2 text-left text-black dark:text-white">
                Author Description
              </th>
              <th className="text-md px-4 py-2 text-black dark:text-white">
                More Details
              </th>
            </tr>
          </thead>
          <tbody>
            {authors.map((author, index) => (
              <tr key={author.authorId} className="">
                <td className="text-md px-4 py-2 text-black dark:text-white">
                  {index + 1}
                </td>
                <td className="text-md px-4 py-2 text-left text-black dark:text-white">
                  {author.name}
                </td>
                <td className="text-md truncate px-4 py-2 text-left text-black dark:text-white">
                  {author.description}
                </td>
                <td className="text-md px-4 py-2 text-black dark:text-white">
                  <Button href={"/authors/" + author.authorId}>More</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <div>
          <p className="text-xl text-black dark:text-white">
            No Authors found, please add one.
          </p>
        </div>
      )}
    </div>
  );
}

export default AuthorTable;
