import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import CircularProgress from "@material-ui/core/CircularProgress";

const useStyles = makeStyles(theme => ({
  loading: {
    position: "fixed",
    left: 0,
    right: 0,
    top: "calc(50% - 20px)",
    margin: "auto",
    height: "40px",
    width: "40px",
    zIndex: 1,
    "& img": {
      position: "absolute",
      height: "25px",
      width: "auto",
      top: 0,
      bottom: 0,
      left: 0,
      right: 0,
      margin: "auto",
      zIndex: 100
    }
  }
}));

const Loading = props => {
  const classes = useStyles();

  return (
    <div className={classes.loading}>
       <img src="/assets/images/logo-circle.svg" alt="" />
      <CircularProgress disableShrink />
    </div>
  );
};

export default Loading;