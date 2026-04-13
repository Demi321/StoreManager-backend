package com.sm.storagemanager.ResponseHttp;

import java.util.List;
import org.springframework.http.HttpStatus;

public class Response<T> implements ResponseHttp<T> {

    private HttpStatus status;
    private String msj;
    private T data;
    private List<T> dataList;

    
    public Response(){}
    public Response( T data, String msj) {
     
        this.data = data;
        this.msj = msj;
    }

   

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public void setMessage(String msj) {
        this.msj = msj;
    }

    @Override
    public String getMessage() {
        return this.msj;
    }

    @Override
    public void setData(T t) {
        this.data = t;
    }

    @Override
    public void setDataList(List<T> t) {
        this.dataList = t;
    }

    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public List<T> getDataList() {
        return this.dataList;
    }

}
