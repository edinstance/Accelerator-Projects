import React from "react";
import { StoryFn, Meta } from "@storybook/react";

import Header from "@/src/components/Header/Header";
import AuthorsPage from "@/src/app/authors/[id]/page";
import SessionProvider from "@/src/components/Providers/Auth";

export default {
  title: "Pages/Authors",
  component: AuthorsPage,
} as Meta;

const Template: StoryFn<typeof AuthorsPage> = (args) => {
  return (
    <>
      <SessionProvider>
        <Header />
        <AuthorsPage
          params={{
            id: 1,
          }}
        />
      </SessionProvider>
    </>
  );
};

export const Default = Template.bind({});
