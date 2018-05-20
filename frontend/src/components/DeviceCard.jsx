import React from 'react';
import * as api from '../config/api';
import {Divider, FormControlLabel, FormGroup, Grid, List, ListItem, ListItemText, Switch} from "material-ui";
import AddDeviceBtn from "./AddDeviceButtons/AddDeviceButton";
class DeviceCard extends React.Component {

  switchDevice = (deviceId) => {
    const {updateDevice} = this.props;
    api.toggleDevice(deviceId)
      .then(
        () => {
          updateDevice();
        }
      )
  };

  render() {
    const {deviceList, roomList, roomId, updateDevice: udl} = this.props;
    const newRoomId = Object.keys(roomList).reverse().pop();
    const title = typeof roomList !== 'undefined' && typeof roomId !== 'undefined' && typeof roomList[roomId] !== 'undefined'
      ? "Devices in " + roomList[roomId].name : "Select room first";
    return (
      <div>
        <h4>{title}</h4>
        <Divider/>
        <List>
          {typeof deviceList !== 'undefined'.toString() &&
          Object.keys(deviceList)
            .map(i => {
              const device = deviceList[i].deviceType;
              const on = deviceList[i].on;
              return (
                <div key={device}>
                  <ListItem >
                    <Grid item xs={12} sm={6}>
                      <ListItemText primary={device}/>
                    </Grid>
                    <Grid item xs={12} sm={6}>
                      <FormGroup>
                        <FormControlLabel
                          control={
                            <Switch color='primary' checked={on} onChange={() => this.switchDevice(i)} aria-label="ToggleSwitch"/>
                          }
                          label={on ? 'On' : 'Off'}
                        />
                      </FormGroup>
                    </Grid>
                  </ListItem>
                  <Divider/>
                </div>
              )
            })
          }
        </List>

          {typeof roomList !== 'undefined' && typeof roomId !== 'undefined' && <AddDeviceBtn roomId={roomId} updateDeviceList={udl}/>}

      </div>
    )
  }
}

export default DeviceCard;