package com.rafaelsouzaf.fbe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    String version = "v0.1.0";
    Object data;

    private Response(Object obj) {
        this.data = obj;
    }

    public static Response of(Object obj) {
        return new Response(obj);
    }

}
