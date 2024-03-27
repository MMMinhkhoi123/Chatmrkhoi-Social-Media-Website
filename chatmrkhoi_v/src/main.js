import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from "./store"
import "./assets/Css/All.css"
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import "./icon/index.js"
import VueCtkDateTimePicker from 'vue-ctk-date-time-picker';
import 'vue-ctk-date-time-picker/dist/vue-ctk-date-time-picker.css';
import Language from './Language/index.js'
const { i18n } = Language();
const app = createApp(App)
app.use(i18n)
app.use(router)
app.use(store)
app.component('VueCtkDateTimePicker', VueCtkDateTimePicker);
app.component('font-awesome-icon', FontAwesomeIcon)
app.mount('#app')
