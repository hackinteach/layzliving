import React from "react";
import PropTypes from "prop-types";
import cx from "classnames";
import {Drawer, Hidden, List, ListItem, ListItemIcon, ListItemText, SvgIcon, withStyles} from "material-ui";
import {HeaderLinks} from "../../components";
import sidebarStyle from "../../variables/styles/sidebarStyle.jsx";
import {withAuth} from "react-router-auth-provider";
import AddHouseBtn from "../AddHouseBtn/AddHouseButtun"
import Button from "../CustomButtons/Button";


const LogoutBtnBase = ({onLogout}) => {
  return (
    <Button variant="raised" onClick={() => onLogout()}>
      Logout
    </Button>
  )
};

const LogoutButton = withAuth(LogoutBtnBase);

function MyLogo({logo, logoText, ...props}) {
  const {classes} = props;
  return (
    <div className={classes.logo}>
      <a href="https://www.google.com" className={classes.logoLink}>
        <div className={classes.logoImage}>
          <img src={logo} alt="logo" className={classes.img}/>
        </div>
        {logoText}
      </a>
    </div>
  )
}

function HomeIcon(props) {
  return (
    <SvgIcon {...props}>
      <path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/>
    </SvgIcon>
  );
}

function MyList(props) {
  const {classes, color, name, id: houseId, active} = props;
  const {callback} = props;
  const listItemClasses = cx({
    [" " + classes[color]]: active
  });
  const whiteFontClasses = cx({
    [" " + classes.whiteFont]: active
  });
  return (
    <List className={classes.list}>
      <ListItem button
                className={classes.itemLink + listItemClasses}
                onClick={() => callback(houseId)}
      >
        <ListItemIcon className={classes.itemIcon + whiteFontClasses}>
          <HomeIcon/>
        </ListItemIcon>
        <ListItemText
          primary={name}
          className={classes.itemText + whiteFontClasses}
          disableTypography={true}
        />
      </ListItem>
    </List>
  );
}

class Sidebar extends React.Component {

  render() {
    const {classes, logo, image, logoText, open, houseList, currHouseId, getHouse} = this.props;
    // const hhc = this.handleHouseChange;
    const {cb} = this.props;
    const mylist = Object.keys(houseList).map(id => {
      const {name} = houseList[id];
      return (
        <MyList name={name}
                id={id}
                key={name}
                callback={cb}
                active={currHouseId === id}
                {...this.props}
        />
      )
    });

    // console.log(houseList);
    return (
      <div>
        <Hidden mdUp>
          <Drawer
            variant="temporary"
            anchor="right"
            open={open}
            classes={{
              paper: classes.drawerPaper
            }}
            onClose={this.props.handleDrawerToggle}
            ModalProps={{
              keepMounted: true // Better open performance on mobile.
            }}
          >
            <MyLogo logo={logo} logoText={logoText} {...this.props}/>
            <div className={classes.sidebarWrapper}>
              <HeaderLinks/>
              {mylist}
              <LogoutButton/>
            </div>
            {image !== undefined ? (
              <div
                className={classes.background}
                style={{backgroundImage: "url(" + image + ")"}}
              />
            ) : null}
          </Drawer>
        </Hidden>
        <Hidden smDown>
          <Drawer
            anchor="left"
            variant="permanent"
            open
            classes={{
              paper: classes.drawerPaper
            }}
          >
            <MyLogo logo={logo} logoText={logoText} {...this.props}/>
            <div className={classes.sidebarWrapper}>
              {
               mylist
              }
              <AddHouseBtn updateHouse={getHouse}/>
              <LogoutButton/>
            </div>
            {image !== undefined ? (
              <div
                className={classes.background}
                style={{backgroundImage: "url(" + image + ")"}}
              />
            ) : null}
          </Drawer>
        </Hidden>
      </div>
    )
  }
}

Sidebar.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(sidebarStyle)(Sidebar);
