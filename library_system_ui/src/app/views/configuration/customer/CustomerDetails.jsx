import React from "react";
import Grid from "@material-ui/core/Grid";
import C1InputField from "app/c1component/C1InputField";
import C1TabContainer from "app/c1component/C1TabContainer";
import { isEditable } from "app/c1utils/utility";
import { useStyles } from "app/c1utils/styles";
import C1DateField from "../../../c1component/C1DateField";

const CustomerDetails = ({
 inputData,
 handleInputChange,
 viewType,
 isSubmitting,
 handleDateChange,
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
                                label="Customer Name"
                                name="customerName"
                                required
                                disabled={viewType === "edit" || viewType === "view" ? true : false}
                                onChange={handleInputChange}
                                value={inputData.customerName}
                                error={errors && errors.customerName ? true : false}
                                helperText={errors && errors.customerName ? errors.customerName : null}
                                inputProps={{ maxLength: 35 }}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <C1InputField
                                required
                                disabled={isDisabled}
                                label="Customer Type"
                                name="customerType"
                                onChange={handleInputChange}
                                value={inputData.customerType}
                                error={errors && errors.customerType ? true : false}
                                helperText={errors && errors.customerType ? errors.customerType : null}
                            />
                        </Grid>
                    </Grid>
                </Grid>
                <Grid item lg={4} md={6} xs={12}>
                    <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                        <Grid item xs={12}>
                            <C1DateField
                                label="Date of Birth"
                                name="dob"
                                type="date"
                                disabled={isDisabled}
                                required
                                onChange={handleDateChange}
                                value={inputData?.dob}
                                disablePast={false}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <C1InputField
                                required
                                disabled={isDisabled}
                                label="Identity CardNo"
                                name="identityCardNo"
                                onChange={handleInputChange}
                                value={inputData.identityCardNo}
                                error={errors && errors.identityCardNo ? true : false}
                                helperText={errors && errors.identityCardNo ? errors.identityCardNo : null}
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
                                label="Gender"
                                name="gender"
                                onChange={handleInputChange}
                                value={inputData.gender}
                                error={errors && errors.gender ? true : false}
                                helperText={errors && errors.gender ? errors.gender : null}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <C1InputField
                                required
                                disabled={isDisabled}
                                label="Phone Number"
                                name="phoneNumber"
                                onChange={handleInputChange}
                                value={inputData.phoneNumber}
                                error={errors && errors.phoneNumber ? true : false}
                                helperText={errors && errors.phoneNumber ? errors.phoneNumber : null}
                            />
                        </Grid>
                    </Grid>
                </Grid>
            </C1TabContainer>
        </React.Fragment>
    );
};

export default CustomerDetails;
