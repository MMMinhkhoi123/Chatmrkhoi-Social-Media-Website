package com.chatmrkhoi.chatmrkhoi.design.Signleton;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class Signleton {

    private Long TimeTokenFresh = (long) (24 * 60 * 60 * 1000);

    private Long TimeTokenVerify = (long) (15 * 60 * 1000);

    private File files = new File("");

    private String UrlImg = files.getAbsolutePath() +  "/target/classes/static/img/" ;

    private  String UrlVideo = files.getAbsolutePath() + "/target/classes/static/video/";

    private  String UrlFile = files.getAbsolutePath() +  "/target/classes/static/file/";

    private static Signleton instance;
    public static Signleton getInstance() {
        if(instance == null) {
            System.out.println("running one a time");
            instance = new Signleton();
        }
        return instance;
    }
    private Signleton() {};
}
