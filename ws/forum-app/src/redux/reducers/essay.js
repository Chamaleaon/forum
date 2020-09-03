import {RECEIVE_ESSAY} from '../constants/essay'

const initEssay = []

export default function essay(state=initEssay,action){
  switch(action.type){
    case RECEIVE_ESSAY:
      return [...action.data]
    default :
      return state 
  }
}