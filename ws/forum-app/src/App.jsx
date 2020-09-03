import React from "react";

//重置样式表
import './assets/index.less'

// font
import "fontsource-roboto";

import Layout from "./Layout";
import LoginAndRegister from './pages/LoginAndRegister'

export default function App() {
  return (
    <>
      <Layout />
      {/* <LoginAndRegister/> */}
    </>
  );
}
