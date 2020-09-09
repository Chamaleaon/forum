import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";
import Button from "@material-ui/core/Button";
import Drawer from "@material-ui/core/Drawer";
import TextField from "@material-ui/core/TextField";

import { connect } from "react-redux";
import { getEssayDetail } from "../../../redux/actions/essay";
import { withRouter } from "react-router-dom";

import {
  reqPublishComment,
  reqReplyToComment,
  reqDelFloor,
  reqDelLayer,
  reqDelEssay,
} from "../../../api/essay";

const useStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  },
  secRoot: {
    width: "90%",
    backgroundColor: "#eee",
    borderRadius: 10,
    padding: 10,
  },
  allComment: {
    fontSize: 20,
    paddingTop: 20,
  },
  commentContaint: {
    padding: 10,
  },
  publisherName: {
    fontSize: 25,
  },
  creationTime: {
    color: "#9e9e9e",
    padding: 10,
  },
  content: {
    fontSize: 18,
    textIndent: 18,
  },
  buttonWrapper: {
    display: "flex",
    justifyContent: "flex-end",
    padding: 50,
  },
  layerName: {
    fontSize: 20,
  },
  layerContent: {
    fontSize: 18,
    textIndent: 18,
  },
}));

//当前回复上传的info
const submitData = {
  //请求类型
  flag: 0,
  //楼层项信息
  item: {},
  //二级评论所在的层
  layer: [],
};
// flag
//0 评论
//1 回复楼主
//2 回复二级评论
function Comment(props) {
  //c_id  登录状态不能放在function外面，否则会导致登录或者注销后无法更新删除按钮是否显示
  const publisher = document.cookie.split("=")[2] * 1;
  const classes = useStyles();
  const [drawerState, setDawerState] = useState(false);
  const [content, setContent] = useState("");
  const toggleDrawer = () => {
    setDawerState(false);
  };

  const publishComment = () => {
    if (document.cookie) {
      setDawerState(true);
      submitData.flag = 0;
    } else {
      props.history.push(`/signin/${props.essay}`);
    }
  };
  const replyToComment = (flag, item, layer) => () => {
    if (document.cookie) {
      //flag 评论类型 item 当前楼或层信息 layer 评论某一层时，该层所在的数组，用于拿到最后一层的level
      setDawerState(true);
      submitData.flag = flag;
      submitData.item = item;
      submitData.layer = layer;
    } else {
      props.history.push(`/signin/${props.essay}`);
    }
  };

  const handleContentInput = (e) => {
    setContent(e.target.value);
  };

  const handleSubmit = () => {
    setDawerState(false);
    // console.log(submitFlag)
    if (submitData.flag === 0) {
      const floorLength = props.floor.length;
      let level;
      if (floorLength) {
        level = props.floor[props.floor.length - 1].level + 1;
      } else {
        level = 1;
      }
      // console.log(props)
      const publisher = document.cookie.split("=")[2] * 1;
      let data = {
        essay: props.essay,
        content,
        publisher,
        level,
      };
      // console.log(data)
      reqPublishComment(data).then((res) => {
        if (res.RE_DESC === "SUCCESS") {
          props.getEssayDetail({ e_id: props.essay });
        }
      });
    }

    if (submitData.flag === 1) {
      const publisher = document.cookie.split("=")[2] * 1;
      //判断当前楼层是否为0，如果为默认level =1 ，回复每层时不用判断，因为有层才能回复 -_-! ...
      const layerLength = submitData.item.layer.length;
      let level;
      if (layerLength) {
        level =
          submitData.item.layer[submitData.item.layer.length - 1].level + 1;
      } else {
        level = 1;
      }
      // console.log(submitData);
      let data = {
        floor: submitData.item.f_id,
        content,
        publisher,
        responder: submitData.item.publisher,
        level,
        replied_lid: -1,
      };
      // console.log(data)
      reqReplyToComment(data).then((res) => {
        props.getEssayDetail({ e_id: props.essay });
      });
    }

    if (submitData.flag === 2) {
      const publisher = document.cookie.split("=")[2] * 1;
      // console.log(submitData);
      let data = {
        floor: submitData.item.floor,
        content,
        publisher,
        responder: submitData.item.publisher,
        level: submitData.layer[submitData.layer.length - 1].level + 1,
        replied_lid: submitData.item.l_id,
      };
      // console.log(data);
      reqReplyToComment(data).then((res) => {
        props.getEssayDetail({ e_id: props.essay });
      });
    }
  };

  const handleDel = (flag, id) => () => {
    let data = {
      opretor: publisher,
    };

    if (flag === "floor") {
      data.f_id = id;
      reqDelFloor(data).then((res) => {
        if (res.RE_DESC === "SUCCESS") {
          props.getEssayDetail({ e_id: props.essay });
          alert("del ok");
        } else {
          alert("del failed");
        }
      });
    } else if (flag === "layer") {
      data.l_id = id;
      reqDelLayer(data).then((res) => {
        if (res.RE_DESC === "SUCCESS") {
          props.getEssayDetail({ e_id: props.essay });
          alert("del ok");
        } else {
          alert("del failed");
        }
      });
    } else if (flag === "essay") {
      data.e_id = id;
      reqDelEssay(data).then((res) => {
        if (res.RE_DESC === "SUCCESS") {
          props.getEssayDetail({ e_id: props.essay });
          props.history.replace("/");
          alert("del ok");
        } else {
          alert("del failed");
        }
      });
    }
  };

  const handleToChange = () => {
    props.history.push(`/changeessay/${props.match.params.e_id}`);
  };

  return (
    <div className={classes.root}>
      <Button variant="contained" color="primary" onClick={publishComment}>
        发表评论
      </Button>
      {publisher === props.publisher && (
        <Button
          variant="contained"
          color="primary"
          onClick={handleDel("essay", props.essay)}
        >
          删除文章
        </Button>
      )}
      {publisher === props.publisher && (
        <Button variant="contained" color="primary" onClick={handleToChange}>
          修改文章
        </Button>
      )}
      <div className={classes.allComment}>全部评论:</div>
      <div>
        {props.floor.map((item) => {
          return (
            <div key={item.f_id}>
              <div className={classes.commentContaint}>
                <div className={classes.publisherName}>
                  {item.publisher_name}
                </div>
                <div className={classes.creationTime}>{item.creation_time}</div>
                <div className={classes.content}>{item.content}</div>
                <div className={classes.buttonWrapper}>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={replyToComment(1, item)}
                  >
                    回复此楼
                  </Button>
                  {publisher === item.publisher && (
                    <Button
                      variant="contained"
                      color="primary"
                      onClick={handleDel("floor", item.f_id)}
                    >
                      删除
                    </Button>
                  )}
                </div>
              </div>
              {item.layer.length > 0 && (
                <div className={classes.secRoot}>
                  {item.layer.length > 0 &&
                    item.layer.map((secItem) => {
                      return (
                        <div key={secItem.l_id}>
                          <div className={classes.layerName}>
                            {secItem.publisher_name}
                          </div>
                          <div className={classes.layerContent}>
                            <div style={{ fontSize: "10%" }}>
                              {secItem.creation_time}
                            </div>
                            {secItem.replied_lid !== -1 && (
                              <span>
                                {secItem.publisher_name}回复#
                                <span style={{ color: "#69f0ae" }}>
                                  {secItem.responder_name}
                                </span>
                                :&nbsp;&nbsp;&nbsp;
                              </span>
                            )}
                            {secItem.content}
                          </div>
                          <div className={classes.buttonWrapper}>
                            <Button
                              variant="contained"
                              color="primary"
                              onClick={replyToComment(2, secItem, item.layer)}
                            >
                              回复
                            </Button>
                            {publisher === secItem.publisher && (
                              <Button
                                variant="contained"
                                color="primary"
                                onClick={handleDel("layer", secItem.l_id)}
                              >
                                删除
                              </Button>
                            )}
                          </div>
                        </div>
                      );
                    })}
                </div>
              )}
            </div>
          );
        })}
      </div>
      <Drawer anchor={"bottom"} open={drawerState} onClose={toggleDrawer}>
        <TextField
          id="outlined-basic"
          label="Outlined"
          variant="outlined"
          onChange={handleContentInput}
        />
        <Button variant="contained" color="primary" onClick={handleSubmit}>
          确认
        </Button>
      </Drawer>
    </div>
  );
}

export default withRouter(connect(null, { getEssayDetail })(Comment));
