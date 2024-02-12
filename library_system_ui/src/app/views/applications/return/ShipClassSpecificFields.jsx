import React from "react";
import {Grid, Box, MenuItem} from "@material-ui/core";

import { useStyles, titleTab } from 'app/c1utils/styles';
import C1InputField from "app/c1component/C1InputField";
import { getValue } from "../../../c1utils/utility";
import { useTranslation } from "react-i18next";
import C1TextArea from "app/c1component/C1TextArea";
import RadioGroup from "@material-ui/core/RadioGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Radio from "@material-ui/core/Radio";
import C1SelectField from "../../../c1component/C1SelectField";

const ShipClassSpecificFields = ({
    inputData,
    errors,
    handleInputChange,
    handleInputChangeSafety,
    viewType,
    isSubmitting
}) => {
    const { t } = useTranslation(["applications", "common", "vpsr"]);
    const title = titleTab();
    const classes = useStyles();
    let isDisabled = true;
    if (viewType === 'new')
        isDisabled = false;
    else if (viewType === 'edit')
        isDisabled = false;
    else if (viewType === 'view')
        isDisabled = true;

    if (isSubmitting)
        isDisabled = true;

    return (
        <div>
            <br />
            <Grid container alignItems="flex-start" spacing={3} className={classes.gridContainer}>
                <Grid item lg={4} md={4} xs={12} >
                    <Box className={classes.gridContainer} >
                        <Box className={title.root}>
                            {t("vpsr:app.vp.safetyAndStructure.scsf.general")}
                        </Box>
                    </Box>

                    <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                        <Grid item xs={12} >
                            <C1InputField
                                name="vpssContainer"
                                disabled={isDisabled || [
                                    'PASSENGER_CRUISE',
                                    'PASSENGER_CRUISE_NC',
                                    'TANKER',
                                    'TANKER_NC'
                                ].includes(inputData?.pediVpGeneralDetails?.vpgdShipType?.shipTypeId)}
                                required
                                type="number"
                                inputProps={{ min: 0 }}
                                label={t("vpsr:app.vp.safetyAndStructure.scsf.container")}
                                onChange={(e) => handleInputChangeSafety(e, 'pediVpSafetyStructure')}
                                error={errors && errors['pediVpSafetyStructure.vpssContainer'] === undefined ? false : true}
                                helperText={(errors && errors['pediVpSafetyStructure.vpssContainer']) || ''}
                                value={getValue(inputData?.pediVpSafetyStructure?.vpssContainer)}
                            />
                            <C1InputField
                                name="vpssSaftyEquipmentCount"
                                disabled={isDisabled}
                                type="number"
                                inputProps={{ min: 0 }}
                                label={t("vpsr:app.vp.safetyAndStructure.scsf.seCount")}
                                onChange={(e) => handleInputChangeSafety(e, 'pediVpSafetyStructure')}
                                error={errors && errors['pediVpSafetyStructure.vpssSaftyEquipmentCount'] === undefined ? false : true}
                                helperText={(errors && errors['pediVpSafetyStructure.vpssSaftyEquipmentCount']) || ''}
                                value={getValue(inputData?.pediVpSafetyStructure?.vpssSaftyEquipmentCount)}
                            />
                        </Grid>
                    </Grid>

                    <Box className={classes.gridContainer} >
                        <Box className={title.root}>
                            {t("vpsr:app.vp.safetyAndStructure.scsf.shipHatchCover")}
                        </Box>
                    </Box>

                    <div>
                        <RadioGroup
                            row
                            aria-label="yesOrno"
                            value={getValue(inputData?.pediVpSafetyStructure?.shipHatchCover)}
                            name="shipHatchCover">
                            <FormControlLabel
                                value="Y"
                                control={
                                    <Radio
                                        disabled={isDisabled}
                                        color="primary"
                                        onChange={(e) => handleInputChange(e, 'pediVpSafetyStructure')}
                                    />
                                }
                                label={t("vpsr:app.vp.safetyAndStructure.scsf.yes")}
                                labelPlacement="start"
                            />
                            <FormControlLabel
                                value="N"
                                control={
                                    <Radio
                                        disabled={isDisabled}
                                        color="primary"
                                        onChange={(e) => handleInputChange(e, 'pediVpSafetyStructure')}
                                    />
                                }
                                label={t("vpsr:app.vp.safetyAndStructure.scsf.no")}
                                labelPlacement="start"
                            />
                        </RadioGroup>
                    </div>

                    <div hidden={inputData.pediVpSafetyStructure.shipHatchCover==='N'}>
                        <Grid item xs={12} >
                            <C1SelectField
                                name="shipHatchCoverType"
                                disabled={isDisabled}
                                required
                                label={t("vpsr:app.vp.safetyAndStructure.scsf.shipHatchCoverType")}
                                onChange={(e) => handleInputChange(e, 'pediVpSafetyStructure')}
                                error={errors && errors['pediVpSafetyStructure.shipHatchCoverType'] === undefined ? false : true}
                                helperText={(errors && errors['pediVpSafetyStructure.shipHatchCoverType']) || ''}
                                value={getValue(inputData?.pediVpSafetyStructure?.shipHatchCoverType)}
                            >
                                <MenuItem value="Lifting" key="Lifting">Lifting </MenuItem>
                                <MenuItem value="Rolling" key="Rolling">Rolling</MenuItem>
                                <MenuItem value="Folding" key="Folding">Folding</MenuItem>
                                <MenuItem value="Sliding" key="Sliding">Sliding</MenuItem>
                                <MenuItem value="Roll Stowing" key="RollStowing">Roll Stowing</MenuItem>
                            </C1SelectField>
                        </Grid>
                    </div>


                </Grid>

                <Grid item lg={4} md={4} xs={12} >
                    <Box className={classes.gridContainer} >
                        <Box className={title.root}>
                            {t("vpsr:app.vp.safetyAndStructure.scsf.cargoCrane")}
                        </Box>
                    </Box>
                    <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                        <Grid item xs={12} >
                            <C1InputField
                                name="vpssCargoCranes"
                                disabled={isDisabled || [
                                    'PASSENGER_CRUISE',
                                    'PASSENGER_CRUISE_NC',
                                    'TANKER',
                                    'TANKER_NC'
                                ].includes(inputData?.pediVpGeneralDetails?.vpgdShipType?.shipTypeId)}
                                type="number"
                                label={t("vpsr:app.vp.safetyAndStructure.scsf.cargoCraneNumber")}
                                onChange={(e) => handleInputChangeSafety(e, 'pediVpSafetyStructure')}
                                error={errors && errors['pediVpSafetyStructure.vpssCargoCranes'] === undefined ? false : true}
                                helperText={(errors && errors['pediVpSafetyStructure.vpssCargoCranes']) || ''}
                                value={getValue(inputData?.pediVpSafetyStructure?.vpssCargoCranes)}
                            />
                            <C1TextArea
                                name="vpssCargoCraneDesc"
                                disabled={isDisabled}
                                type="input"
                                label={t("vpsr:app.vp.safetyAndStructure.scsf.cargoCraneDesc")}
                                onChange={(e) => handleInputChange(e, 'pediVpSafetyStructure')}
                                error={errors && errors['pediVpSafetyStructure.vpssCargoCraneDesc'] === undefined ? false : true}
                                helperText={(errors && errors['pediVpSafetyStructure.vpssCargoCraneDesc']) || ''}
                                value={getValue(inputData?.pediVpSafetyStructure?.vpssCargoCraneDesc)}
                                textLimit={256}
                            />

                        </Grid>
                    </Grid>
                </Grid>


                <Grid item lg={4} md={4} xs={12} >
                    <Box className={classes.gridContainer} >
                        <Box className={title.root}>
                            {t("vpsr:app.vp.safetyAndStructure.extraField.label")}
                        </Box>
                    </Box>
                    <Grid container alignItems="center" spacing={3} className={classes.gridContainer}>
                        <Grid item xs={12} >
                            <C1InputField
                                name="vpssReferSockets"
                                disabled={isDisabled}
                                label={t("vpsr:app.vp.safetyAndStructure.extraField.referSocket")}
                                onChange={(e) => handleInputChange(e, 'pediVpSafetyStructure')}
                                error={errors && errors['pediVpSafetyStructure.vpssReferSockets']===undefined ? false : true}
                                helperText={errors && errors['pediVpSafetyStructure.vpssReferSockets'] ||''}
                                value={getValue(inputData?.pediVpSafetyStructure?.vpssReferSockets)}
                            />

                        </Grid>

                        <Grid item xs={12} >
                            <C1InputField
                                name="vpssBollardPull"
                                disabled={isDisabled}
                                label={t("vpsr:app.vp.safetyAndStructure.extraField.bollardPull")}
                                onChange={(e) => handleInputChange(e, 'pediVpSafetyStructure')}
                                error={errors && errors['pediVpSafetyStructure.vpssBollardPull']===undefined ? false : true}
                                helperText={errors && errors['pediVpSafetyStructure.vpssBollardPull'] ||''}
                                value={getValue(inputData?.pediVpSafetyStructure?.vpssBollardPull)}
                            />
                        </Grid>
                    </Grid>
                </Grid>

            </Grid>
            <br />
        </div>
    )
};
export default ShipClassSpecificFields;