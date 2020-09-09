import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemAvatar from "@material-ui/core/ListItemAvatar";
import Avatar from "@material-ui/core/Avatar";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import Card from "@material-ui/core/Card";
import Divider from "@material-ui/core/Divider";
import TelegramIcon from "@material-ui/icons/Telegram";
import Button from "@material-ui/core/Button";

import { withRouter } from "react-router-dom";
import { connect } from "react-redux";

import avatar from "./1.png";

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    maxWidth: 752,
  },
  demo: {
    backgroundColor: theme.palette.background.paper,
  },
  title: {
    margin: theme.spacing(2, 2, 2),
    fontSize: "120%",
    fontWeight: "bold",
    color: "#424242",
    display: "flex",
    flexGrow: 1,
    padding: "2%",
    border: "2px solid #d1ff33",
    borderRadius: "10px",
  },
  card: {
    width: "100%",
    padding: "5%",
    position: "relative",
  },
  user: {
    width: "30%",
    display: "flex",
    alignItems: "center",
  },
  nickname: {
    color: "#455a64",
    fontSize: "130%",
  },
  header: {
    display: "flex",
  },
  clickspace: {
    position: "absolute",
    width: "100%",
    height: "100%",
    left: "0",
    top: "0",
  },
  content: {
    textIndent: "10%",
    color: "#333",
  },
  return: {
    width:'80%',
    position:'fixed',
    bottom:10,
    left:40
  },
}));

function NewsList(props) {
  const classes = useStyles();

  const handleItemCilck = (e) => {
    if (e.target.id) {
      props.history.push(`/essaydetail/${e.target.id}`);
    }
  };

  let essay = props.essay;
  //判断是否是路由组件展示，如果有flag，则展示myEssay中的数据
  if (props.match.params.flag === "1") {
    essay = props.myEssay;
    // console.log(essay)
  }

  return (
    <div className={classes.root}>
      <Grid item xs={12}>
        {essay.length > 0 && (
          <Typography variant="h6" className={classes.title}>
            Essays
          </Typography>
        )}
        <div className={classes.demo}>
          <List dense={false} onClick={handleItemCilck}>
            {essay.map((item) => {
              return (
                <ListItem key={item.e_id}>
                  <Card className={classes.card}>
                    <div className={classes.time}>{item.creation_time}</div>
                    <div className={classes.header}>
                      <div className={classes.user}>
                        <ListItemAvatar>
                          <Avatar>
                            <img src={avatar} style={{ width: "100%" }} />
                          </Avatar>
                        </ListItemAvatar>
                        <div className={classes.nickname}>
                          {item.publisher_name}
                        </div>
                      </div>
                      <div className={classes.title}>
                        <TelegramIcon style={{ marginRight: "10%" }} />
                        <div>{item.title}</div>
                      </div>
                    </div>
                    <Divider variant="middle" />
                    <div className={classes.content}>
                      {item.content ? item.content : null}
                    </div>
                    <div className={classes.clickspace} id={item.e_id}>
                      {/* 查看详情区域 */}
                    </div>
                  </Card>
                </ListItem>
              );
            })}
          </List>
        </div>
      </Grid>
      {props.match.params.flag === "1" && (
        <Button
          className={classes.return}
          variant="contained"
          color="primary"
          onClick={() => {
            props.history.push("/layout/mine");
          }}
        >
          返回
        </Button>
      )}
    </div>
  );
}

export default withRouter(
  connect((state) => ({ myEssay: state.essay.myEssay }))(NewsList)
);
