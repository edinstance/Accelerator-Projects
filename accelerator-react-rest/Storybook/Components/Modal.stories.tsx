import React, { useState } from "react";
import { StoryFn, Meta } from "@storybook/react";
import Modal from "@/src/components/Modal/Modal";
import { Button } from "@/src/components/Button/Button";
import AddBookModalContent from "@/src/components/AddBookModalContent/AddBookModalContent";

export default {
  title: "Components/Modal",
  component: Modal,
} as Meta;

export const Empty: StoryFn<typeof Modal> = () => {
  const [open, setOpen] = React.useState(false);
  return (
    <>
      <Button onClick={() => setOpen(true)}>Open Modal</Button>
      <Modal open={open} setOpen={setOpen}>
        <Button onClick={() => setOpen(false)}>Close Modal</Button>
      </Modal>
    </>
  );
};

export const WithAddBookContent: StoryFn<typeof Modal> = () => {
  const [open, setOpen] = React.useState(false);
  return (
    <>
      <Button onClick={() => setOpen(true)}>Open Modal</Button>
      <Modal open={open} setOpen={setOpen}>
        <AddBookModalContent setOpen={setOpen} />
      </Modal>
    </>
  );
};
