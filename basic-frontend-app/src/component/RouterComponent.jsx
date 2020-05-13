import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import ListUserComponent from "./user/ListUserComponent";
import AddUserComponent from "./user/AddUserComponent";
import EditUserComponent from "./user/EditUserComponent";
import React from "react";
import LoginComponent from "./user/LoginComponent";
import {PrivateRoute} from "./infrastucture/PrivateRoute";
import DashBoardComponent from "./DashBoard";
import ListFakturaComponent from "./faktura/ListFakturaComponent";
import ListOdberatelComponent from "./Odberatele/ListOdberatelComponent";

const AppRouter = () => {
    return(
            <Router>
                    <Switch>
                        <PrivateRoute exact path="/" component={DashBoardComponent} />
                        <Route path="/login"  component={LoginComponent} />
                        <PrivateRoute path="/list-user" component={ListUserComponent} />
                        <PrivateRoute path="/list-invoice" component={ListFakturaComponent} />
                        <PrivateRoute path="/list-subscriber" component={ListOdberatelComponent} />
                        <PrivateRoute path="/add-user" component={AddUserComponent} />
                        <PrivateRoute path="/edit-user/:id" component={EditUserComponent} />
                    </Switch>
            </Router>
    )
}

export default AppRouter;
