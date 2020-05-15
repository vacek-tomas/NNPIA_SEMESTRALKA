import React, { Component, Fragment } from 'react'
import OdberatelService from "../../service/OdberatelService";
import FakturaService from "../../service/FakturaService";
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import NavBar from "../Navbar";
import {Helmet} from "react-helmet";
import {toast} from "react-toastify";
import Autocomplete from '@material-ui/lab/Autocomplete';
import Grid from '@material-ui/core/Grid';
import DeleteIcon from "@material-ui/icons/Delete";
import {SetTime} from "../../service/TimeService";

class AddFakturaComponent extends Component{

    constructor(props){
        super(props);
        this.state ={
            evidencniCislo: '',
            variabilniSymbol: '',
            datumVystaveni: '',
            datumUzp: '',
            datumSplatnosti: '',
            odberatelId: undefined,
            polozkyFaktury: [{
                id: 0,
                popis: '',
                cenaZaJednotku: undefined,
                jednotka: '',
                mnozstvi: undefined,
                cenaCelkem: undefined

            }],
            odberatele: [],
        }
    }

    componentDidMount() {
    }

    saveFaktura = (e) => {
        e.preventDefault();

        let faktura = {...this.state};
        faktura.datumSplatnosti = SetTime(faktura.datumSplatnosti);
        faktura.datumUzp = SetTime(faktura.datumUzp);
        faktura.datumVystaveni = SetTime(faktura.datumVystaveni);
        FakturaService.addFaktura(faktura)
            .then(res => {
                toast(res.data.message);
                this.props.history.push('/list-invoice');
            });
    }

    onChange = (e) => this.setState({ [e.target.name]: e.target.value });
    onChangeItem = (e, index) => {

        let polozkyFaktury = [...this.state.polozkyFaktury];
        polozkyFaktury[index][e.target.name] = e.target.value;
        if(polozkyFaktury[index].mnozstvi !== undefined &&
            polozkyFaktury[index].cenaZaJednotku !== undefined &&
            polozkyFaktury[index].mnozstvi !== "" &&
            polozkyFaktury[index].cenaZaJednotku !== ""){

            polozkyFaktury[index].cenaCelkem = polozkyFaktury[index].cenaZaJednotku * polozkyFaktury[index].mnozstvi;
        }

        this.setState({
            polozkyFaktury: polozkyFaktury
        });
    }

    onInputChangeAutocomplete = (firma) => {
        OdberatelService.fetchOdberatelByFirma(firma).then(res => {
            this.setState({odberatele: res.data.result});
        })
    }
    onSelectAutocomplete = (id) => {
        this.setState({odberatelId: id});
    }

    deleteRow = (index)  => {
        this.setState({polozkyFaktury: [...this.state.polozkyFaktury.filter((value, i) => i !== index)]})
    }

    addRow = () => {
        this.setState({polozkyFaktury: [...this.state.polozkyFaktury, {
                id: 0,
                popis: '',
                cenaZaJednotku: undefined,
                jednotka: '',
                mnozstvi: undefined,
                cenaCelkem: undefined

            }]});
    }

    render() {
        return(
            <Fragment>
                <Helmet>
                    <title>Tvorba faktury</title>
                </Helmet>
                <NavBar/>
                <Container>
                    <Typography variant="h4" style={style}>Nová faktura</Typography>
                    <form style={formContainer}>

                        <TextField label="EVIDENČNÍ ČÍSLO" type="text" fullWidth margin="normal" name="evidencniCislo" value={this.state.evidencniCislo} onChange={this.onChange}/>

                        <TextField label="VARIABILNÍ SYMBOL" type="text" fullWidth margin="normal" name="variabilniSymbol" value={this.state.variabilniSymbol} onChange={this.onChange}/>

                        <Autocomplete fullWidth margin="normal"
                                      size="medium"
                            options={this.state.odberatele}
                            getOptionLabel={(option) => option.firma}
                                      onChange={(event, reason) => {this.onSelectAutocomplete(reason.id)}}
                              onInputChange={(event, newInputValue) => {
                                  this.onInputChangeAutocomplete(newInputValue);
                              }}
                            renderInput={(params) => (
                                <TextField {...params} variant="standard" label="ODBĚRATEL" placeholder="Odběratel" InputLabelProps={{shrink: true}} />
                            )}
                        />

                        <TextField label="DATUM VYSTAVENÍ" type="date" fullWidth margin="normal" name="datumVystaveni" value={this.state.datumVystaveni} onChange={this.onChange} InputLabelProps={{shrink: true}}/>

                        <TextField label="DATUM UZP" type="date" fullWidth margin="normal" name="datumUzp" value={this.state.datumUzp} onChange={this.onChange} InputLabelProps={{shrink: true}}/>

                        <TextField label="DATUM SPLATNOSTI" type="date" fullWidth margin="normal" name="datumSplatnosti" value={this.state.datumSplatnosti} onChange={this.onChange} InputLabelProps={{shrink: true}}/>
                        {this.state.polozkyFaktury.map((row, index) => (
                            <Grid key={"item"+index} container spacing={2}>
                                <Grid container item sm={2} >
                                    <TextField label="POPIS" type="text" fullWidth margin="normal" name="popis" value={row.popis} onChange={e => this.onChangeItem(e, index)}/>
                                </Grid>
                                <Grid container item sm={3} >
                                    <TextField label="CENA ZA JEDNOTKU" type="number" fullWidth margin="normal" name="cenaZaJednotku" value={row.cenaZaJednotku} onChange={e => this.onChangeItem(e, index)}/>
                                </Grid>
                                <Grid container item sm={2}>
                                    <TextField label="JEDNOTKA" type="text" fullWidth margin="normal" name="jednotka" value={row.jednotka} onChange={e => this.onChangeItem(e, index)}/>
                                </Grid>
                                <Grid container item sm={2}>
                                    <TextField label="MNOŽSTVÍ" type="number" fullWidth margin="normal" name="mnozstvi" value={row.mnozstvi} onChange={e => this.onChangeItem(e, index)}/>
                                </Grid>
                                <Grid container item sm={2}>
                                    <TextField ref={"cenaCelkem"+row} label="CENA CELKEM" type="number" fullWidth margin="normal" name="cenaCelkem" value={row.cenaCelkem} onChange={e => this.onChangeItem(e, index)}/>
                                </Grid>
                                <Grid container item sm={1} alignContent={"flex-end"} >
                                    <DeleteIcon style={deleteButtonStyle} cursor='pointer' onClick={() => this.deleteRow(index)}/>
                                </Grid>
                            </Grid>
                        ))}
                        <Button variant="contained" color="secondary" onClick={this.addRow} >Přidat řádek</Button>&nbsp;
                        <Button variant="contained" color="primary" onClick={this.saveFaktura}>Vytvořit</Button>
                    </form>
                </Container>
            </Fragment>
        );
    }
}
const formContainer = {
    display: 'flex',
    flexFlow: 'row wrap'
};

const style ={
    display: 'flex',
    justifyContent: 'center'

}
const deleteButtonStyle = {
    marginBottom: '5px'
}

export default AddFakturaComponent;
