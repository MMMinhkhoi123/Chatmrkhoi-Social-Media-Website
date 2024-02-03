import { computed } from "vue"
import store from "../../store"

export default () => {
    const authen = computed(() => {
        return store.state.auth.authen;
    })
    const url_img = import.meta.env.VITE_API_URL + "/file/get-png/";
    const url_video = import.meta.env.VITE_API_URL + "/file/geturl-video/";
    const url_img_rs = "/src/assets/images/avata_org/";
    return { authen, url_img, url_img_rs,url_video }
}