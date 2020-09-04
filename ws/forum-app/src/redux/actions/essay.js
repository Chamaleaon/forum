//constants
import {RECEIVE_ESSAY} from '../constants/essay'
import {RECEIVE_MY_ESSAY} from '../constants/essay'
import {REMOVE_MY_ESSAY} from '../constants/essay'

//api function 
import {reqAllEssay} from '../../api/essay'
import {reqMyEssay} from '../../api/essay'



//actions
//allEssay
function getAllEssaySync(data){
  return {
    type:RECEIVE_ESSAY,
    data
  }
}

export function getAllEssay(){
  return dispatch=>{
    reqAllEssay().then(res=>{
      dispatch(getAllEssaySync(res.RES))
    }).catch(()=>{})
  }
}


//myEssay 
function getMyEssaySync(data){
  return {
    type:RECEIVE_MY_ESSAY,
    data 
  }
}

export function getMyEssay(data){
  return dispatch => {
    reqMyEssay(data).then(res =>{
      dispatch(getMyEssaySync(res.RES))
    }).catch(()=>{})
  }
}

//remove_my_essay
export function removeMyessaySync(){
  return {
    type:REMOVE_MY_ESSAY,
  }
}




