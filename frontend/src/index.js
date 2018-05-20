import React from "react";
import ReactDOM from "react-dom";
import { createBrowserHistory } from "history";
import "./assets/css/material-dashboard-react.css";
import registerServiceWorker from "./registerServiceWorker";
import MyRouter from "./views/MyRouter";

import "./index.css";

ReactDOM.render(
  <MyRouter/>,
  document.getElementById("root")
);
registerServiceWorker();