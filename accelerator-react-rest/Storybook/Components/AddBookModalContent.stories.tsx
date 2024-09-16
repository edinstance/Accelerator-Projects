import React from "react";
import { StoryFn, Meta } from "@storybook/react";
import { BookProvider } from "@/src/context/BookContext";
import AddBookModalContent from "@/src/components/AddBookModalContent/AddBookModalContent";

export default {
  title: "Components/AddBookModalContent",
  component: AddBookModalContent,
  decorators: [
    (Story) => (
      <BookProvider>
        <Story />
      </BookProvider>
    ),
  ],
} as Meta;

const setOpen = () => console.log("setOpen triggered");
const Template: StoryFn = (args) => <AddBookModalContent setOpen={setOpen} />;

export const Default = Template.bind({});
