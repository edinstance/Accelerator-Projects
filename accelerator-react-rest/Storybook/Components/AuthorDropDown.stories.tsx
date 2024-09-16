import React from "react";
import { StoryFn, Meta } from "@storybook/react";
import AuthorDropDown from "@/src/components/AuthorDropDown/AuthorDropDown";

export default {
  title: "Components/AuthorDropDown",
  component: AuthorDropDown,
} as Meta;

const Template: StoryFn<typeof AuthorDropDown> = (args) => {
  return <AuthorDropDown {...args}></AuthorDropDown>;
};

export const Default = Template.bind({});
Default.args = {
  authors: [
    { authorId: 1, name: "Lee Child", description: "Description", books: [] },
    {
      authorId: 2,
      name: "Stephen King",
      description: "Description",
      books: [],
    },
    {
      authorId: 3,
      name: "J.K. Rowling",
      description: "Description",
      books: [],
    },
  ],
  selectedAuthor: {
    authorId: -1,
    name: "Select an author",
    description: "",
    books: [],
  },
  setSelectedAuthor: (author) => console.log(author),
};

export const WithAuthorSelected = Template.bind({});
WithAuthorSelected.args = {
  authors: [
    { authorId: 1, name: "Lee Child", description: "Description", books: [] },
    {
      authorId: 2,
      name: "Stephen King",
      description: "Description",
      books: [],
    },
    {
      authorId: 3,
      name: "J.K. Rowling",
      description: "Description",
      books: [],
    },
  ],
  selectedAuthor: {
    authorId: 1,
    name: "Lee Child",
    description: "",
    books: [],
  },
  setSelectedAuthor: (author) => console.log(author),
};
