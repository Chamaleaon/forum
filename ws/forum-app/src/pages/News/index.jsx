import React, { Component } from 'react'

import {connect} from 'react-redux'

import {getAllEssay} from '../../redux/actions/essay'

@connect((state)=>({essay:state.essay}),{getAllEssay})
class News extends Component {
  componentDidMount() {
    this.props.getAllEssay()
  }
  
  render() {
    return (
      <div>
        news
      </div>
    )
  }
}

export default News
