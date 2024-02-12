import React, { useState, useEffect } from "react";
import { useTranslation } from "react-i18next";
import useHttp from "app/c1hooks/http";
import C1DataTable from "app/c1component/C1DataTable";
import C1ListPanel from "app/c1component/C1ListPanel";
import C1DataTableActions from "app/c1component/C1DataTableActions";
import {getActiveMode, getDeActiveMode, getStatusDesc} from "app/c1utils/statusUtils";
import ConfirmationDialog from "../../../../matx/components/ConfirmationDialog";

const BookList = () => {
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
                sendRequest("api/v1/library/mst/entity/book/"+ res.data.data.code +"/activate", "activate", "PUT", res.data.data);
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
            name: "bokId",
            label: "ID",
        },
        {
            name: "bokTitle",
            label: "Book Title",
            options: {
                filter: true,
            },
        },
        {
            name: "bokAuthor",
            label: "Author Name",
            options: {
                filter: true,
            },
        },
        {
            name: "bokPublicDate",
            label: "Public Year",
            options: {
                filter: true,
            },
        },
        {
            name: "bokQty",
            label: "Qty",
            options: {
                filter: true,
            },
        },
        {
            name:"bokUnitPrice",
            label: "Unit Price",
            options: {
                filter: true,
            },
        },
        {
            name: "bokRecStatus",
            label: "Status",
            options: {
                filter: true,
            },
        },
        {
            name: "bokBookStatus",
            label: "Book Status",
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
                            editPath={getDeActiveMode(tableMeta.rowData[6]) ? "/master/books/edit/" + tableMeta.rowData[0]: null}
                            viewPath={"/master/books/view/" + tableMeta.rowData[0]}
                            deActiveEventHandler={getDeActiveMode(tableMeta.rowData[6]) ? () => handleDeActiveHandler(tableMeta.rowData[0]): null}
                            activeEventHandler={getActiveMode(tableMeta.rowData[6]) ? () => handleActiveHandler(tableMeta.rowData[0]): null}
                        />
                    );
                },            },
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
            sendRequest("api/v1/library/mst/entity/book/"+ id, "deActive", "delete", {});
        }else if(action === "active") {
            sendRequest("api/v1/library/mst/entity/book/" + id, "getForActive", "get", {})
        }
        setOpenPopupAction(false);
    }

    return (
        <div>
            <C1ListPanel routeSegments={[{ name: t("masters:book.list.routeSegment") }]}>
                <C1DataTable
                    url={'api/v1/library/mst/entity/book'}
                    columns={columns}
                    title={"Books"}
                    defaultOrder="bokId"
                    isServer={true}
                    isRefresh={isRefresh}
                    showAdd={{
                        path: "/master/books/new",
                    }}
                />
            </C1ListPanel>

            <ConfirmationDialog open={openPopupAction}
                onConfirmDialogClose={() => setOpenPopupAction(false)}
                text={t("common:confirmMsgs.confirm.content")}
                title={t("common:confirmMsgs.confirm.title")}
                onYesClick={(e) => handleConfirmAction(e)}
            />
        </div>
    );
};

export default BookList;
