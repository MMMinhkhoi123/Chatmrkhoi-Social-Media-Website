import store from "../store/index"
import socket from "../socket/common"

// import connects from "../socket/connect_private";

const { stompClient } = socket()
export default () => {
    function connect() {
        store.state.chat.array_connect.forEach(e => {
            stompClient.subscribe("/user/" + e.coderoom + "/private", tick => {
                const x = JSON.parse(tick.body);

                // WATCH
                if(x.watch != null && x.watch != store.state.auth.authen.id ) {
                    store.state.chat.array_all_mess.forEach((e, index) => {
                        if(e.id == x.idmess) {
                            var c = false;
                            store.state.chat.array_all_mess[index].listwatch.forEach((xc)=> {
                                if(xc.id == x.id) {
                                    c = true;
                                }
                            })

                            if (c == false) {
                                store.state.chat.array_all_mess[index].listwatch.push(x)
                            }
                        }
                    })
                }


                // TYPINNG
                if(x.typeingu != null && x.typeingu !=  store.state.auth.authen.id ) {
                    if(page != null) {
                        if(x.typing == '') {
                            page.style.display = "none";
                        } else {
                            page.style.display = "block";
                        }
                    }
                }

                // LEAVE GROUP
                if(x.outgroup != null && x.outgroup !=  store.state.auth.authen.id) {
                    store.state.chat.array_connect.forEach((e, index) => {
                        if(x.code_old == e.coderoom) {
                            store.state.chat.array_connect[index].coderoom = x.code; 
                        }
                    })
                    connect()
                    store.state.chat.array_all_mess.forEach((e, index) => {
                        if(x.code_old == e.room) {
                            if(store.state.avaible_chat.array_new.length > 0) {
                                store.state.avaible_chat.array_new.forEach((xm, index) => {
                                    if(xm.room == x.code_old) {
                                        store.state.avaible_chat.array_new[index] = x.code;
                                    }
                                })
                            }
                            store.state.chat.array_all_mess[index].room = x.code; 
                        }
                    })
                    var check = true;
                    store.state.chat.array_all_mess.forEach((e) => {
                        if(x.data[0].id == e.id) {
                            check = false;
                        }
                    })
                    if (check == true) {
                        if(store.state.avaible_chat.array_new.length > 0) {
                            store.state.avaible_chat.array_new.push(x.data[0])
                        }
                        store.state.chat.array_all_mess.push(x.data[0])
                    }
                    if(localStorage.getItem("data-select") == x.code_old) {
                        localStorage.setItem("data-select", x.code);
                        store.state.avaible_chat.open_change_array = !store.state.avaible_chat.open_change_array;
                        store.state.avaible_chat.action_group = !store.state.avaible_chat.action_group;
                    } else {
                        localStorage.setItem("data-select", x.code);
                        store.state.avaible_chat.action_group = !store.state.avaible_chat.action_group;
                    }
                }

                // REMOVE GROUP
                if (x.deletegroup != null && x.deletegroup != store.state.auth.authen.id) {
                    store.state.chat.array_connect = store.state.chat.array_connect.filter((e) => e.idgroup != x.idgroup);
                    store.state.chat.array_mygroup = store.state.chat.array_mygroup.filter((e) => e.id != x.idgroup);
                    if(localStorage.getItem("data-select") == x.code) {
                        const data = {
                            img: 'unfriend.png',
                            title: 'Notification.DeleteGroup.Title',
                            content: 'Notification.DeleteGroup.Content'
                        }
                        store.state.avaible_chat.Notification.data = data;
                        store.state.avaible_chat.Notification.warning.user_remove_group = true;
                        store.state.avaible_chat.action_group = !store.state.avaible_chat.action_group;
                        localStorage.removeItem("data-select");
                        store.state.avaible_chat.open_change_array = !store.state.avaible_chat.open_change_array;
                    }
                }

                // ADD USER GROUP ( USER NEW )
                if(x.newuser != null 
                    && x.newuser != store.state.auth.authen.id
                    && x.useraply ==  store.state.auth.authen.id ) {
                    var checkx = true;
                    store.state.chat.array_mygroup.forEach((e) => {
                        if(e.id == x.newgroup.id) {
                            checkx = false;
                        }
                    })
                    if(checkx == true) {
                        store.state.chat.array_mygroup.push(x.newgroup);
                    }
                    var check = true;
                    store.state.chat.array_connect.forEach((e) => {
                        if(e.coderoom == x.newconnect.coderoom) {
                            check = false;
                        }
                    })
                    if(check == true) {
                        store.state.chat.array_connect.push(x.newconnect);  

                        store.state.chat.array_all_mess.forEach((e, index) => {
                            if(e.id_group == x.idgroup) {
                                if(store.state.avaible_chat.array_new.length > 0) {
                                    store.state.avaible_chat.array_new.forEach((xm, index) => {
                                        if(xm.id_group ==e.id_group) {
                                            store.state.avaible_chat.array_new[index].room = x.code;
                                        }
                                    })
                                }
                                store.state.chat.array_all_mess[index].room = x.code;
                            }
                        })
                        var check2 = false;
                        store.state.chat.array_all_mess.forEach((e, index) => {
                            if(e.id == x.data.mess_reponse.id) {
                                check2 = true;
                            }
                        })
                        if(check2 == false) {
                            if(store.state.avaible_chat.array_new.length > 0) {
                                store.state.avaible_chat.array_new.push(x.data.mess_reponse);
                            }
                            store.state.chat.array_all_mess.push(x.data.mess_reponse);
                        }
                        connect(); 
                    }
                }


                // KICK USER GROUP
                if (x.kick != null && x.kick != store.state.auth.authen.id) {
                    if(x.userkick == store.state.auth.authen.id) {
                        store.state.chat.array_connect = store.state.chat.array_connect.filter((e) => e.idgroup != x.idgroup);
                        connect();
                        if(localStorage.getItem("data-select") == x.code_old) {
                            const data = {
                                img: 'kick.png',
                                title: 'Notification.KickUser.Title',
                                content: 'Notification.KickUser.Content'
                            }
                            store.state.avaible_chat.Notification.data = data;
                            store.state.avaible_chat.Notification.warning.kick = true;
                            store.state.avaible_chat.open_change_array = !store.state.avaible_chat.open_change_array;
                            store.state.avaible_chat.action_group = !store.state.avaible_chat.action_group;
                            localStorage.removeItem("data-select");
                        }
                    } else {
                        store.state.chat.array_connect.forEach((e, index) => {
                         if(e.idgroup ==  x.idgroup) {
                                 store.state.chat.array_connect[index].coderoom = x.code;
                             }
                         })   
                         connect();
                         store.state.chat.array_all_mess.forEach((e, index) => {
                             if(e.id_group ==  x.idgroup) {
                                if(store.state.avaible_chat.array_new.length > 0) {
                                    store.state.avaible_chat.array_new.forEach((xm, index) => {
                                        if(xm.id_group == e.id_group) {
                                            store.state.avaible_chat.array_new[index].room = x.code;
                                        }
                                    })
                                }
                                 store.state.chat.array_all_mess[index].room =x.code;
                             }
                         })
                         if(store.state.avaible_chat.array_new.length > 0) {
                            store.state.avaible_chat.array_new.push(x.data);
                        }
                         store.state.chat.array_all_mess.push(x.data);
                         if(localStorage.getItem("data-select") == x.code_old) {
                             localStorage.setItem("data-select", x.code);
                             store.state.avaible_chat.open_change_array = !store.state.avaible_chat.open_change_array;
                         }
                    }
                 }


                // ADD USER GROUP (USER OLD)
                if(x.statusgroup !=  null && x.statusgroup != store.state.auth.authen.id ) {
                    store.state.chat.array_connect.forEach((e, index) => {
                        if(e.idgroup == x.idgroup) {
                            store.state.chat.array_connect[index].coderoom = x.code;
                        }
                    })
                    connect();
                    store.state.chat.array_all_mess.forEach((e, index) => {
                        if(e.id_group == x.idgroup) {
                            if(store.state.avaible_chat.array_new.length > 0) {
                                store.state.avaible_chat.array_new.forEach((xm, index) => {
                                    if(xm.id_group == e.id_group) {
                                        store.state.avaible_chat.array_new[index].room = x.code;
                                    }
                                })
                                store.state.avaible_chat.array_new[index].room = x.code;
                            }
                            store.state.chat.array_all_mess[index].room = x.code;
                        }
                    })
                    var check = false;
                    store.state.chat.array_all_mess.forEach((e, index) => {
                        if(e.id == x.data.mess_reponse.id) {
                            check = true;
                        }
                    })
                    if(check == false) {
                        if(store.state.avaible_chat.array_new.length > 0) {
                            store.state.avaible_chat.array_new.push(x.data.mess_reponse);
                        }
                        store.state.chat.array_all_mess.push(x.data.mess_reponse);
                    }
                    if(localStorage.getItem("data-select") == x.code_old) {
                        localStorage.setItem("data-select", x.code);
                        store.state.avaible_chat.open_change_array = !store.state.avaible_chat.open_change_array;
                     }
                }

                //TRANSACTION MESSENGER
                if(x.move != null && x.move !=  store.state.auth.authen.id) {
                    x.list.forEach(element => {
                        if(store.state.avaible_chat.array_new.length > 0) {
                            store.state.avaible_chat.array_new.push(element);
                        }
                        store.state.chat.array_all_mess.push(element);
                    });
                }

                //REVOKE MESSENGER
                if (x.revoke != null && x.revoke != store.state.auth.authen.id) {
                    store.state.chat.array_all_mess.forEach((e, index) => {
                        if (e.id == x.mess.id) {
                            if(store.state.avaible_chat.array_new.length > 0) {
                                store.state.avaible_chat.array_new.forEach((xm, index) => {
                                    if(xm.id == e.id) {
                                        store.state.avaible_chat.array_new[index] = x.mess;
                                    }
                                })
                            }
                            store.state.chat.array_all_mess[index] = x.mess;
                        }
                    })
                }

                //ADD PINED MESSENGER
                if (x.pintype != null && x.pins != store.state.auth.authen.id) {
                    x.mess.forEach((e) => {
                        if(store.state.avaible_chat.array_new.length > 0) {
                            store.state.avaible_chat.array_new.push(e);
                        }
                        store.state.chat.array_all_mess.push(e);
                    })
                    store.state.chat.array_pin.push(x.pintype)
                }

                // UNPINED MESSENGER
                if (x.pintype2 != null && x.pins != store.state.auth.authen.id) {
                    x.mess.forEach((e) => {
                        if(store.state.avaible_chat.array_new.length > 0) {
                            store.state.avaible_chat.array_new.push(e);
                        }
                        store.state.chat.array_all_mess.push(e);
                    })
                    store.state.chat.array_pin = store.state.chat.array_pin.filter((e) => e.id != x.pintype2);
                }

                //UNFEEEL FEEL MESSENGER
                if(x.unfeel != null && x.unfeel != store.state.auth.authen.id) {
                    store.state.chat.array_all_mess.forEach((e, index) => {
                        if(e.id == x.id) {
                            if(store.state.avaible_chat.array_new.length > 0) {
                                store.state.avaible_chat.array_new.forEach((xm, index) => {
                                    if(xm.id == e.id) {
                                        store.state.avaible_chat.array_new[index] = x;
                                    }
                                })
                            }
                            store.state.chat.array_all_mess[index] = x;
                            if(x.listfeel.length == 0 && localStorage.getItem("data-selec") == x.coderoom) {
                                const div = document.querySelector("#form__row" + x.id);
                                const div2 = document.querySelector("#file__frame" + x.id);
                                if(div.style.marginBottom == '10px') {
                                    div.style.marginBottom = "5px";
                                }else {
                                    div2.style.marginBottom = "5px";
                                }
                            }
                        }
                    })
                }


                // ADD FEEL MESSENGER
                if(x.feelx != null  && x.feelu != store.state.auth.authen.id) {
                    store.state.chat.array_all_mess.forEach((e, index) => {
                        if(e.id == x.feelx.id) {
                            if(store.state.avaible_chat.array_new.length > 0) {
                                store.state.avaible_chat.array_new.forEach((xm, index) => {
                                    if(xm.id == e.id) {
                                        store.state.avaible_chat.array_new[index] = x.feelx;
                                    }
                                })
                            }
                            store.state.chat.array_all_mess[index] = x.feelx;
                            if(x.feelx.listfeel.length == 0 && localStorage.getItem("data-selec") == x.feelx.coderoom) {
                                const div = document.querySelector("#form__row" + x.feelx.id);
                                const div2 = document.querySelector("#file__frame" + x.feelx.id);
                                if(div.style.marginBottom == '10px') {
                                    div.style.marginBottom = "5px";
                                }else {
                                    div2.style.marginBottom = "5px";
                                }
                            }
                        }
                    })
                    var check = true;
                    store.state.chat.array_all_mess.forEach((e) => {
                        if(e.id == x.data.id) {
                            check = false;
                        }
                    })
                    if(check == true) {
                        if(store.state.avaible_chat.array_new.length > 0) {
                            store.state.avaible_chat.array_new.push(x.data);
                        }
                        store.state.chat.array_all_mess.push(x.data);
                    }
                }

                // SEND MESSENGER
                if(x.sendmess != null) {
                    if(x.sendmess == store.state.auth.authen.id) {
                        // update status
                        x.list.forEach((e) => { 
                            store.state.chat.array_all_mess.forEach((em, index) => {
                                if(em.id == e.id) {
                                    if(store.state.avaible_chat.array_new.length > 0) {
                                        store.state.avaible_chat.array_new.forEach((xm, index) => {
                                            if(xm.id == em.id) {
                                                store.state.avaible_chat.array_new[index].read = true;
                                            }
                                        })
                                    }
                                    store.state.chat.array_all_mess[index].read = true;
                                }
                            })
                        })
                    }
                    if( x.sendmess != store.state.auth.authen.id) {
                        x.list.forEach((e) => {
                            var check = true;
                            store.state.chat.array_all_mess.forEach((em) => {
                                if(em.id == e.id) {
                                    check = false;
                                }
                            })
                            if(check == true) {  
                                if(store.state.avaible_chat.array_new.length > 0) {
                                    store.state.avaible_chat.array_new.push(e);
                                }             
                                store.state.chat.array_all_mess.push(e);
                            }
                        })
                    }


                }
            })
        });
    }
    if(stompClient.connected == false) {
        stompClient.connect({ login: store.state.auth.authen.id }, (e) => {
        }, () => {})
    }
    connect();
}