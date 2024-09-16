import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Header from "../components/Header/Header";
import { Providers } from "../components/Providers";
import { getServerSession } from "next-auth";
import SessionProvider from "../components/Providers/Auth";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "6point6 React Accelerator",
  description: "An Accelerator for 6poitn6 nextjs projects",
};

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const session = await getServerSession();
  return (
    <html lang="en">
      <body className={`bg-white dark:bg-black ${inter.className}`}>
        <SessionProvider session={session}>
          <Providers>
            <Header></Header>
            {children}
          </Providers>
        </SessionProvider>
      </body>
    </html>
  );
}
