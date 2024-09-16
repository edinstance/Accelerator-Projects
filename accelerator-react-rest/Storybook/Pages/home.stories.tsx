import React, { useState } from "react";
import { StoryFn, Meta } from "@storybook/react";
import Home from "@/src/app/page";
import Header from "@/src/components/Header/Header";
import SessionProvider from "@/src/components/Providers/Auth";

export default {
  title: "Pages/Home",
  component: Home,
} as Meta;

const Template: StoryFn<typeof Home> = (args) => {
  return (
    <>
      <SessionProvider>
        <Header />
        <Home />
      </SessionProvider>
    </>
  );
};

export const Default = Template.bind({});
