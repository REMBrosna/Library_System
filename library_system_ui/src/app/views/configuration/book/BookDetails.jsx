import React from "react";
import Grid from "@material-ui/core/Grid";
import C1InputField from "app/c1component/C1InputField";
import C1TabContainer from "app/c1component/C1TabContainer";
import { isEditable } from "app/c1utils/utility";
import { useStyles } from "app/c1utils/styles";

const BookDetails = ({
     inputData,
     handleInputChange,
     viewType,
     isSubmitting,
     errors,
     locale
}) => {
    let isDisabled = isEditable(viewType, isSubmitting);
    const classes = useStyles();
    return (
        <React.Fragment>
            <C1TabContainer>
                <Grid item lg={4} md={6} xs={12}>
                    <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                        <Grid item xs={12}>
                            <C1InputField
                                label="Book ID"
                                name="bokId"
                                required
                                disabled={viewType === "edit" || viewType === "view" ? true : false}
                                onChange={handleInputChange}
                                value={inputData.bokId}
                                error={errors && errors.bokId ? true : false}
                                helperText={errors && errors.bokId ? errors.bokId : null}
                                inputProps={{ maxLength: 35 }}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <C1InputField
                                required
                                disabled={isDisabled}
                                label="Author Name"
                                name="bokAuthor"
                                onChange={handleInputChange}
                                value={inputData.bokAuthor}
                                error={errors && errors.bokAuthor ? true : false}
                                helperText={errors && errors.bokAuthor ? errors.bokAuthor : null}
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
                                label="Book Title"
                                name="bokTitle"
                                onChange={handleInputChange}
                                value={inputData.bokTitle}
                                error={errors && errors.bokTitle ? true : false}
                                helperText={errors && errors.bokTitle ? errors.bokTitle : null}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <C1InputField
                                required
                                disabled={isDisabled}
                                label="Book Qty"
                                name="bokQty"
                                onChange={handleInputChange}
                                value={inputData.bokQty}
                                error={errors && errors.bokQty ? true : false}
                                helperText={errors && errors.bokQty ? errors.bokQty : null}
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
                                label="Public Year"
                                name="bokPublicDate"
                                onChange={handleInputChange}
                                value={inputData.bokPublicDate}
                                error={errors && errors.bokPublicDate ? true : false}
                                helperText={errors && errors.bokPublicDate ? errors.bokPublicDate : null}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <C1InputField
                                required
                                disabled={isDisabled}
                                label="Unit Price"
                                name="bokUnitPrice" 
                                onChange={handleInputChange}
                                value={inputData.bokUnitPrice}$
                                error={errors && errors.bokUnitPrice ? true : false}
                                helperText={errors && errors.bokUnitPrice ? errors.bokUnitPrice : null}
                            />
                        </Grid>
                    </Grid>
                </Grid>
            </C1TabContainer>
        </React.Fragment>
    );
};

export default BookDetails;
