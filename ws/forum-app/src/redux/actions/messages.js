import {RECEIVE_MY_MESSAGES,RECEIVE_MY_REPLYS} from '../constants/messages'

import {reqMyMessages,reqMyReplys} from '../../api/messages'

//与我相关 
function getMyMessagesSync(data){
  return {
    type:RECEIVE_MY_MESSAGES,
    data 
  }
}

export function getMyMessages(data){
  return dispatch =>{
    reqMyMessages(data).then(res=>{
      dispatch(getMyMessagesSync(res.RES))
    })
  } 
}

function getMyReplysSync(data){
  return {
    type:RECEIVE_MY_REPLYS,
    data 
  }
}

export function getMyReplys(data){
  return dispatch =>{
    reqMyReplys(data).then(res=>{
      dispatch(getMyReplysSync(res.RES))
    })
  } 
}