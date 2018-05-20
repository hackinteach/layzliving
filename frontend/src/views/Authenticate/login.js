import React from 'react'
import Button from 'material-ui/Button';
import Avatar from 'material-ui/Avatar';
import LockIcon from 'material-ui-icons/LockOutline';
import Card, {CardContent} from 'material-ui/Card';
import {withRouter} from 'react-router-dom'
import {withAuth} from "react-router-auth-provider";
import {login} from '../../config/api';
import {makeDialog, makeTextbox} from "../../components/my-component";
import {createMuiTheme, withStyles} from 'material-ui/styles';
import image from "../../assets/img/sandhouse.jpg"
import {MuiThemeProvider} from "material-ui";

const styles = theme => ({

    card: {
        minWidth: 300,
        paddingBottom: '1em',
    },

    title: {
        marginBottom: 16,
        fontSize: 14,
        color: theme.palette.text.secondary,
    },

    main: {
        display: 'flex',
        flexDirection: 'column',
        minHeight: '100%',
        alignItems: 'center',
        justifyContent: 'center',
        backgroundImage: `url(${image})`,
        backgroundAttachment: 'fixed',
        backgroundSize: 'cover',
    },

    avatar: {
        margin: '1em',
        color: '#fff',
        textAlign: 'center ',
        backgroundColor: '#BDBDBD',
        width: 60,
        height: 60,
    },

    avatarpos: {
        display: 'flex',
        justifyContent: 'center',
    },

    button: {
        margin: '0.4em',
        color: '#ffffff',
        width: 255,
    },

    buttonpos: {
        display: 'flex',
        alignItems: 'center',
        flexDirection: 'column',
    }
});


const Logo = withStyles(styles)(({classes}) => {
    return (<div className={classes.avatarpos}>
        <Avatar className={classes.avatar}>
            <LockIcon style={{fontSize: 40}}/>
        </Avatar>
    </div>)
})


const LoginButton = withStyles(styles)(({classes, handleLogin}) => {
    return(
        <Button color="primary"
                variant="raised"
                className={classes.button}
                onClick={handleLogin}>
                Login
        </Button>
    )
})

const CreateAccountButton = withStyles(styles)(({classes, handleRegisterClick}) => {
    return(
        <Button color="secondary"
                variant="raised"
                className={classes.button}
                onClick={handleRegisterClick}>
                Create New Account
        </Button>
    )
})


const theme = createMuiTheme({
    palette: {
        primary: {main: '#BDBDBD'},
        secondary: {main: '#212121'},
    },
});

const UsernameTextbox = makeTextbox('Username');
const PasswordTextbox = makeTextbox('Password', {password: true});
const ErrorDialog = makeDialog("Invalid username or password. Please try again!","Error");

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: '',
            err: false,
        };
    };

    componentWillMount () {
        const props = this.props;
        console.log("from login ",props);
        if(props.isLoggedIn){
            this.props.history.push('/dashboard')
        }
    }

    handleDialogClose = () => {
        this.setState({
            err: false,
        })
    };

    handleInputChange = (name) => (value) => {
        this.setState({[name]: value});
    };

    handleLogin = () => {
        const {username, password} = this.state;
        login(username, password)
            .then(({data}) => {
                console.log(data);
                this.props.onLoginSuccess(data,
                    () => this.props.history.push('/dashboard'))
            })
            .catch(({response: {data, status, message}}) => {
                console.log("err", data, status, message);
                if(status === 401){
                    this.setState({
                        err:true,
                    })
                    console.log("Login failed");
                }
            });
    };

    handleRegisterClick = () => {
        this.props.history.push('/register');
    };

    render() {

        const {classes} = this.props;
        const {username, password, err} = this.state;
        const hic = this.handleInputChange;
        return (
            <MuiThemeProvider theme={theme}>
                <div className={classes.main}>
                    <Card className={classes.card}>
                        <CardContent>
                            <Logo/>
                            <ErrorDialog err={err} onClick={this.handleDialogClose}/>
                            <form>
                                <UsernameTextbox val={username} onChange={hic('username')} fullWidth/>
                                <br/>
                                <PasswordTextbox val={password} onChange={hic('password')} fullWidth/>
                            </form>
                        </CardContent>

                        <div className={classes.buttonpos}>
                            <LoginButton handleLogin={this.handleLogin}/>
                            <CreateAccountButton handleRegisterClick={this.handleRegisterClick}/>
                        </div>
                    </Card>
                </div>
            </MuiThemeProvider>

        )
    }
}

export default withRouter(withAuth(withStyles(styles)(Login)));
