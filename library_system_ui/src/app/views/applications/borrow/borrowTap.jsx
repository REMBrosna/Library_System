import {Box, Button, Grid} from "@material-ui/core";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import C1DataTableActions from "app/c1component/C1DataTableActions";
import C1DataTable from "app/c1component/C1DataTable";
import { useStyles } from "app/c1utils/styles";
import { MatxLoading } from "matx";
import C1PopUp from "../../../c1component/C1PopUp";
import BorrowPopUp from "./BorrowPopupForms/BorrowPopUp";
import ShipFleetDetailVTab1 from "./ShipFleetDetailVTab1";
const ItemTab = ({
     viewType,
     filterId,
     inputData,
     handleBtnAddItem,
     popupData,
     handlePopupAutoCompleted,
     handlePopupInputChange,
     openPopUp ,
     setOpenPopUp,
     popUpFieldError ,
     isRefresh,
     view ,
     popUpHandler,
     isLoading,
                 }) => {
    const classes = useStyles();
    console.log("viewType", viewType)
    const url = "api/v1/library/mst/entity/itm";

    const columns = [
        {
            name: "itmID", // field name in the row object
            label: "Item",
            options: {
                sort: true,
                filter: true,
            },
        },
        {
            name: "book.bokTitle",
            label: "Book",
            options: {
                filter: true,
            },
        },
        {
            name: "book.bokAuthor",
            label: "Author Name",
            options: {
                filter: true,
            },
        },
        {
            name: "itmQty",
            label: "QTY",
            options: {
                filter: true,
            },
        },
        {
            name: "itmOweQty",
            label: "Owe QTY",
            options: {
                filter: true,
            },
        },
        {
            name: "action",
            label: " ",
            options: {
                filter: false,
                display: true,
                sort: false,
                viewColumns: false,
                customBodyRender: (value, tableMeta) => {
                    return (
                        <C1DataTableActions
                            // removeEventHandler={!view ? () => handleDeleteFile(tableMeta.rowData[0]) : null}
                            // returnEventHandler={!view ? () => editClickEventHandler(tableMeta.rowData[0]) : value}
                        />
                    );
                },
            },
        },
    ];

    let title = "List Items :";
    return (<React.Fragment>
            {isLoading && <MatxLoading />}
            <Grid container spacing={3} p={5} className={classes.gridContainer}>
                <Grid item xs={12} l={12} m={12}>
                    {
                         filterId && <C1DataTable url={url}
                         columns={columns}
                         title={title}
                         isShowToolbar={viewType !== 'view'}
                         filterBy={[
                             { attribute: "itmID", value: filterId }
                         ]}
                         defaultOrder="itmID"
                         isRefresh={isRefresh}
                         showAdd={{
                             type: "popUp",
                             popUpHandler: popUpHandler,
                         }}
                        />
                    }
                    <C1PopUp
                        title="Add borrow book"
                        openPopUp={openPopUp}
                        setOpenPopUp={setOpenPopUp}
                        maxWidth="md">
                        <BorrowPopUp
                            viewType={viewType}
                            inputData={popupData}
                            isShowToolbar={!viewType ==='view'}

                            view={view}
                            appID={inputData?.appID}
                            errors={popUpFieldError}
                            handlePopupInputChange={handlePopupInputChange}
                            handleSelectAutoCompleteChanged={handlePopupAutoCompleted}
                            handleBtnAddClick={handleBtnAddItem}
                        />
                    </C1PopUp>
                </Grid>
            </Grid>
        </React.Fragment>
    );
};


export default ItemTab;
