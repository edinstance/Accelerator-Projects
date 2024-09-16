This is a [Next.js](https://nextjs.org/) project bootstrapped with [`create-next-app`](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

## Running the application

First, run the backend:

### Wiremock

You can run the wiremock services using docker and to run it use `docker-compose up wiremock -d`

### Spring Application

This repo is also designed to be run with the Spring-Rest-Accelerator. To run this with that follow the Spring setup instructions and then set the env variable to point at it.

### Environment variables

Set the env variables based on [.env.local.example](.env.local.example) you also need to validate them in this folder [/src/env](src/env/) based on whether they are server or client env variables. This is beacause we are using [t3 env](https://env.t3.gg/docs/introduction) and also validating the env vars.

### Authentication

Currently there are two ways to authenticate as a user. Either using Google authentication which must use a 6point6 email address, or you can use the custom sign-up sign-in that we created.
### Next application

Now you can run the next application, there are two ways it can be run.

#### Development server

You can run the app on the development server by using `npm run dev`.

#### Docker

Or the application can be run using docker using `docker-compose up next-accelerator`
and if you want to run both the wiremock and the application you can use `docker-compose up`

## Tests

### End-to-End

To run the end-to-end tests you need to be running either the backend or the wiremock, to run the tests you can either run them headless by running `npm run cy:run:e2e` or you can run them with the cypress app by running `npm run cy:open`.

### Component

The component tests can be run without the backend or wire mocks. To run them you can either run them headless using `npm run cy:run:ct`or you can run them with the cypress app by running npm run cy:open.

## Data Fetching

### Polling

There is a polling rate of 1 minute set for the getAllBooks api, so that the data is periodically fetched. This can be edited by changing the Interval in the [BookContext](src/context/BookContext.tsx).

## Previewing the components

### Storybook

To view all the components individually you can use storybook. To run storybook you should run `npm run storybook`. All the stories are contained in the .stories.tsx files and the config is in the [.storybook/](.storybook/) folder.

## Learn More

To learn more about Next.js, take a look at the following resources:

- [Next.js Documentation](https://nextjs.org/docs) - learn about Next.js features and API.
- [Learn Next.js](https://nextjs.org/learn) - an interactive Next.js tutorial.

You can check out [the Next.js GitHub repository](https://github.com/vercel/next.js/) - your feedback and contributions are welcome!
