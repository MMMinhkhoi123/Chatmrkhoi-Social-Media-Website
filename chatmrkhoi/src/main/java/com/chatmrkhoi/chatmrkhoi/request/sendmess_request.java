package com.chatmrkhoi.chatmrkhoi.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class sendmess_request {
private Long id;
private String content;
private Long id_friend;
private String status;
private Long reply;
private String typerep;
private List<Long> idfile;
}
