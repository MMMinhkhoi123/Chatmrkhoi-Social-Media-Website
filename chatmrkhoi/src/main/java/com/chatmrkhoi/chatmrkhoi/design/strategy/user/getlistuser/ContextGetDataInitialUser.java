package com.chatmrkhoi.chatmrkhoi.design.strategy.user.getlistuser;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Component
public class ContextGetDataInitialUser {
   private   ITypeListUser  typeUser;
   public List<DataInfoUserOtherRep> getData() {
       return  typeUser.getData();
   }
}
