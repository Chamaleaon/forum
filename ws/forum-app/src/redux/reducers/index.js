import {combineReducers} from 'redux'

import essay from './essay'
import message from './messages'

export default combineReducers({
  essay,
  message,
})