import React, { Component } from "react";

import { getMyMessages, getMyReplys } from "../../redux/actions/messages";
import { connect } from "react-redux";

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
        <button onClick={this.handleReturn}>返回</button>
        <div>
          列表一 我的消息
          {this.props.myMessages.map((item, index) => {
            return (
              <div key={index}>
                <div>原文</div>
                <div>
                  {item.original.type === "ESSAY" && (
                    <span>title:{item.original.title}</span>
                  )}
                </div>
                <div>type:{item.original.type}</div>
                <div>time:{item.original.update_time}</div>
                <div>content:{item.original.content}</div>
                <div>回复</div>
                <div>{item.reply.publisher_name}回复到###：</div>
                <div>time:{item.reply.update_time}</div>
                <div>type:{item.reply.type}</div>
                <div>content:{item.reply.content}</div>
              </div>
            );
          })}
        </div>
        <div>
          列表二 我的回复
          {this.props.myReplys.map((item, index) => {
            return (
              <div key={index}>
                <div>原文</div>
                <div>
                  {item.original.type === "ESSAY" && <span>title:{item.original.title}</span>}
                </div>
                <div>time:{item.original.update_time}</div>
                <div>type:{item.original.type}</div>
                <div>content:{item.original.content}</div>
                <div>我回复--{item.original.publisher_name}--的消息</div>
                <div>回复内容</div>
                <div>type:{item.reply.type}</div>
                <div>time:{item.reply.update_time}</div>
                <div>content:{item.reply.content}</div>
              </div>
            );
          })}
        </div>
      </div>
    );
  }
}
export default Messages;
