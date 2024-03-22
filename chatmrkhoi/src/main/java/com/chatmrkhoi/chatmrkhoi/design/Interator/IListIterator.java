package com.chatmrkhoi.chatmrkhoi.design.Interator;


import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;

public interface IListIterator {
    DataInfoUserOtherRep First();
    DataInfoUserOtherRep getNext();
    boolean hasNext();
    void  reset();
}
