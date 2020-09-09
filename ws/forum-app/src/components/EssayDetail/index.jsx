import React, { useState, useEffect } from "react";

import { connect } from "react-redux";
import { getEssayDetail } from "../../redux/actions/essay";

import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";

import Comment from "./Comment";

const useStyles = makeStyles({
  root: {
    padding: 20,
  },
  title: {
    fontSize: 30,
    color: "#263238",
  },
  pos: {
    marginBottom: 12,
  },
  update_time: {
    fontSize: 12,
  },
  content:{
    fontSize:18,
    color:'#37474f'
  },
  back:{
    position:'fixed',
    width:"80%",
    bottom:'3%',
    left:'10%',
  }
});

function EssayDetail(props) {
  const handleBack = () => {
    props.history.push("/");
  };

  useEffect(() => {
    let data = props.match.params;
    props.getEssayDetail(data);
  }, []);

  const classes = useStyles();

  const essayInfo = props.essayInfo;
  // console.log(essayInfo)
  return (
    <Card className={classes.root}>
      <Typography className={classes.title} color="textSecondary" gutterBottom>
        {essayInfo.title ? essayInfo.title : "..."}
      </Typography>
      <Typography
        className={classes.update_time}
        color="textSecondary"
        gutterBottom
      >
        {essayInfo.update_time ? essayInfo.update_time : "..."}
      </Typography>
      <Typography className={classes.content}>
        {essayInfo.content ? essayInfo.content : "..."}
      </Typography>
      <Typography className={classes.pos} color="textSecondary">
        label:{essayInfo.label?essayInfo.label:"未知"}
      </Typography>
      <Typography variant="body2" component="div">
        <Comment
          floor={essayInfo.floor || []}
          essay={props.match.params.e_id}
          publisher={essayInfo.publisher}
        />
      </Typography>
      <Button onClick={handleBack} variant="contained" color="primary" className={classes.back}>
        Back
      </Button>
    </Card>
  );
}

export default connect((state) => ({ essayInfo: state.essay.essayInfo }), {
  getEssayDetail,
})(EssayDetail);
