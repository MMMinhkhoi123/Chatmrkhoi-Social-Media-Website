package com.chatmrkhoi.chatmrkhoi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class update_profile_request {
    private String fullname;
   private Long birday;
   private String gender;
   private String desc;
   private String  avata;
   private String type_img;
}
