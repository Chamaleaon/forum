import React, { Component } from "react";

import { connect } from "react-redux";
import { getMyEssay,removeMyessaySync } from "../../redux/actions/essay";
import { Link } from "react-router-dom";

import Button from "@material-ui/core/Button";

import List from "../../components/NewsList";

import "./index.less";

@connect((state) => ({ myEssay: state.essay.myEssay }), { getMyEssay,removeMyessaySync })
class Mine extends Component {
  handleSearchMine = () => {
    this.props.getMyEssay({
      id: this.publisherId,
    });
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
    //清空redux中myEssay
    this.props.removeMyessaySync()
    this.setState({});
  };

  render() {
    const cookieArr = document.cookie.split("=");
    const publisherId = 1 * cookieArr[cookieArr.length - 1];
    this.publisherId = publisherId;
    return (
      <>
        <List essay={this.props.myEssay} />
        <div className="option-in-mine">
          {publisherId ? (
            <>
              <Button
                variant="contained"
                color="primary"
                onClick={this.handleSearchMine}
              >
                搜索我的帖子
              </Button>
              <Button
                variant="contained"
                color="primary"
                onClick={this.handleLogout}
              >
                下号
              </Button>
            </>
          ) : (
            <Link to="/signin">
              <Button variant="contained" color="primary">
                还没有上号，去登陆
              </Button>
            </Link>
          )}
        </div>
      </>
    );
  }
}

export default Mine;
