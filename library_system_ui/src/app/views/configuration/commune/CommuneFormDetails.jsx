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
import CommuneDetails from "./CommuneDetails";
// import ConfirmationDialog from "../../../../../matx/components/ConfirmationDialog";

const CommuneFormDetails = () => {

    let { viewType, communeId } = useParams();
    const { t } = useTranslation(["masters", "common"]);
    let history = useHistory();
    const { isLoading, isFormSubmission, res, validation, error, urlId, sendRequest } = useHttp();
    const [tabIndex, setTabIndex] = useState(0);
    const [errors, setErrors] = useState({});

    const [loading, setLoading] = useState(false);
    const [inputData, setInputData] = useState({
        code: "",
        desc: "",
        descOth: "",
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
            sendRequest("api/v1/library/mst/entity/commune/" + communeId, "getCommune", "get", {});
        }
    }, [communeId, viewType]);

    useEffect(() => {
        if (!isLoading && !error && res && !validation) {
            setLoading(isLoading);

            switch (urlId) {
                case "getCommune":
                    break;
                case "getCommune":
                    setSnackBarOptions({
                        ...snackBarOptions,
                        success: true,
                        successMsg: t("masters:commune.msg.successMsg"),
                        redirectPath: "/master/commune/list",
                    });
                    break;
                case "updateCommune":
                case "deActive":
                case "activate":
                    setSnackBarOptions({
                        ...snackBarOptions,
                        success: true,
                        successMsg: t("masters:commune.msg.successOperation"),
                        redirectPath: "/master/commune/list",
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
                sendRequest("api/v1/library/mst/entity/commune", "saveCommune", "post", {
                    ...inputData,
                    recStatus: "A",
                });
                break;

            case "edit":
                sendRequest("api/v1/library/mst/entity/commune/" + communeId, "updateCommune", "put", {
                    ...inputData,
                });
                break;
            default:
                break;
        }
    };

    const handleValidate = () => {
        const errors = {};

        if (viewType === "new") {
            if (!inputData.code) errors.code = t("masters:contract.suppDocs.required");
        }

        if (!inputData.desc) {
            errors.desc = t("masters:contract.suppDocs.required");
        }

        return errors;
    };

    const handleActiveChange = (e) => {
        setTimeout(
            () =>
                sendRequest(
                    "api/v1/library/mst/entity/commune/" + communeId + "/activate",
                    "activate",
                    "put",
                    inputData
                ),
            1000
        );
    };

    const handleDeActiveChange = (e) => {
        setTimeout(
            () => sendRequest("api/v1/library/mst/entity/commune/" + communeId, "deActive", "delete", {}),
            1000
        );
    };

    const handleConfirmAction = () => {
        if (action === "delete") {
            setTimeout(
                () => sendRequest("api/v1/library/mst/entity/commune/" + communeId, "deActive", "delete", {}),
                1000
            );
        } else if (action === "active") {
            setTimeout(
                () =>
                    sendRequest(
                        "api/v1/library/mst/entity/commune/" + communeId + "/activate",
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

    let bcLabel = t("masters:commune.details.breadCrumbs.sub.edit");
    if (viewType) {
        switch (viewType) {
            case "view":
                bcLabel = t("masters:commune.details.breadCrumbs.sub.view");
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
                bcLabel = t("masters:commune.details.breadCrumbs.sub.edit");
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
                bcLabel = t("masters:commune.details.breadCrumbs.sub.new");
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
                    { name: t("masters:commune.details.breadCrumbs.main"), path: "/master/commune/list" },
                    { name: bcLabel },
                ]}
                title={bcLabel}
                formButtons={formButtons}
                initialValues={{ ...inputData }}
                values={{ ...inputData }}
                onSubmit={(values, actions) => handleSubmit(values, actions)}
                onValidate={handleValidate}
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
                                    <CommuneDetails
                                        inputData={inputData}
                                        handleInputChange={handleInputChange}
                                        viewType={viewType}
                                        isSubmitting={loading}
                                        errors={{ ...props.errors, ...errors }}
                                        locale={t}
                                    />
                                )}
                                {tabIndex === 1 && (
                                    <C1Propertiestab
                                        dtCreated={inputData.comDtCreate}
                                        usrCreated={inputData.comUidLupd}
                                        dtLupd={inputData.comDtLupd}
                                        usrLupd={inputData.comUidLupd}
                                    />
                                )}
                                {tabIndex === 2 && <C1AuditTab filterId={communeId} />}
                            </Paper>
                        </Grid>
                    </Grid>
                )}
            </C1FormDetailsPanel>
{/* 
           <ConfirmationDialog
                open={openPopupAction}
                onConfirmDialogClose={() => setOpenPopupAction(false)}
                text={t("common:confirmMsgs.confirm.content")}
                title={t("common:confirmMsgs.confirm.title")}
                onYesClick={(e) => handleConfirmAction(e)}
            /> */}
        </React.Fragment>
    );
};

export default withErrorHandler(CommuneFormDetails);
