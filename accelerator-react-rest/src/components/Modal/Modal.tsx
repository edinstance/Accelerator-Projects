import { Dialog, DialogPanel } from "@headlessui/react";
import { Dispatch, SetStateAction } from "react";

function Modal({
  open,
  setOpen,
  children,
}: {
  open: boolean;
  setOpen: Dispatch<SetStateAction<boolean>>;
  children: React.ReactNode;
}) {
  return (
    <Dialog
      open={open}
      onClose={() => setOpen(false)}
      className="relative z-50"
    >
      <div
        className="fixed inset-0 bg-black bg-opacity-50"
        aria-hidden="true"
      />
      <div className="fixed inset-0 flex items-center justify-center p-4">
        <DialogPanel className="rounded bg-white p-6 shadow-lg ring-2 ring-black dark:bg-black dark:ring-white">
          <div className="mt-4">{children}</div>
        </DialogPanel>
      </div>
    </Dialog>
  );
}

export default Modal;
