package com.si.familyapp.exceptions.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.si.familyapp.exceptions.BadRequestException;
import com.si.familyapp.exceptions.NotFoundException;
import com.si.familyapp.exceptions.handler.ErrorMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    private static final int BAD_REQUEST_CODE = 400;
    private static final int NOT_FOUND_CODE = 404;

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorMessage message;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            message = mapper.readValue(bodyIs, ErrorMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        return switch (response.status()) {
            case BAD_REQUEST_CODE ->
                    new BadRequestException(Objects.isNull(message) ? "Bad request" : StringUtils.join(message.getErrors().toArray(), ";"));
            case NOT_FOUND_CODE ->
                    new NotFoundException(Objects.isNull(message) ? "Not found" : StringUtils.join(message.getErrors().toArray()));
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}