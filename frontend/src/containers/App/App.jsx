import React from "react";
import PropTypes from "prop-types";
import {Redirect, Route, Switch} from "react-router-dom";
// creates a beautiful scrollbar
import PerfectScrollbar from "perfect-scrollbar";
import "perfect-scrollbar/css/perfect-scrollbar.css";
import {withStyles} from "material-ui";
import DashBoard from '../../views/Dashboard/Dashboard'
import {Footer, Header, Sidebar} from "../../components";
import appRoutes from "../../routes/app";
import appStyle from "../../variables/styles/appStyle.jsx";

import image from "../../assets/img/house-1.jpg";
import logo from "../../assets/img/unrar.png";
import * as api from "../../config/api";

const switchRoutes = (
  <Switch>
    {appRoutes.map((prop, key) => {
      if (prop.redirect)
        return <Redirect from={prop.path} to={prop.to} key={key}/>;
      return <Route path={prop.path} component={prop.component} key={key}/>;
    })}
  </Switch>
);

class App extends React.Component {
  state = {
    mobileOpen: false,
    houseList: {},
    currHouseId: '',
    roomList: {},
  };

  handleDrawerToggle = () => {
    this.setState({mobileOpen: !this.state.mobileOpen});
  };

  componentDidMount() {
    if (navigator.platform.indexOf('Win') > -1) {
      // eslint-disable-next-line
      const ps = new PerfectScrollbar(this.refs.mainPanel);
    }
    this.getHouseList();
  }

  getHouseList = () => {
    api.getHouseList()
      .then(({data}) => {
        data.sort((a, b) => a.id > b.id);
        const mutate = data.reduce((accu, {id, ...rest}) => ({[id]: rest, ...accu}), {});
        this.setState({
            houseList: mutate,
          },
          () => {
            const {houseList} = this.state;
            if (houseList > 0 || typeof houseList !== 'undefined') {
              this.initDefaultHouse()
            }
          });
      });
  };

  initDefaultHouse = () => {
    const {houseList} = this.state;
    // first house
    // console.log(houseList);
    const hid = Object.keys(houseList).reverse().pop();
    this.setState({
      currHouseId: hid,
    }, () => this.getRoomList());
  };

  getRoomList = () => {
    const {currHouseId} = this.state;
    api.getRoomList(currHouseId)
      .then(({data}) => {
          data.sort((a, b) => a.roomId > b.roomId);
          const mutate = data.reduce((accu, {roomId, ...rest}) => ({[roomId]: rest, ...accu}), {});
          // console.log("room list: ", mutate);
          this.setState({
            roomList: mutate,
          }, () => console.log(mutate));
        }
      )

  };

  componentDidUpdate() {
    this.refs.mainPanel.scrollTop = 0;
  }

  changeCurrHouse = (houseId) => {
    this.setState({
        currHouseId: houseId
      }, () => this.setState({
        roomList: this.getRoomList(),
      })
    );
  };

  render() {
    const {classes, ...rest} = this.props;
    const {houseList, currHouseId} = this.state;
    const changeChid = this.changeCurrHouse;
    const updateRoom = this.getRoomList;
    return (
      <div className={classes.wrapper}>
        <Sidebar
          routes={appRoutes}
          logoText={"SMART LIVING"}
          logo={logo}
          image={image}
          handleDrawerToggle={this.handleDrawerToggle}
          open={this.state.mobileOpen}
          color="blue"
          houseList={houseList}
          cb={changeChid}
          currHouseId={currHouseId}
          getHouse={this.getHouseList}
          {...rest}
        />

        <div className={classes.mainPanel} ref="mainPanel">
          <Header
            routes={appRoutes}
            handleDrawerToggle={this.handleDrawerToggle}
            {...rest}
          />
          <div className={classes.content}>
            <div className={classes.container}>
              {
                Object.keys(houseList).length > 0 ?
                  <DashBoard data={this.state} updateRoom={updateRoom}/>
                  :
                  <p> Add new house</p>
              }

            </div>
          </div>
          <Footer/>
        </div>
      </div>
    );
  }
}

App.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(appStyle)(App);
