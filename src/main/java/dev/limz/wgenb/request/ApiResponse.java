package dev.limz.wgenb.request;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse extends ResponseEntity<Object> {


    private static class Response {
        public boolean ok;
        public Object data;
        public String message;

        public Response(String message, boolean ok, Object data){
            this.ok = ok;
            this.data = data;
            this.message = message;
        }
    }

    public ApiResponse(String message, boolean ok, Object data, HttpStatus status) {
        super(new Response(message, ok, data), status);
    }
}
