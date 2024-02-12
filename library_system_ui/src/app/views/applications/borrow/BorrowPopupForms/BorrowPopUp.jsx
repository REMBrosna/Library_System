import { Button, Grid } from "@material-ui/core";
import React from "react";
import { useTranslation } from "react-i18next";
import C1InputField from "app/c1component/C1InputField";
import C1SelectAutoCompleteField from "../../../../c1component/C1SelectAutoCompleteField";
import {BOOKS_URL} from "../../../../c1utils/const";

const BorrowPopUp = ({
       inputData,
       errors,
       view,
       handlePopupInputChange,
       handleSelectAutoCompleteChanged,
       handleBtnAddClick,
}) => {
    const { t } = useTranslation(["applications", "buttons", "declarations"]);
    return (
        <React.Fragment>
            <Grid container spacing={3}>
                <Grid container item spacing={3}>
                    <Grid container item xs={12} sm={12} direction="column">
                        <C1SelectAutoCompleteField
                            name="book.bokId"
                            isServer={true}
                            isShowCode={false}
                            options={{
                                url: BOOKS_URL,
                                id: 'bokId',
                                desc: 'bokTitle'
                            }}
                            label="Book"
                            value={inputData?.book?.bokId || ''}
                            onChange={handleSelectAutoCompleteChanged}
                        />
                        <C1InputField
                            label="Quality"
                            name="itmQty"
                            type="number"
                            disabled={view}
                            isShowCode={false}
                            value={inputData?.itmQty}
                            onChange={handlePopupInputChange}
                            error={errors && errors?.itmQty ? true : false}
                            helperText={errors && errors?.itmQty ? errors?.itmQty : null}
                        />

                    </Grid>
                </Grid>
                <Grid container item xs={12} sm={12}>
                    <Grid container alignItems="flex-end" spacing={1} direction="row" justify="flex-end">
                        <Grid item lg={3} md={3} xs={3}>
                            {!view && (
                                <Button
                                    style={
                                        {
                                        borderRadius: 35,
                                        backgroundColor: "#182C61",
                                        padding: "10px 10px",
                                        fontSize: "18px",
                                    }
                                    }
                                    variant="contained"
                                    color="primary"
                                    fullWidth
                                    onClick={(e) => handleBtnAddClick(e)}
                                >
                                    {inputData?.bokId === undefined ? t("buttons:add") : t("buttons:update")}
                                </Button>
                            )}
                        </Grid>
                    </Grid>
                </Grid>
            </Grid>
        </React.Fragment>
    );
};
export default BorrowPopUp;
