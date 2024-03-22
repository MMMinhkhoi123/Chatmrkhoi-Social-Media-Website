package com.chatmrkhoi.chatmrkhoi.Data.Send;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessNewAndInfoNumberRep;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataSendUserInGroup {
    private  Long statusgroup;
    private  Long idgroup;
    private  String code;
    private  String code_old;
    private DataMessNewAndInfoNumberRep data;
}
