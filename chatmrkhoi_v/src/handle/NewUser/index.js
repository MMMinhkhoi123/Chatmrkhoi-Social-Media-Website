import { reactive, ref } from "vue";
import { useRouter } from "vue-router"
import store from "../../store/index"
export default () => {
    const router = useRouter();
    const err = ref(null);
    const date = ref(null);
    const img_ref = ref(null);
    const img_load = reactive({
        file: null,
        url: null,
    })



    // REDIRECT STEP 1
    const GoStep1 = () => {
        var values = ref(null);
        const div = document.querySelectorAll("#ip");
        div.forEach((e) => {
            if(e.checked == true) {
                values.value = e.value;
            }
        })
        localStorage.setItem("gender", values.value);
        localStorage.setItem("action", 2);
        router.push({ query: { step:2} });
    }



    // REDIRECT STEP 2
    const GoStep2 = () => {
        err.value = null;
        if(date.value == null) {
            err.value = "TextNew.Date.err.Empty"
        }
        if(new Date(date.value).getTime() > new Date().getTime()) {
            err.value = "TextNew.Date.err.Valid"
        }
        if(err.value == null) {
            localStorage.setItem("birtday", date.value);
            localStorage.setItem("action", 3)
            router.push({ query: { step: 3 } })
        }
   }




//    REDIRECT MAIN DONE FILL INFOMATION
   const GoStep3 = () => {
    store.state.chat.data_after_upload = null;
    const datapost = {
        type: null,
        namefile: null,
        gender: localStorage.getItem("gender"),
        date: new Date(localStorage.getItem("birtday")).getTime()
                }
    if(img_ref.value == null) {
        if(img_load.file == null) {
            err.value = "TextNew.Avata.err.Empty"
        }
          else {
            const datal = {
            file: img_load.file,
            type: "img",
        }
        store.dispatch("chat/upload_clone", datal)

        const l = setInterval(() => {
                if (store.state.chat.data_after_upload != null) {
                    clearInterval(l)
                    datapost.namefile = store.state.chat.data_after_upload.namefile;
                    datapost.type = "cpt";
                    localStorage.removeItem("action")
                    store.dispatch("chat/upload_profile", datapost);
                    router.push({ name: "chat" });
                }
            },1000)
            }
        } else {
                datapost.type = "rs";
                datapost.namefile = img_ref.value;
                localStorage.removeItem("action")
                store.dispatch("chat/upload_profile", datapost);
                router.push({ name: "chat" });
        }
    }


    
    // CHOICE IMAGES RAMDOM
    const ChoiceImg = (event) => {
        let files = event.target.files[0];
        if(files.type.split("/")[0] != 'image') {
            err.value = "TextNew.Avata.err.Valid"
        } else {
            img_ref.value = null;
            img_load.file = files;
            const parent = document.querySelectorAll(".select__radio");
            parent.forEach((e, index) => {
                if( parent[index].checked == true) {
                    parent[index].checked = false;
                }
            })
            var reader = new FileReader();
            reader.onloadend = function() {
                img_load.url =  reader.result;
            }
        }
        reader.readAsDataURL(files);
    }

    // CHOICE IMAGES SYSTEM PROVIVE
    const ChoiceAvatadefault = (value, type) => {
        img_ref.value = value;
        img_load.file = null;
        img_load.url = null;
    }

    // GET LIST IMAGES SYSTEM PROVIVE
    const array_img = [];
    for(var x = 1 ; x <= 5; x++) {
        const m = "boy" + x + ".png";
        array_img.push(m);
    }

    for(var x = 1 ; x <= 5; x++) {
        const m = "girl" + x + ".png";
        array_img.push(m);
    }

    return {  GoStep1, GoStep2,GoStep3, err, ChoiceImg, date,img_load, img_ref, ChoiceAvatadefault,  err,array_img}
}