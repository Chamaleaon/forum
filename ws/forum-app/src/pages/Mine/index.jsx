import React, { Component } from "react";

import { connect } from "react-redux";
import { getMyEssay, removeMyessaySync } from "../../redux/actions/essay";
import { Link } from "react-router-dom";

import Card from "@material-ui/core/Card";


import mineStyles from './mine.module.less'

@connect((state) => ({ myEssay: state.essay.myEssay }), {
  getMyEssay,
  removeMyessaySync,
})
class Mine extends Component {
  handleSearchMine = async () => {
    await this.props.getMyEssay({
      id: this.publisherId,
    });
    this.props.history.push("/list/1");
  };
  // publisherId = null; //实例上的属性可以不写

  handleLogout = () => {
    function exit() {
      //拿到cookie
      var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
      if (keys) {
        for (var i = keys.length; i--; )
          document.cookie =
            keys[i] + "=0;expires=" + new Date(0).toUTCString() + "; path=/";
      }
    }
    exit();
    //退出登录清空redux中myEssay
    this.props.removeMyessaySync();
  };

  handleSearchMyMessage = () => {
    this.props.history.push("/messages");
  };

  render() {
    const cookieArr = document.cookie.split("=");
    const publisherId = 1 * cookieArr[cookieArr.length - 1];
    this.publisherId = publisherId;
    return (
      <div>
        {publisherId ? (
          <>
            <Card className={mineStyles.card} onClick={this.handleSearchMine}>我的帖子</Card>
            <Card className={mineStyles.card} onClick={this.handleSearchMyMessage}>与我相关</Card>
            <Card className={mineStyles.card} onClick={this.handleLogout}>下号</Card>
          </>
        ) : (
          <Link to="/signin" style={{ textDecoration: "none" }}>
            <Card className={mineStyles.card}>还没有上号，去登陆</Card>
          </Link>
        )}
      </div>
    );
  }
}

export default Mine;
