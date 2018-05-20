import React from "react";
import {IconButton, Switch, Table, TableBody, TableCell, TableRow, Tooltip, withStyles} from "material-ui";
import {Close, Edit} from "material-ui-icons";

import PropTypes from "prop-types";

import tasksStyle from "../../variables/styles/tasksStyle.jsx";
import AddDeviceButton from "../AddDeviceButtons/AddDeviceButton";

class Tasks extends React.Component {
  state = {
    checked: this.props.checkedIndexes
  };

  handleToggle = value => () => {
    const { checked } = this.state;
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    this.setState({
      checked: newChecked
    });
  };
  render() {
    const { classes, tasksIndexes, tasks } = this.props;
    return (
        <div>
          <Table className={classes.table}>
            <TableBody>
              {tasksIndexes.map(value => (
                <TableRow key={value} className={classes.tableRow}>
                  <TableCell className={classes.tableCell}>

                      <Switch
                          onChange={this.handleToggle(value)}
                          checked={this.state.checked.indexOf(value) !== -1}
                      />

                  </TableCell>
                  <TableCell className={classes.tableCell}>
                    {tasks[value]}
                  </TableCell>
                  <TableCell className={classes.tableActions}>
                    <Tooltip
                      id="tooltip-top"
                      title="Edit Task"
                      placement="top"
                      classes={{ tooltip: classes.tooltip }}
                    >
                      <IconButton
                        aria-label="Edit"
                        className={classes.tableActionButton}
                      >
                        <Edit
                          className={
                            classes.tableActionButtonIcon + " " + classes.edit
                          }
                        />
                      </IconButton>
                    </Tooltip>
                    <Tooltip
                      id="tooltip-top-start"
                      title="Remove"
                      placement="top"
                      classes={{ tooltip: classes.tooltip }}
                    >
                      <IconButton
                        aria-label="Close"
                        className={classes.tableActionButton}
                      >
                        <Close
                          className={
                            classes.tableActionButtonIcon + " " + classes.close
                          }
                        />
                      </IconButton>
                    </Tooltip>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
            <AddDeviceButton/>
        </div>
    );
  }
}

Tasks.propTypes = {
  classes: PropTypes.object.isRequired,
  tasksIndexes: PropTypes.arrayOf(PropTypes.number),
  tasks: PropTypes.arrayOf(PropTypes.node)
};

export default withStyles(tasksStyle)(Tasks);
