const x =     {
    TextBlog: {
        ButtonLogin : 'LOGIN',
        ButtonSignUp : "CREATE ACCOUNT",
        CaroselTitle : "MESSAGE WITH FRIENDS, CONNECT, LOVE",
        CaroselContent : "Easy exchange",
        IntroduceTitle: "Simple operation",
        Introducecontent: "Application operation is accurate, simple for beginners. Listen to every user request, to create great experiences",
        IntroduceTitle2: "Interface optimization",
        IntroduceContent2: "The design is minimalist, eye-catching design, creating a pleasant feeling of smooth application operations. Supports a variety of functions",
        OverViewTitle: "Connect people",
        OverViewContent2: "Exchange information with friends, fun chat groups, join hands to create an energetic active community.",
        FooterTitle1: "Help",
        FooterItem1TT1: "Help Center",
        FooterItem2TT1: "Help Forum",
        FooterTitle2: "Community",
        FooterTitle3: "Developer",
        FooterItem1TT3: "Developer Forum",
    },
    TextAcount: {
        Common: {
            Back: "Back Home",
        },
        Err: {
          SignUp: {
            FullNameEmpty: "Fullname is not empty",
            FullNameLengh: "Fullname of invalid length",
            GmailEmpty: "Email is not empty",
            GmailUse: "Gmail is already in use",
            PassWordEmpty: "Password is not empty",
            PassWordValid: "The password consists of letters, numbers, uppercase letters, special characters, at least 8 characters",
          }, 
          Login: {
            GmailNotExit: "Account doesn't exist",
            PassWordCorrect: "Incorrect password",
           }
        },
        Forget_Password: {
            StepByStep: {
                step1: "Check Your Email",
                step2: "Enter Code Verify",
                step3: "Success",
            },
            From: {
                step1: {
                    Title: "Verify Acount",
                    TextInputEmail: "Enter email",
                    ButtonGetCode: "Get Code",
                    ButtonCancel: "Cancel",
                    err: "Account doesn't exist"
                },
                step2: {
                    Title: "Verify Code",
                    Content: "Verify email",
                    TextInputCode: "Enter Verify Code",
                    ButtonNext: "Next",
                    ButtonCancel: "Back",
                    err: {
                        Incorrect: "Incorrect code",
                        NoLonger: "The code is no longer valid",
                    }
                },
                step3: {
                    Title: "New Password",
                    Content: "Account",
                    TextInputPassword: "Enter new password",
                    ButtonChange: "Change password",
                    err: "The password consists of letters, numbers, uppercase letters, special characters, at least 8 characters",
                }
            }
        },
        verify_Email: {
            StepByStep: {
                step1: "Check Your Email",
                step2: "Enter verify",
                step3: "Success",
            },
            From: {
                Title: "Verify email",
                TextContent: "Check your email",
                ButtonResend: "Resend",
                ButtonCancel: "Cancel",
                TexTime: "Try again in {time} seconds",
            }
        },
        Login: {
            TitleRight: "Login Account",
            ButtonSubmit: "Join Chat",
            TitleLeft: "Wealcome Back",
            ButtonMove: "New Acount",
            LinkMove: "Forget password",
            TextInputEmail: "Enter email",
            TextInputPassword: "Enter password",
        },
        SignUp: {
            TitleRight: "Create Acount",
            ButtonSubmit: "Create New",
            TitleLeft: "Wealcome Back",
            ButtonMove: "SignIn",
            TextInputName: "Enter name",
            TextInputEmail: "Enter email",
            TextInputPassword: "Enter password",
        }
    },
    TextNew: {
        Gender: {
            Title: "Choice Gender",
            TextMale: "Male",
            TextFaMale: "Famale",
            ButtonNext: "Next",
        },
        Date: {
            Title: "Choice birthday",
            ButtonBack: "Back",
            ButtonNext: "Next",
        },
        Avata: {
            Title: "Choice birthday",
            ButtonBack: "Back",
            ButtonFish: "Complete",
        },
    },
    TextMain: {
        Setting: {
            Menu: {
                Profile: "Individual",
                Security: "Security",
                Advanted: "Advanted"
            },
            Screen: {
                Profile: {
                    Title: "Individual",
                    TitleName: "Fullname",
                    TitleGender: "Gender",
                    Titlebirday: "Birday",
                    TitleDesc: "Description",
                    TitlOption: "Option Acount",
                    buttonChange: "Save change",
                },
                Security: {
                    Title: "Infomation",
                    Logout: "Logout",
                    ChangePass: "Change Password",
                    ChangeEmail: "Change Email",
                    LaterUpdate: " (Later Update )"
                },
                Advanted: {
                    TitleLanguage: "Change Language",
                }
            }
        },
        Menu: {
            ItemProfile: "Profile", 
            ItemChats: "Chats",
            ItemGroup: "Groups",
            ItemEveryone: "Everyone",
            ItemSetting: "Setting",  
        },
        Everyone: {
            Menu: {
                ItemAddFriend: "Find friends",
                ItemSendRequest: "Request submitted",
                ItemFriendRequest: "Friend Requests",
                ItemListFriend: "Friends list",
            },
            Filter: {
                Clear: "Clear",
                Result: "All results",
                InputSeachGmail: "Enter email",
                InputSeachName: "Enter name",
                DropDownChoiceName: "Search by name",
                DropDownChoiceEmail: "Search by email",
                DropdownName: "By Name",
                DropDownEmail: "By Email",
            }
        },
        Group: {
            Add: {
                Input: "Enter name",
                Content: "selected",
                ButtonNext: "Next",
                ButtomCreate: "Create Group",
                ButtomBack: "Back",
                InputNameGroup: "Enter name group",
                ChoiceImg: "Select image",
            },
            Filter: {
                Clear: "Clear",
                Result: "All results",
                InputSeach: "Enter name group",
                DropDownChoiceAll: "All Group",
                DropDownChoiceMy: "The group I created",
                DropDownChoiceYour: "The group I joined",
                DropdownAll: "All",
                DropDownMy: "The group I created",
                DropDownYour: "The group I joined",
            },
            List: {
                Remove: "Remove group",
                View: "View",
                Leave: "Leave group",
            },
            Detail: {
                ItemSetting: "Setting",
                ItemCustomizeinformation: "Customize",
                ItemNewUser: "Add members",
                ItemDeails: "Detail",
                ItemMember: "Menbers",
                ItemAdvanted: "Advanted",
                ItemRemove: "Remove Group",
                ItemLeave: "Leave Group",
                ListImg: "Images",
                ListVideo: "Video",
                ListFile: "File",
                Viewprofile: "View Profile",
            }
        },
        Profile: {
            Screen: {
                Option: {                      
                  MakeFriend: "Make Friend",
                  DestroyRequest: "Cancel a friend request",
                  Unfriend: "Unfriend",
                  Sendmess: "Send messenger",
                  Agree: "Agree",
                  DisAgree: "Disagree",
                },
                Info: {
                    TitleGender: "Gender" ,
                    GenderMale: "Male",
                    GenderFaMale: "FaMale",
                    Titlebirday: "Birday",
                    birday: " Date {date} Month {month} Year {year}",
                    Desc: "Descripstion",
                }
            },
            Listprofile: {
                Title: "friend profile",
                Online: "Online",
                offline: "Offline",
            },
        },
        Chat: {
            Drop: "Drop is here",
            Menu: {
                Textsearch: "Search",
                YourSendFeel: "{user} expressed his feelings {feel} a message",
                UnPing: "{user} unpinned a message",
                Ping: "{user} pinned a message",
                Remove: "The message has been recovered",
                SendFile: "{user} file sent",
                AddPerson: "{user} invited {usernew} to join the group",
                KickPerson: "{user} invited  {usernew} out of the group",
                UserOut: "{user} has left the group"
            },
            Screen: {
                Header: {
                    OfflineMiNu: "Active {time} minutes ago",
                    OfflineHour: "Active {time} hour age",
                    OfflineSecond: "Active {time} second age",
                    Online: "In operation"
                },
                Mess: {
                    newmess: "There are new messages",
                    ping: "{user} pinned a message",
                    unping: "{user} unpinned a message",
                    User: "You",
                    PingView: "View All",
                    AddPerson: "{user} added  {usernew} to group",
                    KickPerson: "{user} invited {usernew} out of the group",
                    UserOut: "{user} has left the group",
                    option: {
                        transaction: "Transitions",
                        pin: "Pin",
                        Unpin: "Unpin",
                        remove: "Delete, Remove",
                        delete: "Delete",
                        revoke: "You've recalled a message"
                    },
                    input: "Type a message ",
                    Myreply: "Reply to my messages",
                    Yourreply: "Reply to a message {user}",
                    Replyfile: "Files",
                    watch: "watched at",
                    audience: "Audience"
                },
                From : {
                    watch: {
                        text: " watched at ",
                        title: " Audience "
                    },
                    Feel: {
                        Title: "List of emotions",
                        Content: "Number of people expressing feelings",
                    },
                    Pin: {
                        Title: "Pinned messages",
                        Content: "Number of pinned messages",
                        Month: " Month ",
                        menu: {
                            View: "View messages",
                            Unpin: "Unpined",
                            PinAt: "Pinned at",
                            Input: "Enter name",
                        }
                    },
                    Transitions: {
                        Title: "Transitions",
                        send: "Send",
                        Sended: "Sended",
                        Input: "Enter name",
                    },
                    Member: {
                        Title: "Member",
                        Content: "Number of members",
                    },
                    AddGroup: {
                        Title: "Add members",
                        Input: "Enter name",
                        Joined: "Joined group",
                        Add: "Add to",
                    },
                    Edit: {
                        Input: "Enter name group",
                        Button: "Update",
                    }
                }
            },
        }
    },
    Notification: {
        verifyEmailsucssec: {
            Title: "SUCCESS",
            Content: "Your acount has been verify email",
            buton: "Close"
        },
        ChangePassword: {
            Title: "SUCCESS",
            Content: "Your password has been changed",
            button: "Close"
        },
        DeleteObj: {
            Title: "WARNING",
            Content: "All shared chat data is permanently deleted",
            ButtonSubmit: "Confirm",
            ButtonClose: "Close",
        },
        unfriend: {
            Title: "SORRY",
            Content: "The enemy canceled his friends",
            Button: "Close",
        },
        KickUser: {
            Title: "SORRY",
            Content: "You ave forcibly left the group",
            Button: "Close",
        },
        DeleteGroup: {
            Title: "SORRY",
            Content: "The group owner deleted the group",
            Button: "Close",
        },
        Addgroup: {
            Step1: {
                Title: "FAIL",
                Content: "Please select the right quantity",
                Button: "Close",
            },
            Step2: {
                Title: "FAIL",
                ContentFormat: "Please select the correct image format",
                ContentEmpty: "Please select a group images",
                Button: "Close",
            }
        },
        EditGoup: {
            Title: "FAIL",
            Content: "Please select the correct image format",
            Button: "Close",
        },
        UpInfo: {
            Invalid: {
                Title: "FAIL",
                Content: "Please select the correct image format",
                content2: "Invalid information",
                Button: "close",
            },
            Valid: {
                Title: "SUCCESS",
                Content: "Update profile successfully",
                Button: "Close",
            }
        }
    }
}
export default x;