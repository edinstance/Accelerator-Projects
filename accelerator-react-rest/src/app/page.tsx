"use client";
import { signIn } from "next-auth/react";
import { Button } from "../components/Button/Button";

function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center bg-white p-44 dark:bg-black">
      <div className="pb-32">
        <div className="pb-16">
          <p className="text-md text-black dark:text-white">
            This is a React Accelerator that interacts with a rest backend that
            can either be ran with local mock data or connected to a backend.
            For more information on that please check the README.md
          </p>
          <div className="w-fit">
            <div className="flex h-fit w-full flex-row items-center space-x-4 pt-8">
              <p className="text-md flex-1 text-black dark:text-white">
                To view a list of all of the books go to this page.
              </p>
              <Button href={"/books"}>Books Page</Button>
            </div>
            <div className="flex h-fit w-full flex-row items-center space-x-4 pt-8">
              <p className="text-md flex-1 text-black dark:text-white">
                To view a list of all of the authors go to this page.
              </p>
              <Button href={"/authors"}>Authors Page</Button>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}

export default Home;
