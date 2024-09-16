describe("Navigate", () => {
  it("should navigate to the books page and back", () => {
    cy.visit("http://localhost:3000/");

    cy.contains("Books Page").click();

    // The new url should include "/books"
    cy.url().should("include", "/books");

    cy.contains("Home").click();
  });

  it("should navigate to the authors page and back", () => {
    cy.visit("http://localhost:3000/");

    cy.contains("Authors Page").click();

    // The new url should include "/books"
    cy.url().should("include", "/authors");

    cy.contains("Home").click();
  });

  it("should navigate to the login and registration page and back", () => {
    cy.visit("http://localhost:3000/");

    cy.contains("Login").click();
    // The new url should include "/authors"
    cy.url().should("include", "/login");

    cy.contains("Register").click();
    // The new url should include "/authors"
    cy.url().should("include", "/register");

    cy.contains("Home").click();
  });
});
