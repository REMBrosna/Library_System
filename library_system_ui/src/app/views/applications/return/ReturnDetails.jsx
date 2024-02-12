import React from "react";
import { Grid, Box } from "@material-ui/core";
import { useStyles, titleTab } from 'app/c1utils/styles';
import C1InputField from "app/c1component/C1InputField";
import {APPLICATION_URL} from "../../../c1utils/const";
import { useTranslation } from "react-i18next";
import C1SelectAutoCompleteField from "app/c1component/C1SelectAutoCompleteField";
import ReturnTap from "./returnTap";
import { MatxLoading } from "matx";
import C1DateField from "../../../c1component/C1DateField";

const ReturnDetails = ({
    inputData,
    errors,
    handleInputChange,
    handleBtnAddItem,
    handleSelectAutoCompleteChanged,
    appID,
    viewType,
    popupData,
    setOpenPopUp,
    handlePopupAutoCompleted,
    handlePopupInputChange,
    openPopUp ,
    popUpFieldError ,
    isRefresh,
    view,
    popUpHandler,
    isSubmitSuccess
}) => {
    console.log("inputData", inputData)
    const title = titleTab();
    const classes = useStyles();
    const { t } = useTranslation(["ret", "common"]);


    let isDisabled = true;
    if (viewType === 'new')
        isDisabled = false;
    else if (viewType === 'edit')
        isDisabled = false;
    else if (viewType === 'view')
        isDisabled = true;
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
                                    error={!(errors && errors['pediVpGeneralDetails.mstApplicationType'] === undefined)}
                                    helperText={(errors && errors['pediVpGeneralDetails.mstApplicationType']) || ''}
                                    value={inputData?.application?.mstApplicationType?.aptCode || ''}
                                    onChange={(e, name, value, reason) => handleSelectAutoCompleteChanged(e, name, value, reason, "pediVpGeneralDetails")}
                                />
                                <C1DateField
                                    label="Borrow Date"
                                    name="borDtBorrow"
                                    type="date"
                                    disabled={isDisabled}
                                    required
                                    value={inputData?.borDtBorrow}
                                    disablePast={false}
                                />
                            </Grid>
                        </Grid>
                    </Grid>
                    <Grid item lg={4} md={6} xs={12} >
                        <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                            <Grid item xs={12} >
                                <C1InputField
                                    required
                                    name="borTotalQty"
                                    disabled={isDisabled}
                                    label="Return Qty"
                                    onChange={(e) => handleInputChange(e, 'pediVpGeneralDetails')}
                                    error={errors && errors.borTotalQty ? true : false}
                                    helperText={(errors && errors['pediVpGeneralDetails.vesselName']) || ''}
                                    value={inputData?.returnQty}
                                />
                            </Grid>
                        </Grid>
                    </Grid>
                    <ReturnTap
                        inputData = {inputData}
                        handleBtnAddItem = {handleBtnAddItem}
                        popupData = {popupData}
                        filterId={inputData?.application?.appID}
                        appID={inputData?.application?.appID}
                        handlePopupAutoCompleted={handlePopupAutoCompleted}
                        handlePopupInputChange ={handlePopupInputChange}
                        openPopUp ={openPopUp}
                        popUpFieldError ={popUpFieldError}
                        isRefresh ={isRefresh}
                        view ={view}
                        viewType={viewType}

                        popUpHandler ={popUpHandler}
                        isSubmitSuccess ={isSubmitSuccess}
                        setOpenPopUp={setOpenPopUp}

                />
                </Grid>
                : <MatxLoading />
            }
            <br />
        </div>
    );
};

export default ReturnDetails;