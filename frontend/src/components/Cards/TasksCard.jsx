import React from "react";
import PropTypes from "prop-types";
import {Card, CardContent, CardHeader, Tab, Tabs, withStyles} from "material-ui";
import {BugReport} from "material-ui-icons";
import * as api from '../../config/api';

import tasksCardStyle from "../../variables/styles/tasksCardStyle";
import DeviceCard from "../DeviceCard";
import TaskAddRoom from "../Tasks/TaskAddRoom"

function MyTab({name, classes}) {
  return (
    <Tab
      classes={{
        wrapper: classes.tabWrapper,
        rootLabelIcon: classes.labelIcon,
        label: classes.label,
        rootInheritSelected: classes.rootInheritSelected
      }}
      icon={<BugReport className={classes.tabIcon}/>}
      label={name}
      key={name}
    />
  )
}

const header = ({classes, roomList, currRoomId}) => {
  return (
    <CardHeader
      classes={{
        root: classes.cardHeader,
        title: classes.cardTitle,
        content: classes.cardHeaderContent
      }}
      title={"Rooms"}
      action={
        <div>
          <Tabs
            classes={{
              flexContainer: classes.tabsContainer
            }}
            scrollable
            scrollButtons="on"
            value={
              typeof currRoomId !== 'undefined'
                ? currRoomId : -1
            }
            onChange={this.handleChange}
            indicatorClassName={classes.displayNone}
            textColor="inherit"
          >
            {
              typeof roomList !== 'undefined'.toString() &&
              Object.keys(roomList)
                .map(i => {
                  const name = roomList[i].name;
                  // console.log("id: ",i);
                  return (
                    <Tab
                      key={name}
                      classes={{
                        wrapper: classes.tabWrapper,
                        rootLabelIcon: classes.labelIcon,
                        label: classes.label,
                        rootInheritSelected: classes.rootInheritSelected
                      }}
                      icon={<BugReport className={classes.tabIcon}/>}
                      label={name}
                      value={i}
                    />
                  )
                })
            }
            <Tab label={<i className="material-icons">add_circle_outline</i>}
                 key={"Add icon"}
                 value={-1}
            />
          </Tabs>

        </div>
      }
    />
  )
}

class TasksCard extends React.Component {
  state = {
    deviceList: {},
    currRoomId: -1,
  };

  handleChange = (event, value) => {
    // console.log("value changed!!", value);
    if (typeof value !== 'undefined'.toString()) {
      this.setState(
        {currRoomId: value},
        () => {
          this.getDeviceList();
          // console.log(this.state.currRoomId);
        });
    }

  };

  componentDidMount() {
    const {roomList} = this.props.data;
    // console.log(roomList);
    const rid = Object.keys(roomList).reverse().pop();
    this.setState({
      currRoomId: rid,
    }, () => this.getDeviceList());
  }

  getDeviceList = () => {
    const {currRoomId: roomId} = this.state;
    // console.log("getDL", roomId);
    if (typeof roomId !== 'undefined'.toString() && roomId !== -1) {
      api.getDevicesInRoom(roomId)
        .then(
          ({data}) => {
            data.sort((a, b) => a.deviceId > b.deviceId);
            const mutate = data.reduce((accu, {deviceId, ...rest}) => ({[deviceId]: rest, ...accu}), {});
            Object.keys(mutate).sort().forEach(function (key) {
              mutate[key] = mutate[key];
            });
            // console.log("room list: ", mutate);
            this.setState({
              deviceList: mutate,
            });
          }
        )
    }

  };

  render() {
    const {classes, updateRoom} = this.props;
    const {deviceList, currRoomId} = this.state;
    const {currHouseId, roomList} = this.props.data;
    const updateDevice = this.getDeviceList;
    return (
      <Card className={classes.card}>
        <CardHeader
          classes={{
            root: classes.cardHeader,
            title: classes.cardTitle,
            content: classes.cardHeaderContent
          }}
          title={"Rooms"}
          action={
            <Tabs
              classes={{
                flexContainer: classes.tabsContainer
              }}
              value={
                typeof currRoomId !== 'undefined'
                  ? currRoomId : -1
              }
              onChange={this.handleChange}
              indicatorClassName={classes.displayNone}
              textColor="inherit"
            >
              {
                typeof roomList !== 'undefined'.toString() &&
                Object.keys(roomList)
                  .map(i => {
                    const name = roomList[i].name;
                    // console.log("id: ",i);
                    return (
                      <Tab
                        key={name}
                        classes={{
                          wrapper: classes.tabWrapper,
                          rootLabelIcon: classes.labelIcon,
                          label: classes.label,
                          rootInheritSelected: classes.rootInheritSelected
                        }}
                        icon={<BugReport className={classes.tabIcon}/>}
                        label={name}
                        value={i}
                      />
                    )
                  })
              }
              <Tab label={<i className="material-icons">add_circle_outline</i>}
                   key={"Add icon"}
                   value={-1}
              />
            </Tabs>
          }
        />
        <CardContent>
          {
            typeof roomList !== 'undefined'.toString() && currRoomId !== -1 ?
              <DeviceCard deviceList={deviceList}
                          updateDevice={updateDevice}
                          roomList={roomList}
                          roomId={currRoomId}
              />
              :
              <div>
                <TaskAddRoom currHouseId={currHouseId} updateRoom={updateRoom}/>
              </div>
          }
        </CardContent>
      </Card>
    );
  }
}

TasksCard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(tasksCardStyle)(TasksCard);
