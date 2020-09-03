import React from "react";


//router 
import {BrowserRouter as Router, Route, Link} from 'react-router-dom'

//重置样式表
import './assets/index.less'

// font
import "fontsource-roboto";

import Layout from "./Layout";
import SignIn from './pages/LoginAndRegister/SignIn'
import SignUp from './pages/LoginAndRegister/SignUp'

export default function App() {
  return (
    <Router>
      <Route path="/" component={Layout} exact/>
      <Route path="/layout" component={Layout} />
      <Route path="/signin" component={SignIn} />
      <Route path="/signup" component={SignUp} />
    </Router>
  );
}
