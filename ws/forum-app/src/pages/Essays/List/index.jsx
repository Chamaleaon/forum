import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemAvatar from "@material-ui/core/ListItemAvatar";
import ListItemText from "@material-ui/core/ListItemText";
import Avatar from "@material-ui/core/Avatar";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";

import avatar  from './1.png'

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    maxWidth: 752,
  },
  demo: {
    backgroundColor: theme.palette.background.paper,
  },
  title: {
    margin: theme.spacing(4, 0, 2),
  },
}));

export default function NewsList(props) {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Grid item xs={12}>
        <Typography variant="h6" className={classes.title}>
          News
        </Typography>
        <div className={classes.demo}>
          <List dense={false}>
            {props.essay.map((item) => {
              return (
                <ListItem key={item.e_id}>
                  <ListItemAvatar>
                    <Avatar>
                      <img src={avatar} style={{width:"100%"}} />
                    </Avatar>

                  </ListItemAvatar>
                  <ListItemText
                    primary={item.title}
                    secondary={item.content ? item.content : null}
                  />
                  <span>username:{item.publisher_name}</span>
                </ListItem>
              );
            })}
          </List>
        </div>
      </Grid>
    </div>
  );
}
