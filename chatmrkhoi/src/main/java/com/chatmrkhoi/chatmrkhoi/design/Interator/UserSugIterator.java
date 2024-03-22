package com.chatmrkhoi.chatmrkhoi.design.Interator;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.Util.convert.UserConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IFriendRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class UserSugIterator implements IListIterator {

    @Autowired Common COMMON;
    @Autowired IFriendRepo FRIEND_REPO;
    @Autowired UserConvert CONVERT;
    private ConcreteCollection data;

    private  int currentPosition = 0;
    private  boolean check = false;
    private boolean checkFriend = true;
    private boolean checkFriend2 = true;
    private boolean checkCom = false;
    @Override
    public DataInfoUserOtherRep First() {

        List<Long> countUser = new ArrayList<>();

        while (!CheckUser(data.getUser(currentPosition),countUser)) {
            currentPosition++;
            if(!hasNext()) {
                break;
            }
        }
        if(hasNext()) {
            DataInfoUserOtherRep dataUser = CONVERT.convertDataInfoUser(data.getUser(currentPosition).getId(), "not", null);
            dataUser.setSugg(countUser);
            return dataUser;
        } else {
            return  null;
        }
    }
    @Override
    public DataInfoUserOtherRep getNext() {
        List<Long> countUser = new ArrayList<>();
        currentPosition++;
        if(hasNext()) {
            while (!CheckUser(data.getUser(currentPosition), countUser)) {
                currentPosition++;
                if(!hasNext()) {
                    break;
                }
            }
            if(hasNext()) {
                DataInfoUserOtherRep dataUser = CONVERT.convertDataInfoUser(data.getUser(currentPosition).getId(), null, null);
                dataUser.setSugg(countUser);
                return dataUser;
            } else {
                return  null;
            }
        } else  {
            return  null;
        }
    }
    @Override
    public boolean hasNext() {
        return  currentPosition <= data.getSize() - 1;
    }
    @Override
    public void reset() {
        currentPosition = 0;
    }
    public  boolean CheckUser(UserEn use, List<Long> data) {
        check = false;
        checkFriend = true;
        checkFriend2 = true;

        if (use == COMMON.getUserAuthentication()) {
            return false;
        }

        if (use.getFriend_entities().isEmpty()) {
            FRIEND_REPO.findAllByFriendId(use.getId()).orElseThrow().forEach((m) -> {
                if(m.getUsersentities().getId().equals(COMMON.getUserAuthentication().getId())) {
                    checkFriend = false;
                }
            });
        } else  {
            use.getFriend_entities().forEach((x) -> {
                if(x.getIdfriend().equals(COMMON.getUserAuthentication().getId())) {
                    checkFriend = false;
                }
            });
        }

        if (COMMON.getUserAuthentication().getFriend_entities().isEmpty()) {

            FRIEND_REPO.findAllByFriendId(COMMON.getUserAuthentication().getId()).orElseThrow().forEach((m) -> {
                checkCom = false;
                if (m.getUsersentities().getId().equals(use.getId())) {
                    checkFriend2 = false;
                }
                FRIEND_REPO.findByFriendIdAndUserId(m.getUsersentities().getId(), use.getId()).ifPresent((x) -> {
                    checkCom = true;
                    check = true;
                });
                FRIEND_REPO.findByFriendIdAndUserId(use.getId(),m.getUsersentities().getId()).ifPresent((x) -> {
                    checkCom = true;
                    check = true;
                });
                if(checkFriend && checkFriend2 && check && checkCom) {
                    data.add(m.getUsersentities().getId());
                }
            });

        } else  {

            COMMON.getUserAuthentication().getFriend_entities().forEach((e) -> {
                checkCom = false;
                if (e.getIdfriend().equals(use.getId())) {
                    checkFriend2 = false;
                }
                FRIEND_REPO.findByFriendIdAndUserId(e.getIdfriend(), use.getId()).ifPresent((x) -> {
                    checkCom = true;
                    check = true;
                });

                FRIEND_REPO.findByFriendIdAndUserId(use.getId(),e.getIdfriend()).ifPresent((x) -> {
                    checkCom = true;
                    check = true;
                });

                if(checkFriend && checkFriend2 && check &&  checkCom ) {
                    System.out.println(checkFriend + ":" + checkFriend2 + ":" + check +  ":" + checkCom + "for "  + e.getIdfriend());
                    data.add(e.getIdfriend());
                }
            });

        }
        return checkFriend&&checkFriend2&&check;
    }

}
