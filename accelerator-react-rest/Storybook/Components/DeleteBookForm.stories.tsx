import React from "react";
import { StoryFn, Meta } from "@storybook/react";
import DeleteBookForm from "@/src/components/DeleteBookForm/DeleteBookForm";

export default {
  title: "Components/DeleteBookForm",
  component: DeleteBookForm,
} as Meta;

const Template: StoryFn<typeof DeleteBookForm> = (args) => {
  return <DeleteBookForm />;
};

export const Default = Template.bind({});
