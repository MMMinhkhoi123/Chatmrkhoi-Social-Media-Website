package com.chatmrkhoi.chatmrkhoi.design.observer;

public class CenterOb {
    public Publisher eventsNumber;
    public  CenterOb() {
        this.eventsNumber = new Publisher("NotifyNewMess");
    }
    public void Notify() {
        eventsNumber.Notify("NotifyNewMess");
    }
}
