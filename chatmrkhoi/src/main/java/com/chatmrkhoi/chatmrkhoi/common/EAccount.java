package com.chatmrkhoi.chatmrkhoi.common;

import lombok.Getter;
@Getter
public enum EAccount {
    REQUEST("request"),
    FRIEND("friend")
    ;
    private final String value;
    EAccount(String data) {
        this.value = data;
    }
}
