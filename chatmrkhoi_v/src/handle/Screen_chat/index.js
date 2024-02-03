import { computed, reactive, ref, watch } from 'vue';
import connect_private from "../../socket/handle_chat_private"
import store from '../../store/index';
import runtyping from "../../socket/runtyping";
import { useRoute, useRouter } from 'vue-router';

export default () => {
    const router = useRouter();
    const route = useRoute();
    var open_emoji = ref(false);
    var inx = 0;
    const id_obtans = ref(null)
    const room = ref(null)

    const array = (id) => {
        return "list for " + id
    }
    
    const url = import.meta.env.VITE_API_URL;
    // get friendadd
    const profile_friend = (value) => {
        return store.getters["everyone/get_your_friend_id"](Number(value))
    }

    const profile_not_friend = (value) => {
        return store.getters["everyone/get_friend_id"](value)
    }





   // ARRRAY GET MESSENGER

    const array_mess = computed(() => {
        const x = store.getters["chat/get_data_room"](localStorage.getItem("data-select"));

        if(store.state.avaible_chat.array_new.length > 0) {
            if(store.state.chat.data_index_pin != null  && store.state.chat.runscroll == false) {
                const m = x.filter((e, index) => index >= store.state.chat.data_index_pin.index) ;
              return m;     
            }
           return store.state.avaible_chat.array_new;
        }

        if(store.state.chat.data_index_pin != null) {
            const m = x.filter((e, index) => index >= store.state.chat.data_index_pin.index) ;
            return m   
          }

        if(store.state.avaible_chat.open_change_array) {}

        if(x.length > 15) {
            const l = x.filter((e, index) => index > (x.length - 15) );
            return l;
        }
        return x;
    })







    // get acount auth
    const authen = computed(() => {
        return store.state.auth.authen;
    })


    const convertdata = (value) => {
       return value.split("&3&")[2]
    }
    var typereply = ref(null);

    const icon = computed(() => {
        return store.getters["chat/get_emoji_number"]();
    })

    
    const send = () => {
        store.state.chat.runscroll = false;
        if ((store.state.chat.data_input == "" ) &&  (store.state.chat.array_img.length == 0)) {
          //  array_file.value = [];
            store.state.chat.array_img = [];
            avaible_rep.value = null;
            // data.value = "";
        }  else {
        // data data post user
        const dataxx = reactive({
            content: store.state.chat.data_input,
            id_friend: Number(id_obtans.value),
            room: localStorage.getItem("data-select"),
            reply: store.state.chat.data_rep == null ? null : store.state.chat.data_rep[0].id, 
            typerep: store.state.chat.data_rep == null ? null:  store.state.chat.data_rep[0].type,
            status: "group",
            idfile: store.state.chat.array_fileupload,
            listwatch: [],
        });

           // set status 
        if(route.query.id != null) {
            dataxx.status = "friend";         
        }
        store.dispatch("chat/sendmess", dataxx)
        const mm = setInterval((x) => {
            if(store.state.chat.data_after_send != null) {
                connect_private({ list:store.state.chat.data_after_send, sendmess: authen.value.id, code: localStorage.getItem("data-select")},  localStorage.getItem("data-select"));
                clearInterval(mm)
            }
        }, 200)


        store.state.chat.array_img = [];
        store.state.chat.data_rep = null;
        store.state.chat.data_input = "";

        const datas = {
        }
        datas.istyping= true;
        // runtyping({user_id: authen.value.id,
        //     room: localStorage.getItem("data-select"),
        //     istyping: false });
        }
    }


    var array_file = ref([]);

    const close = (value) => {
      //  array_file.value =  array_file.value.filter((e, index) => index  != value);
        store.state.chat.array_fileupload = store.state.chat.array_fileupload.filter((e, index) => index  != value)
        store.state.chat.array_img = store.state.chat.array_img.filter((e, index) => index  != value)
    }

    /// upload file 
    const uploadfiles = (event) => {
        let files = event.target.files[0];
        if(checktype_suport(files).status == true) {   
            const data_upload = reactive({
                file: files,
                type: checkfile(files),
            })
            store.dispatch("chat/upload",data_upload)
            document.getElementById("files").value = null;
        }
         else {
              alert(checktype_suport(files).mess)
        }
    }

    // check suport file
    function checktype_suport(value) {
        const type = reactive({
            typec: null,
            mess: null,
            status: true,
        });

        if(value.type.split("/")[0]  == "image") {
             type.typec = "img"
            if((value.size / 1000) >= 10 * 1080) {
                type.status = false;
                type.mess = "max size 10MB"
            }
        } else 
           if(value.type.split("/")[0] == "video") {
               type.typec = "video"
            if ( value.type.split("/")[1] != "mp4") {
                type.status = false;
                type.mess = "video not suport"
            }
            if((value.size / 1000) >= 10 * 1080) {
                type.status = false;
                type.mess = "max size 10MB"
            }
        } else {
            type.typec = "file"
            if((value.size / 1000) >= 10 * 1080) {
                    type.status = false;
                    type.mess = "max size 10MB"
            }
        }
        return type;
    }


    const reply  = (value, typse) => {
        store.state.chat.data_rep = array_mess.value.filter((e) => e.id == value);
        store.state.chat.data_rep[0].type = typse;
    }

    const reply_mess_show  = (value) => {
        return store.state.chat.array_all_mess.filter((e) => e.id == value);
    }

    // get type
    function checkfile(value) {
        if(value.type.substring(0,5) == "image") {
            return "image"
        }else
        if(value.type.substring(0,5) == 'video') {
            return "video"
        }
        else {
            return "file"
        }
       }



    // SCROLL 
    const introview = (value, type) => {
        store.state.chat.runscroll = true;
        if (type == 'text') {
           const div = document.querySelector(".form__row" + value);
           if(div != null) {
            hightlight(div)
            div.scrollIntoView();
           } else {
            const m = store.getters["chat/get_data_room"](localStorage.getItem("data-select"));
            m.forEach((e, index) => {
                if(e.id == value) {
                    store.state.chatdata_index_pin = index;
                }
            })
            hightlight(div)
            div.scrollIntoView();
           }
        } else {
           const div = document.getElementById("file__frame" + value);
           if(div != null) { 
            hightlight(div)
             div.scrollIntoView();
           }else {
            const m = store.getters["chat/get_data_room"](localStorage.getItem("data-select"));
            m.forEach((e, index) => {
                if(e.id == value) {
                    store.state.chatdata_index_pin = index;
                }
            })
            hightlight(div)
            div.scrollIntoView();
           }

        }
    }

    function hightlight(div) {
        div.getAttribute("class").includes("hightlight") == true 
        ?  (div.classList.remove("hightlight") , setTimeout(() => {  div.classList.add("hightlight") }, 300))  
        :   div.classList.add("hightlight") 
    }

    const showmenufeel = (idmesss, type) => {
        if (type =='text') {
            const div = document.getElementById("feel_text"+ idmesss);
            if(div.getAttribute("class").includes("show_emoji")) {
                div.classList.remove("show_emoji")
            } else {
                div.classList.add("show_emoji");
            }
        } else {
            const div = document.getElementById("feel_file"+ idmesss)
            if(div.getAttribute("class").includes("show_emoji")) {
                div.classList.remove("show_emoji")
            } else {
                div.classList.add("show_emoji");
            }
        }
    }

    function showmenu_off(idmesss, type) {
        const x =  document.getElementById("option_" + type + idmesss)
        const y =  document.getElementById("triangle_" + type + idmesss)
        const row = document.getElementById("mess" + idmesss);
        x.setAttribute("style", '')
        y.setAttribute("style", '')
        // screen__form
        if(x.getBoundingClientRect().y < 120) {
          row.style.zIndex = 100;
           x.style.top = 'calc(100% + 10px)'
           y.style.borderBottom= '10px solid #ffffff'
           y.style.bottom = '100%'
        } else {
            row.style.zIndex = 100;
            x.style.bottom = 'calc(100% + 10px)'
            y.style.borderTop = '10px solid #ffffff'
            y.style.top = '100%'
        }
    }

    function checkmenu(idmesss, type) {
        const revoke = document.querySelectorAll(".option__menu--list");
        const row = document.querySelectorAll(".form__row--main");
        row.forEach((e) => {
            e.setAttribute("style", '')
        })
        revoke.forEach((e, index) => {
            if(e.getAttribute("class").includes("showm_option") == true) {
                revoke[index].classList.remove("showm_option")
            }
        })
    }
    const showmenuoption = (idmesss, type) => {
        if (type =='text') {
            const div = document.getElementById("option_text"+ idmesss)
               if( div.getAttribute("class").includes("showm_option") == true) {
                   div.classList.remove("showm_option");
               } else {
                   checkmenu(idmesss, type);
                   div.classList.add("showm_option");
                   showmenu_off(idmesss, type);
               }
           } 
           if(type =='file') {
               const div = document.getElementById("option_file"+ idmesss)
               if( div.getAttribute("class").includes("showm_option") == true) {
                div.classList.remove("showm_option");
                } else {
                    checkmenu(idmesss, type);
                    div.classList.add("showm_option");
                    showmenu_off(idmesss, type);
                }
           }
           if(type == 'unmess') {
            const div = document.getElementById("option_unmess"+ idmesss)
               if( div.getAttribute("class").includes("showm_option") == true) {
                div.classList.remove("showm_option");
                } else {
                    checkmenu();
                    div.classList.add("showm_option");
                    showmenu_off(idmesss, type);
                }
           }
    }


   const get_info_group = (id)=> {
    return store.getters["chat/get_info_group"](Number(id))
}
var status_smoth = ref(true)


// ADD FEEL NMESS
const post_feel = (idmessx, feel, types) => {

    if(types == "file") {
        const div = document.getElementById("feel_file"+ idmessx);
        div.classList.remove("show_emoji");
    }else {
        const div = document.getElementById("feel_text"+ idmessx);
        div.classList.remove("show_emoji");
        const divrow = document.querySelector(".form__row" + idmessx);
        divrow.style.marginBottom = '25px';
    }

  const data = reactive({
     type: types,
     idmess: idmessx,
     feel: feel,
  })
  store.dispatch("chat/addfeel", data).then((e) => {
     const m = setInterval(() => {
        if(store.state.chat.data_after_update != null) {
            clearInterval(m);
            const x = array_mess.value.filter((e) => e.id == idmessx);
            connect_private( { feelx: x[0] ,data: store.state.chat.data_after_update.messnew, feelu: authen.value.id  }, localStorage.getItem("data-select"))
        } 
     }, 500)
  });
  status_smoth.value = false
}

const outfeel = (idmesss, type) => {
     if (type == 'text') {
            const div = document.getElementById("feel_text"+ idmesss);
                div.classList.remove("show_emoji")
           
        } else {
            const div = document.getElementById("feel_file"+ idmesss)
                div.classList.remove("show_emoji")
        }
}


const  deletefeel = (ids,types) => {
    store.state.chat.data_after_update = null;
    const datax = {
        type: types,
        id: ids,
    }
    store.dispatch("chat/deletefeel",datax);
      const m = setInterval(() => {
        if(store.state.chat.data_after_update != null) {
            clearInterval(m); 
            status_smoth.value = false;
            store.state.chat.data_after_update.unfeel = authen.value.id;
            connect_private(store.state.chat.data_after_update,  localStorage.getItem("data-select"))
            if(store.state.chat.data_after_update.listfeel.length == 0) {
                const div = document.querySelector("#form__row" + ids);
                const div2 = document.querySelector("#file__frame" + ids);
                if(types == 'file') {
                    div2.style.marginBottom = "5px";
                }else {
                    div.style.marginBottom = "5px";
                }
            }
        }
    })
}


const check_revoke = (item) => {
    store.state.avaible_chat.unmess.idmess = item.id;
    if (item.id_user == authen.value.id) {
        store.state.avaible_chat.unmess.status =  false;
    } else {
        store.state.avaible_chat.unmess.status = true;
    }
  }


  const unmesschose = (item) => {
    const datapost = {
      type: 'one',
      idmess:  item.id,
      iduser: authen.value.id
    }
    store.dispatch("chat/unmess", datapost);
  }



  const array_icon = (array, type) => {
    const x = array.filter((e) => e.type == type );
    return x;
}
const array_icon_index = (array) => {
    const x = array.filter((e , index) => index < 2);
    return x;
} 
const checkincludes = (array) => {
var feel = false;
array.forEach(element => {
    if (element.iduser == authen.value.id) {
        feel = true;
    }
});

return feel;
}
const checkshowmess = (array) => {
var status = true;
if (array == null) {
    return false
}
array.forEach((e) => {
    if(authen.value.id == e.iduser && e.type == 'one') {
        status = false;
    }
    if (e.type == "all") {
        status = false;
    }
})
return status;
}
const showrevoke = (array) => {
    var status = true;
    if(array == null) {
        return true
    }
    array.forEach((e) => {
        if (e.type == "all") {
            status = false;
        }
    })
    return status;
}
const showrevoke2 = (array) => {
var status = false;
array.forEach((e) => {
    if ((authen.value.id == e.iduser && e.type == 'one')) {
        status = true;
    } 
})
return status;
}

const show_detail_feel = (item,type) => 
{
    store.state.avaible_chat.feel.mess = item;
    store.state.avaible_chat.feel.type = type;
    store.state.avaible_chat.feel.status = true
}



const pin_mess = (item, type) => {
    store.state.chat.data_after_send = null;
    const data = {
        type: type,
        idmess: item.id,
        idgroup: item.id_group,
        idfriend: item.id_friend == authen.value.id ? item.id_user :  item.id_friend,
    }
    store.dispatch("chat/pin", data)
    const x = setInterval(() => {
        if(store.state.chat.data_after_send != null) {
            clearInterval(x);
           connect_private({ pins: authen.value.id, pintype: store.state.chat.data_pin_after, mess: store.state.chat.data_after_send  },  localStorage.getItem("data-select"))
        }
    },300)
    store.dispatch("chat/getpin")
}



const array_pin_room = computed(() => {
    if(store.state.chat.array_pin) {};
    return store.getters["chat/get_pin_room"](localStorage.getItem("data-select"));
})


const checkinclupin = (idmess, type) => {
    var status = true;
    array_pin_room.value.forEach((e) => {
        if(e.idmess == idmess && e.iduser == authen.value.id) {
            status = false;
        }
    })
    store.state.chat.array_all_mess.forEach((e) => {
        if(e.id == idmess) {
           if(e.content != '' && type == 'file') {
             status = false; 
           }
        }
    })
    return status;
}

const check_head = (idmess, type) => {
    var status = true;
    store.state.chat.array_all_mess.forEach((e) => {
        if(e.id == idmess) {
           if( e.content != '' && type == 'file') {
             status = false; 
           }
        }
    })
    return status;
} 

const checkunghim = (idmess) => {
    store.state.chat.data_after_send = null;
    var datax = null;
    array_pin_room.value.forEach((e) => {
        if(e.idmess == idmess && e.iduser == authen.value.id) {
            datax = e.id;
        }
    })
    store.state.chat.array_pin = store.state.chat.array_pin.filter((e) => e.id != datax)
    store.dispatch("chat/unpin",datax);
    const x = setInterval(() => {
        if(store.state.chat.data_after_send != null) {
            clearInterval(x);          
            connect_private({ pins: authen.value.id,pintype2: datax, mess:store.state.chat.data_after_send },  localStorage.getItem("data-select"))
        }
    })
}



const handlepin = (data) => {
    return data.split("&")[0];
}
const def = computed (() => {
    return store.state.chat.data_input
})
// watch(def, () => {
//     connect_private({ typing: def.value, typeingu: authen.value.id, code: localStorage.getItem("data-select")}, localStorage.getItem("data-select"))
// })

const check_move = (item, type) => {
    store.state.avaible_chat.open_send = true;
    store.state.avaible_chat.save_move.data = item;
    store.state.avaible_chat.save_move.type = type;
}

const convert_time_hour = (time) => {
    const dates = new Date(Number(time))
    return dates.getHours() + " : " + dates.getMinutes()
}




const checkscrooll = (event) => {
    store.state.chat.runscroll = true;
    localStorage.removeItem("pin")

    const item = document.querySelectorAll(".form__row");
    if(item.length > 1) {
        if(event.target.getBoundingClientRect().y - item[0].getBoundingClientRect().y  <= 20 ) {

            store.state.avaible_chat.open_change_array = ! store.state.avaible_chat.open_change_array;

             var indexx = 0;
             const m = store.getters["chat/get_data_room"](localStorage.getItem("data-select"));
             m.forEach((e, index) => {
                if(e.id == array_mess.value[0].id) {
                    indexx = index;
                }
             })
             var arraynew = null;
             if(indexx - 6 > 0) {
                 arraynew = m.filter((e, index) => index > indexx - 6 && index < indexx)
             }else {
                 arraynew = m.filter((e, index) =>  index < indexx);
             }
             const array = [...arraynew,...array_mess.value];
             if(array.length != m.length) {
                store.state.avaible_chat.array_new = array;
             }      
          }
    }
  }



const convet_date_start = (time , month) => {
    const dart = new Date(time);
    return dart.getDate() + month + (dart.getMonth() + 1) + " ,"+ dart.getFullYear()
}
var count = 0;
const checkincludesperson = (item) => {
    if(item.watch.includes(authen.value.id) == true) {
        return true;
    } else {
        // count = count + 1;
        // // post watch
        // const data = {
        //     idmess: item.id,
        //     idperson: authen.value.id,
        // }
        // store.dispatch("chat/watchmess", data);
    }
}
// $router.push({ name: 'chat', query: { id: 1, action: 'detail', type: 'group' } })

const showdetail = () => {
    store.state.chat.id_detail = id_obtans.value;

    if(route.query.id == null) {
        localStorage.setItem("iddetail", route.query.idgroup)
        router.push("/main/chat?action=detail&&idgroup=" + route.query.idgroup);
    } else {
        localStorage.setItem("iddetail", route.query.id)
        router.push("/main/chat?action=detail&&id=" +  route.query.id);
    }
}



const convert_group_status = (data) => {
    if(data.split("&")[0] == "add") {
        return "add";
    } if(data.split("&")[0] == "out") {
        return "out"
    } else {
        return "kick"
    }
}






const dowload = (name) => {
    store.dispatch("chat/dowload", name);
}

const data_profile = (id) => {
    var data = null;
    store.state.everyone.array_send_request_friend.forEach((e , index) => {
        if(e.id == id) {
            data = store.state.everyone.array_send_request_friend[index];
        }
    })
    if(store.state.everyone.array_request_friend != null) {
        store.state.everyone.array_request_friend.forEach((e , index) => {
            if(e.id == id) {
                data = store.state.everyone.array_request_friend[index];
            }
        })
    }
    if(store.state.everyone.array_not_friend != null) {
        store.state.everyone.array_not_friend.forEach((e, index) => {
            if(e.id == id) {
                data = store.state.everyone.array_not_friend[index];
            }
        })
    }
    if(store.state.everyone.array_friend != null) {
        store.state.everyone.array_friend.forEach((e, index) => {
            if(e.id == id) {
                data =  store.state.everyone.array_friend[index];
            }
        })
    }
    return data;
}

const deletegroup = () => {
    store.state.chat.array_connect = store.state.chat.array_connect.filter((e) => e.idgroup != localStorage.getItem("iddetail"));
    store.state.chat.array_mygroup = store.state.chat.array_mygroup.filter((e) => e.id != localStorage.getItem("iddetail"));
    store.dispatch("chat/delete_group", Number(localStorage.getItem("iddetail")));
    connect_private({ deletegroup: authen.value.id,
        idgroup: Number(localStorage.getItem("iddetail")), code: localStorage.getItem("data-select") }, localStorage.getItem("data-select"))
    localStorage.removeItem("iddetail")
    localStorage.removeItem("data-select")
    store.state.avaible_chat.action_group = !store.state.avaible_chat.action_group;
    store.state.avaible_chat.Notification.warning.delete_group = false;
    router.push({ query: {} })
}




const outgroup = () => {
    store.state.chat.data_after_send = null;
    store.state.chat.array_connect = store.state.chat.array_connect.filter((e) => e.idgroup != localStorage.getItem("iddetail"));   
    store.state.chat.array_mygroup = store.state.chat.array_mygroup.filter((e) => e.id != localStorage.getItem("iddetail"));

    var codenw = localStorage.getItem("data-select").split("&")[0] ;
    localStorage.getItem("data-select").split("&").forEach((e, index) => {
        if(e != authen.value.id && index > 0) {
            codenw = codenw + "&" + e;
        }
    })
    const data = {
        id: Number(localStorage.getItem("iddetail")),
        code: codenw,
    }

    localStorage.setItem("data-select2",localStorage.getItem("data-select"));
    
    router.push({ query:{} })
    localStorage.removeItem("data-select")
    store.state.avaible_chat.action_group = !store.state.avaible_chat.action_group; 


    store.dispatch("chat/out_group", data).then((e) => {
       const m = setInterval(() => {
        if( store.state.chat.data_after_send != null) {
            clearInterval(m);
            // send private 
            connect_private({ 
                 outgroup: authen.value.id,
                 data: store.state.chat.data_after_send,
                 code: codenw,
                 code_old: localStorage.getItem("data-select2") },
                 localStorage.getItem("data-select2") )
                 
        }

       }, 1000) 
    });
}

var indexx = 0; 
   // uploaddatx
   const adddrop = (event) => {
    indexx = 0;
    const filearray = event.dataTransfer.files;
    if(filearray.length >= 3) {
        const data ={
            img: 'drag-and-drop.png',
            title: 'UPLOAD FAIL',
            content: 'You can only upload up to 2 photos at a time',
          };
          store.state.avaible_chat.Notification.data = data;
        store.state.avaible_chat.Notification.warning.upload_img = true;
    } else if((store.state.chat.array_img.length + filearray.length) > 4) {
        const data ={
            img: 'error.png',
            title: 'COUNT FAIL',
            content: 'Up to 4 files at a time',
          };
          store.state.avaible_chat.Notification.data = data;
        store.state.avaible_chat.Notification.warning.upload_img = true;
    } else { 
        upnax(filearray);
    }
    const x = document.getElementById("drag");
        x.style.display = "none";
    }

function upnax(filearray) {
        store.state.chat.data_uploadx = null;
        up(filearray[indexx]);
        console.log(filearray.length -1 + ": " + indexx)
        if((filearray.length -1) != indexx) {
            const m = setInterval(() => {
                if(store.state.chat.data_uploadx  != null) {
                    clearInterval(m);
                    indexx = indexx + 1;
                    upnax(filearray);
                }
             }, 500);
        }
}

function up(files) {
    if(checktype_suport(files).status == true) {   
        const data_upload = reactive({
            file: files,
            type: checkfile(files),
        })
        store.dispatch("chat/upload",data_upload)
        document.getElementById("files").value = null;
    }
     else {
          alert("not suport" + checktype_suport(files).mess)
    }
}
return {
    outgroup,
    deletegroup,
    convert_group_status,
    showdetail,
    dowload,
    data_profile,
    checkincludesperson,
    convet_date_start,
    checkscrooll,
    convert_time_hour,
    check_move,
    handlepin ,
    checkunghim,
    checkinclupin,
    outfeel,
    show_detail_feel,
    array_icon ,
    array_icon_index,
    checkincludes,
    showrevoke2 ,
    showrevoke,
    check_revoke,
    adddrop,
    checkshowmess ,
    room,
    array_pin_room,
    pin_mess,
    post_feel,
    get_info_group,
    status_smoth,
    typereply, 
    open_emoji,
    icon ,
    introview, 
    showmenufeel,
    reply_mess_show,
    array, send, 
    id_obtans, 
    room, 
    array_mess,
    authen,
    profile_friend,
    uploadfiles,
    close,
    url, 
    convertdata ,
    reply,
    // type,
    deletefeel,
    showmenuoption,
    unmesschose,
    check_head,
 }
}