import store from "../../store";
import datas from "../Screen_chat/index";
import {reactive, ref } from 'vue'

export default  () => {
    const { authen, url } = datas();
    const data_img = ref(null);
    const filemix = ref(null);
    const data_alert = reactive({
        title: '',
        content: '',
        img: 'alert.png'
    });

    var convert_date  = ref(null);

    const x = setInterval(() => {
       authen.value != null ? ( clearInterval(x), convert_date.value = new Date(authen.value.briday)): null
    }, 1000)

    // set new image
    const upload = (event) => {
        let files = event.target.files[0];
        files.type.split("/")[0] == "image"
        ? (filemix.value = files, loaderfile(files, data_img))
        : ( data_alert.title = 'Notification.UpInfo.Invalid.Title',
           data_alert.content = "Notification.UpInfo.Invalid.Content",
          store.state.avaible_chat.Notification.warning.update_info = true,
          store.state.avaible_chat.Notification.data = data_alert)
    }

    function loaderfile(files, x) {
        const reader = new FileReader();
        reader.onloadend = () =>  x.value = reader.result;
        reader.readAsDataURL(files);
    }

   // call api upload new image
    const posts = () =>  {
        filemix.value != null
        ? (store.dispatch("chat/upload_clone", { file: filemix.value, type: 'img' }), up_info()) 
        :  up_info();
    }
    

    // call api update infomation
    function up_info() {
        var gender = '';
        const cm = document.querySelectorAll("#gender__checkbox");
        cm.forEach((e) => {
            e.checked == true ? gender = e.value: null;
        })
        const data_up = {
            fullname: authen.value.fullname,
            birday: new Date(convert_date.value).getTime(),
            gender: gender,
            desc: authen.value.desc,
            avata: authen.value.images,
            type_img:  authen.value.type_img
        }
        data_up.fullname == '' || data_up.birday > new Date().getTime() || convert_date.value == null
        ? (alertdata("Notification.UpInfo.Invalid.Title","Notification.UpInfo.Invalid.Content2","alert.png"),store.state.avaible_chat.Notification.warning.update_info = true) 
        : (alertdata("Notification.UpInfo.Valid.Title","Notification.UpInfo.Valid.Content", "alert.png"),
            filemix.value != null ?  upfile(data_up) : (store.dispatch("chat/update_profile", data_up), store.state.avaible_chat.Notification.warning.update_info = true));
    }
    function upfile(data_up) {
        const m = setInterval(() => {
            store.state.chat.data_after_upload != null 
            ? ( clearInterval(m),
             data_up.avata = store.state.chat.data_after_upload.namefile,  data_up.type_img = 'cpt',
             store.dispatch("chat/update_profile", data_up),
             store.state.avaible_chat.Notification.warning.update_info = true, filemix.value = null
             ) 
            : null;
        },1000)
    }

    function alertdata(title, content, nameimg) {
        data_alert.title = title;
        data_alert.content = content;
        data_alert.img = nameimg;
        store.state.avaible_chat.Notification.data = data_alert;
    }

    return { upload, data_alert,convert_date,data_img, up_info,filemix, upload }

}