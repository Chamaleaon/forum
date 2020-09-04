import React from 'react'


import Button from "@material-ui/core/Button";

class EssayDetail extends React.Component{
  handleBack=()=>{
    this.props.history.push('/')
  }
  componentDidMount() {
    console.log(this.props.match.params)
  }
  
  render(){
    return (
      <>
        <Button onClick={this.handleBack}>Back</Button>
        EssayDetail
      </>
    )
  }
}

export default EssayDetail