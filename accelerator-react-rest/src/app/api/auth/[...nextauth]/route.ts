import NextAuth, { NextAuthOptions } from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
import Google from "next-auth/providers/google";
import { env } from "@/src/env/server";

const authOptions = {
  // Configure one or more authentication providers
  providers: [
    CredentialsProvider({
      // The name to display on the sign in form (e.g. 'Sign in with...')
      name: "Credentials",
      credentials: {
        email: {
          label: "Email",
          type: "email",
          placeholder: "jsmith@google.com",
        },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials) {
        try {
          const response = await fetch(
            `${env.BACKEND_URL}/api/v1/users/login`,
            {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                email: credentials?.email,
                password: credentials?.password,
              }),
            },
          );
          if (response.ok) {
            const data = await response.json();
            console.log("User:", data);

            const user = {
              id: data.userId,
              name: data.name,
              email: data.email,
              provider: data.provider,
              role: data.role,
            };
            return user;
          } else {
            return null;
          }
        } catch (error) {
          console.error("Error:", error);
          return null;
        }
      },
    }),
    Google({
      clientId: env.GOOGLE_CLIENT_ID as string,
      clientSecret: env.GOOGLE_CLIENT_SECRET as string,
    }),
  ],
  callbacks: {
    async signIn({ user, account }) {
      if (user?.provider?.name === "google") {
        try {
          const response = await fetch(
            `${env.BACKEND_URL}/api/v1/users/postGoogleSignIn`,
            {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify({
                name: user.name,
                email: user.email,
                role: "user",
                providerEntity: {
                  name: account?.provider,
                  externalId: user.id,
                },
              }),
            },
          );
          const data = await response.json();

          // Assuming the backend returns the updated user data
          user.id = data.userId;
          user.name = data.name;
          user.email = data.email;
          user.provider = data.provider;
          user.role = data.role;
        } catch (error) {
          console.error("Error:", error);
        }
      }
      return true;
    },
    async jwt({ token, user }) {
      if (user) {
        token.id = user.id;
        token.role = user.role;
      }
      return token;
    },

    async session({ session, token }) {
      session.user.role = token.role as string;
      session.user.id = token.id as string;
      return session;
    },
    async redirect({ baseUrl }) {
      // Redirect to a specific URL after sign-in
      return baseUrl;
    },
  },
  secret: env.NEXTAUTH_SECRET,
  pages: {
    signIn: "/login",
  },
} satisfies NextAuthOptions;

const handler = NextAuth(authOptions);

export { handler as GET, handler as POST };
