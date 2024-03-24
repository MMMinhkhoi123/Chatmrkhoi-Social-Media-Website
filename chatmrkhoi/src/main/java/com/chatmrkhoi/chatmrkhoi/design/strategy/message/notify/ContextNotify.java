package com.chatmrkhoi.chatmrkhoi.design.strategy.message.notify;


public class ContextNotify {
   private INotify iNotify;
   public  ContextNotify(INotify data) {
       iNotify = data;
   }
   public void active() {
       iNotify.startNotify();
   }
}
