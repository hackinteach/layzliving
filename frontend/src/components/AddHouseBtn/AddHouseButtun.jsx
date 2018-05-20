import React from 'react';
import Button from 'material-ui/Button';
import Dialog, {DialogActions, DialogContent, DialogTitle,} from 'material-ui/Dialog';
import {makeTextbox} from "../my-component";
import {withStyles} from 'material-ui/styles';
import {addHouse} from '../../config/api'


const HousenameTextbox = makeTextbox('Housename');
const StreetAddressTextbox = makeTextbox('Street Address');
const CityTextbox = makeTextbox('City');
const CountryTextbox = makeTextbox('Country');
const ZipCodeTextbox = makeTextbox('Zipcode');

const styles = theme => ({
    textField: {
        marginLeft: theme.spacing.unit,
        marginRight: theme.spacing.unit,
        width: 200,
    },

    button: {
        // margin: '0.4em',
        color: '#ffffff',
        //width: 255,
    },
});

class AddHouseButtun extends React.Component {
    state = {
        open: false,
    };

    handleClickOpen = () => {
        this.setState({ open: true });
    };

    handleClose = () => {
        this.setState({ open: false });
    };

    handleInputChange = (name) => (value) => {
        this.setState({[name]: value});
    };

    handleAddHouse = () => {
        const {houseName,streetAddress, city, country, zipCode} = this.state;
        const {updateHouse} = this.props;
        addHouse(houseName,streetAddress, city, country, zipCode)
            .then(({data}) => {
                console.log(data);
            }).then(
            () => {
                updateHouse();
            }
        )
        this.handleClose();
    };
    render() {
        const {classes} = this.props;
        const hic = this.handleInputChange;

        return (
            <div>
                <Button className={classes.button} onClick={this.handleClickOpen}>ADD NEW HOUSE</Button>
                <Dialog
                    open={this.state.open}
                    onClose={this.handleClose}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">{"ADD NEW HOUSE"}</DialogTitle>

                        <DialogContent >

                            <HousenameTextbox className={classes.textField} onChange={hic('houseName')}/>
                            <StreetAddressTextbox className={classes.textField} onChange={hic('streetAddress')}/>
                            <CityTextbox className={classes.textField} onChange={hic('city')}/>
                            <CountryTextbox className={classes.textField} onChange={hic('country')}/>
                            <ZipCodeTextbox className={classes.textField} onChange={hic('zipCode')}/>

                        </DialogContent>

                    <DialogActions>

                        <Button onClick={this.handleClose} color="primary">
                            CANCEL
                        </Button>
                        <Button onClick={this.handleAddHouse} color="primary" autoFocus>
                            ADD
                        </Button>

                    </DialogActions>
                </Dialog>
            </div>
        );
    }
}

export default withStyles(styles)(AddHouseButtun);
