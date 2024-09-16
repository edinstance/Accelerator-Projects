import { defineConfig } from "cypress";

export default defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
    viewportWidth: 1280,
    viewportHeight: 800,
  },

  component: {
    devServer: {
      framework: "next",
      bundler: "webpack",
    },
    viewportWidth: 1280,
    viewportHeight: 800,
  },
});
