"use client";

import { Dispatch, SetStateAction, useEffect, useState } from "react";
import {
  Listbox,
  ListboxButton,
  ListboxOption,
  ListboxOptions,
} from "@headlessui/react";
import { CheckIcon, ChevronUpDownIcon } from "@heroicons/react/20/solid";

function AuthorDropDown({
  authors,
  selectedAuthor,
  setSelectedAuthor,
}: {
  authors: Author[];
  selectedAuthor: Author;
  setSelectedAuthor: Dispatch<SetStateAction<Author>>;
}) {
  return (
    <Listbox
      value={selectedAuthor}
      onChange={setSelectedAuthor}
      data-testid="author-dropdown"
      disabled={authors.length === 0}
    >
      <div className="relative mt-2">
        <ListboxButton className="relative w-full cursor-default rounded-md bg-gray-500 px-4 py-2 pr-8 text-left text-gray-900 shadow-sm">
          <span
            className={`block truncate ${selectedAuthor.authorId > 0 ? "text-black dark:text-white" : "text-gray-400"}`}
          >
            {selectedAuthor.name}
          </span>
          <span className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
            <ChevronUpDownIcon
              aria-hidden="true"
              className="h-5 w-5 text-gray-400"
            />
          </span>
        </ListboxButton>
        <ListboxOptions
          transition
          className="absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-md bg-gray-500 py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none data-[closed]:data-[leave]:opacity-0 data-[leave]:transition data-[leave]:duration-100 data-[leave]:ease-in sm:text-sm"
        >
          {authors.map((author) => (
            <ListboxOption
              key={author.authorId}
              value={author}
              className="group relative cursor-default select-none py-2 pl-3 pr-9 text-gray-900 data-[focus]:bg-gray-600 data-[focus]:text-white"
            >
              <span className="block truncate font-normal group-data-[selected]:font-semibold">
                {author.name}
              </span>

              <span className="absolute inset-y-0 right-0 flex items-center pr-4 text-black group-data-[focus]:text-white [.group:not([data-selected])_&]:hidden">
                <CheckIcon aria-hidden="true" className="h-5 w-5" />
              </span>
            </ListboxOption>
          ))}
        </ListboxOptions>
      </div>
    </Listbox>
  );
}

export default AuthorDropDown;
