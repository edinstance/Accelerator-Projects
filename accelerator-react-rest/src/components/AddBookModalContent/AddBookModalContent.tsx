import addBook from "@/src/actions/addBook";
import { Button } from "../Button/Button";
import {
  Dispatch,
  SetStateAction,
  useContext,
  useEffect,
  useState,
} from "react";
import { Popover } from "@headlessui/react";
import { BookContext } from "@/src/context/BookContext";
import AuthorDropDown from "../AuthorDropDown/AuthorDropDown";

function AddBookModalContent({
  setOpen,
}: {
  setOpen: Dispatch<SetStateAction<boolean>>;
}) {
  const [bookTitle, setBookTitle] = useState("");
  const [bookISBN, setBookISBN] = useState("");

  const [isPopoverOpen, setIsPopoverOpen] = useState(false);

  const { fetchBooks } = useContext(BookContext);

  const [authors, setAuthors] = useState<Author[]>([]);

  const [selectedAuthor, setSelectedAuthor] = useState<Author>(
    authors.length > 0
      ? authors[0]
      : { authorId: -1, name: "Select an author", description: "", books: [] },
  );

  useEffect(() => {
    // Fetch authors data from an API or other source
    const fetchAuthors = async () => {
      const response = await fetch("/api/getAllAuthors", { cache: "no-store" });
      const data = await response.json();
      setAuthors(data);
    };

    fetchAuthors();
  }, []);

  const handleAddBook = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (
      !bookTitle ||
      (selectedAuthor && selectedAuthor.authorId <= 0) ||
      !bookISBN
    ) {
      setIsPopoverOpen(true);
      setTimeout(() => {
        setIsPopoverOpen(false);
      }, 3000);
      return;
    }
    try {
      await addBook({ bookTitle, selectedAuthor, bookISBN });
      fetchBooks();
      setBookTitle("");
      setSelectedAuthor({
        authorId: -1,
        name: "Select an author",
        description: "",
        books: [],
      });
      setBookISBN("");
      setOpen(false);
    } catch (error) {
      console.error("Failed to add book:", error);
    }
  };
  return (
    <>
      <div className="max-w-fit bg-white p-4 dark:bg-black">
        <div className="relative">
          <div className="pb-4 text-lg">
            <p className="text-xl text-black dark:text-white">
              Add a book to the system
            </p>
          </div>
          <form onSubmit={handleAddBook} className="space-y-4">
            <div className="flex flex-col space-y-4">
              <input
                type="text"
                className="rounded-md bg-gray-500 px-4 py-2"
                placeholder="Book Title"
                value={bookTitle}
                onChange={(e) => setBookTitle(e.target.value)}
              />
              <AuthorDropDown
                authors={authors}
                selectedAuthor={selectedAuthor}
                setSelectedAuthor={setSelectedAuthor}
              ></AuthorDropDown>
              <input
                type="text"
                className="rounded-md bg-gray-500 px-4 py-2"
                placeholder="Book ISBN"
                value={bookISBN}
                onChange={(e) => setBookISBN(e.target.value)}
              />
            </div>
            {isPopoverOpen && (
              <Popover className="w-fill absolute rounded-md bg-red-500 p-4 text-white">
                <div>Please add Book details</div>
              </Popover>
            )}
            <div className="flex flex-row justify-between pt-20">
              <Button
                onClick={() => {
                  setOpen(false);
                }}
              >
                Close
              </Button>
              <Button type="submit">Add Book</Button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}

export default AddBookModalContent;
