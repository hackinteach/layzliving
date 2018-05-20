import React from 'react'
import {Link} from 'react-router-dom'
import {register} from '../../config/api';
import {createMuiTheme, MuiThemeProvider, withStyles} from 'material-ui/styles';
import {makeDialog, makeTextbox} from "../../components/my-component";
import {Dialog, FormControl, FormHelperText} from "material-ui";
import Card, {CardContent} from 'material-ui/Card';
import Button from 'material-ui/Button';
import Avatar from 'material-ui/Avatar';
import CreateIcon from 'material-ui-icons/Create';
import image from '../../assets/img/cellphone.jpg';

const MyLink = props => <Link to="/login" {...props} />;

const styles = theme => ({
  card: {
    minWidth: 350,
    paddingBottom: '1em',
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
  button: {
    margin: '0.4em',
    color: '#ffffff',
    width: 300,
  },
  buttonpos: {
    display: 'flex',
    alignItems: 'center',
    flexDirection: 'column',
  }
});

const UsernameTextbox = makeTextbox('Username');
const PasswordTextbox = makeTextbox('Password', {password: true});
const ConfirmPassword = makeTextbox('Confirm Password', {password: true});
const FirstnameTextbox = makeTextbox('FirstName');
const LastnameTextbox = makeTextbox('LastName');
const EmailTextbox = makeTextbox('Email');

function ErrorMessage({error}) {
  if (!error) {
    return null
  } else {
    return (<FormHelperText id="name-error-text">Password mismatch</FormHelperText>)
  }
}

const Logo = withStyles(styles)(({classes}) => {
  return (<div className={classes.avatarpos}>
    <Avatar className={classes.avatar}>
      <CreateIcon style={{fontSize: 40}}/>
    </Avatar>
  </div>)
});

const theme = createMuiTheme({
  palette: {
    primary: {main: '#BDBDBD'},
    secondary: {main: '#212121'},
  },
  text: {
    primary: '#ffffff',
    secondary: '#ffffff',
  },
});

const SuccessDialog = makeDialog("Register successfully", "Message");

class Register extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      repeatPassword: '',
      email: '',
      firstName: '',
      lastName: '',
      success: false,
    };
  }

  handleInputChange = (name) => (value) => {
    this.setState({[name]: value});
  };

  onRegisterSuccess = () => {
    this.setState({
      success: true,
    })
  };

  handleDialogClose = () => {
    this.setState({
      success: false,
    }, this.props.history.push("/login"))
  };

  handleRegister = () => {
    const {username, password, repeatPassword, email, firstName, lastName} = this.state;
    register(username, password, repeatPassword, email, firstName, lastName)
      .then((data) => {
        console.log("data: ", data);
        if (data.status === 200) {
          this.onRegisterSuccess();
        }
      })
      .catch(err => {
      })
  };

  render() {
    const {classes} = this.props;
    const {username, password, repeatPassword, email, firstName, lastName, success} = this.state;
    const hic = this.handleInputChange;
    const error = password !== repeatPassword;
    return (
      <MuiThemeProvider theme={theme}>
        <SuccessDialog err={success} onClick={this.handleDialogClose}/>
        <div className={classes.main}>
          <Card className={classes.card}>
            <CardContent>
              <Logo/>

              <FormControl fullWidth>
                <UsernameTextbox val={username} onChange={hic('username')}/>
                <PasswordTextbox val={password} onChange={hic('password')}/>

                <ConfirmPassword
                  val={repeatPassword}
                  onChange={hic('repeatPassword')}
                  error={error}
                />

                <ErrorMessage error={error}/>
                <EmailTextbox val={email} onChange={hic('email')}/>
                <FirstnameTextbox val={firstName} onChange={hic('firstName')}/>
                <LastnameTextbox val={lastName} onChange={hic('lastName')}/>

              </FormControl>
            </CardContent>

            <div className={classes.buttonpos}>
              <Button color="primary" variant="raised" className={classes.button}
                      onClick={this.handleRegister}>REGISTER</Button>
              <Button color="secondary" variant="raised" className={classes.button} component={MyLink}>CANCEL</Button>
            </div>
          </Card>
        </div>
      </MuiThemeProvider>
    )
  }
}

export default withStyles(styles)(Register);
