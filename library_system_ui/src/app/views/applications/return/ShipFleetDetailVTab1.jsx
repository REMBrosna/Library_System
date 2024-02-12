import React from "react";
import { Grid } from "@material-ui/core";
import { makeStyles } from '@material-ui/core/styles';
import Tabs from '@material-ui/core/Tabs';
import ReturnDetails from "./ReturnDetails";
import { Formik } from "formik";
import { tabScroll } from "app/c1utils/styles";
import { useTranslation } from "react-i18next";
import TabLabel from "../../../portedicomponent/TabLabel";
import {TabsWrapper} from "../../../portedicomponent/TabsWrapper";

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  padding: {
    padding: theme.spacing(3),
  },
  demo1: {
    backgroundColor: theme.palette.background.paper,
  },
  demo2: {
    backgroundColor: '#2e1534',
  }
}));


const ShipFleetDetailVTab1 = ({
  handleSubmit,
  data,
  inputData,
                                setOpenPopUp,
  errors,
  handleInputChange,
  handleSelectAutoCompleteChanged,
  handleInputChangeImo,
  handleInputChangeCallSign,
  handleInputChangeVesselCode,
  handleInputChangeFax,
  handleChangeBuiltYear,
  handleInputChangeVesselName,
  tabIndex,
  handleTab1Changed,
  handleValidate,
  viewType,
  appID,
  handleBtnAddItem,
  popupData,
  handlePopupAutoCompleted,
  handlePopupInputChange,
  openPopUp ,
  popUpFieldError ,
  isRefresh,
  view ,
  popUpHandler,
  isSubmitSuccess
}) => {
  const classes = useStyles();
  const { t } = useTranslation(["applications", "common", "vpsr"]);

  const tabList = [
    { text: "Application Return", name: "pediVpGeneralDetails" },
  ];


  return (
    <Formik
      initialValues={{ ...data }}
      onSubmit={(values, isSubmitting) => handleSubmit(values, isSubmitting)}
      enableReinitialize={true}
      values={{ ...data }}
      validate={handleValidate}
    >
      {(props) => (
        <div className={classes.root}>
          <div className={classes.demo1}></div>
          <Tabs
            className="mt-4"
            value={tabIndex}
            onChange={handleTab1Changed}
            variant="scrollable"
            scrollButtons="auto"
            indicatorColor="primary"
            textColor="primary" >
            {tabList && tabList.map((item, ind) => {
              return <TabsWrapper className="capitalize" value={ind} disabled={item.disabled}
                label={<TabLabel viewType={viewType}  tab={item} />}
                key={ind} {...tabScroll(ind)} />
            })}
          </Tabs>
          <Grid container spacing={3} direction="column">
            <Grid item xs={12}>
              {tabIndex === 0 && <ReturnDetails
                handleSubmit={handleSubmit}
                errors={errors}
                handleInputChange={handleInputChange}
                handleInputChangeFax={handleInputChangeFax}
                handleSelectAutoCompleteChanged={handleSelectAutoCompleteChanged}
                handleInputChangeImo={handleInputChangeImo}
                handleInputChangeCallSign={handleInputChangeCallSign}
                handleInputChangeVesselName={handleInputChangeVesselName}
                handleChangeBuiltYear={handleChangeBuiltYear}
                handleInputChangeVesselCode={handleInputChangeVesselCode}
                handleValidate={handleValidate}
                data={data} inputData={inputData}
                viewType={viewType}
                appID={appID}
                props={props}
                handleBtnAddItem = {handleBtnAddItem}
                popupData = {popupData}
                handlePopupAutoCompleted ={handlePopupAutoCompleted}
                handlePopupInputChange = {handlePopupInputChange}
                openPopUp ={openPopUp}
                popUpFieldError ={popUpFieldError}
                isRefresh ={isRefresh}
                setOpenPopUp={setOpenPopUp}
                view ={view}
                popUpHandler ={popUpHandler}
                isSubmitSuccess ={isSubmitSuccess}
              />}
            </Grid>
          </Grid>
        </div>
      )
      }
    </Formik >
  );
}

export default ShipFleetDetailVTab1;