"use client";
import registerUser from "@/src/actions/registerUser";
import { Provider } from "@/src/user";
import { Field, Label, Select } from "@headlessui/react";
import { EyeIcon, EyeSlashIcon } from "@heroicons/react/24/outline";
import { signIn } from "next-auth/react";
import { useRouter } from "next/navigation";

import { ChangeEventHandler, useState } from "react";

function RegisterPage() {
  const { push } = useRouter();

  const [showPassword, setShowPassword] = useState(false);

  const roles = ["User", "Author"];

  const [formData, setFormData] = useState({
    email: "",
    password: "",
    confirmPassword: "",
    firstName: "",
    lastName: "",
    role: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleRoleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setFormData({
      ...formData,
      role: event.target.value,
    });
  };

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }
    if (formData.password.length < 6) {
      alert("This password is too short!");
      return;
    }
    const provider: Provider = {
      name: "Application",
      externalId: formData.email,
    };
    registerUser({
      name: formData.firstName + " " + formData.lastName,
      email: formData.email,
      role: formData.role,
      password: formData.password,
      provider: provider,
    });

    push("/login");
  };

  return (
    <main className="flex min-h-screen flex-col items-center bg-white pb-32 pt-20 dark:bg-black">
      <div className="w-full max-w-md rounded-md bg-white p-8 ring-2 ring-black dark:bg-black dark:ring-white">
        <h1 className="mb-6 text-center text-2xl font-bold text-black dark:text-white">
          Register to be an User
        </h1>
        <form className="space-y-6" onSubmit={handleSubmit}>
          <div className="flex-1">
            <label
              htmlFor="firstName"
              className="block text-sm font-medium text-black dark:text-white"
            >
              First Name
            </label>
            <input
              type="text"
              id="firstName"
              name="firstName"
              required
              placeholder="John"
              value={formData.firstName}
              onChange={(e) => handleChange(e)}
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400"
            />
          </div>
          <div className="flex-1">
            <label
              htmlFor="lastName"
              className="block text-sm font-medium text-black dark:text-white"
            >
              Last Name
            </label>
            <input
              type="text"
              id="lastName"
              name="lastName"
              required
              placeholder="Doe"
              value={formData.lastName}
              onChange={(e) => handleChange(e)}
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400"
            />
          </div>

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
              placeholder="register@6point6.co.uk"
              value={formData.email}
              onChange={(e) => handleChange(e)}
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
                value={formData.password}
                onChange={(e) => handleChange(e)}
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
                  <EyeIcon className="h-5 w-5" />
                ) : (
                  <EyeSlashIcon className="h-5 w-5" />
                )}
              </button>
            </div>
            <p className="pt-1 text-xs text-black dark:text-white">
              * Passwords must be at least 6 characters.
            </p>
          </div>

          <div>
            <label
              htmlFor="confirm-password"
              className="block text-sm font-medium text-black dark:text-white"
            >
              Confirm Password
            </label>
            <div className="relative mt-1">
              <input
                type={showPassword ? "text" : "password"}
                id="confirm-password"
                name="confirmPassword"
                required
                placeholder="**********"
                value={formData.confirmPassword}
                onChange={(e) => handleChange(e)}
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
                  <EyeIcon className="h-5 w-5" />
                ) : (
                  <EyeSlashIcon className="h-5 w-5" />
                )}
              </button>
            </div>
          </div>

          <div className="w-full">
            <Field>
              <Label className="text-sm/6 font-medium text-black dark:text-white">
                Role
              </Label>
              <div className="relative w-full">
                <Select
                  className="mt-1 block w-full rounded-md border border-gray-300 bg-white px-3 py-2 shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-indigo-500 dark:border-gray-600 dark:bg-gray-700 dark:text-white dark:placeholder-gray-400"
                  value={formData.role}
                  onChange={handleRoleChange}
                >
                  {roles.map((role) => (
                    <option key={role} value={role}>
                      {role}
                    </option>
                  ))}
                </Select>
              </div>
            </Field>
          </div>

          <div>
            <div className="flex items-center justify-between pb-1">
              <div></div> {/* Empty div to take up space on the left */}
              <button
                className="text-xs text-blue-500 hover:underline"
                type="button"
                onClick={() => signIn()}
              >
                If you already have an account, sign in here.
              </button>
            </div>
            <button
              type="submit"
              className="flex w-full justify-center rounded-md border border-transparent bg-indigo-600 px-4 py-2 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
            >
              Register
            </button>
          </div>
        </form>
      </div>
    </main>
  );
}

export default RegisterPage;
