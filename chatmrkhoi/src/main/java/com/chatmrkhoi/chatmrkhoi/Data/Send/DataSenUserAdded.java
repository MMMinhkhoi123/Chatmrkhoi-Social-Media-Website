package com.chatmrkhoi.chatmrkhoi.Data.Send;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoGroupRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoRoomRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessNewAndInfoNumberRep;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class DataSenUserAdded {
    private Long newuser;
    private Long useraply;
    private Long idgroup;
    private DataInfoRoomRep newconnect;
    private DataInfoGroupRep newgroup;
    private DataMessNewAndInfoNumberRep data;
    private String code;
}
