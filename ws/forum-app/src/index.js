import React from "react";
import ReactDOM from "react-dom";

import { Provider } from "react-redux";
import store from "./redux/store";

//theme
import theme from './config/theme';
import { ThemeProvider } from "@material-ui/core/styles";

import App from "./App";

ReactDOM.render(
  <ThemeProvider theme = {theme}>
    <Provider store={store}>
      <App />
    </Provider>
  </ThemeProvider>,
  document.getElementById("app")
);
