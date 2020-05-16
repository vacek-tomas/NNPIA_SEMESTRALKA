import Grid from "@material-ui/core/Grid";
import TextField from "@material-ui/core/TextField";
import DeleteIcon from "@material-ui/icons/Delete";
import React from "react";

export function PolozkaFakturyComponent(props) {
    return<Grid container spacing={2}>
            <Grid container item sm={2} >
                <TextField label="POPIS" type="text" fullWidth margin="normal" name="popis" value={props.item.popis} onChange={e => props.onChangeItem(e, props.index)}/>
            </Grid>
            <Grid container item sm={3} >
                <TextField label="CENA ZA JEDNOTKU" type="number" fullWidth margin="normal" name="cenaZaJednotku" value={props.item.cenaZaJednotku}  onChange={e => props.onChangeItem(e, props.index)} InputLabelProps={{shrink: props.item.cenaZaJednotku !== undefined && props.item.cenaZaJednotku !==''}}/>
            </Grid>
            <Grid container item sm={2}>
                <TextField label="JEDNOTKA" type="text" fullWidth margin="normal" name="jednotka" value={props.item.jednotka} onChange={e => props.onChangeItem(e, props.index)}/>
            </Grid>
            <Grid container item sm={2}>
                <TextField label="MNOŽSTVÍ" type="number" fullWidth margin="normal" name="mnozstvi" value={props.item.mnozstvi} onChange={e => props.onChangeItem(e, props.index)} InputLabelProps={{shrink: props.item.mnozstvi !== undefined && props.item.mnozstvi !==''}}/>
            </Grid>
            <Grid container item sm={2}>
                <TextField  label="CENA CELKEM" type="number" fullWidth margin="normal" name="cenaCelkem" value={props.item.cenaCelkem} onChange={e => props.onChangeItem(e, props.index)} InputLabelProps={{shrink: props.item.cenaCelkem !== undefined && props.item.cenaCelkem !==''}}/>
            </Grid>
            <Grid container item sm={1} alignContent={"flex-end"} >
                <DeleteIcon style={deleteButtonStyle} cursor='pointer' onClick={() => props.deleteRow(props.index)}/>
            </Grid>
        </Grid>
}
const deleteButtonStyle = {
    marginBottom: '5px'
}
