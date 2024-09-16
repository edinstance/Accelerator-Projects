describe("Authors", () => {
  beforeEach(() => {
    // Start from the books page
    cy.visit("http://localhost:3000/");
  });

  it("should navigate to the registration page and register as a user", () => {
    // It should navigate to the registration page
    cy.contains("Login").click();
    cy.contains("Register").click();
    cy.url().should("include", "/register");

    // It should register a user
    cy.get("#firstName").type("Test");
    cy.get("#lastName").type("User");
    cy.get("#email").type("test@6point6.co.uk");
    cy.get("#password").type("password");
    cy.get("#confirm-password").type("password");
    cy.contains("button", "Register").click();
  });

  it("should login a user and then logout", () => {
    // It should go to the login page
    cy.contains("Login").click();

    // It should login as a user
    cy.get("#email").type("test-default@6point6.co.uk");
    cy.get("#password").type("password");
    cy.get("form").contains('button[type="submit"]', "Login").click();

    // It should check the user can logout and view their profile
    cy.contains("Logout").should("exist");
    cy.contains("Profile").click();

    // It should check the user is a user and check their details
    cy.contains("Test User").should("exist");
    cy.contains("test-default@6point6.co.uk").should("exist");
    cy.contains("Role: User").should("exist");
    cy.contains("Apply to be an Author").should("exist");

    // It should logout the user
    cy.contains("Logout").click();
    cy.url().should("include", "/");
  });

  it("should login a user that is an author", () => {
    cy.contains("Login").click();

    // It should login as an author
    cy.get("#email").type("test-author-user@6point6.co.uk");
    cy.get("#password").type("password");
    cy.get("form").contains('button[type="submit"]', "Login").click();

    // It should check the user can logout and view their profile
    cy.contains("Logout").should("exist");
    cy.contains("Profile").click();

    // It should check the user is an author and check their details
    cy.contains("Lee Child").should("exist");
    cy.contains("test-author-user@6point6.co.uk").should("exist");
    cy.contains("Role: Author").should("exist");

    // It should check the author has books in the system
    cy.contains("test title");
    cy.contains("test isbn");
  });
});
