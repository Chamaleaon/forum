import React from 'react';

import {withRouter} from 'react-router-dom'

import { makeStyles } from '@material-ui/core/styles';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import RestoreIcon from '@material-ui/icons/Restore';
import LocationOnIcon from '@material-ui/icons/LocationOn';

const useStyles = makeStyles({
  root: {
    width: "100%",
    position:'absolute',
    bottom:0,
    height:"8%"
  },
});



function SimpleBottomNavigation(props) {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);

  const handleTabChange = (event, newValue) => {
    setValue(newValue);
    switch (newValue){
      case 0:
        props.history.push('/layout/news')
        break
      case 1:
        props.history.push('/layout/mine')
    }
  }

  return (
    <BottomNavigation
      value={value}
      onChange={handleTabChange}
      showLabels
      className={classes.root}
    > 
      <BottomNavigationAction label="News" icon={<RestoreIcon />} />  
      <BottomNavigationAction label="Mine" icon={<LocationOnIcon />} />
    </BottomNavigation>
  );
}

export default withRouter(SimpleBottomNavigation)