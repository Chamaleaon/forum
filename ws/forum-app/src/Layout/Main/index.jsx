import React,{Component} from 'react'

import {Route} from 'react-router-dom'
import Essays from '../../pages/Essays'
import Mine from '../../pages/Mine'

import './index.less'

export default class Main extends Component{
  render(){
    return (
      <div className="wrapper">
        <Route path="/" component={Essays} exact />
        <Route path="/layout/essays" component={Essays} />
        <Route path="/layout/mine" component={Mine} />
      </div>
    )
  }
}

