package com.chatmrkhoi.chatmrkhoi.design;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.File;

@Getter
@Setter
public class Signgleton  {
    private Long TimeTokenFresh = (long) (24 * 60 * 60 * 1000);

    private Long TimeTokenVerify = (long) (15 * 60 * 1000);

    private File files = new File("");

    private String UrlImg = files.getAbsolutePath() +  "/target/classes/static/img/" ;

    private  String UrlVideo = files.getAbsolutePath() + "/target/classes/static/video/";

    private  String UrlFile = files.getAbsolutePath() +  "/target/classes/static/file/";

    private static Signgleton instance;
    public static Signgleton getInstance() {
        if(instance == null) {
            System.out.println("running one a time");
            instance = new Signgleton();
        }
        return instance;
    }
    private Signgleton() {};
}
