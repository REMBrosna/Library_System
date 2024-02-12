import React from "react";
const borrowRoutes = [

    {
        path: "/borrow/applicationBorrow/list",
        component: React.lazy(() =>
            import("./borrow/BorrowProfileList")
        )
    },
    {
        path: "/borrow/applicationBorrow/:viewType/:appID",
        component: React.lazy(() =>
            import("./borrow/BorrowFormDetails")
        )
    },
    {
        path: "/return/applicationReturn/list",
        component: React.lazy(() =>
            import("./return/ReturnProfileList")
        )
    },
    {
        path: "/return/applicationReturn/:viewType/:appID",
        component: React.lazy(() =>
            import("./return/ReturnFormDetails")
        )
    },
];

export default borrowRoutes;