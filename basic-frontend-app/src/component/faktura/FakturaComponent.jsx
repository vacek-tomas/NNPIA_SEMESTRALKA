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
import {PolozkaFakturyComponent} from "./PolozkaFakturyComponent";
import PropTypes from 'prop-types';


class FakturaComponent extends Component{
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
            defaultOdberatel: {
                firma: '',
                id: undefined
            },
            isLoading: true
        }
    }

    componentDidMount() {
        if(this.props.mode !== "Create") {
            FakturaService.fetchFakturaById(this.props.match.params.id)
            .then(res => {this.setState({...res.data.result})})
            .then(() => {OdberatelService.fetchOdberatelById(this.state.odberatelId)
                .then(res => this.setState({defaultOdberatel: {firma: res.data.result.firma + " " + res.data.result.mesto, id: this.state.odberatelId}, isLoading: false}))});
        }
    }

    onSubmit = (e) => {
        e.preventDefault();
        this.props.onSubmit(e, this.state)
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

    isViewMode = () => {
        return this.props.mode === "View";
    }

    render() {
        return(
            <Fragment>
                <Helmet>
                    <title>{this.renderTitle()}</title>
                </Helmet>
                <NavBar/>
                {!this.state.isLoading &&
                <Container>
                    <Typography variant="h4" style={style}>{this.renderTitle()}</Typography>
                    <form style={formContainer}>

                        <TextField label="EVIDENČNÍ ČÍSLO" type="text" fullWidth margin="normal" name="evidencniCislo" value={this.state.evidencniCislo} onChange={this.onChange} disabled={this.isViewMode()}/>

                        <TextField label="VARIABILNÍ SYMBOL" type="text" fullWidth margin="normal" name="variabilniSymbol" value={this.state.variabilniSymbol} onChange={this.onChange} disabled={this.isViewMode()}/>
                        {this.renderAutoComplete()}
                        <TextField label="DATUM VYSTAVENÍ" type="date" fullWidth margin="normal" name="datumVystaveni" value={this.state.datumVystaveni}  onChange={this.onChange} InputLabelProps={{shrink: true}} disabled={this.isViewMode()}/>

                        <TextField label="DATUM UZP" type="date" fullWidth margin="normal" name="datumUzp" value={this.state.datumUzp} onChange={this.onChange} InputLabelProps={{shrink: true}} disabled={this.isViewMode()}/>

                        <TextField label="DATUM SPLATNOSTI" type="date" fullWidth margin="normal" name="datumSplatnosti" value={this.state.datumSplatnosti} onChange={this.onChange} InputLabelProps={{shrink: true}} disabled={this.isViewMode()}/>
                        {this.state.polozkyFaktury.map((row, index) => (
                            <PolozkaFakturyComponent key={"item-"+index} item={row} index={index} onChangeItem = {this.onChangeItem} deleteRow = {this.deleteRow} disabled={this.isViewMode()}/>
                        ))}
                        {this.renderButtons()}
                    </form>
                </Container>
                }
            </Fragment>
        );
    }

    renderTitle() {
        switch (this.props.mode) {
            case "Create":
                return ("Nová faktura")
            case "Edit":
                return ("Úprava faktury")
            case "View":
                return ("Náhled faktury")
        }
    }

    renderButtons() {
        if(this.props.mode === "View")
            return(
                <Fragment>
                <Button variant="contained" color="primary" onClick={this.onSubmit}>Zpět</Button>
                </Fragment>
            )
        else
            return(
                <Fragment>
                    <Button variant="contained" color="secondary" onClick={this.addRow} >Přidat řádek</Button>&nbsp;
                    <Button variant="contained" color="primary" onClick={this.onSubmit}>{this.props.mode === "Create" ? "Vytvořit" : "Uložit změny"}</Button>
                </Fragment>
            )
    }

    renderAutoComplete() {
        if(this.props.mode === "Create")
            return(
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
        />)
        else
            return(
            <Autocomplete fullWidth margin="normal"
                          size="medium"
                          options={this.state.odberatele}
                          defaultValue={this.state.defaultOdberatel}
                          getOptionLabel={(option) => option.firma}
                          onChange={(event, reason) => {this.onSelectAutocomplete(reason.id)}}
                          onInputChange={(event, newInputValue) => {
                              this.onInputChangeAutocomplete(newInputValue);
                          }}
                          renderInput={(params) => (
                              <TextField {...params} variant="standard" label="ODBĚRATEL" placeholder="Odběratel" InputLabelProps={{shrink: true}} />
                          )}
                          disabled={this.isViewMode()}
            />)
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

export default FakturaComponent;

FakturaComponent.propTypes = {
    mode: PropTypes.oneOf(["View", "Edit", "Create"]),
    onSubmit: PropTypes.func
};

