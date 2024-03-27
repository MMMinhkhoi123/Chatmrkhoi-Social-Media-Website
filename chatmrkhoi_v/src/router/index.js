import { createRouter, createWebHistory } from 'vue-router'
import store from "../store/index"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/test',
      name: 'test',
      component: () => import("../views/Page/test.vue")
    },
    {
      path: '/linkemail/id=:id&type=:type',
      name: 'test',
      redirect: to => {
        if(to.params.type == 'private') {
          return { name: 'chat', query: { id: to.params.id } }
        }
        return { name: 'chat', query: { idgroup: to.params.id } }
      },
    },
    {
      path: '/:pathMatch(.*)*',
      name: 'NotFound',
      component: () => import("../views/Page/Erro.vue")
    },
    {
      path: '/',
      name: 'blog',
      component: () => import("../views/Page/Blog.vue")
    },
    {
      path: '/acount',
      name: 'acount',
      beforeEnter: (to, from, next) => {
        to.query.action == 'SignUp' || to.query.action == 'SignIn' || to.query.action =='VerifyEmail' ||  to.query.action == 'Forget'
        ? to.query.action == 'SignUp' ||  to.query.action == 'SignIn'
           ?  next()  
           : to.query.action == 'VerifyEmail' && localStorage.getItem('Action') != null  
              ? next() 
              : to.query.action =='Forget' && CheckForget(to.query.step) == true 
                ? next() : next({ name: 'NotFound'})
        :  next({ name: 'NotFound'});
      },
     
      component: () => import("../views/Page/Acount.vue")
    },
    {
      path: '/New',
      name: 'New',
      beforeEnter: (to, from, next) => {
         CheckNew(to.query.step) == false ? next({ name: 'NotFound'}): next()
      },
      component: () => import("../views/Page/New.vue")
    },
    {
      path: '/main',
      name: 'main',
      beforeEnter: (to, from, next) => {
        if(localStorage.getItem("token") != null  && localStorage.getItem("token") != undefined) {
          store.dispatch("auth/authen", localStorage.getItem("token")).then(() => {
            const x = setInterval(() => {
             store.state.auth.authen == null
             ? (clearInterval(x), next({ name: "acount", query: { action: "SignIn" } }))
             :  (clearInterval(x), LoadData(localStorage.getItem("token")), next());
            }, 200);
           })
        }
      },
      component: () => import("../views/Page/Main.vue"),
      children: [
        {
          path: 'notice',
          name: 'notice',
          component: () => import("../views/Child-Page/Notice.vue"),
        },
        {
          path: 'chat',
          name: 'chat',
          component: () => import("../views/Child-Page/Chat.vue"),
        },
        {
          path: 'everyone',
          name: 'everyone',
          component: () => import("../views/Child-Page/Everyone.vue"),
        },
        {
          path: 'profile',
          name: 'profile',
          component: () => import("../views/Child-Page/Profile.vue"),
        },
        {
          path: 'setting',
          name: 'setting',
          component: () => import("../views/Child-Page/Setting.vue"),
        },
        {
          path: 'group',
          name: 'group',
          component: () => import("../views/Child-Page/Group.vue"),
        }
      ]
    }
  ]
})

function LoadData(data) {
    const x = setInterval(() => {
      if(data != null) {
          clearInterval(x);
          store.dispatch("chat/get_all_mess", data);
          store.dispatch("chat/get_all_action", data);
          store.dispatch("chat/get_emoji", data);

              // CALL APPLICATION DATA SERVER 
          store.dispatch("everyone/get_data_initial_friend",{key :"FRIEND",token: data})
          store.dispatch("everyone/get_data_initial_friendrequest", {key:"FRIEND_REQUEST", token: data});
          store.dispatch("everyone/get_data_initial_notfriend", {key:"NOT_FRIEND", token: data});
          store.dispatch("everyone/get_data_initial_sendfriendrequest", {key:"SEND_FRIEND_REQUEST", token: data });
          store.dispatch("everyone/get_data_sug_friend",data);
          
          store.dispatch("notice/getAllNotice", data);
          
          store.dispatch("chat/get_mygroup", data);
          store.dispatch("chat/get_array_connect", data);
          store.dispatch("chat/getpin",data);
      }
  }, 200)
}




//CHECK NEW USER
function CheckNew(step) {
  var check = false;
  step != null 
  ? check = true : null
  step == 1 ||   step == 2 ||  step == 3 ? check = true : check == null;
  step >= 1 && step <= 3 && Number(localStorage.getItem("action")) >= step 
    ? check = true 
    : check = false  
 return check;
}


//CHECK FORGET PASSWORD
 function CheckForget(step) {
  var check = false;
    step == null 
    ? check = true 
     : step == 1 
     ? check = true  
       : step > 1 && step <= 3 && Number(localStorage.getItem("action")) >= step 
          ? check = true 
          : null 
   return check;
 }




export default router
