import React, { Component } from "react";

import { connect } from "react-redux";

import { getAllEssay } from "../../redux/actions/essay";

import Fab from "@material-ui/core/Fab";
import AddIcon from '@material-ui/icons/Add';
import List from "../../components/NewsList";



import './index.less'

@connect((state) => ({ allEssay: state.essay.allEssay }), { getAllEssay })
class Essays extends Component {
  componentDidMount() {
    this.props.getAllEssay();
  }

  handleAdd = ()=>{
    //判断是否登录 
    if(document.cookie){
      this.props.history.push('/addessay')
    }else{
      this.props.history.push('/signin')
    }
  }

  render() {
    return (
      <div className="newsWrapper">
        <List essay={this.props.allEssay} />
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
