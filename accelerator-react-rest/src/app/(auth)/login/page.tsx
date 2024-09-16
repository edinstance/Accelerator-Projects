"use client";

import { EyeIcon, EyeSlashIcon } from "@heroicons/react/24/outline";
import { signIn } from "next-auth/react";
import Link from "next/link";
import { useState } from "react";

function SignInPage() {
  const [showPassword, setShowPassword] = useState(false);
  const [loginDetails, setLoginDetails] = useState({
    email: "",
    password: "",
  });

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();

    signIn("credentials", {
      ...loginDetails,
      callbackUrl: `${window.location.origin}`,
    });
  };
  return (
    <main className="flex min-h-screen flex-col items-center bg-white p-44 dark:bg-black">
      <div className="w-full max-w-md rounded-md bg-white p-8 ring-2 ring-black dark:bg-black dark:ring-white">
        <h1 className="mb-6 text-center text-2xl font-bold text-black dark:text-white">
          Login
        </h1>
        <form className="space-y-6" onSubmit={handleSubmit}>
          <div>
            <label
              htmlFor="email"
              className="block text-sm font-medium text-black dark:text-white"
            >
              Email
            </label>
            <input
              type="email"
              id="email"
              name="email"
              required
              placeholder="accelerator@6point6.co.uk"
              value={loginDetails.email}
              onChange={(e) =>
                setLoginDetails({ ...loginDetails, email: e.target.value })
              }
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400"
            />
          </div>
          <div>
            <label
              htmlFor="password"
              className="block text-sm font-medium text-black dark:text-white"
            >
              Password
            </label>
            <div className="relative mt-1">
              <input
                type={showPassword ? "text" : "password"}
                id="password"
                name="password"
                required
                placeholder="**********"
                value={loginDetails.password}
                onChange={(e) =>
                  setLoginDetails({ ...loginDetails, password: e.target.value })
                }
                className="block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400"
              />
              <button
                type="button"
                onClick={() => {
                  setShowPassword(!showPassword);
                }}
                className="absolute inset-y-0 right-0 flex items-center px-3 text-gray-500 dark:text-gray-400"
              >
                {showPassword ? (
                  <EyeSlashIcon className="h-5 w-5" />
                ) : (
                  <EyeIcon className="h-5 w-5" />
                )}
              </button>
            </div>
          </div>
          <div className="space-y-4 md:flex-row md:space-y-0">
            <div className="flex w-full justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
              <button
                type="button"
                onClick={() => {
                  signIn("google");
                }}
              >
                Login with Google
              </button>
            </div>
            <div className="flex w-full justify-between space-x-4 pt-2">
              <Link
                href="/register"
                className="flex flex-1 items-center justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
              >
                Register
              </Link>
              <button
                type="submit"
                className="flex flex-1 items-center justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
              >
                Login
              </button>
            </div>
          </div>
        </form>
      </div>
    </main>
  );
}

export default SignInPage;
