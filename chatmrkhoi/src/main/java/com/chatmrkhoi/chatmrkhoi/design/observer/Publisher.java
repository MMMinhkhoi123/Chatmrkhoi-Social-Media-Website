package com.chatmrkhoi.chatmrkhoi.design.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Publisher {
    private final Map<String, List<ISubscriber> > listSubscriber = new HashMap<>();

    public Publisher(String... operations) {
        for (String operation : operations) {
            this.listSubscriber.put(operation, new ArrayList<>());
        }
    }

    public  void  Subscriber(String type,ISubscriber data) {
        List<ISubscriber> even = listSubscriber.get(type);
        even.add(data);
    }

    public  void  UnSubscriber(String type,ISubscriber data) {
        List<ISubscriber> even =  listSubscriber.get(type);
        even.remove(data);
    }

    public  void  Notify(String type) {
        List<ISubscriber> even = listSubscriber.get(type);
        even.forEach(ISubscriber::startNotify);
    }


}
