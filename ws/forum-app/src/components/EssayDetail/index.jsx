import React, { useState, useEffect } from "react";

import { connect } from "react-redux";
import { getEssayDetail } from "../../redux/actions/essay";

import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";

import Comment from "./Comment";

const useStyles = makeStyles({
  root: {
    minWidth: 275,
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
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
    <>
      <Button onClick={handleBack} variant="contained" color="primary">
        Back
      </Button>
      <Card className={classes.root}>
        <CardContent>
          <Typography
            className={classes.title}
            color="textSecondary"
            gutterBottom
          >
            title:{essayInfo.title}
          </Typography>
          <Typography variant="h5" component="h2">
            content:{essayInfo.content}
          </Typography>
          <Typography className={classes.pos} color="textSecondary">
            label:{essayInfo.label}
          </Typography>
          <Typography variant="body2" component="div">
            <Comment floor={essayInfo.floor || []} essay={props.match.params.e_id} publisher = {essayInfo.publisher} />
          </Typography>
        </CardContent>
      </Card>
    </>
  );
}

export default connect((state) => ({ essayInfo: state.essay.essayInfo }), {
  getEssayDetail,
})(EssayDetail);
