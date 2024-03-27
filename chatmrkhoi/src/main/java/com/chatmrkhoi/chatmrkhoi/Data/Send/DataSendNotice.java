package com.chatmrkhoi.chatmrkhoi.Data.Send;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataSendNotice {
    private Long id;
    private  String title;
    private Long Date;
    private String describe;
    private String type;
    private String key;
    private boolean status;
}
