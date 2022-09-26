package com.si.familyapp.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.si.familyapp.util.DateUtil.DATE_TIME_FORMAT;
import static com.si.familyapp.util.DateUtil.getCurrentDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {

    private List<String> errors;
    private String path;

    @JsonFormat(pattern = DATE_TIME_FORMAT)
    private LocalDateTime timestamp;

    public ErrorMessage(List<String> errors, String path) {
        this.errors = errors;
        this.path = path;
        this.timestamp = getCurrentDateTime();
    }

    public ErrorMessage(String error, String path) {
        this.errors = Collections.singletonList(error);
        this.path = path;
        this.timestamp = getCurrentDateTime();
    }
}
