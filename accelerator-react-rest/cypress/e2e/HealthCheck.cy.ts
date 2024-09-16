describe("Health Check", () => {
  it("should verify the frontend is up", () => {
    cy.request("http://localhost:3000/api/health").then((response) => {
      expect(response.status).to.eq(200);
    });
  });
});
