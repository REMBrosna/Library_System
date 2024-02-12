import React from "react";
import Grid from "@material-ui/core/Grid";
import C1InputField from "app/c1component/C1InputField";
import C1TabContainer from "app/c1component/C1TabContainer";
import { isEditable } from "app/c1utils/utility";
import { useStyles } from "app/c1utils/styles";
// import ConfirmationDialog from "../../../../../matx/components/ConfirmationDialog";

const DistrictDetails = ({ inputData, handleInputChange, viewType, isSubmitting, errors, locale }) => {
    let isDisabled = isEditable(viewType, isSubmitting);
    const classes = useStyles();

    return (
        <React.Fragment>
            <C1TabContainer>
                <Grid item lg={4} md={6} xs={12}>
                    <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                        <Grid item xs={12}>
                            <C1InputField
                                label="Commune ID"
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
                                label="Description in Khmer"
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
