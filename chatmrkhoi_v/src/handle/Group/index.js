import data from "../Screen_chat/index"
import store from '../../store/index'
import { useRoute, useRouter } from 'vue-router';
import { computed, ref } from 'vue'
export default () => {
    const { authen, outgroup}  = data();
    const route = useRoute();
    const router = useRouter();
        const dates = computed(() => {

            if(store.state.chat.data_search_group != '') {
                const array = [];
                store.state.chat.array_mygroup.forEach(element => {
                        if(upper(element.name).search(upper(store.state.chat.data_search_group)) != -1) {
                            array.push(element);
                        }
                });
                store.state.avaible_chat.count_group = array.length;
                return array;
            }
            if(route.query.action == 'my') {
                const array = store.state.chat.array_mygroup.filter((e) => e.master == authen.value.id); 
                if(store.state.chat.data_search_group != '') {
                    const array = [];
                    array.forEach(element => {
                            if(upper(element.name).search(upper(store.state.chat.data_search_group)) != -1) {
                                array.push(element);
                            }
                    });
                    store.state.avaible_chat.count_group = array.length;
                    return array;
                }
                store.state.avaible_chat.count_group = array.length;
                return array;
            }
            if(route.query.action == 'your') {
                const array = store.state.chat.array_mygroup.filter((e) => e.master != authen.value.id ); 
                if(store.state.chat.data_search_group != '') {
                const array = [];
                array.forEach(element => {
                        if(upper(element.name).search(upper(store.state.chat.data_search_group)) != -1) {
                            array.push(element);
                        }
                });
                store.state.avaible_chat.count_group = array.length;
                return array;
            }
            store.state.avaible_chat.count_group = array.length;
                return array;
            }
            store.state.avaible_chat.count_group = store.state.chat.array_mygroup.length;
            return store.state.chat.array_mygroup;
        })

        function upper(text) {
            return text.toUpperCase();
        }
        const selectgroup = (item) => {
            localStorage.setItem("data-select", item.coderoom);
            router.push('/main/chat?idgroup=' + item.id );
            localStorage.setItem("select-menu", 1);
        }
        const setupremove = (item) => {
            const data = {
                img: 'delete.png',
                title: 'WARNING',
                content: 'All data belonging to this group will be permanently deleted !'
            }
            store.state.avaible_chat.Notification.data = data;
            store.state.avaible_chat.Notification.warning.delete_group = true;

            localStorage.setItem("data-select", item.coderoom);
            localStorage.setItem("iddetail", item.id);
        }
        const setupout = (item) => {
            localStorage.setItem("data-select", item.coderoom);
            localStorage.setItem("iddetail", item.id);
            outgroup();
        }
        const open = ref(false);


        const namesearch = ref('');

        function upper(text) {
            return text.toUpperCase();
        }
        const array_friend = computed(() => {
            if(namesearch.value != '') {
                const array = [];
                store.state.everyone.array_friend.forEach((e) => {
                    if(upper(e.fullname).search(upper(namesearch.value)) != -1) {
                        array.push(e);
                    }
                })
                return array;
            }
            return store.state.everyone.array_friend;
        })
        
        const addgroup = (id) => {
            store.state.chat.array_addgroup.includes(id) == false ?
                store.state.chat.array_addgroup.push(id)
                : store.state.chat.array_addgroup =  poparry(store.state.chat.array_addgroup,id);
        }

        function poparry(array, id) {
            var indexx = null;
            array.forEach((element, index) => {
                if(element == id ){
                  indexx = index;
                }
            });
            return array.filter((e, index) => index != indexx);
        }

        const checknext = () => {
                if(store.state.chat.array_addgroup.length <= 1) {
                    const data = {
                        title: 'Notification.Addgroup.Step1.Title',
                        content: 'Notification.Addgroup.Step1.Content',
                        img: 'alert.png',
                    }
                    store.state.avaible_chat.Notification.data = data;
                    store.state.avaible_chat.Notification.warning.addgroup = true;
                } else {
                    if(route.query.id != null && route.query.rom != null) {
                   router.push({ query: { id: route.query.id, rom:route.query.rom ,action: 'add-group', step: 2 } })
                    } else {
                        router.push("/main/chat?action=add-group&step=2");
                    }
                }
            }


            
        return { dates, open, selectgroup, setupremove, outgroup,setupout,checknext,addgroup,namesearch,array_friend }
}