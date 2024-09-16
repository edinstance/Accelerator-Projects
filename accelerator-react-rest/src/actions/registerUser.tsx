"use server";

import { env } from "@/src/env/server";
import { Provider } from "../user";

async function registerUser({
  name,
  email,
  role,
  provider,
  password,
}: {
  name: String;
  email: String;
  role: String;
  password: String;
  provider: Provider;
}) {
  try {
    const response = await fetch(`${env.BACKEND_URL}/api/v1/users/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: name,
        email: email,
        role: role,
        password: password,
        providerEntity: provider,
      }),
    });
  } catch (error) {
    console.error("Error in fetch:", error);
  }
}

export default registerUser;
