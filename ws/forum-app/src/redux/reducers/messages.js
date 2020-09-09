import { RECEIVE_MY_MESSAGES, RECEIVE_MY_REPLYS } from "../constants/messages";

const initMessages = {
  myMessages: [],
  myReplys: [],
};

export default function message(state = initMessages, action) {
  switch (action.type) {
    case RECEIVE_MY_MESSAGES:
      return {
        ...state,
        myMessages: [...action.data],
      };
    case RECEIVE_MY_REPLYS:
      return {
        ...state,
        myReplys: [...action.data],
      };
    default:
      return state;
  }
}
