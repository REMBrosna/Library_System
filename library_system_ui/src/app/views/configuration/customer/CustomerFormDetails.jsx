import React, { useState, useEffect } from "react";
import { Grid, Paper, Tabs, Tab, Divider } from "@material-ui/core";
import { useParams, useHistory } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { MatxLoading } from "matx";
import withErrorHandler from "app/hoc/withErrorHandler/withErrorHandler";
import C1FormDetailsPanel from "app/c1component/C1FormDetailsPanel";
import C1FormButtons from "app/c1component/C1FormButtons";
import C1Propertiestab from "app/c1component/C1PropertiesTab";
import C1AuditTab from "app/c1component/C1AuditTab";
import { commonTabs } from "app/c1utils/const";
import useHttp from "app/c1hooks/http";
import { deepUpdateState } from "app/c1utils/stateUtils";
import CustomerDetails from "./CustomerDetails";
import ConfirmationDialog from "../../../../matx/components/ConfirmationDialog";
//import ConfirmationDialog from "../../../../../matx/components/ConfirmationDialog";

const CustomerFormDetails = () => {

    let { viewType, cusId } = useParams();
    const { t } = useTranslation(["masters", "common"]);
    let history = useHistory();
    const { isLoading, isFormSubmission, res, validation, error, urlId, sendRequest } = useHttp();
    const [tabIndex, setTabIndex] = useState(0);
    const [errors, setErrors] = useState({});

    const [loading, setLoading] = useState(false);
    const [inputData, setInputData] = useState({
        customerName: "",
        customerType: "",
        gender: "",
        dob:"",
        phoneNumber:"",
        identityCardNo:"",
        recStatus:""
    });

    const defaultSnackbarValue = {
        success: false,
        successMsg: "",
        error: false,
        errorMsg: "",
        redirectPath: "",
    };
    const [snackBarOptions, setSnackBarOptions] = useState(defaultSnackbarValue);
    const [openPopupAction, setOpenPopupAction] = useState(false);
    const [action, setAction] = useState("");

    //api request for the details here
    useEffect(() => {
        setLoading(false);
        if (viewType !== "new") {
            sendRequest("api/v1/library/mst/entity/customer/" + cusId, "getCustomer", "get", {});
        }
    }, [cusId, viewType]);

    useEffect(() => {
        if (!isLoading && !error && res && !validation) {
            setLoading(isLoading);

            switch (urlId) {
                case "getCustomer":
                    break;
                case "saveCustomer":
                    setSnackBarOptions({
                        ...snackBarOptions,
                        success: true,
                        successMsg: "Save customer successfully",
                        redirectPath: "/master/customer/list",
                    });
                    break;
                case "updateCustomer":
                case "deActive":
                case "activate":
                    setSnackBarOptions({
                        ...snackBarOptions,
                        success: true,
                        successMsg: "Update customer successfully",
                        redirectPath: "/master/customer/list",
                    });
                    break;
                default:
                    break;
            }

            setInputData({ ...inputData, ...res.data.data });

            if (validation) {
                console.log("validation in useEffect....", validation);
            }
        } else if (error) {
            setLoading(false);
        }

        if (validation) {
            setErrors({ ...validation });
            setLoading(false);
        }

        // eslint-disable-next-line
    }, [urlId, isLoading, isFormSubmission, res, validation, error]);

    const handleTabChange = (e, value) => {
        setTabIndex(value);
    };

    const handleSubmit = async (values) => {
        setLoading(true);

        switch (viewType) {
            case "new":
                sendRequest("api/v1/library/mst/entity/customer", "saveCustomer", "post", {
                    ...inputData,
                    recStatus: "A",
                });
                break;

            case "edit":
                sendRequest("api/v1/library/mst/entity/customer/" + cusId, "updateCustomer", "put", {
                    ...inputData,
                });
                break;
            default:
                break;
        }
    };
    const handleActiveChange = (e) => {
        setTimeout(
            () =>
                sendRequest(
                    "api/v1/library/mst/entity/customer/" + cusId + "/activate",
                    "activate",
                    "put",
                    inputData
                ),
            1000
        );
    };

    const handleDeActiveChange = (e) => {
        setTimeout(
            () => sendRequest("api/v1/library/mst/entity/customer/" + cusId, "deActive", "delete", {}),
            1000
        );
    };

    const handleConfirmAction = () => {
        if (action === "delete") {
            setTimeout(
                () => sendRequest("api/v1/library/mst/entity/customer", "deActive", "delete", {}),
                1000
            );
        } else if (action === "active") {
            setTimeout(
                () =>
                    sendRequest(
                        "api/v1/library/mst/entity/customer/" + cusId + "/activate",
                        "active",
                        "put",
                        inputData
                    ),
                1000
            );
        }
        setOpenPopupAction(false);
    };

    const handleInputChange = (e) => {
        const elName = e.target.name;
        setInputData({ ...inputData, ...deepUpdateState(inputData, elName, e.target.value) });
    };

    const handleDateChange = (name, e) => {
        console.log("handleDateChange =", name, e);
        setInputData({ ...inputData, [name]: e });
    }

    let formButtons = (
        <C1FormButtons
            options={{
                back: {
                    show: true,
                    eventHandler: () => history.goBack(),
                },
                submit: true,
            }}
        />
    );

    let bcLabel = "customer";
    if (viewType) {
        switch (viewType) {
            case "view":
                bcLabel = "customer";
                formButtons = (
                    <C1FormButtons
                        options={{
                            back: { show: true, eventHandler: () => history.goBack() },
                            activate: {
                                show: inputData.recStatus === "I",
                                eventHandler: () => handleActiveChange(),
                            },
                        }}
                    />
                );
                break;
            case "edit":
                bcLabel = "Edit Customer";
                formButtons = (
                    <C1FormButtons
                        options={{
                            back: { show: true, eventHandler: () => history.goBack() },
                            activate: {
                                show: inputData.recStatus === "I",
                                eventHandler: () => handleActiveChange(),
                            },
                            deactivate: {
                                show: inputData.recStatus === "A",
                                eventHandler: () => handleDeActiveChange(),
                            },
                            submit: true,
                        }}
                    />
                );
                break;
            case "new":
                bcLabel = "Add new customer";
                break;
            default:
                break;
        }
    }

    return loading ? (
        <MatxLoading />
    ) : (
        <React.Fragment>
            <C1FormDetailsPanel
                breadcrumbs={[
                    { name: t("Customer List"), path: "/master/customer/list" },
                    { name: bcLabel },
                ]}
                title={bcLabel}
                formButtons={formButtons}
                initialValues={{ ...inputData }}
                values={{ ...inputData }}
                onSubmit={(values, actions) => handleSubmit(values, actions)}
                snackBarOptions={snackBarOptions}
                isLoading={loading}
            >
                {(props) => (
                    <Grid container spacing={3}>
                        <Grid item xs={12}>
                            <Paper className="p-3">
                                <Tabs
                                    className="mt-4"
                                    value={tabIndex}
                                    onChange={handleTabChange}
                                    indicatorColor="primary"
                                    textColor="primary"
                                >
                                    {commonTabs.map((item, ind) => (
                                        <Tab
                                            className="capitalize"
                                            value={ind}
                                            label={t(item.text)}
                                            key={ind}
                                            icon={item.icon}
                                        />
                                    ))}
                                </Tabs>
                                <Divider className="mb-6" />

                                {tabIndex === 0 && (
                                    <CustomerDetails
                                        inputData={inputData}
                                        handleInputChange={handleInputChange}
                                        handleDateChange={handleDateChange}
                                        viewType={viewType}
                                        isSubmitting={loading}
                                        errors={{ ...props.errors, ...errors }}
                                        locale={t}
                                    />
                                )}
                                {tabIndex === 1 && (
                                    <C1Propertiestab
                                        dtCreated={inputData.dtCreate}
                                        usrCreated={inputData.uidCreate}
                                        dtLupd={inputData.dtLupd}
                                        usrLupd={inputData.uidLupd}
                                    />
                                )}
                                {tabIndex === 2 && <C1AuditTab filterId={cusId} />}
                            </Paper>
                        </Grid>
                    </Grid>
                )}
            </C1FormDetailsPanel>
        </React.Fragment>
    );
};

export default withErrorHandler(CustomerFormDetails);
