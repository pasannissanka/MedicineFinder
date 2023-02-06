package com.medifinder.medifinder.dto.responses;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {
    private HttpStatus status;
    private T data;
    private Object errors;
    private Object metadata;

    public static <T> Response<T> badRequest() {
        return new Response<T>()
                .setStatus(HttpStatus.BAD_REQUEST);
    }

    public static <T> Response<T> ok(T data) {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.OK);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> validationException() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        return response;
    }

    public static <T> Response<T> accessDenied() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.FORBIDDEN);
        return response;
    }

    public static <T> Response<T> exception() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.BAD_REQUEST);
        return response;
    }

    public static <T> Response<T> notFound() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    public static <T> Response<T> duplicateEntity() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.CONFLICT);
        return response;
    }

    public void addErrorMsgToResponse(String errorMsg, T data) {
        ResponseError<T> error = new ResponseError<T>()
                .setMessage(errorMsg)
                .setData(data)
                .setTimestamp(new Date());
        setErrors(error);
    }


    @Getter
    @Accessors(chain = true)
    public static class PageMetadata {
        private final int size;
        private final long totalElements;
        private final int totalPages;
        private final int number;

        public PageMetadata(int size, long totalElements, int totalPages, int number) {
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.number = number;
        }
    }

    @Data
    @Accessors(chain = true)
    public static class ResponseError<T> {
        private Date timestamp;
        private String message;
        private T data;
    }
}