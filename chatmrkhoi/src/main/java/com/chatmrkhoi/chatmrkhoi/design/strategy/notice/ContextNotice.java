package com.chatmrkhoi.chatmrkhoi.design.strategy.notice;

public class ContextNotice {
    private INotice context;

    public ContextNotice(INotice notice) {
        this.context = notice;
    }
    public void  Notice(Object object) {
        context.Notice(object);
    }
}
