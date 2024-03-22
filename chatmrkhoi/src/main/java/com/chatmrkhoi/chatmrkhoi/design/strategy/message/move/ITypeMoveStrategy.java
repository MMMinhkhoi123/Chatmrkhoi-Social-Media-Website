package com.chatmrkhoi.chatmrkhoi.design.strategy.message.move;

import com.chatmrkhoi.chatmrkhoi.Data.request.DatatransactionMessReq;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;

public interface ITypeMoveStrategy {
    MessageEn transactionMessage(DatatransactionMessReq  data, MessageEn messageEn);
}
