package com.marcohnp.votingsession.provider;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class DateTimeProvider {

    public LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }
}
