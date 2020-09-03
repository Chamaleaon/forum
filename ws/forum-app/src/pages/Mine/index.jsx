import React, { Component } from 'react'

import {Link} from 'react-router-dom'

export default class Mine extends Component {
  render() {
    return (
      <div>
        Mine
        <Link to="/signin">去登陆</Link>
      </div>
    )
  }
}
