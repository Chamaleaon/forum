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

import { reqPublishComment, reqReplyToComment } from "../../../api/essay";

const useStyles = makeStyles((theme) => ({
  root: {
    width: "100%",
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  },
  secRoot: {
    width: "100%",
    backgroundColor: "#ccc",
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
  const classes = useStyles();
  const [drawerState, setDawerState] = useState(false);
  const [content, setContent] = useState("");

  const toggleDrawer = () => {
    setDawerState(false);
  };

  const publishComment = () => {
    setDawerState(true);
    submitData.flag = 0;
  };
  const replyToComment = (flag, item, layer) => () => {
    //flag 评论类型 item 当前楼或层信息 layer 评论某一层时，该层所在的数组，用于拿到最后一层的level
    setDawerState(true);
    submitData.flag = flag;
    submitData.item = item;
    submitData.layer = layer;
  };

  const handleContentInput = (e) => {
    setContent(e.target.value);
  };

  const handleSubmit = () => {
    setDawerState(false);
    // console.log(submitFlag)
    if (submitData.flag === 0) {
      const level = props.floor[props.floor.length - 1].level + 1;
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
      const layerLength = submitData.item.layer.length
      let level
      if(layerLength){
        level = submitData.item.layer[submitData.item.layer.length - 1].level + 1
      } else{
        level = 1
      }
      console.log(submitData);
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
      console.log(submitData);
      let data = {
        floor: submitData.item.floor,
        content,
        publisher,
        responder: submitData.item.publisher,
        level: submitData.layer[submitData.layer.length - 1].level + 1,
        replied_lid: submitData.item.l_id,
      };
      console.log(data);
      reqReplyToComment(data).then((res) => {
        props.getEssayDetail({ e_id: props.essay });
      });
    }
  };

  return (
    <div className={classes.root}>
      <Button variant="contained" color="primary" onClick={publishComment}>
        发表评论
      </Button>
      <div>评论内容:</div>
      <List component="nav" aria-label="main mailbox folders">
        {props.floor.map((item) => {
          return (
            <div key={item.f_id}>
              <ListItem>
                <ListItemIcon>{item.publisher_name}</ListItemIcon>
                <ListItemText primary={item.content} />
                <Button
                  variant="contained"
                  color="primary"
                  onClick={replyToComment(1, item)}
                >
                  回复此楼
                </Button>
              </ListItem>
              二级评论:
              <List className={classes.secRoot}>
                {item.layer.length > 0 &&
                  item.layer.map((secItem) => {
                    return (
                      <ListItem key={secItem.l_id}>
                        ##
                        <div>
                          {secItem.replied_lid !== -1 ? (
                            <span>
                              {secItem.publisher_name}回复
                              {secItem.responder_name}
                            </span>
                          ) : (
                            <span>{secItem.publisher_name}回复楼主</span>
                          )}
                        </div>
                        <ListItemIcon>NickName:{secItem.publisher_name}</ListItemIcon>
                        <ListItemText primary={secItem.content} />
                        <Button
                          variant="contained"
                          color="primary"
                          onClick={replyToComment(2, secItem, item.layer)}
                        >
                          回复此层
                        </Button>
                      </ListItem>
                    );
                  })}
              </List>
            </div>
          );
        })}
      </List>
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

export default connect(null, { getEssayDetail })(Comment);
