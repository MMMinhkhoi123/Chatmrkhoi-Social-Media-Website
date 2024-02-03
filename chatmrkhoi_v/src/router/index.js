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
          store.dispatch("everyone/get_send_friend_request", data);
          store.dispatch("everyone/get_friend_request", data);
          store.dispatch("everyone/get_not_friend", data);
          store.dispatch("everyone/get_friend", data);
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



  // LOAD DATA FROM SERVER
  function Load_Data() {
    store.dispatch("chat/get_all_mess");
    store.dispatch("chat/get_all_action");
    store.dispatch("chat/get_emoji");
    store.dispatch("everyone/get_send_friend_request");
    store.dispatch("everyone/get_friend_request");
    store.dispatch("everyone/get_not_friend");
    store.dispatch("everyone/get_friend");
    store.dispatch("chat/get_mygroup");
    store.dispatch("chat/get_array_connect");
    store.dispatch("chat/getpin");
  }

export default router
