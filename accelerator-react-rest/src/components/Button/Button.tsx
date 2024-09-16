import Link from "next/link";

type ButtonProps =
  | React.ComponentPropsWithoutRef<typeof Link>
  | (React.ComponentPropsWithoutRef<"button"> & { href?: undefined });

export function Button({ className, ...props }: ButtonProps) {
  className =
    "group inline-flex items-center justify-center py-1 px-2 text-lg rounded-md bg-white text-black dark:bg-black dark:text-white ring-2 ring-black dark:ring-white";

  return typeof props.href === "undefined" ? (
    <button className={className} {...props} />
  ) : (
    <Link className={className} {...props} />
  );
}
