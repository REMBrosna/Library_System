import { Box, Grid, MenuItem } from "@material-ui/core";
import { makeStyles } from '@material-ui/core/styles';
import Tab from '@material-ui/core/Tab';
import Tabs from '@material-ui/core/Tabs';
import React, { useState } from "react";

import C1SelectField from "app/c1component/C1SelectField";
import { tabScroll } from "app/c1utils/styles";
import SupportingDocs from "app/portedicomponent/SupportingDocs";

const tabList = [{ text: "Supporting Documents" }];

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
  },
}));

const ShipFleetDetailVTab4 = ({
  handleSubmit,
  data,
  inputData,
  appsId,
  handleInputChange,
  handleInputChangeShip,
  handleValidate,
  viewType,
  viewTypeReg,
  shipApprovedList,
  portList,
  isRegisterPort }) => {

  const [tabIndex, setTabIndex] = useState(0);
  const classes = useStyles();

  const handleChange = (event, newValue) => {
    setTabIndex(newValue);
  };

  return (
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
        onChange={handleChange}
        variant="scrollable"
        scrollButtons="auto"
        indicatorColor="primary"
        textColor="primary" >
        {tabList.map((item, ind) => (
          <Tab className="capitalize" value={ind} label={item.text} key={ind}  {...tabScroll(ind)} />
        ))}
      </Tabs>
      {<SupportingDocs handleSubmit={handleSubmit}
        data={data} inputData={inputData}
        handleInputChange={handleInputChange}
        handleValidate={handleValidate}
        viewType={viewType}
        isEndWorkflow={false}
        appType={"VP"}
        isRequireExpireDate={true}
        isRefNoRequired={true}
        isExpDtRequired={true}
        appId={appsId}
        props={null} />}
    </div>
  );
}
export default ShipFleetDetailVTab4;