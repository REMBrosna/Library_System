import React, { useEffect, useState } from "react";

import { useTranslation } from "react-i18next"
import C1DataTable from 'app/c1component/C1DataTable';
import C1DataTableActions from 'app/c1component/C1DataTableActions';
import C1ListPanel from "app/c1component/C1ListPanel";
import useHttp from "app/c1hooks/http";
import ConfirmationDialog from "matx/components/ConfirmationDialog";
import {customFilterDateDisplay, formatDate} from "../../../c1utils/utility";

const ReturnProfileList = () => {

    const { t } = useTranslation(["applications", "common"]);
    const { isLoading, res, error, urlId, sendRequest } = useHttp();
    const [confirm, setConfirm] = useState({ retId: null });
    const [open, setOpen] = useState(false);
    const [dtRefresh, setDtRefresh] = useState(false);
    const [loading, setLoading] = useState(false);
    const [confirmAction, setConfirmAction] = useState("");


    useEffect(() => {
        if (!isLoading && !error && res) {
            if (urlId === "delete") {
                setOpen(false);
                setDtRefresh(true);
                setLoading(false);
            }
        }
    }, [isLoading, res, error, urlId]);

    const handleConfirmAction = (e) => {
        setDtRefresh(false);
        if (confirmAction === "delete") {
            handleDeleteHandler(e);
        }
    }

    const handleDeleteHandler = (e) => {
        if (confirm && !confirm.retId)
            return;
        setLoading(true);
        sendRequest("api/v1/library/application/ret" + confirm.retId, "delete", "delete", {})
    }

    const handleDeleteConfirm = (e, retId) => {
        setConfirmAction("delete");
        e.preventDefault();
        setConfirm({ ...confirm, retId: retId });
        setOpen(true);
    }

    const handlePreviewFormPdf = (e, appId) => {
        e.preventDefault();
        window.open(`${process.env.REACT_APP_CONTEXT_NAME}/form/preview/vc/` + appId, "_blank");
    };


    const columns = [
        {
            name: "retId",
            label: "RET ID",
            options: {
                filter: true,
                display:false,
            },
        },
        {
            name: "application.appID",
            label: "Application Return",
            options: {
                filter: true,
                display:true,
            },
        },
        {
            name: "borApplication.appID",
            label: "Application Borrow",
        },
        {
            name: "returnQty",
            label: "Return Qty",
            options: {
                filter: true,
            },
        },
        {
            name: "retBorrow",
            label: "Return Date",
            options: {
                filter: true,
                customBodyRender: (value) => {
                    return formatDate(value, true);
                }
            },
        },
        {
            name: "retPenaltyAmount",
            label: "Penalty Amount",
            options: {
                filter: true,
            },
        },
        {
            name: "application.mstApplicationStatus.apsCode",
            label: "Application Status",
            options: {
                filter: true,
            },
        },
        {
            name: "recStatus",
            label: "Status",
            options: {
                filter: true,
                display:false,
            },
        },
        {
            name: "action",
            label: " ",
            options: {
                filter: false,
                display: true,
                viewColumns: false,
                customBodyRender: (value, tableMeta) => {
                    console.log(tableMeta.rowData)
                    return <C1DataTableActions
                        editPath={tableMeta.rowData[6] === "RET" ? null : "/return/applicationReturn/edit/" + tableMeta.rowData[1]}
                        viewPath={"/return/applicationReturn/view/" + tableMeta.rowData[1]}
                        removeEventHandler={tableMeta.rowData[6] || tableMeta.rowData[7] === "I" ? null :
                            ((e) => handleDeleteConfirm(e, tableMeta.rowData[1]))}

                    />
                },
            },
        },
    ];

    return (<React.Fragment>
        {confirm && confirm.retId &&
            <ConfirmationDialog
                title="Application Return"
                open={open}
                text={t("Return", { retId: confirm.retId, action: confirmAction })}
                onYesClick={() => handleConfirmAction()}
                onConfirmDialogClose={() => setOpen(false)}
            />
        }

        <C1ListPanel
            routeSegments={[
                { name: "Application Return" },
            ]}>
            <C1DataTable 
                url={'api/v1/library/application/ret'}
                columns={columns}
                title={"Application Return"}
                defaultOrder="retId"
                isServer={true}
                isShowDownload={false}
                isShowPrint={false}
                isRowSelectable={false}
                isShowToolbar
                isRefresh={dtRefresh}
                filterBy={[
                    { attribute: "application.appID", value: "A" }
                ]}
            />
        </C1ListPanel>
    </React.Fragment>
    );
};

export default ReturnProfileList;
