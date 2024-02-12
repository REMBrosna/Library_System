import React, { useEffect, useState } from "react";

import { useTranslation } from "react-i18next"
import C1DataTable from 'app/c1component/C1DataTable';
import C1DataTableActions from 'app/c1component/C1DataTableActions';
import C1ListPanel from "app/c1component/C1ListPanel";
import useHttp from "app/c1hooks/http";
import ConfirmationDialog from "matx/components/ConfirmationDialog";
import history from "../../../../history";
import {formatDate} from "../../../c1utils/utility";

const BorrowProfileList = () => {

    const { t } = useTranslation(["applications", "common"]);
    const { isLoading, res, error, urlId, sendRequest } = useHttp();
    const [confirm, setConfirm] = useState({ borId: null });
    const [open, setOpen] = useState(false);
    const [dtRefresh, setDtRefresh] = useState(false);
    const [borReturn, setBorReturn] = useState();
    const [loading, setLoading] = useState(false);
    const [confirmAction, setConfirmAction] = useState("");
    const API = '/api/v1/application/return/';
    useEffect(() => {
        if (!isLoading && !error && res) {
            if (urlId === "delete") {
                setOpen(false);
                setDtRefresh(true);
                setLoading(false);
            }else if(urlId === "getReturn"){
                //console.log("res.data", res.data);
                if (res.data === ''){
                    console.log("res.data", res.data);
                    history.push("/return/applicationReturn/new/" + borReturn);
                } else {
                    history.push("/return/applicationReturn/edit/" + res.data?.tapplication?.appID);
                }
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
        if (confirm && !confirm.borId)
            return;
        setLoading(true);
        sendRequest("api/v1/library/application/bor" + confirm.borId, "delete", "delete", {})
    }
    const getReturnByBorrowId = (retID) => {
        setLoading(true);
        sendRequest(API + retID, "getReturn", "get", {})
        setBorReturn(retID);
    }

    const handleDeleteConfirm = (e, borId) => {
        setConfirmAction("delete");
        e.preventDefault();
        setConfirm({ ...confirm, borId: borId });
        setOpen(true);
    }

    const columns = [
        {
            name: "application.appID",  
            label: "Application Borrow",
        },
        {
            name: "borCustomer.username",
            label: "Customer Name",
            options: {
                filter: true,
            },
        },
        {
            name: "borTotalQty",
            label: "Borrow Qty",
            options: {
                filter: true,
            },
        },
        {
            name: "borTotalOwe",
            label: "Owe",
            options: {
                filter: true,
            },
        },
        {
            name: "borPenaltyAmount",
            label: "Penalty Amount",
            options: {
                filter: true,
            },
        },
        {
            name: "application.mstApplicationType.aptCode",
            label: "Application Type",
            options: {
                filter: false,
                display: false
            },
        },
        {
            name: "borDtBorrow",
            label: "Borrow Date",
            options: {
                filter: true,
                customBodyRender: (value) => {
                    return formatDate(value, true);
                }
            },
        },
        {
            name: "borDtReturn",
            label: "Return Date",
            options: {
                filter: true,
                customBodyRender: (value) => {
                    return formatDate(value, true);
                }
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
            name: "application.appRecStatus",
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
                    return <C1DataTableActions
                        returnHandler={tableMeta.rowData[8] === "RET" ? null : tableMeta.rowData[8] === "DRF" ? null : ()=>getReturnByBorrowId(tableMeta.rowData[0])}
                        editPath={tableMeta.rowData[8] === "RET" ? null : tableMeta.rowData[8] === "BOR" ? null : tableMeta.rowData[8] === "OWE" ? null : tableMeta.rowData[6] === "OVR" ? null : tableMeta.rowData[8] === "SUB" ? null : "/borrow/applicationBorrow/edit/" + tableMeta.rowData[0]}
                        viewPath={"/borrow/applicationBorrow/view/" + tableMeta.rowData[0]}
                    />
                },
            },
        },
    ];

    return (<React.Fragment>
        {confirm && confirm.borId &&
            <ConfirmationDialog
                title="Application Borrow List"
                open={open}
                text={t("common:confirmMsgs.confirm", { borId: confirm.borId, action: confirmAction })}
                onYesClick={() => handleConfirmAction()}
                onConfirmDialogClose={() => setOpen(false)}
            />
        }

        <C1ListPanel
            routeSegments={[
                { name: "Application Borrow" },
            ]}>
            <C1DataTable 
                url={'api/v1/library/application/bor'}
                columns={columns}
                title={"Application Borrow List"}
                defaultOrder="borDtReturn"
                isServer={true}
                isShowDownload={false}
                isShowPrint={false}
                isRowSelectable={false}
                isShowToolbar
                isRefresh={dtRefresh}
                /*filterBy={[
                    { attribute: "borDtReturn" }
                ]}
                defaultOrderDirection={"asc"}*/
                showAdd={{
                    path: "/borrow/applicationBorrow/new/0"
                }}
            />
        </C1ListPanel>
    </React.Fragment>
    );
};

export default BorrowProfileList;
