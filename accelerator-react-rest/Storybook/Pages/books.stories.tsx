import React from "react";
import { StoryFn, Meta } from "@storybook/react";

import Header from "@/src/components/Header/Header";
import BooksPage from "@/src/app/books/page";
import SessionProvider from "@/src/components/Providers/Auth";

export default {
  title: "Pages/Books",
  component: BooksPage,
} as Meta;

const Template: StoryFn<typeof BooksPage> = (args) => {
  return (
    <>
      <SessionProvider>
        <Header />
        <BooksPage />
      </SessionProvider>
    </>
  );
};

export const Default = Template.bind({});
