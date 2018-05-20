// for AddRoom content


import React from "react";
import {Button, Table, TableBody, TableCell, TableRow, withStyles} from "material-ui";
import PropTypes from "prop-types";
import tasksStyle from "../../variables/styles/tasksStyle.jsx";
import {addRoom} from '../../config/api'
import {makeTextbox} from "../my-component";


const RoomNametextbox = makeTextbox('Room Name');





class TaskAddRoom extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            roomName: '',
        };
    };

    handleInputChange = (name) => (value) => {
        this.setState({[name]: value});
    };

    handleAddRoom = () => {
        const {roomName} = this.state;
        const {currHouseId, updateRoom} = this.props;
        addRoom(roomName,currHouseId)
            .then(
              () => updateRoom()
            );
    };


    render() {
        const { classes} = this.props;
        return (
          <div>
            <RoomNametextbox onChange={this.handleInputChange('roomName')}/>
            <Button
              variant="raised"
              className={classes.tableActionButton}
              margin="normal"
              onClick={this.handleAddRoom}
            >
              ADD
            </Button>
          </div>
        );
    }
}

TaskAddRoom.propTypes = {
    classes: PropTypes.object.isRequired,
    tasksIndexes: PropTypes.arrayOf(PropTypes.number),
    tasks: PropTypes.arrayOf(PropTypes.node)
};

export default withStyles(tasksStyle)(TaskAddRoom);
