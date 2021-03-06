import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import Snackbar from "@material-ui/core/Snackbar";
import MuiAlert from "@material-ui/lab/Alert";

import { reqChangeEssay } from "../../api/essay";

import "./index.less";

const useStyles = makeStyles((theme) => ({
  root: {
    "& .MuiTextField-root": {
      margin: theme.spacing(1),
      width: "25ch",
    },
  },
}));

export default function ChangeEssay(props) {
  const classes = useStyles();

  let [title, setTitle] = useState("");
  let [content, setContent] = useState("");
  let [open, setOpen] = useState(false);

  const handleTitleInput = (e) => {
    setTitle(e.target.value);
  };

  const handleContentInput = (e) => {
    setContent(e.target.value);
  };

  const handleSubmit = async () => {
    const cookieArr = document.cookie.split("=");
    let data = {
      title,
      content,
      opretor: 1 * cookieArr[cookieArr.length - 1],
      e_id: props.match.params.e_id,
    };
    const res = await reqChangeEssay(data);
    if (res.RE_DESC === "SUCCESS") {
      setOpen(true);
      setTimeout(() => {
        props.history.push(`/essaydetail/${props.match.params.e_id}`);
      }, 1000);
    }
  };

  const handleCancel = () => {
    props.history.push(`/essaydetail/${props.match.params.e_id}`);
  };

  const handleClose = (event, reason) => {
    if (reason === "clickaway") {
      return;
    }

    setOpen(false);
  };

  function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
  }

  return (
    <form
      className={classes.root}
      noValidate
      autoComplete="off"
      className="add-form"
    >
      <TextField
        id="outlined-helperText"
        label="title"
        defaultValue="标题"
        variant="outlined"
        onChange={handleTitleInput}
      />
      <TextField
        id="outlined-helperText"
        label="content"
        defaultValue="内容"
        variant="outlined"
        onChange={handleContentInput}
      />
      <div>
        <Button variant="contained" color="primary" onClick={handleSubmit}>
          提交
        </Button>
        <Button variant="contained" color="primary" onClick={handleCancel}>
          返回
        </Button>
      </div>
      <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
        <Alert onClose={handleClose} severity="success">
          Update Success!
        </Alert>
      </Snackbar>
    </form>
  );
}
