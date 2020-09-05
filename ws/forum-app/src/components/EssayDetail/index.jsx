import React from 'react'

import {connect} from 'react-redux'
import {getEssayDetail} from '../../redux/actions/essay'

import Button from "@material-ui/core/Button";


@connect(state=>({essayInfo:state.essay.essayInfo}),{getEssayDetail})
class EssayDetail extends React.Component{
  handleBack=()=>{
    this.props.history.push('/')
  }
  componentDidMount() {
    let data = this.props.match.params
    this.props.getEssayDetail(data)
  }
  
  render(){
    console.log(this.props.essayInfo)
    return (
      <>
        <Button onClick={this.handleBack}>Back</Button>
        EssayDetail
      </>
    )
  }
}

export default EssayDetail