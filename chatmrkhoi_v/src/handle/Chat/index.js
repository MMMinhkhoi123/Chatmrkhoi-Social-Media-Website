import { computed, ref } from 'vue'
import store from '../../store/index'
import { useRoute, useRouter } from 'vue-router'
export default () => {
    const route = useRoute();
    const namesearch = ref('');
    
    const router = useRouter();

    function upper(text) {
        return text.toUpperCase();
    }


    const get_info_group = (id)=> {
        return store.getters["chat/get_info_group"](id)
    }
    
    
    const get_info_friend = (id) => {
            return store.getters["everyone/get_your_friend_id"](id)
    }

    const url = import.meta.env.VITE_API_URL;

    var namedata = ref(null);


    // OPEN GROUP
    const addgroup = () => {
        if(route.query.id != null) {
            router.push({ query: { id: route.query.id, rom:route.query.rom ,action: 'add-group', step: 1 } })
        } else 
        if(route.query.idgroup != null) {
            router.push({ query: { idgroup: route.query.idgroup, rom:route.query.rom ,action: 'add-group', step: 1 } })
        } else {
            router.push({ query: {rom:route.query.rom ,action: 'add-group', step: 1 } })
        }
    }





    // GET MENU PERSON 
    const data = computed(() => {
        if(namesearch.value != '') {
            const array = [];
            store.state.chat.array_connect.forEach(element => {
                if(element.idgroup == null) {
                    if(upper(get_info_friend(element.idfriend)[0].fullname).search(upper(namesearch.value)) != -1) {
                        array.push(element);
                    }
                } else {
                    if(upper(get_info_group(element.idgroup)[0].name).search(upper(namesearch.value)) != -1) {
                        array.push(element);
                    }
                }

            });
            return array;
        }

        if(store.state.chat.array_connect == null) {
            return [];
        } 

        store.state.chat.array_connect.forEach((item, index) => {
            var index1 = index;
            const test = store.getters["chat/get_data_room"](item.coderoom);
            store.state.chat.array_connect.forEach((item2, index) => { 
                const test2 = store.getters["chat/get_data_room"](item2.coderoom);
                if(test.length > test2.length) {
                    var temp = store.state.chat.array_connect[index];
                    store.state.chat.array_connect[index] = store.state.chat.array_connect[index1];
                    store.state.chat.array_connect[index1] = temp;
                }
            })
        })

        store.state.chat.array_connect.forEach((item, index) => {
            var index1 = index;
            const test = store.getters["chat/get_data_room"](item.coderoom);
            if(test.length > 0) {
                store.state.chat.array_connect.forEach((item2, index) => { 
                    const test2 = store.getters["chat/get_data_room"](item2.coderoom);
                    if(test2.length > 0) {
                        if(test[test.length -1].time >  test2[test2.length -1].time) {
                            var temp = store.state.chat.array_connect[index];
                            store.state.chat.array_connect[index] = store.state.chat.array_connect[index1];
                            store.state.chat.array_connect[index1] = temp;
                        }
                    }
                })
            }
        })
        
        return store.state.chat.array_connect;
    })









    // MOVE INTO FROM CHAT AFTER CHOICE
    const move = (item) => {
        store.state.chat.group_new = item;
        store.state.chat.runscroll = false;
        store.state.avaible_chat.array_new = [];
        store.state.chat.data_index_pin = null;
        localStorage.setItem("data-select", item.coderoom)
        store.state.avaible_chat.action_group = !store.state.avaible_chat.action_group;
        const div = document.getElementById("action" + item.coderoom);
        if (div != null) {
            div.style.display = "none"
        }
        if (item.idgroup != null) {
            router.push('/main/chat?idgroup=' + item.idgroup );
        } else {
            router.push('/main/chat?id=' + item.idfriend);
        }
         store.state.avaible_chat.open_change_array = !store.state.avaible_chat.open_change_array;
    }



    // PRINT STATUS ACOUNT ( ONLINE OR OFFLINE )
    const convert_online = (id) => {
        if( sessionStorage.getItem("online" + id) == null) {
            return ''
        }
        return  sessionStorage.getItem("online" + id);
    }


    // GET STATUS ACCOUNT 
    const check_online = (id) => {
        var check = false;
        store.state.chat.data_online.forEach((e) => {
            if(e.id == id && e.status == 'online') {
                check = true;
            }
        })
        return check;
    }

    return  { 
        check_online,
        data,
        get_info_group,
        get_info_friend,
        url,
        move,
        addgroup,
        namedata,
        convert_online,
        namesearch,
     }
}