import { env } from "@/src/env/server";
import { NextRequest } from "next/server";

export async function GET(request: NextRequest) {
  const result = await fetch(
    `${env.BACKEND_URL}/api/v1/authors/getBooksByUserId?userId=${request.nextUrl.searchParams.get("userId")}`,
    {
      cache: "no-store",
    },
  );
  if (!result.ok) {
    throw new Error("Error fetching data");
  }
  return result;
}
