import React, { Component } from 'react'
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import NavBar from "../Navbar";
import {Helmet} from "react-helmet";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import CreateIcon from "@material-ui/icons/Create";
import DeleteIcon from "@material-ui/icons/Delete";
import Button from "@material-ui/core/Button";
import Pagination from "@material-ui/lab/Pagination";
import FakturaService from "../../service/FakturaService";
import {GetTime} from "../../service/TimeService";
import {toast, ToastContainer} from "react-toastify";
import Loader from "react-loader-spinner";
import SortingSelect from "../infrastucture/SortingSelect";
import 'react-toastify/dist/ReactToastify.css';

class ListFakturaComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            fakturyPaging: {
                faktury: [],
                totalCount: 0,
            },
            isLoading: true,
            pageNo: 0,
            pageSize: 5,
            sortAsc: true,
            sortBy: 'id',
        }
    }

    componentDidMount() {
        const {pageNo, pageSize, sortAsc, sortBy} = this.state
        this.fetchFakturaList(pageNo, pageSize, sortAsc, sortBy);
    }

    fetchFakturaList = (pageNo, pageSize, sortAsc, sortBy) => {
        FakturaService.fetchFaktury(pageNo, pageSize, sortAsc, sortBy)
            .then((res) => {
                this.setState({fakturyPaging: res.data.result, isLoading: false})
            });
    }

    deleteOdberatel = (id) => {
        FakturaService.deleteFaktura(id)
            .then(res => {
                toast(res.data.message);
                this.setState(prevState =>
                    ({...prevState,
                        fakturyPaging:
                            {
                                ...prevState.fakturyPaging,
                                totalCount: prevState.fakturyPaging.totalCount - 1,
                                faktury: this.state.odberatelePaging.odberatele.filter(odberatel => odberatel.id !== id)
                            }
                    })
                );
            })
    }

    editOdberatel = (id) => {
        this.props.history.push('/edit-subscriber/' + id);
    }

    addFaktura = () => {
        this.props.history.push('/add-invoice');
    }

    changePage = (event, value) => {
        const {pageSize, sortAsc, sortBy} = this.state
        this.setState({pageNo: value - 1});
        this.fetchFakturaList(value - 1, pageSize, sortAsc, sortBy);
    }

    changeSortBy = (event) => {
        const {pageNo, pageSize, sortAsc} = this.state
        this.setState({sortBy: event.target.value});
        this.fetchFakturaList(pageNo, pageSize, sortAsc, event.target.value);
    }
    changeSortAsc = (event) => {
        const {pageNo, pageSize, sortBy} = this.state
        this.setState({sortAsc: event.target.value});
        this.fetchFakturaList(pageNo, pageSize, event.target.value, sortBy);
    }

    render() {
        const order = [
            {
                name: "Faktury",
                value: this.state.sortBy,
                handleChange: this.changeSortBy,
                items:
                    [
                        {value: "id", text: "None"},
                        {value: "evidencniCislo", text: "Ev. číslo"},
                        {value: "variabilniSymbol", text: "Var. symbol"},
                        {value: "odberatelFirma", text: "Odběratel"},
                        {value: "datumVystaveni", text: "Datum vystavení"},
                        {value: "datumSplatnosti", text: "Datum splatnosti"},
                        {value: "datumUzp", text: "Datum UZP"},
                        {value: "cenaCelkem", text: "Částka"}
                    ]
            },
            {
                name: "Vzestupně/Sestupně",
                value: this.state.sortAsc,
                handleChange: this.changeSortAsc,
                items:
                    [
                        {value: "true", text: "Vzestupně"},
                        {value: "false", text: "Sestupně"},
                    ]
            },

        ]
        return (
            <React.Fragment>
                <ToastContainer/>
                <Helmet>
                    <title>Seznam faktur</title>
                </Helmet>
                <NavBar/>
                <Container>
                    <Typography variant="h4" style={style}>Seznam faktur</Typography>
                    <Loader style={style} type="Grid" color="blue" visible={this.state.isLoading}/>
                    {!this.state.isLoading &&
                    <React.Fragment>
                        <SortingSelect order={order}/>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell align="left">Ev. číslo</TableCell>
                                    <TableCell align="left">Var. symbol</TableCell>
                                    <TableCell align="left">Odběratel</TableCell>
                                    <TableCell align="left">Datum vystavení</TableCell>
                                    <TableCell align="left">Datum splatnosti</TableCell>
                                    <TableCell align="left">Datum UZP</TableCell>
                                    <TableCell align="left">Částka</TableCell>
                                    <TableCell align="left">Akce</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {this.state.fakturyPaging.faktury.map(row => (
                                    <TableRow key={row.id}>
                                        <TableCell align="left">{row.evidencniCislo}</TableCell>
                                        <TableCell align="left">{row.variabilniSymbol}</TableCell>
                                        <TableCell align="left">{row.odberatelFirma}</TableCell>
                                        <TableCell align="left">{GetTime(row.datumVystaveni)}</TableCell>
                                        <TableCell align="left">{GetTime(row.datumSplatnosti)}</TableCell>
                                        <TableCell align="left">{GetTime(row.datumUzp)}</TableCell>
                                        <TableCell align="left">{row.cenaCelkem.toFixed(2)} Kč</TableCell>
                                        <TableCell align="left"><CreateIcon cursor='pointer' onClick={() => this.editOdberatel(row.id)} />&nbsp;<DeleteIcon cursor='pointer' onClick={() => this.deleteOdberatel(row.id)}/></TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                        <Pagination style={paginationStyle} count={Math.ceil(this.state.fakturyPaging.totalCount / this.state.pageSize)} page={this.state.pageNo + 1} onChange={this.changePage} />
                        <br/>
                        <Button variant="contained" color="primary" onClick={this.addFaktura}>
                            Nová Faktura
                        </Button>
                    </React.Fragment>
                    }
                </Container>
            </React.Fragment>
        );
    }

}
const style = {
    display: 'flex',
    justifyContent: 'center'
}
const paginationStyle = {
    display: 'flex',
    justifyContent: 'flex-end'
}
export default ListFakturaComponent;
