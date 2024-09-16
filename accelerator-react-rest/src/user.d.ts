import { Session } from "next-auth";

type Provider = {
  providerId?: number;
  name: string;
  externalId: string;
};

// This adds the type of role to to the sesison
declare module "next-auth" {
  interface Session {
    user: {
      id: string;
      name: string;
      email: string;
      image?: string;
      role: string;
    };
  }
  interface User {
    role: string;
    provider: Provider;
  }
}
