describe("Books", () => {
  beforeEach(() => {
    // Start from the books page
    cy.visit("http://localhost:3000/books");

    // The new url should include "/books"
    cy.url().should("include", "/books");
  });

  it("should check there is a book in the system", () => {
    // Get the id of the first book in the table
    cy.contains("test title");
    cy.contains("test isbn");
    cy.contains("Released");
    cy.contains("Lee Child");
    cy.contains("View").click();
    cy.url().should("include", "/authors/1");
  });
});
