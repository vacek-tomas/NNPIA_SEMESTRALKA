import React, { Component } from 'react'
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import NavBar from "../Navbar";
import {Helmet} from "react-helmet";

class ListOdberatelComponent extends Component {

    constructor(props) {
        super(props)
    }

    render() {
        return (
            <React.Fragment>
                <Helmet>
                    <title>Seznam odběratelů</title>
                </Helmet>
                <NavBar/>
                <Container>
                    <Typography variant="h4" style={style}>Seznam odběratelů</Typography>
                </Container>
            </React.Fragment>
        );
    }

}
const style = {
    display: 'flex',
    justifyContent: 'center'
}
export default ListOdberatelComponent;
