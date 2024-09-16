import BookTable from "@/src/components/Tables/BookTable";

describe("<BookTable />", () => {
  it("renders", () => {
    cy.mount(<BookTable></BookTable>);
  });
});
