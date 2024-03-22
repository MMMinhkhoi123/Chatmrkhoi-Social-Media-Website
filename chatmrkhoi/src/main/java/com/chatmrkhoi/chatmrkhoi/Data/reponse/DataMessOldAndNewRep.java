package com.chatmrkhoi.chatmrkhoi.Data.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataMessOldAndNewRep {
    private DataMessageRep messnew;
    private DataMessageRep messupdate;
}
