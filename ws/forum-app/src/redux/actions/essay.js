//constants
import {RECEIVE_ESSAY,RECEIVE_MY_ESSAY,REMOVE_MY_ESSAY,RECEIVE_ESSAY_DETAIL} from '../constants/essay'


//api function 
import {reqEssayDetail,reqSearchEssay,reqAllEssay,reqMyEssay} from '../../api/essay'



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



//essay_detail
function getEssayDetailSync(data){
  return {
    type:RECEIVE_ESSAY_DETAIL,
    data 
  }
}

export function getEssayDetail(data){
  return dispatch =>{
    reqEssayDetail(data).then(res=>{
      dispatch(getEssayDetailSync(res))
    })
  }
}


//searchEssay 
function searchEssaySync(data){
  return {
    type:RECEIVE_ESSAY,
    data 
  }
}

export function searchEssay(data){
  return dispatch => {
    return reqSearchEssay(data).then(res => {
      if(res.RES.length){
        dispatch(searchEssaySync(res.RES))
        return 1;
      }
    })
  }
}



