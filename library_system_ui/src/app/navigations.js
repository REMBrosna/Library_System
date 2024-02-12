export const navigations = [

    {
        name: "Applications",
        icon: "list",
        children: [
            {
                name: "Application Borrow",
                path: "/borrow/applicationBorrow/list",
                iconText: "CC",
            },
            {
                name: "Application Return",
                path: "/return/applicationReturn/list",
                iconText: "CC",
            }
        ],
    },
    {
        name: "Master Data",
        icon: "group",
        children: [
            {
                name: "Book",
                path: "/master/books/list",
                iconText: "CC",
            },
            {
                name: "Province",
                path: `/master/province/list`,
                iconText: "CC",
            },
            {
                name: "District",
                path: "/master/district/list",
                iconText: "CC",
            },
            {
                name: "Commune",
                path: "/master/commune/list",
                iconText: "CC",
            },
        ],
    },
    {
        name: "Administrations",
        icon: "group",
        children: [
            {
                name: "Manage Users",
                path: "/master/customer/list",
                iconText: "CC",
            },
        ],
    },
];
