import {RECEIVE_ESSAY} from '../constants/essay'
import {RECEIVE_MY_ESSAY} from '../constants/essay'

const initEssay = {
  allEssay:[],
  myEssay:[]
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
    default :
      return state 
  }
}