package com.chatmrkhoi.chatmrkhoi.Util.convert;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoWatchRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataFeelReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoFileReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveRevokeReq;
import com.chatmrkhoi.chatmrkhoi.entity.FeelingEn;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import com.chatmrkhoi.chatmrkhoi.entity.RevokeEn;
import com.chatmrkhoi.chatmrkhoi.entity.WatchEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class MessageConverts {

    @Autowired IFileRepo FILE_REPO;

    public DataMessageRep convertMessRep(MessageEn mess) {
        DataMessageRep data = null;
        if(mess.getPin() == null) {
            Long id = null;
            String code = null;
            Long groupid = null;
            if (mess.getGroupmess() == null) {
                id =  mess.getFriendmess().getIdfriend();
                if(mess.getUsersentity().getId().equals(id)) {
                    id = mess.getFriendmess().getUsersentities().getId();
                }
                code = 	mess.getFriendmess().getCoderoom();
            } else {
                code = mess.getGroupmess().getCoderoom();
                groupid = mess.getGroupmess().getId();
            }

            List<DataFeelReq> datafeel = new ArrayList<DataFeelReq>();
            if(mess.getFeelentitty()!= null) {
                datafeel = convertfell(mess.getFeelentitty());
            }
            List<DataSaveRevokeReq> datarevoke = new ArrayList<DataSaveRevokeReq>();
            if(mess.getRevokeatity()!= null) {
                datarevoke = convertRevoke(mess.getRevokeatity());
            }
            List<DataInfoWatchRep> watch_reponses = new ArrayList<DataInfoWatchRep>();
            if(mess.getWatch_entities() != null) {
                watch_reponses = convertWatch(mess.getWatch_entities());
            }

            data = DataMessageRep.builder()
                    .content(mess.getContent())
                    .id_user(mess.getUsersentity().getId())
                    .id_friend(id)
                    .time(mess.getTime())
                    .listwatch(watch_reponses)
                    .id(mess.getId())
                    .start(mess.isStart_date())
                    .feel(mess.getFeel())
                    .id_group(groupid)
                    .reply(mess.getReply())
                    .img(getListImg(mess.getId()))
                    .typereply(mess.getTypereply())
                    .listrevoke(datarevoke)
                    .room(code)
                    .group_status(mess.getAddgroup())
                    .listfeel( datafeel)
                    .build();


        } else {
            Long id = null;
            String code = null;
            Long groupid = null;
            if (mess.getGroupmess() == null) {
                id =  mess.getFriendmess().getIdfriend();
                if(mess.getUsersentity().getId() == id) {
                    id = mess.getFriendmess().getUsersentities().getId();
                }
                code = 	mess.getFriendmess().getCoderoom();
            } else {
                code = mess.getGroupmess().getCoderoom();
                groupid = mess.getGroupmess().getId();
            }
            List<DataInfoWatchRep> watch_reponses = new ArrayList<DataInfoWatchRep>();
            if(mess.getWatch_entities() != null) {
                watch_reponses = convertWatch(mess.getWatch_entities());
            }
            data = DataMessageRep.builder().pin(mess.getPin()).time(mess.getTime())
                    .id(mess.getId())
                    .id_user(mess.getUsersentity()
                            .getId()).id_group(groupid)
                    .id_friend(id)
                    .listwatch(watch_reponses)
                    .room(code).build();
        }
        return data;
    }

    private List<DataInfoFileReq> getListImg(Long idmess) {
        List<DataInfoFileReq> data = new ArrayList<DataInfoFileReq>();
        FILE_REPO.getAllByIdMess(idmess).get().forEach((e) -> {
            data.add(DataInfoFileReq.builder()
                    .type(e.getTypefile())
                    .namefile(e.getNamefile())
                    .id(e.getId())
                    .idfile(idmess)
                    .size(e.getSize())
                    .status(true)
                    .build());
        });
        return data;
    }



    private List<DataInfoWatchRep> convertWatch(Set<WatchEn> watch) {
        List<DataInfoWatchRep> watch_reponses = new ArrayList<DataInfoWatchRep>();
        watch.forEach((e) -> {
            DataInfoWatchRep ws = DataInfoWatchRep.builder().id(e.getUsersentity().getId()).timetamp(e.getTimetamp()).build();
            watch_reponses.add(ws);
        });
        return watch_reponses;
    }



    private List<DataSaveRevokeReq> convertRevoke(Set<RevokeEn> revokexx) {
        List<DataSaveRevokeReq> x = new ArrayList<DataSaveRevokeReq>();
        revokexx.forEach((e) -> {
            DataSaveRevokeReq data = DataSaveRevokeReq.builder()
                    .idmess(e.getMessentity().getId())
                    .iduser(e.getUserentity().getId())
                    .type(e.getType()).build();
            x.add(data);
        });
        return x;
    }



    public List<DataFeelReq> convertfell(Set<FeelingEn> feel) {
        List<DataFeelReq> x = new ArrayList<DataFeelReq>();
        feel.forEach((e) -> {
            DataFeelReq datac =	DataFeelReq.builder()
                    .feel(e.getMess())
                    .type(e.getType())
                    .idmess(e.getMessentity()
                            .getId())
                    .iduser(e.getIduser().getId())
                    .build();
            x.add(datac);
        });
        return x;
    }


}
