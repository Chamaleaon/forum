import React,{Component} from 'react'

import {Route} from 'react-router-dom'
import News from '../../pages/News'
import Mine from '../../pages/Mine'

import './index.less'

export default class Main extends Component{
  render(){
    return (
      <div className="wrapper">
        <Route path="/" component={News} exact />
        <Route path="/layout/news" component={News} />
        <Route path="/layout/mine" component={Mine} />
      </div>
    )
  }
}

