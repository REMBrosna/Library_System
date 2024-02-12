 import React from "react";

// uncomment these routes after you put landing files in this folder
const landingRoutes = [
  {
    path: "/master/province/list",
    component: React.lazy(() => import("../configuration/province/ProvinceList")),
  },
  {
    path: "/master/books/list",
    component: React.lazy(() => import("../configuration/book/BookList")),
  },
];

export default landingRoutes;
