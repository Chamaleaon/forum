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
import AddEssay from './pages/AddEssay'
import ChangeEssay from './pages/ChangeEssay'
import EssayDetail from './components/EssayDetail'
import Messages from './components/Messages'
import List from './components/NewsList'


export default function App() {
  return (
    <Router>
      <Route path="/" component={Layout} exact/>
      <Route path="/layout" component={Layout} />
      <Route path="/signin/:e_id?" component={SignIn} />
      <Route path="/signup" component={SignUp} />
      <Route path="/addessay" component={AddEssay} />
      <Route path="/changeessay/:e_id" component={ChangeEssay} />
      <Route path="/essaydetail/:e_id" component={EssayDetail} />
      <Route path="/messages" component={Messages} />
      <Route path="/list/:flag?" component={List}></Route>
    </Router>
  );
}
