import { env } from "@/src/env/server";

export async function GET() {
  const result = await fetch(`${env.BACKEND_URL}/api/v1/books/getAllBooks`, {
    cache: "no-store",
  });
  if (!result.ok) {
    throw new Error("Error fetching data");
  }
  return result;
}
