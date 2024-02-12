import { Grid, Tabs } from "@material-ui/core";
import { Icon } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useHistory, useParams } from "react-router-dom";
import C1AuditTab from "app/c1component/C1AuditTab";
import C1FormButtons from "app/c1component/C1FormButtons";
import C1FormDetailsPanel from "app/c1component/C1FormDetailsPanel";
import useHttp from "app/c1hooks/http";
import { tabScroll } from "app/c1utils/styles";
import { isDecimal } from "app/c1utils/utility";
import withErrorHandler from "app/hoc/withErrorHandler/withErrorHandler";
import { MatxLoading } from "matx";
import { TabsWrapper } from "../../../portedicomponent/TabsWrapper";
import ShipFleetDetailVTab1 from "./ShipFleetDetailVTab1";
import ShipFleetDetailVTab2 from "./ShipFleetDetailVTab2";
import ShipFleetDetailVTab3 from "./ShipFleetDetailVTab3";
import ShipFleetDetailVTab4 from "./ShipFleetDetailVTab4";

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
        color: '#f0db72'
    },
    error: {
        color: 'red'
    }
}));

const ReturnFormDetails = () => {
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
    const popUpHandler = () => {
        setView(false);
        setOpenPopUp(true);
        setPopUpFieldError({});
        setPopupData(popupDefaultValue);
    };
    //*************

    //capacityAndMachine
    const tabList = [
        { text: t("Return Details"), icon: <Icon>schedule</Icon>},
        { text: t("Audits"), icon: <Icon>schedule</Icon>, disabled: false }
        ];

     useEffect(() => {
         if(viewType ==='new'){
             sendRequest(`/api/v1/library/application/ret/new/${appID}`, 'doNew','POST');
         } else {
             sendRequest(`/api/v1/library/application/ret/${appID}`, 'doFetch');
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
                        successMsg: `Return book.`,
                        redirectPath: `/return/applicationReturn/edit/` + res?.data?.data?.application?.appID
                    });
                    break;
                }
                case "doUpdate": {
                    setInputData(res.data);
                    if (res.data) {
                        setSnackBarOptions({
                            ...snackBarOptions,
                            success: true,
                            successMsg: `Return book has been save!`,
                            redirectPath: `/return/applicationReturn/list/`
                        })
                    }
                    break;
                }
                case "get": {
                    Object.assign(res.data, popupDefaultValue);
                    setPopupData(res.data);
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
                    setSubmitSuccess(true);
                    setInputData({
                        ...inputData,
                        returnQty : res?.data?.data?.itmQty + inputData.returnQty
                    })
                    break;
                }
                case "submit" : {
                    setLoading(false);
                    setSnackBarOptions({
                        ...snackBarOptions,
                        success: true,
                        successMsg: "return has been submitted !",
                        redirectPath: "/return/applicationReturn/list/"
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
                sendRequest(`api/v1/library/application/ret`, "doCreate", "post", { ...inputData });
                break;
            case 'edit':
                sendRequest(`api/v1/library/application/ret/${appID}`, "doUpdate", "put", { ...inputData });
                break;
            default: break;
        }
    }

    const setActiveTab = (key) => {
        const keys = ['pediVpGeneralDetails', 'registry', 'chartererPerson', 'bareBoatPerson', 'operationPerson', 'pediVpDimension',
            'pediVpEngine', 'pediVpLoadline', 'pediVpSafetyStructure'
        ];
        let temp = { ...inputData }
        for (let i = 0; i < keys.length; i++) {
            if (keys[i] === key) {
                Object.assign(temp, { [key]: { ...inputData[key], "activeTab": true } });
            } else {
                Object.assign(temp, { [keys[i]]: { ...inputData[keys[i]], "activeTab": false } })
            }
        }
        setInputData(temp);
    }
    const setActiveTab1 = (value) => {
        switch (value) {
            case 0:
                setActiveTab('pediVpGeneralDetails'); break;
            case 1:
                setActiveTab('registry');
                break;
            case 2:
                setActiveTab('chartererPerson');
                break;
            case 3:
                setActiveTab('bareBoatPerson');
                break;
            case 4:
                setActiveTab('operationPerson');
                break;
            default: break;
        }
    }
    const setActiveTab2 = (value) => {
        switch (value) {
            case 0:
                setActiveTab('pediVpLoadline');
                break;
            case 1:
                setActiveTab('pediVpDimension');
                break;
            case 2:
                setActiveTab('pediVpEngine');
                break;
            default: break;
        }
    }

    const handleTabChange = async (e, value) => {
        setTabIndex(value);
        switch (value) {
            case 0:
                setActiveTab1(tabIndex1);
                break;
            case 1:
                setActiveTab2(tabIndex2);
                break;
            case 2:
                setActiveTab('pediVpSafetyStructure');
                break;
            default: break;
        }
    };

    const handleTab1Changed = async (e, value) => {
        setTabIndex1(value);
        setActiveTab1(value);
    }

    const handleTab2Changed = async (e, value) => {
        setTabIndex2(value);
        setActiveTab2(value);
    }

    const handleTab3Changed = async (e, value) => {
        setTabIndex3(value);
        setActiveTab('pediVpSafetyStructure');
    }

    const handleSubmit = () => {
        setLoading(true);
        switch (viewType) {
            case "edit":
                setLoading(true);
                sendRequest("api/v1/library/application/confirm/ret", "submit", "post",  inputData );
                break;
            default:
                break;
        }
    };
    const handleValidate = () => {
        const { vpssContainer } = inputData?.application.appID || {};
        const errors = {};

        if (vpssContainer == null || vpssContainer === "") {
            errors["pediVpSafetyStructure.vpssContainer"] = t("vpsr:app.vp.safetyAndStructure.field.required");
        } else if (vpssContainer < 0) {
            errors["pediVpSafetyStructure.vpssContainer"] = t("vpsr:app.vp.safetyAndStructure.field.min", { min: 0 });
        } else if (isDecimal(vpssContainer)) {
            errors["pediVpSafetyStructure.vpssContainer"] = t("vpsr:app.vp.safetyAndStructure.field.numberOnly");
        }
        setErrors(errors);
        return errors;
    }

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
        }
    }

    let bcLabel = 'Application Return';
    if (viewType) {
        switch (viewType) {
            case 'edit':
                break;
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
        back: { show: true, eventHandler: () => history.push("/return/applicationReturn/list") }
    }}>
    </C1FormButtons>
    return (
        loading ? <MatxLoading /> :
            <React.Fragment>
            <C1FormDetailsPanel
                isForm="true"
                breadcrumbs={[
                    { name: "Application Return ", path: "/return/applicationReturn/list" },
                    { name: bcLabel },
                ]}
                title={bcLabel}
                formButtons={formButtons}
                snackBarOptions={snackBarOptions}
                formInitialValues={{ ...inputData }}
                onSubmit={(values, actions) => handleSubmit(values, actions)}
                formValues={{ ...inputData }}
                formValidate={handleValidate}>
                {() => (
                    <Grid container spacing={3}>
                        <Grid item xs={2}>
                            <Tabs
                                className="mt-4"
                                value={tabIndex}
                                orientation="vertical"
                                onChange={handleTabChange}
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
                                handleTab1Changed={handleTab1Changed}
                                viewType={viewType}
                                handleBtnAddItem = {handleBtnAddItem}
                                popupData = {popupData}
                                setOpenPopUp={setOpenPopUp}
                                handlePopupAutoCompleted ={handlePopupAutoCompleted}
                                handlePopupInputChange ={handlePopupInputChange}
                                appID={appID}
                                openPopUp ={openPopUp}
                                popUpFieldError ={popUpFieldError}
                                isRefresh ={isRefresh}
                                view ={view}
                                popUpHandler ={popUpHandler}
                                isSubmitSuccess ={isSubmitSuccess}
                                name="tab1" />}
                            {tabIndex === 1 && <ShipFleetDetailVTab2
                                data={data} inputData={inputData} tabIndex={tabIndex2} errors={errors}
                                handleInputChange={handleInputChange}
                                handleTabChanged={handleTab2Changed}
                                handleSelectAutoCompleteChanged={handleSelectAutoCompleteChanged}
                                handleValidate={handleValidate}
                                viewType={viewType} />}
                            {tabIndex === 2 && <ShipFleetDetailVTab3 handleSubmit={handleSubmit}
                                data={data} inputData={inputData} tabIndex={tabIndex3} errors={errors}
                                handleTabChanged={handleTab3Changed}
                                handleInputChange={handleInputChange}
                                handleInputChangeSafety={handleInputChangeSafety}
                                handleInputChangeExtFields={handleInputChangeExtFields}
                                handleValidate={handleValidate}
                                viewType={viewType} />}

                            {tabIndex === 3 && <ShipFleetDetailVTab4 handleSubmit={handleSubmit}
                                data={data} inputData={inputData} appsId={appID}
                                handleInputChange={handleInputChange}
                                handleValidate={handleValidate}
                                isRefresh={isRefresh}
                                viewType={viewType} />}
                            {tabIndex === 4 && <C1AuditTab appStatus={inputData.status} filterId={inputData?.appID} />}
                        </Grid>
                    </Grid>
                )}
            </C1FormDetailsPanel >
        </React.Fragment>
    );
};
export default withErrorHandler(ReturnFormDetails);