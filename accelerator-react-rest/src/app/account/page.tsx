"use client";

import AddBookModalContent from "@/src/components/AddBookModalContent/AddBookModalContent";
import { Button } from "@/src/components/Button/Button";
import DeleteBookForm from "@/src/components/DeleteBookForm/DeleteBookForm";
import Modal from "@/src/components/Modal/Modal";
import BookTable from "@/src/components/Tables/BookTable";
import { useSession } from "next-auth/react";
import { useEffect, useState } from "react";

function AccountPage() {
  const { data: session } = useSession();
  const [authorBooks, setAuthorBooks] = useState<Book[]>([]);
  const [modalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    if (session?.user?.role === "Author") {
      const fetchBooks = async () => {
        try {
          const response = await fetch(
            `/api/getBooksByUserId?userId=${session?.user?.id}`,
            { cache: "no-store" },
          );
          const data = await response.json();
          setAuthorBooks(data);
        } catch (error) {
          console.error("Error fetching books:", error);
        }
      };

      fetchBooks();
    }
  }, [session?.user?.role, session?.user?.id]);

  return (
    <main className="flex min-h-screen flex-col bg-white p-44 dark:bg-black">
      <div className="pb-16">
        <div className="space-y-4">
          <p className="text-2xl text-black dark:text-white">
            {session?.user?.name}
          </p>
          <p className="text-md text-black dark:text-white">
            Email: {session?.user?.email}
          </p>
          {session?.user?.role && (
            <p className="text-md text-black dark:text-white">
              Role: {session?.user?.role}
            </p>
          )}
          {session?.user?.role == "User" && (
            <div>
              <Button>Apply to be an Author</Button>
            </div>
          )}
        </div>
      </div>
      {session?.user?.role == "Author" && (
        <>
          <Modal open={modalOpen} setOpen={setModalOpen}>
            <AddBookModalContent setOpen={setModalOpen}></AddBookModalContent>
          </Modal>
          <div className="flex flex-row space-x-8 pb-4">
            <Button
              onClick={() => {
                setModalOpen(true);
              }}
            >
              Add Book
            </Button>
            <DeleteBookForm></DeleteBookForm>
          </div>
          <BookTable
            books={authorBooks}
            showReleaseStatus={true}
            showBookId={true}
          ></BookTable>
        </>
      )}
    </main>
  );
}

export default AccountPage;
