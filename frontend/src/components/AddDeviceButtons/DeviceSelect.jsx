import React from 'react';
import {makeTextbox} from "../my-component";
import {withStyles} from 'material-ui/styles';
import TextField from 'material-ui/TextField';
import MenuItem from 'material-ui/Menu/MenuItem';
import classNames from 'classnames';

const styles = theme => ({
    root: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    margin: {
        margin: theme.spacing.unit,
    },
    withoutLabel: {
        marginTop: theme.spacing.unit * 3,
    },
    textField: {
        flexBasis: 200,
    },
});

const ranges = [
    {
        value: 'Light',
        label: 'Light',
    },
    {
        value: 'Fan',
        label: 'Fan',
    },
    {
        value: 'Air Conditioner',
        label: 'Air Conditioner',
    },
    {
        value: 'Door',
        label: 'Door',
    },
];

const DeviceNameTextbox = makeTextbox('Device Name')

class DeviceSelect extends React.Component {

    render(){
        const { classes , updateDevice} = this.props;
        const {deviceType, deviceName} = this.props.data;

        return(

            <div className={classes.root}>
                 <DeviceNameTextbox
                     className={classes.margin}
                     val={deviceName}
                     onChange={updateDevice('deviceName')}
                 />

                <TextField
                    select
                    label="Type"
                    className={classNames(classes.margin, classes.textField)}
                    value={deviceType}
                    onChange={(e) => updateDevice('deviceType')(e.target.value)}
                    inputProps={{
                        name: 'Device Type',
                        id: 'select device',
                    }}

                >
                    {ranges.map(option => (
                        <MenuItem key={option.value} value={option.value}>
                            {option.label}
                        </MenuItem>
                    ))}
                </TextField>

                {/*<FormControl >*/}
                    {/*<InputLabel htmlFor="age-native-simple">Device Type</InputLabel>*/}
                        {/*<Select*/}
                            {/*value={deviceType}*/}
                            {/*onChange={this.handleChangeDeviceSelect}*/}
                            {/*inputProps={{*/}
                                {/*name: 'Device Type',*/}
                                {/*id: 'select device',*/}
                            {/*}}*/}
                        {/*>*/}
                            {/*<option value={'Light'}>Light</option>*/}
                            {/*<option value={'Fan'}>Fan</option>*/}
                            {/*<option value={'Air Conditioner'}>Air Conditioner</option>*/}
                            {/*<option value={'Door'}>Door</option>*/}
                        {/*</Select>*/}
                {/*</FormControl>*/}

            </div>

        )
    }
}

export default withStyles(styles)(DeviceSelect);