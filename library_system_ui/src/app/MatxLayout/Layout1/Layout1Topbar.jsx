import React from "react";
import {
  Icon,
  IconButton,
  MenuItem,
  Avatar,
  useMediaQuery,
  Hidden,
} from "@material-ui/core";
import { useDispatch, useSelector } from "react-redux";
import { setLayoutSettings } from "app/redux/actions/LayoutActions";
import { MatxMenu } from "matx";
//import NotificationBar from "../SharedCompoents/NotificationBar";
import { Link } from "react-router-dom";

import { makeStyles, useTheme } from "@material-ui/core/styles";
import { merge } from "lodash";
import clsx from "clsx";
//import NotificationBar2 from "app/views/notification/NotificationBar2";
import useAuth from "app/hooks/useAuth";
import LockOpenIcon from '@material-ui/icons/LockOpen';

const useStyles = makeStyles(({ palette, ...theme }) => ({
  topbar: {
    top: 0,
    zIndex: 96,
    transition: "all 0.3s ease",
    background:
      "linear-gradient(180deg, rgba(255, 255, 155, 0.95) 44%, rgba(247, 247, 247, 0.4) 50%, rgba(255, 255, 255, 0))",

    "& .topbar-hold": {
      backgroundColor: palette.primary.main,
      height: 80,
      paddingLeft: 18,
      paddingRight: 20,
      [theme.breakpoints.down("sm")]: {
        paddingLeft: 16,
        paddingRight: 16,
      },
      [theme.breakpoints.down("xs")]: {
        paddingLeft: 14,
        paddingRight: 16,
      },
    },
    "& .fixed": {
      boxShadow: theme.shadows[8],
      height: 64,
    },
  },
  userMenu: {
    display: "flex",
    alignItems: "center",
    cursor: "pointer",
    borderRadius: 24,
    padding: 4,
    "& span": {
      margin: "0 8px",
      // color: palette.text.secondary
    },
    "&:hover": {
      backgroundColor: palette.action.hover,
    },
  },
  menuItem: {
    display: "flex",
    alignItems: "center",
    minWidth: 185,
  },
}));

const Layout1Topbar = () => {
  const theme = useTheme();
  const classes = useStyles();
  const dispatch = useDispatch();
  const { settings } = useSelector(({ layout }) => layout);
  const { logout, user } = useAuth();
  const isMdScreen = useMediaQuery(theme.breakpoints.down("md"));
  const fixed = settings?.layout1Settings?.topbar?.fixed;

  const updateSidebarMode = (sidebarSettings) => {
    dispatch(
      setLayoutSettings(
        merge({}, settings, {
          layout1Settings: {
            leftSidebar: {
              ...sidebarSettings,
            },
          },
        })
      )
    );
  };

  const handleSidebarToggle = () => {
    let { layout1Settings } = settings;
    let mode;

    if (isMdScreen) {
      mode = layout1Settings.leftSidebar.mode === "close" ? "mobile" : "close";
    } else {
      mode = layout1Settings.leftSidebar.mode === "full" ? "close" : "full";
    }

    updateSidebarMode({ mode });
  };

  return (
    <div className={classes.topbar}>
      <div className={clsx({ "topbar-hold": true, fixed: fixed })}>
        <div className="flex justify-between items-center h-full">
          <div className="flex">
            <IconButton onClick={handleSidebarToggle} className="hide-on-pc">
              <Icon>menu</Icon>
            </IconButton>

            {/* <div className="hide-on-mobile">
              <IconButton>
                <Icon>mail_outline</Icon>
              </IconButton>

              <IconButton>
                <Icon>web_asset</Icon>
              </IconButton>

              <IconButton>
                <Icon>star_outline</Icon>
              </IconButton>
            </div> */}
          </div>
          <div className="flex items-center">
            {/* <MatxSearchBox /> */}

            {/* <NotificationBar />
            <NotificationBar2 />
            <ShoppingCart /> */}

            <MatxMenu
              menuButton={
                <div className={classes.userMenu}>
                  <Hidden xsDown>
                    <span>
                      Hi <strong>{user.name}</strong>
                    </span>
                  </Hidden>
                  <Avatar className="cursor-pointer" src={user.avatar} />
                </div>
              }
            >
              <MenuItem>
                <Link className={classes.menuItem} to="/">
                  <Icon> home </Icon>
                  <span className="pl-4"> Home </span>
                </Link>
              </MenuItem>
              <MenuItem>
                {/* <Link
                className={classes.menuItem}
                to="/page-layouts/user-profile"
              > */}
                <Icon> person </Icon>
                <span className="pl-4"> Profile </span>
                {/* </Link> */}
              </MenuItem>
              <MenuItem className={classes.menuItem}>
                <Link
                  className={classes.menuItem}
                  to={"/manageAccountUser/updatePassword/"}>
                  <LockOpenIcon />
                  <span className="pl-4"> Change password </span>
                </Link>
              </MenuItem>
              <MenuItem onClick={logout} className={classes.menuItem}>
                <Icon> power_settings_new </Icon>
                <span className="pl-4"> Logout </span>
              </MenuItem>
            </MatxMenu>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Layout1Topbar;
