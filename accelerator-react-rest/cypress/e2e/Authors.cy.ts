describe("Authors", () => {
  beforeEach(() => {
    // Start from the books page
    cy.visit("http://localhost:3000/authors");
  });

  it("should check there are mutliple books in the system", () => {
    cy.contains("Lee Child");
    cy.contains("Stephen King");
    cy.contains("William Shakespeare");
  });
});

describe("Individual Author", () => {
  beforeEach(() => {
    // Start from the books page
    cy.visit("http://localhost:3000/authors");

    cy.contains("a", "More").click();
    // The new url should include "/authors"
    cy.url().should("include", "/authors/1");
  });

  it("should check the authors details", () => {
    cy.contains("Lee Child");
    cy.contains("British author");
  });

  it("should check the author has books in the system", () => {
    cy.contains("test title");
    cy.contains("test isbn");
  });
});
