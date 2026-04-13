package com.sm.storagemanager.ResponseHttp;

import java.util.List; 

public interface ResponseHttp<T>    {

    public void setMessage(String msj);

    public String getMessage();

    public void setData(T t);

    public void setDataList(List<T> t);

    public T getData();

    public List<T> getDataList();

}
