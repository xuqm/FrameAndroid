package com.xuqm.base.model;

import androidx.annotation.NonNull;

public class HttpResult<T> {
    private String status;
    private String maxNumber;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    @NonNull
    public String toString() {
        return "HttpResult{" +
                "status='" + status + '\'' +
                ", maxNumber='" + maxNumber + '\'' +
                ", data=" + data +
                '}';
    }
}
