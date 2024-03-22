package com.chatmrkhoi.chatmrkhoi.Util.convert;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoRoomRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.common.EAccount;
import com.chatmrkhoi.chatmrkhoi.design.observer.Publisher;
import com.chatmrkhoi.chatmrkhoi.entity.ActionEn;
import com.chatmrkhoi.chatmrkhoi.entity.FriendEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.*;
import com.chatmrkhoi.chatmrkhoi.service.impl.MessageSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserConvert {

    @Autowired IUserRepo USER_REPO;
    @Autowired IActionRepo ACTION_REPO;
    @Autowired IInfoRepo INFO_REPO;
    @Autowired IFriendRepo FRIEND_REPO;
    @Autowired MessageSer MESSAGE_SER;



    String status = "offline";
    Long timeAction = null;
    public DataInfoUserOtherRep convertDataInfoUser(Long IdUser, String status, String CodeRoom) {
        this.status = "offline";
        UserEn data = USER_REPO.findById(IdUser).orElseThrow();

        Optional<ActionEn> acEntity = ACTION_REPO.findByIdUser(IdUser);
        acEntity.ifPresent((e) -> {
            this.status = e.getStatus();
            timeAction = e.getTimetamp();
        });

        if(!data.isVerify()) {
            return null;
        }
        InfoEn info = INFO_REPO.findByIdUser(data.getId()).orElseThrow();

        if(info.getAvatars().isEmpty()) {
            return null;
        }

        return DataInfoUserOtherRep.builder()
                .desc(info.getDescs())
                .fullname(info.getFullnames())
                .gmail(data.getGmails())
                .status(status)
                .type_img(info.getType_avatas())
                .id(data.getId())
                .briday(info.getBirday())
                .gender(info.getGender())
                .images(info.getAvatars())
                .action(this.status)
                .coderoom(CodeRoom)
                .timeaction(timeAction)
                .countfriend(get_count_friend(IdUser))
                .build();
    }




    int count = 0;
    private int get_count_friend(Long id) {
        UserEn user = USER_REPO.findById(id).orElseThrow();
        count = user.getFriend_entities().size();
        count = count + FRIEND_REPO.findAllByFriendId(id).orElseThrow().size();
        return count;
    }

    public String getRoom(Long id1, Long id2) {
        if (FRIEND_REPO.findByFriendIdAndUserId(id2, id1).isPresent()) {
            return id1.toString() + id2.toString();
        } else {
            return 	id2.toString() + id1.toString();
        }
    }

    public String status_fake = "not";
    public String checkStatus(Set<FriendEn> listFriend, Long idFriend) {
        status_fake = "not";
        listFriend.forEach((e) -> {
            if(e.getIdfriend().equals(idFriend)) {
                status_fake = e.getStatus();
            }
        });
        return status_fake;
    }



    public List<DataInfoRoomRep> getListInfoRoom(long idUser) {

        List<DataInfoRoomRep> dataRep = new ArrayList<DataInfoRoomRep>();

        UserEn user = USER_REPO.findById(idUser).orElseThrow();

        user.getFriend_entities().forEach((e) -> {
            if(e.getStatus().equalsIgnoreCase(EAccount.FRIEND.getValue())) {
                DataInfoRoomRep data = DataInfoRoomRep.builder().coderoom(e.getCoderoom()).idfriend(e.getIdfriend()).time(e.getTime()).build();
                dataRep.add(data);
            }
        });

        FRIEND_REPO.findAllByFriendId(idUser).orElseThrow().forEach((e) -> {
            if(e.getStatus().equalsIgnoreCase(EAccount.FRIEND.getValue())) {
                DataInfoRoomRep data = DataInfoRoomRep.builder().coderoom(e.getCoderoom()).idfriend(e.getUsersentities().getId()).time(e.getTime()).build();
                dataRep.add(data);
            }
        });


        user.getGrouarraymaster().forEach((e) -> {
            DataInfoRoomRep data = DataInfoRoomRep.builder().coderoom(e.getCoderoom()).idgroup(e.getId()).time(e.getTime()).build();
            dataRep.add(data);
        });

        user.getGroup_entities().forEach((e) -> {
            DataInfoRoomRep data = DataInfoRoomRep.builder().coderoom(e.getCoderoom()).idgroup(e.getId()).time(e.getTime()).build();
            dataRep.add(data);
        });

        Comparator<DataInfoRoomRep> data = new Comparator<DataInfoRoomRep>() {
            @Override
            public int compare(DataInfoRoomRep o1, DataInfoRoomRep o2) {
                return  Long.compare(o1.getTime(), o2.getTime());
            }
        };
        dataRep.sort(data);

        List<DataInfoRoomRep> listMessNull = new ArrayList<DataInfoRoomRep>();
        List<DataInfoRoomRep> listMessNotNulls = new ArrayList<DataInfoRoomRep>();

        dataRep.forEach((e) -> {
            if(!MESSAGE_SER.getListMessFromCode(e.getCoderoom()).isEmpty()) {
                listMessNotNulls.add(e);
            }else {
                listMessNull.add(e);
            }
        });

        Comparator<DataInfoRoomRep> dataCompare = new Comparator<DataInfoRoomRep>() {
            @Override
            public int compare(DataInfoRoomRep o1, DataInfoRoomRep o2) {
                return
                        Long.compare(MESSAGE_SER.getListMessFromCode(o1.getCoderoom()).get(MESSAGE_SER.getListMessFromCode(o1.getCoderoom()).size() - 1).getTime()
                                , MESSAGE_SER.getListMessFromCode(o2.getCoderoom()).get(MESSAGE_SER.getListMessFromCode(o2.getCoderoom()).size() - 1).getTime());
            }
        };
        listMessNotNulls.sort(dataCompare);
        Collections.reverse(listMessNotNulls);

        listMessNotNulls.addAll(listMessNull);
        return listMessNotNulls;
    }

    boolean statusA = false;
    public boolean checkExit(Set<FriendEn> listFriend, Long idFriend) {
        statusA = false;
        listFriend.forEach((e) -> {
            if(e.getIdfriend().equals(idFriend))  {
                statusA = true;
            }
        });
        return statusA;
    }

}
