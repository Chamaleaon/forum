//constants
import {RECEIVE_ESSAY} from '../constants/essay'


//api function 
import {reqAllEssay} from '../../api/essay'



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






