import Header from "@/src/components/Header/Header";
import SessionProvider from "@/src/components/Providers/Auth";

describe("<Header />", () => {
  it("renders", () => {
    cy.mount(
      <SessionProvider session={null}>
        <Header />
      </SessionProvider>,
    );
  });
});
