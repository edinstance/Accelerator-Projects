import React from "react";
import { StoryFn, Meta } from "@storybook/react";
import { Button } from "@/src/components/Button/Button";

export default {
  title: "Components/Button",
  component: Button,
} as Meta;

const Template: StoryFn<typeof Button> = (args) => {
  return <Button>Button</Button>;
};

export const Default = Template.bind({});
