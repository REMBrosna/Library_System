import React from "react";

const masterRoutes = [

    // Province
    {
        path: "/master/province/list",
        component: React.lazy(() => import("./province/ProvinceList")),
    },
    {
        path: "/master/province/:viewType/:provinceId?",
        component: React.lazy(() => import("./province/ProvinceFormDetails")),
    },
    {
        path: "/master/books/list",
        component: React.lazy(() => import("./book/BookList")),
    },
    {
        path: "/master/books/:viewType/:bokId?",
        component: React.lazy(() => import("./book/BookFormDetails")),
    },
    {
        path: "/master/district/list",
        component: React.lazy(() => import("./district/DistrictList")),
    },
    {
        path: "/master/district/:viewType/:districtId?",
        component: React.lazy(() => import("./district/DistrictFormDetails")),
    },
    {
        path: "/master/commune/list",
        component: React.lazy(() => import("./commune/CommuneList")),
    },
    {
        path: "/master/commune/:viewType/:communeId?",
        component: React.lazy(() => import("./commune/CommuneFormDetails")),
    },

    //Customer
    {
        path: "/master/customer/list",
        component: React.lazy(() => import("./customer/CustomerList")),
    },
    {
        path: "/master/customer/:viewType/:cusId?",
        component: React.lazy(() => import("./customer/CustomerFormDetails")),
    },

];

export default masterRoutes;
