import React, { useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import useHttp from "app/c1hooks/http";
import C1DataTable from "app/c1component/C1DataTable";
import C1ListPanel from "app/c1component/C1ListPanel";
import C1DataTableActions from "app/c1component/C1DataTableActions";
import {getActiveMode, getDeActiveMode, getStatusDesc} from "app/c1utils/statusUtils";
import ConfirmationDialog from "../../../../matx/components/ConfirmationDialog";

const DistrictList = () => {
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
                sendRequest("api/v1/library/mst/entity/district/"+ res.data.data.code +"/activate", "activate", "PUT", res.data.data);
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
            name: "code",  
            label: "ID",
        },
        {
            name: "desc",
            label: "Description",
            options: {
                filter: true,
            },
        },
        {
            name: "descOth",
            label: "Description Other",
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
                            editPath={getDeActiveMode(tableMeta.rowData[3]) ? "/master/district/edit/" + tableMeta.rowData[0]: null}
                            viewPath={"/master/district/view/" + tableMeta.rowData[0]}
                            deActiveEventHandler={getDeActiveMode(tableMeta.rowData[3]) ? () => handleDeActiveHandler(tableMeta.rowData[0]): null}
                            activeEventHandler={getActiveMode(tableMeta.rowData[3]) ? () => handleActiveHandler(tableMeta.rowData[0]): null}
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
            sendRequest("api/v1/library/mst/entity/district/"+ id, "deActive", "delete", {});
        }else if(action === "active") {
            sendRequest("api/v1/library/mst/entity/district/" + id, "getForActive", "get", {})
        }
        setOpenPopupAction(false);
    }

    return (
        <div>
            <C1ListPanel routeSegments={[{ name: t("masters:district.list.routeSegment") }]}>
                <C1DataTable
                    url={'api/v1/library/mst/entity/district'}
                    columns={columns}
                    title={"District"}
                    defaultOrder="code"
                    isServer={true}
                    isRefresh={isRefresh}
                    showAdd={{
                        path: "/master/district/new",
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

export default DistrictList;
