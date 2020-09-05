import {RECEIVE_ESSAY} from '../constants/essay'
import {RECEIVE_MY_ESSAY} from '../constants/essay'
import {REMOVE_MY_ESSAY} from '../constants/essay'
import {RECEIVE_ESSAY_DETAIL} from '../constants/essay'

const initEssay = {
  allEssay:[],
  myEssay:[],
  essayInfo:{}
}

export default function essay(state=initEssay,action){
  switch(action.type){
    case RECEIVE_ESSAY:
      return {
        ...state,
        allEssay:[...action.data]
      }
    case RECEIVE_MY_ESSAY:
      return {
        ...state,
        myEssay:[...action.data]
      }
    case REMOVE_MY_ESSAY:
      return {
        ...state,
        myEssay:[]
      }
    case RECEIVE_ESSAY_DETAIL:
      return {
        ...state,
        essayInfo:{
          ...action.data
        }
      }
    default :
      return state 
  }
}