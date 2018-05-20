import React from 'react';
import Button from 'material-ui/Button';
import Dialog, {DialogActions, DialogContent, DialogContentText, DialogTitle,} from 'material-ui/Dialog';
import DeviceSelect from "./DeviceSelect";
import * as api from '../../config/api'


// need to know room id from the parent component
export default class AddDeviceDialog extends React.Component {

    state = {
        open: false,
        deviceType : '',
        deviceName: '',
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

    handleAddDevice = ()=>{
        const {deviceName, deviceType} = this.state;
        const {roomId, updateDeviceList} = this.props;
        console.log("state",this.state)
        api.addDevice(deviceName, deviceType,roomId)
            .then(({data}) => {
                console.log(data);
            })
            .then(() => updateDeviceList())
        this.handleClose();
        // ******************************to implement
    };

    render() {
        const hic = this.handleInputChange;

        return (
            <div>
                <Button onClick={this.handleClickOpen}>Add Device</Button>
                <Dialog
                    open={this.state.open}

                    onClose={this.handleClose}
                    aria-labelledby="add device"
                >
                    <DialogTitle id="add device">Add Device</DialogTitle>
                    <DialogContent>

                        <div display ="flex">
                            <DialogContentText>
                                Your new device's info
                            </DialogContentText>

                            <DeviceSelect updateDevice={hic} data={this.state}/>

                        </div>

                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={this.handleAddDevice} color="primary">
                            Add
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        );
    }
}