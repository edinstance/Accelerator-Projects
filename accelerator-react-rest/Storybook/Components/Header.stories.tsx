import React from "react";
import { StoryFn, Meta } from "@storybook/react";
import Header from "@/src/components/Header/Header";
import SessionProvider from "@/src/components/Providers/Auth";

export default {
  title: "Components/Header",
  component: Header,
} as Meta;

const Template: StoryFn<typeof Header> = (args) => {
  return (
    <SessionProvider session={null}>
      <Header />
    </SessionProvider>
  );
};

export const Default = Template.bind({});
