import { Grid, Snackbar, Tabs } from "@material-ui/core";
import { Icon } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useHistory, useParams } from "react-router-dom";
import C1FormButtons from "app/c1component/C1FormButtons";
import C1FormDetailsPanel from "app/c1component/C1FormDetailsPanel";
import useHttp from "app/c1hooks/http";
import { tabScroll } from "app/c1utils/styles";
import withErrorHandler from "app/hoc/withErrorHandler/withErrorHandler";
import { MatxLoading } from "matx";
import { TabsWrapper } from "../../../portedicomponent/TabsWrapper";
import ShipFleetDetailVTab1 from "./ShipFleetDetailVTab1";
import C1AuditTab from "../../../c1component/C1AuditTab";

const useStyles = makeStyles((theme) => ({

    tabText: {
        marginLeft: '16px',
        marginTop: '-1px',
    },
    tabIcon: {
        position: 'absolute',
        marginLeft: '-5px',
    },
    valid: {
        color: '#F0DB72'
    },
    error: {
        color: 'red'
    }
}));

const BorrowFormDetails = () => {
    const classes = useStyles();
    const { t } = useTranslation(["applications", "common", "vpsr"]);
    let { viewType, appID } = useParams();
    const history = useHistory();
    const [isAppID, setappID] = useState(appID);
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState({});
    const [openDeleteAction, setOpenDeletePopupAction] = useState(false);
    const [isRefresh, setRefresh] = useState(false);
    const [inputData, setInputData] = useState({});
    const { isLoading, isFormSubmission, res, validation, error, urlId, sendRequest } = useHttp();
    const [tabIndex, setTabIndex] = useState(0);
    const [tabIndex1, setTabIndex1] = useState(0);
    const [tabIndex2, setTabIndex2] = useState(0);
    const [tabIndex3, setTabIndex3] = useState(0);
    const [errors, setErrors] = useState({});

    const [openPopUp, setOpenPopUp] = useState(false);
    const [popUpFieldError, setPopUpFieldError] = useState({});
    const [view, setView] = useState(false);
    const [isSubmitSuccess, setSubmitSuccess] = useState(false);

    //*************
    const popupDefaultValue = {
        itmQty: "",
        itmReference:"",
        book:{
            bokId: null
        },
        application: inputData?.application
    };

    const [popupData, setPopupData] = useState(popupDefaultValue);

    const defaultSnackbarValue = {
        success: false,
        successMsg: "",
        error: false,
        errorMsg: "",
        redirectPath: ""
    }
    const [snackBarOptions, setSnackBarOptions] = useState(defaultSnackbarValue);

    const url = "api/v1/library/mst/entity/itm";
    const [snackBarState, setSnackBarState] = useState({
        open: false,
        vertical: 'top',
        horizontal: 'center',
        msg: '',
        severity: 'success'
    });

    const handlePopupAutoCompleted = (e, name, value) => {
        setPopupData({ ...popupData, "book":{bokId:value?.value}, itmReference:value?.value});
    }
    const handlePopupInputChange = (e) => {
        if(e.target.name === 'itmQty') {
            setPopupData({ ...popupData, [e.target.name]: e.target.value});
        }
    }
    const handleDateChange = (name, e) => {
        console.log("handleDateChange =", name, e);
        setInputData({ ...inputData, [name]: e });
    }
    const handleValidate = () => {
        const errors = {};
        if (inputData?.itmQty === null) {
            errors.itmQty = "Required";
        }
        return errors;
    };
    const handleClose = () => {
        console.log("handleClosePopUp",handleClose)
        setSnackBarState({ ...snackBarState, open: false });
    };
    const handleClosePopUp = () => {
        console.log("handleClosePopUp",handleClosePopUp)
        setRefresh(!isRefresh)
        setOpenPopUp(false);
    };

    const popUpHandler = () => {
        setView(false);
        setOpenPopUp(true);
        setPopUpFieldError({});
        setPopupData(popupDefaultValue);
    };

    const handleTabChange = (e, value) => {
        setTabIndex(value);
    };

    //*************
    //capacityAndMachine
    const tabList = [
        { text: t("Borrow Details"), icon: <Icon>schedule</Icon>},
        { text: t("Audits"), icon: <Icon>schedule</Icon>, disabled: false }
    ];

    useEffect(() => {
        if(viewType =='new'){
            sendRequest('/api/v1/library/application/bor/new/0', 'doNew','POST');
        } else {
            sendRequest(`/api/v1/library/application/bor/${appID}`, 'doFetch');
        }
        // eslint-disable-next-line
    }, [isAppID, viewType]);
    useEffect(() => {
        setSnackBarOptions(defaultSnackbarValue);
        if (!isLoading && !error && res && !validation) {
            setLoading(false);
            switch (urlId) {
                case "doFetch":{
                    setInputData(res.data);
                    setData(res.data)
                    break;
                }
                case "doNew": {
                    setInputData(res.data.data);
                    setData(res.data.data)
                    break;
                }
                case "doCreate": {
                    setInputData(res.data);
                    setSnackBarOptions({
                        ...snackBarOptions,
                        success: true,
                        successMsg: `Application Borrow has been created.`,
                        redirectPath: `/borrow/applicationBorrow/edit/` + res?.data?.data?.application?.appID
                    });
                    break;
                }
                case "doUpdate": {
                    setInputData(res.data);
                    if (res.data) {
                        setSnackBarOptions({
                            ...snackBarOptions,
                            success: true,
                            successMsg: `Application Borrow has been save!`,
                            redirectPath: `/borrow/applicationBorrow/list/`
                        })
                    }
                    break;
                }
                case "create": {
                    setPopupData(res.data);
                    setRefresh(true);
                    setSnackBarState({
                        ...snackBarState,
                        open: true,
                        success: true,
                        successMsg: "Return book"
                    });
                    console.log("inputData",inputData)
                    setSubmitSuccess(true);
                    setInputData({
                        ...inputData,
                        borTotalQty : res?.data?.data?.itmQty + inputData.borTotalQty
                    })
                    break;
                }
                case "submit" : {
                    setLoading(false);
                    setSnackBarOptions({
                        ...snackBarOptions,
                        success: true,
                        successMsg: "borrow has been submitted!",
                        redirectPath: "/borrow/applicationBorrow/list/"
                    });
                    break;
                }
                default: break;
            }
            setRefresh(true);
            setOpenDeletePopupAction(false);
        }
        if (validation) {
            setErrors(validation);
            setLoading(false);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [isLoading, isFormSubmission, validation, error]);
    const handleSave = async (e) => {
        e.preventDefault();
        setLoading(true);
        setErrors({});
        switch (viewType) {
            case 'new':
                sendRequest(`api/v1/library/application/bor`, "doCreate", "post", { ...inputData });
                break;
            case 'edit':
                sendRequest(`api/v1/library/application/bor/${appID}`, "doUpdate", "put", { ...inputData });
                break;
            default: break;
        }
    }


    const handleSubmit = () => {
        setLoading(true);
        switch (viewType) {
            case "edit":
                setLoading(true);
                const newInputData = inputData;
                delete newInputData.borCustomer.authorities
                delete newInputData.borCustomer.roles
                sendRequest("api/v1/library/application/confirm/bor", "submit", "post",  newInputData );
                break;
            default:
                break;
        }
    };

    const handleInputChange = (e, key) => {
        setInputData({ ...inputData, [e.target.name]: e.target.value });
    };

    const handleInputChangeSafety = (e, key) => {
        const value = e.target.value;
        if (value.length <= 9) {
            const tmp = { ...inputData, [key]: { ...inputData[key], [e.target.name]: parseInt(value), 'checkValidate': true } };
            setInputData(tmp);
        }
    };

    const handleInputChangeExtFields = (e, key) => {
        if (e.target.value.length <= 35) {
            const tmp = { ...inputData, [key]: { ...inputData[key], [e.target.name]: e.target.value, 'checkValidate': true } };
            setInputData(tmp);
        }
    };

    const  handleBtnAddItem = (e) => {
        e.preventDefault();
        setOpenPopUp(false);
        setPopUpFieldError({});
        setRefresh(false);
        // if id undefined mean create new item else update
        if (popupData.appID === undefined) {
            sendRequest(`${url}`, "create", "POST", popupData);
        } else {
            sendRequest(`${url}/` + popupData.appID, "update", "PUT", popupData);
        }
    }
    const handleSelectAutoCompleteChanged = async (e, name, value, reason, key) => {
        // eslint-disable-next-line default-case
        switch (name) {
            case 'application.mstApplicationType.aptCode':
                setInputData({
                    ...inputData,
                    "application": {
                        ...inputData?.application,
                        "mstApplicationType": {
                            ...inputData?.application?.mstApplicationType,
                            "aptCode": value?.value
                        }
                    }
                });
                break;
            case 'borCustomer.id':
                setInputData({
                    ...inputData,
                    "borCustomer": {
                        ...inputData?.borCustomer,
                        "id": value?.value
                    }
                });
                break;
        }
    }

    let bcLabel = 'Application Borrow';
    if (viewType) {
        switch (viewType) {
            case 'view':
                break;
            case 'new':
                bcLabel = 'New ' + bcLabel;
                break;
            default: break;
        }
    }

    let formButtons = <C1FormButtons options={{
        save: { show: viewType === 'new', eventHandler: (e) => handleSave(e) },
        submitOnClick: { show:  viewType === 'edit',  eventHandler: handleSubmit},
        back: { show: true, eventHandler: () => history.push("/borrow/applicationBorrow/list") }
    }}>
    </C1FormButtons>
    return (
        loading ? <MatxLoading /> :
            <React.Fragment>
                <C1FormDetailsPanel
                    //isForm="true"
                    breadcrumbs={[
                        { name: "Application Borrow ", path: "/borrow/applicationBorrow/list" },
                        { name: bcLabel },
                    ]}
                    title={bcLabel}
                    formButtons={formButtons}
                    snackBarOptions={snackBarOptions}
                    // onSubmit={(values, actions) => handleSubmit(values, actions)}
                    formInitialValues={{ ...inputData }}
                    formValues={{ ...inputData }}
                    onValidate={handleValidate}
                    isLoading={loading}
                >
                    {() => (
                        <Grid container spacing={3}>
                            <Grid item xs={2}>
                                <Tabs
                                    className="mt-4"
                                    value={tabIndex}
                                    onChange={handleTabChange}
                                    orientation="vertical"
                                    variant="scrollable"
                                    scrollButtons="auto"
                                    indicatorColor="primary"
                                    textColor="primary">
                                    {tabList.map((item, ind) => (
                                        <TabsWrapper className="capitalize" value={ind} disabled={item.disabled} label={item.text} key={ind} icon={item.icon} {...tabScroll(ind)} />
                                    ))}
                                </Tabs>
                            </Grid>
                            <Grid item xs={10}>
                                {tabIndex === 0 && <ShipFleetDetailVTab1 handleSubmit={handleSubmit}
                                 data={data} inputData={inputData} tabIndex={tabIndex1} errors={errors}
                                 handleInputChange={handleInputChange}
                                 handleSelectAutoCompleteChanged={handleSelectAutoCompleteChanged}
                                 viewType={viewType}
                                 handleBtnAddItem={handleBtnAddItem}
                                 popupData = {popupData}
                                 handlePopupAutoCompleted ={handlePopupAutoCompleted}
                                 handlePopupInputChange ={handlePopupInputChange}
                                 appID={appID}
                                 openPopUp ={openPopUp}
                                 setOpenPopUp={setOpenPopUp}
                                 handleClose={handleClose}
                                 handleClosePopUp={handleClosePopUp}
                                 popUpFieldError ={popUpFieldError}
                                 isRefresh ={isRefresh}
                                 view ={view}
                                 handleDateChange={handleDateChange}
                                 popUpHandler ={popUpHandler}
                                 isSubmitSuccess ={isSubmitSuccess} />}
                                {tabIndex === 1 && <C1AuditTab filterId={appID} />}
                            </Grid>

                        </Grid>
                    )}
                </C1FormDetailsPanel >
            </React.Fragment>
    );
};
export default withErrorHandler(BorrowFormDetails);