const avaible_chat = {
    namespaced: true,
    state: {
       unmess : {
        status: null,
        idmess: null,
        type: null
       },
       feel: {
        status: false,
        mess: null,
        type: null
       },
       open_pin: false,
       open_send: false,
       save_move: {
         type: null,
         data: null,
       },
       open_watch: false,
       open_addperson: false,
       open_number: false,
       open_change_array: false,
       open_loader: false,
       open_notifi: false,
       open_editinfo: false,
       open_editinfo_v2: false,
       open_editinfo_v3: false,
       action_group: false,
       data_select_user: null,
       
       Notification: {
          data: {
            img: '',
            title: '',
            content: '',
          },
          warning: {
            upload_img: false,
            delete_group: false,
            unfriend: false,
            kick: false,
            update_info: false,
            addgroup: false,
            changepass: false,
            user_remove_group: false,
            edit_group: false,
          }
       },
       setting: {
        select: 1,
       },
       count_user: 0,
       count_group: 0,
       status_time: null,
       array_new: [],
    }
  }
export default avaible_chat;