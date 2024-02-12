import React from "react";
import { Grid, Box } from "@material-ui/core";
import { useStyles, titleTab } from 'app/c1utils/styles';
import C1InputField from "app/c1component/C1InputField";
import {APPLICATION_URL, CUSTOMER_URL, USERS_URL} from "../../../c1utils/const";
import C1SelectAutoCompleteField from "app/c1component/C1SelectAutoCompleteField";
import ItemTab from "./borrowTap";
import { MatxLoading } from "matx";
import C1DateField from "../../../c1component/C1DateField";
import C1DataTable from "../../../c1component/C1DataTable";


const BorrowDetails = ({
   inputData,
   errors,
   handleInputChange,
   handleClosePopUp,
   handleBtnAddItem,
   handleSelectAutoCompleteChanged,
   viewType,
   popupData,
   handlePopupAutoCompleted,
   handlePopupInputChange,
   openPopUp ,
   setOpenPopUp,
   popUpFieldError ,
   isRefresh,
   view,
   popUpHandler,
   isSubmitSuccess,
   handleClose,
   handleDateChange
                       }) => {
    const title = titleTab();
    const classes = useStyles();
    let isDisabled = true;
    if (viewType === 'new')
        isDisabled = false;
    else if (viewType === 'edit')
        isDisabled = false;
    else if (viewType === 'view')
        isDisabled = false;
    return (
        <div>
            <br />
            {
                inputData ?
                    <Grid container alignItems="flex-start" spacing={3} className={classes.gridContainer}>
                        <Grid item lg={4} md={6} xs={12} >
                            <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                                <Grid item xs={12} >
                                    <C1SelectAutoCompleteField
                                        required
                                        disabled={isDisabled}
                                        name="application.mstApplicationType.aptCode"
                                        isServer={true}
                                        options={{
                                            url: APPLICATION_URL,
                                            id: 'aptCode',
                                            desc: 'aptDesc'
                                        }}
                                        label="Application Type"
                                        error={!(errors && errors['application.mstApplicationType.aptCode'] === undefined)}
                                        helperText={(errors && errors['application.mstApplicationType.aptCode']) || ''}
                                        value={inputData?.application?.mstApplicationType?.aptCode || ''}
                                        onChange={(e, name, value, reason) => handleSelectAutoCompleteChanged(e, name, value, reason, "pediVpGeneralDetails")}
                                    />
                                    <C1InputField
                                        name="borTotalQty"
                                        disabled={true}
                                        label="Total Borrow"
                                        onChange={(e) => handleInputChange(e, 'borTotalQty')}
                                        value={inputData?.borTotalQty}
                                    />
                                    <C1InputField
                                        name="borTotalOwe"
                                        disabled={true}
                                        label="Total Owe"
                                        onChange={(e) => handleInputChange(e, 'borTotalOwe')}
                                        value={inputData?.borTotalOwe}
                                    />
                                </Grid>
                            </Grid>
                            {/*<Grid container alignItems="center" spacing={3} className={classes.gridContainer}>*/}
                            {/*    <Grid item xs={12} >*/}

                            {/*    </Grid>*/}
                            {/*</Grid>*/}
                        </Grid>
                        <Grid item lg={4} md={6} xs={12} >
                            <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                                <Grid item xs={12} >
                                    <C1SelectAutoCompleteField
                                        required
                                        disabled={isDisabled}
                                        name="borCustomer.id"
                                        isServer={true}
                                        isShowCode={true}
                                        options={{
                                            url: USERS_URL,
                                            id: 'id',
                                            desc: 'username'
                                        }}
                                        label="Customer Name"
                                        error={!(errors && errors['borCustomer.id'] === undefined)}
                                        helperText={(errors && errors['borCustomer.id']) || ''}
                                        value={inputData?.borCustomer?.id || ''}
                                        onChange={(e, name, value, reason) => handleSelectAutoCompleteChanged(e, name, value, reason, "pediVpGeneralDetails")}
                                    />
                                    <C1DateField
                                        label="Borrow Date"
                                        name="borDtBorrow"
                                        type="date"
                                        disabled={true}
                                        required
                                        onChange={handleDateChange}
                                        value={inputData?.borDtBorrow}
                                        disablePast={true}
                                    />

                                        <C1DateField
                                            label="Return Date"
                                            name="borDtReturn"
                                            type="date"
                                            disabled={isDisabled}
                                            required
                                            onChange={handleDateChange}
                                            value={inputData?.borDtReturn}
                                            disablePast={true}
                                        />
                                </Grid>
                                </Grid>
                        </Grid>
                        <ItemTab
                            inputData = {inputData}
                            handleBtnAddItem = {handleBtnAddItem}
                            popupData = {popupData}
                            filterId={inputData?.application?.appID}
                            appID={inputData?.application?.appID}
                            handlePopupAutoCompleted={handlePopupAutoCompleted}
                            handlePopupInputChange ={handlePopupInputChange}
                            openPopUp={openPopUp}
                            setOpenPopUp={setOpenPopUp}
                            popUpFieldError ={popUpFieldError}
                            isRefresh ={isRefresh}
                            view={view}
                            viewType={viewType}
                            popUpHandler ={popUpHandler}
                            isSubmitSuccess ={isSubmitSuccess}
                            handleClose={handleClose}
                            handleClosePopUp={handleClosePopUp}
                        />
                    </Grid>
                    : <MatxLoading />
            }
            <br />
        </div>
    );
};


export default BorrowDetails;