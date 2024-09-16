import { Button } from "@/src/components/Button/Button";

describe("<Button />", () => {
  it("renders", () => {
    cy.mount(<Button>Test Button</Button>);
  });
});
