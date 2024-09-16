"use client";
import { useTheme } from "next-themes";
import { Button } from "../Button/Button";
import { signIn, signOut, useSession } from "next-auth/react";

function Header() {
  const { resolvedTheme, setTheme } = useTheme();

  const { data: session, status } = useSession();

  return (
    <header className="fixed left-0 right-0 top-0 z-50 flex h-16">
      <div className="flex w-full flex-row items-center justify-between bg-white px-16 py-8 dark:bg-black">
        <div>
          <h1 className="text-2xl text-black dark:text-white">
            6point6 React Accelerator
          </h1>
        </div>
        <div className="flex flex-row space-x-4">
          <Button href={"/"}>Home</Button>
          {session && status === "authenticated" ? (
            <>
              <Button href={"/account"}>Profile</Button>
              <Button
                onClick={() => {
                  signOut();
                }}
              >
                Logout
              </Button>
            </>
          ) : (
            <Button
              onClick={() => {
                signIn();
              }}
            >
              Login
            </Button>
          )}
          <Button
            onClick={() => {
              resolvedTheme === "light" ? setTheme("dark") : setTheme("light");
            }}
            suppressHydrationWarning={true}
          >
            {resolvedTheme === "light" ? "Dark" : "Light"}
          </Button>
        </div>
      </div>
    </header>
  );
}

export default Header;
