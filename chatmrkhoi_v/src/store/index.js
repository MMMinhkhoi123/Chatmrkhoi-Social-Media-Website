import { createStore } from 'vuex'
import authen from "./modules/modules_authen"
import module_everyone from './modules/modules_everyone'
import mobule_chat from "../store/modules/modules_chat"
import avaible_chat from "../store/modules/mobules_extra_chat"
// Create a new store instance.
const store = createStore({
  modules: {
    auth: authen,
    everyone: module_everyone,
    chat: mobule_chat,
    avaible_chat: avaible_chat,
  }
})

export default store;