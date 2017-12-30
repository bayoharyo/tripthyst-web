package com.tripthyst.webapp.model;

import lombok.Data;

@Data
public class RestModelWrapper<E> {

    private int status;
    private String msg;
    private E result;

    public RestModelWrapper(E result) {
        this.status = 200;
        this.msg = "success";
        this.result = result;
    }

    public RestModelWrapper() {
        this.status = 404;
        this.msg = "not found";
    }

}
