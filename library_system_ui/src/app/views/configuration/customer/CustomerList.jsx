import React, { useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import useHttp from "app/c1hooks/http";
import C1DataTable from "app/c1component/C1DataTable";
import C1ListPanel from "app/c1component/C1ListPanel";
import C1DataTableActions from "app/c1component/C1DataTableActions";
import {getActiveMode, getDeActiveMode, getStatusDesc} from "app/c1utils/statusUtils";
import ConfirmationDialog from "../../../../matx/components/ConfirmationDialog";

const CustomerList = () => {
    const { t } = useTranslation(["masters", "common"]);
    const { isLoading, res, error, urlId, sendRequest } = useHttp();
    const [isRefresh, setRefresh] = useState(false);
    const [loading, setLoading] = useState(false);
    const [openPopupAction, setOpenPopupAction] = useState(false);
    const[action, setAction] = useState("");
    const [id, setId] = useState("");

    useEffect(() => {
        if (!isLoading && !error && res) {
            if (urlId==='getForActive'){
                sendRequest("api/v1/library/mst/entity/customer/"+ res.data.data.cusId +"/activate", "activate", "PUT", res.data.data);
            }
            if (urlId==='activate' || urlId==='deActive'){
                setRefresh(true);
                setLoading(false);
            }
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [isLoading, res, error, urlId]);

    const columns = [
        {
            name: "cusId",
            label: "ID",
        },
        {
            name: "customerName",
            label: "Customer Name",
            options: {
                filter: true,
            },
        },
        {
            name: "customerType",
            label: "Type",
            options: {
                filter: true,
            },
        },
        {
            name: "gender",
            label: "Gender",
            options: {
                filter: true,
            },
        },
        {
            name: "dob",
            label: "Date Of Birth",
            options: {
                filter: true,
            },
        },
        {
            name: "phoneNumber",
            label: "Phone NUmber",
            options: {
                filter: true,
            },
        },
        {
            name: "identityCardNo",
            label: "Identity CardNo",
            options: {
                filter: true,
            },
        },
        {
            name: "recStatus",
            label: "Status",
            options: {
                filter: true,
            },
        },
        {
            name: "action",
            label: " ",
            options: {
                filter: false,
                display: true,
                viewColumns: false,
                customBodyRender: (value, tableMeta, updateValue) => {
                    return (
                        <C1DataTableActions
                            editPath={getDeActiveMode(tableMeta.rowData[7]) ? "/master/customer/edit/" + tableMeta.rowData[0]: null}
                            viewPath={"/master/customer/view/" + tableMeta.rowData[0]}
                            deActiveEventHandler={getDeActiveMode(tableMeta.rowData[7]) ? () => handleDeActiveHandler(tableMeta.rowData[0]): null}
                            activeEventHandler={getActiveMode(tableMeta.rowData[7]) ? () => handleActiveHandler(tableMeta.rowData[0]): null}
                        />
                    );
                },
            },
        },
    ];

    const handleDeActiveHandler = (id) => {
        setOpenPopupAction(true);
        setAction("delete")
        setId(id);
    }

    const handleActiveHandler = (id) => {
        setOpenPopupAction(true);
        setAction("active")
        setId(id);
    }

    const handleConfirmAction = () => {
        setLoading(true);
        setRefresh(false);

        if(action === "delete") {
            sendRequest("api/v1/library/mst/entity/customer/"+ id, "deActive", "delete", {});
        }else if(action === "active") {
            sendRequest("api/v1/library/mst/entity/customer/" + id, "getForActive", "get", {})
        }
        setOpenPopupAction(false);
    }

    return (
        <div>
            <C1ListPanel routeSegments={[{ name: t("List Customer") }]}>
                <C1DataTable
                    url={'api/v1/library/mst/entity/customer'}
                    columns={columns}
                    title={"Customer"}
                    defaultOrder="cusId"
                    isServer={true}
                    isRefresh={isRefresh}
                    showAdd={{
                        path: "/master/customer/new",
                    }}
                />
            </C1ListPanel>

            <ConfirmationDialog open={openPopupAction}
                                onConfirmDialogClose={() => setOpenPopupAction(false)}
                                text={t("common:confirmMsgs.confirm.content")}
                                title={t("common:confirmMsgs.confirm.title")}
                                onYesClick={(e) => handleConfirmAction(e)} />
        </div>
    );
};

export default CustomerList;
