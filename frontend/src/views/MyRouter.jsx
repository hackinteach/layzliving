import React, {Component} from 'react';
import * as api from '../config/api';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import {AuthProvider, AuthRoute} from 'react-router-auth-provider'
import {createMuiTheme, MuiThemeProvider} from 'material-ui';
import Dashboard from "./Dashboard/Dashboard";
import Login from './Authenticate/login';
import Register from './Authenticate/register';
import App from '../containers/App/App'

const theme = createMuiTheme();

function MyAuthProvider(props) {
    return (
        <AuthProvider
            whoami={api.whoami}
            logout={api.logout}
            {...props}
        />
    )
}

function MyAuthRoute(props){
    return (
        <AuthRoute
            loginRoute="/login"
            {...props}
        />
    )
}

class MyRouter extends Component {
    render() {
        return (
            <MuiThemeProvider theme={theme}>
                <Router>
                    <MyAuthProvider>
                        <Route exact path="/login" component={Login} />
                        <Route exact path="/" component={Login}/>
                        <Route exact path="/register" component={Register}/>
                        <MyAuthRoute exact path="/dashboard" component={App}/>
                    </MyAuthProvider>
                </Router>
            </MuiThemeProvider>
        );
    }
}

export default MyRouter;
