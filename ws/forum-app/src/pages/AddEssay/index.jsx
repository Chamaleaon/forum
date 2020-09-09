import React, { useState } from "react";
import TextField from "@material-ui/core/TextField";
import { makeStyles } from "@material-ui/core/styles";
import Button from "@material-ui/core/Button";
import Snackbar from "@material-ui/core/Snackbar";
import MuiAlert from "@material-ui/lab/Alert";

import { reqPublishEssay } from "../../api/essay";


const useStyles = makeStyles((theme) => ({
  root: {},
}));

export default function FormPropsTextFields(props) {
  const classes = useStyles();

  let [title, setTitle] = useState("");
  let [content, setContent] = useState("");
  let [label, setLabel] = useState("");
  let [open, setOpen] = useState(false);

  const handleTitleInput = (e) => {
    setTitle(e.target.value);
  };

  const handleContentInput = (e) => {
    setContent(e.target.value);
  };

  const handleLabel = (e) => {
    setLabel(e.target.value);
  };

  const handleSubmit = async () => {
    const cookieArr = document.cookie.split("=");
    let data = {
      title,
      content,
      label,
      publisher: 1 * cookieArr[cookieArr.length - 1],
    };
    const res = await reqPublishEssay(data);
    if (res.RE_DESC === "SUCCESS") {
      setOpen(true);
      setTimeout(() => {
        props.history.push("/layout/essays");
      }, 1000);
    }
  };

  const handleCancel = () => {
    props.history.push("/layout/essays");
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
    <form noValidate autoComplete="off" className="add-form">
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
      <TextField
        id="outlined-helperText"
        label="label"
        defaultValue="test"
        variant="outlined"
        onChange={handleLabel}
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
          Publish Success!
        </Alert>
      </Snackbar>
    </form>
  );
}
