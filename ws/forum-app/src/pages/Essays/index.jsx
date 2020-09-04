import React, { Component } from "react";

import { connect } from "react-redux";

import { getAllEssay } from "../../redux/actions/essay";

import Fab from "@material-ui/core/Fab";
import AddIcon from '@material-ui/icons/Add';
import List from "./List";



import './index.less'

@connect((state) => ({ essay: state.essay }), { getAllEssay })
class Essays extends Component {
  componentDidMount() {
    this.props.getAllEssay();
  }

  handleAdd = ()=>{
    this.props.history.push('/addessay')
  }

  render() {
    console.log(this.props.essay);
    return (
      <div className="newsWrapper">
        <List essay={this.props.essay} />
        <div className="addButton">
          <Fab color="primary" aria-label="add" onClick={this.handleAdd}>
            <AddIcon />
          </Fab>
        </div>
      </div>
    );
  }
}

export default Essays;
