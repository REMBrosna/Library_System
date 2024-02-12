import React from "react";
import { Redirect } from "react-router-dom";
import landingRoutes from "./views/landing/LandingRoutes";
import masterRoutes from "./views/configuration/masterRoutes";
import borrowRoutes from "./views/applications/borrowRoutes";


const redirectRoute = [
  {
    path: "/",
    exact: true,
    component: () => <Redirect to="/borrow/applicationBorrow/list" />
  },
];

const errorRoute = [
  {
    component: () => <Redirect to="/session/404" />,
  },
];

const routes = [
  ...redirectRoute,
  ...landingRoutes,
  ...borrowRoutes,
  ...masterRoutes,
  ...errorRoute,
];

export default routes;
