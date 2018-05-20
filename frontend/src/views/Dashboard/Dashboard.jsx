import React from "react";
import PropTypes from "prop-types";
// react plugin for creating charts
import ChartistGraph from "react-chartist";
import {
  Accessibility,
  AccessTime,
  ArrowUpward,
  ContentCopy,
  DateRange,
  InfoOutline,
  LocalOffer,
  Store,
  Update,
  Warning
} from "material-ui-icons";
import {Grid, withStyles} from "material-ui";

import {ChartCard, ItemGrid, StatsCard, TasksCard} from "../../components";

import {completedTasksChart, dailySalesChart, emailsSubscriptionChart} from "../../variables/charts";

import dashboardStyle from "../../variables/styles/dashboardStyle";

class Dashboard extends React.Component {
  state = {
    value: 0, //current room
  };
  handleChange = (event, value) => {
    this.setState({value});
  };

  handleChangeIndex = index => {
    this.setState({value: index});
  };

  render() {
    const {data, updateRoom} = this.props;
    return (
      <div>
        <Grid container>
          <ItemGrid xs={12} sm={12} md={4}>
            <ChartCard
              chart={
                <ChartistGraph
                  className="ct-chart"
                  data={dailySalesChart.data}
                  type="Line"
                  options={dailySalesChart.options}
                  listener={dailySalesChart.animation}
                />
              }
              chartColor="green"
              title="Daily Sales"
              text={
                <span>
                  <span className={this.props.classes.successText}>
                    <ArrowUpward
                      className={this.props.classes.upArrowCardCategory}
                    />{" "}
                    55%
                  </span>{" "}
                  increase in today sales.
                </span>
              }
              statIcon={AccessTime}
              statText="updated 4 minutes ago"
            />
          </ItemGrid>
          <ItemGrid xs={12} sm={12} md={4}>
            <ChartCard
              chart={
                <ChartistGraph
                  className="ct-chart"
                  data={emailsSubscriptionChart.data}
                  type="Bar"
                  options={emailsSubscriptionChart.options}
                  responsiveOptions={emailsSubscriptionChart.responsiveOptions}
                  listener={emailsSubscriptionChart.animation}
                />
              }
              chartColor="orange"
              title="Email Subscriptions"
              text="Last Campaign Performance"
              statIcon={AccessTime}
              statText="campaign sent 2 days ago"
            />
          </ItemGrid>
          <ItemGrid xs={12} sm={12} md={4}>
            <ChartCard
              chart={
                <ChartistGraph
                  className="ct-chart"
                  data={completedTasksChart.data}
                  type="Line"
                  options={completedTasksChart.options}
                  listener={completedTasksChart.animation}
                />
              }
              chartColor="red"
              title="Completed Tasks"
              text="Last Campaign Performance"
              statIcon={AccessTime}
              statText="campaign sent 2 days ago"
            />
          </ItemGrid>
        </Grid>

        <Grid container>
          <ItemGrid xs={12}>
            <TasksCard data={data} updateRoom={updateRoom}/>
          </ItemGrid>
        </Grid>

        <Grid container>
          <ItemGrid xs={12} sm={6} md={3}>
            <StatsCard
              icon={ContentCopy}
              iconColor="orange"
              title="Total Consumption"
              description="50"
              small="kWh"
              statIcon={Warning}
              statIconColor="danger"
              statLink={{text: "Get More Space...", href: "#pablo"}}
            />
          </ItemGrid>

          <ItemGrid xs={12} sm={6} md={3}>
            <StatsCard
              icon={Store}
              iconColor="green"
              title="Consumption by Room"
              description="245"
              small="kWh"
              statIcon={DateRange}
              statText="Last 24 Hours"
            />
          </ItemGrid>

          <ItemGrid xs={12} sm={6} md={3}>
            <StatsCard
              icon={InfoOutline}
              iconColor="red"
              title="Fixed Issues"
              description="75"
              statIcon={LocalOffer}
              statText="Tracked from Github"
            />
          </ItemGrid>

          <ItemGrid xs={12} sm={6} md={3}>
            <StatsCard
              icon={Accessibility}
              iconColor="blue"
              title="Followers"
              description="+245"
              statIcon={Update}
              statText="Just Updated"
            />
          </ItemGrid>
        </Grid>
      </div>
    );
  }
}

Dashboard.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(dashboardStyle)(Dashboard);
