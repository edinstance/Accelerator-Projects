import BookPage from "@/src/app/page";

describe("<BookPage />", () => {
  it("renders", () => {
    cy.mount(<BookPage />);
  });
});
