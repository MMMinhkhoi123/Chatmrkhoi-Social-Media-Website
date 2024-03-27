package com.chatmrkhoi.chatmrkhoi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeEn extends Base {
    @Column
    private String titleNotice;
    @Column
    private String describeNotice;
    @Column
    private String typeNotice;
    @Column
    private String keyNotice;
    @Column
    private boolean statusNotice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEn userEntity;

}
