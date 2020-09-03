import {createStore,applyMiddleware} from 'redux'
import thunk from 'redux-thunk'
import {composeWithDevTools} from 'redux-devtools-extension' //不用会出现其他数据，并且redux调试工具不出现自己的数据

import reducer from './reducers'

export default createStore(reducer,composeWithDevTools(applyMiddleware(thunk)))