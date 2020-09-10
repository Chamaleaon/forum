import React, { Component } from "react";

import { getMyMessages, getMyReplys } from "../../redux/actions/messages";
import { connect } from "react-redux";

import Card from "@material-ui/core/Card";
import Divider from "@material-ui/core/Divider";
import Avatar from "@material-ui/core/Avatar";
import msgStyle from "./msg.module.less";

import avatar from "./1.png";

@connect(
  (state) => ({
    myMessages: state.message.myMessages,
    myReplys: state.message.myReplys,
  }),
  { getMyMessages, getMyReplys }
)
class Messages extends Component {
  componentDidMount() {
    this.u_id = document.cookie.split("=")[2];
    this.props.getMyMessages({ u_id: this.u_id });
    this.props.getMyReplys({ u_id: this.u_id });
  }

  handleReturn = () => {
    this.props.history.push("/layout/mine");
  };

  render() {
    return (
      <div>
        <Card className={msgStyle.return} onClick={this.handleReturn}>
          返回
        </Card>
        <Card className={msgStyle.card}>
          <div className={msgStyle.listName}>我的消息</div>

          {this.props.myMessages.map((item, index) => {
            return (
              <div key={index}>
                {/* <div>原文</div> */}
                <div>
                  {item.original.type === "ESSAY" && (
                    <div className={msgStyle.title}>{item.original.title}</div>
                  )}
                </div>
                {/* <div>type:{item.original.type}</div> */}
                <div className={msgStyle.time}>{item.original.update_time}</div>
                <div className={msgStyle.content}>{item.original.content}</div>
                {/* <div>回复</div> */}
                <Divider />
                <div className={msgStyle.replyer}>
                  <Avatar alt="Remy Sharp" src={avatar} />
                  <span>{item.reply.publisher_name}&nbsp;#:</span>
                </div>
                <div className={msgStyle.time}>
                  time:{item.reply.update_time}
                </div>
                {/* <div>type:{item.reply.type}</div> */}
                <div className={msgStyle.content}>{item.reply.content}</div>
              </div>
            );
          })}
        </Card>
        <Card className={msgStyle.card}>
          <div className={msgStyle.listName}>我的回复</div>
          {this.props.myReplys.map((item, index) => {
            return (
              <div key={index}>
                {/* <div>原文</div> */}
                <div className={msgStyle.replyer}>
                  <Avatar alt="Remy Sharp" src={avatar} />
                  <span>{item.original.publisher_name}</span>
                </div>
                <div>
                  {item.original.type === "ESSAY" && (
                    <div className={msgStyle.title}>{item.original.title}</div>
                  )}
                </div>
                <div className={msgStyle.time}>{item.original.update_time}</div>
                {/* <div>type:{item.original.type}</div> */}
                <div className={msgStyle.content}>{item.original.content}</div>
                <div>我&nbsp;#:</div>
                {/* <div>回复内容</div> */}
                {/* <div>type:{item.reply.type}</div> */}
                <div className={msgStyle.time}>{item.reply.update_time}</div>
                <div className={msgStyle.content}>{item.reply.content}</div>
              </div>
            );
          })}
        </Card>
      </div>
    );
  }
}
export default Messages;
