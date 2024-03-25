package com.chatmrkhoi.chatmrkhoi.api;

import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.InfoSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/info-center")
public class InfoApi {
    @Autowired InfoSer INFO_SER;
    @PostMapping("/update-info")
    public void updateProfile(@RequestBody Optional<DataInfoReq> data) {
        INFO_SER.update(data.orElseThrow());
    }

    @GetMapping("/change-notify/{status}")
    public void updateNotify(@PathVariable(value = "status") Optional<String> data) {
        INFO_SER.updateNotify(Boolean.parseBoolean(data.orElseThrow()));
    }

    @GetMapping("/change-theme/{status}")
    public void updateTheme(@PathVariable(value = "status") Optional<String> data) {
        INFO_SER.updateTheme(Boolean.parseBoolean(data.orElseThrow()));
    }
}
