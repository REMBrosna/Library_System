import React from "react";
import { makeStyles } from '@material-ui/core/styles';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import { Formik } from "formik";
import { tabScroll } from "app/c1utils/styles";
import C1SelectField from "app/c1component/C1SelectField";
import { Grid, MenuItem, Box } from "@material-ui/core";
import { useTranslation } from "react-i18next";
import TabLabel from "../../../portedicomponent/TabLabel";

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

const ShipFleetDetailVTab2 = ({
  handleSubmit,
  data,
  inputData,
  tabIndex,
  errors,
  handleInputChange,
  handleInputChangeShip,
  handleSelectAutoCompleteChanged,
  handleTabChanged,
  handleValidate,
  viewType,
  viewTypeReg,
  shipApprovedList,
  portList,
  isRegisterPort
}) => {
  const { t } = useTranslation(["applications", "common", "vpsr"]);
  const classes = useStyles();

  const tabList = [
    {
      text: t("vpsr:app.vp.capacityAndMachine.loadLine.label"),
      name: "pediVpLoadline"
    },
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
          {isRegisterPort && <Grid container direction="row">
            <Grid item xs={12} lg={4}>
              <Box ml={2} mr={3}>
                <C1SelectField
                  label="Ship"
                  name="imoNo"
                  onChange={handleInputChangeShip}
                  value={inputData.imoNo || ""}
                  required
                  disabled={"S" === inputData.status || "A" === inputData.status || "view" === viewTypeReg}
                  options={
                    shipApprovedList && shipApprovedList.list.map((item, ind) => {
                      return <MenuItem value={item.imoNo} key={ind}>{item.vesselName} - {item.imoNo}</MenuItem>;
                    })
                  } />
              </Box>
            </Grid>
            <Grid item xs={12} lg={4}>
              <Box ml={2} mr={3}>
                <C1SelectField
                  label="Ship Registation Port"
                  name="shipRegPort"
                  onChange={handleInputChange}
                  value={inputData.shipRegPort || ""}
                  required
                  disabled={"S" === inputData.status || "A" === inputData.status || "view" === viewTypeReg}
                  options={
                    portList && portList.map((item, ind) => {
                      return <MenuItem value={item.key} key={item.key}>{item.value}</MenuItem>;
                    })
                  } />
              </Box>
            </Grid>
          </Grid>}

          <Tabs
            className="mt-4"
            value={tabIndex}
            onChange={handleTabChanged}
            variant="scrollable"
            scrollButtons="auto"
            indicatorColor="primary"
            textColor="primary" >
            {tabList && tabList.map((item, ind) => {
              return <Tab className="capitalize" value={ind}
                label={<TabLabel viewType={viewType} errors={errors} invalidTabs={inputData.invalidTabs} tab={item} />}
                key={ind} {...tabScroll(ind)} />
            })}
          </Tabs>
        </div>
      )}
    </Formik>
  );
}
export default ShipFleetDetailVTab2;