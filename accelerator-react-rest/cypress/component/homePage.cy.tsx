import HomePage from "@/src/app/page";

describe("<HomePage />", () => {
  it("renders", () => {
    cy.mount(<HomePage />);
  });
});
