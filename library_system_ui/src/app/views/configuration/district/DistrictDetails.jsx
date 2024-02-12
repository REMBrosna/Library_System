import React from "react";
import Grid from "@material-ui/core/Grid";
import C1InputField from "app/c1component/C1InputField";
import C1TabContainer from "app/c1component/C1TabContainer";
import { isEditable } from "app/c1utils/utility";
import { useStyles } from "app/c1utils/styles";
import C1SelectAutoCompleteField from "../../../c1component/C1SelectAutoCompleteField";
import {PROVINCE_URL} from "../../../c1utils/const";

const DistrictDetails = ({ inputData, handleInputChange, viewType, isSubmitting, errors, locale }) => {
    let isDisabled = isEditable(viewType, isSubmitting);
    const classes = useStyles();

    return (
        <React.Fragment>
            <C1TabContainer>
                <Grid item lg={4} md={6} xs={12}>
                    <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                        <Grid item xs={12}>
                            {/*<C1SelectAutoCompleteField*/}
                            {/*    name="code"*/}
                            {/*    isServer={true}*/}
                            {/*    options={{*/}
                            {/*        url: PROVINCE_URL,*/}
                            {/*        id: 'code',*/}
                            {/*        desc: 'desc'*/}
                            {/*    }}*/}
                            {/*    label="Province"*/}
                            {/*    error={!(errors && errors['pediVpGeneralDetails.bokId'] === undefined)}*/}
                            {/*    helperText={(errors && errors['pediVpGeneralDetails.bokId']) || ''}*/}
                            {/*    value={inputData?.bokId || ''}*/}
                            {/*    // onChange={(e, name, value, reason) => handleSelectAutoCompleteChanged(e, name, value, reason, "pediVpGeneralDetails")}*/}
                            {/*/>*/}
                            <C1InputField
                                label="District ID"
                                name="code"
                                required
                                disabled={viewType === "edit" || viewType === "view" ? true : false}
                                onChange={handleInputChange}
                                value={inputData.code}
                                error={errors && errors.code ? true : false}
                                helperText={errors && errors.code ? errors.code : null}
                                inputProps={{ maxLength: 35 }}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <C1InputField
                                required
                                disabled={isDisabled}
                                label="Description"
                                name="desc"
                                onChange={handleInputChange}
                                value={inputData.desc}
                                error={errors && errors.desc ? true : false}
                                helperText={errors && errors.desc ? errors.desc : null}
                            />
                        </Grid>
                    </Grid>
                </Grid>
                <Grid item lg={4} md={6} xs={12}>
                    <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                        <Grid item xs={12}>
                            <C1InputField
                                required
                                disabled={isDisabled}
                                label="Description Other"
                                name="descOth"
                                onChange={handleInputChange}
                                value={inputData.descOth}
                                error={errors && errors.descOth ? true : false}
                                helperText={errors && errors.descOth ? errors.descOth : null}
                            />
                        </Grid>
                    </Grid>
                </Grid>
            </C1TabContainer>
        </React.Fragment>
    );
};

export default DistrictDetails;
