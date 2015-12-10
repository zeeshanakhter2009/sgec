/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgec.utility;

import java.util.List;

/**
 *
 * @author Zeeshan
 */
public class ReturnStatus {

    private int errorCode;
    private String statusCode;
    private String exceptionMessage;
    private Object object;
    private List<Object> objectList;
    private String message;
    private String streamUrl;
    private String contentId;
    private int lastInsertedId;

    public ReturnStatus() {
    }

    public ReturnStatus(int errorCode, String statusCode, String exceptionMessage, Object object, List<Object> objectList, String message, String streamUrl, String contentId, int lastInsertedId) {
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.exceptionMessage = exceptionMessage;
        this.object = object;
        this.objectList = objectList;
        this.message = message;
        this.streamUrl = streamUrl;
        this.contentId = contentId;
        this.lastInsertedId = lastInsertedId;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<Object> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Object> objectList) {
        this.objectList = objectList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public int getLastInsertedId() {
        return lastInsertedId;
    }

    public void setLastInsertedId(int lastInsertedId) {
        this.lastInsertedId = lastInsertedId;
    }

    @Override
    public String toString() {
        return "ReturnStatus{" + "errorCode=" + errorCode + ", statusCode=" + statusCode + ", exceptionMessage=" + exceptionMessage + ", object=" + object + ", objectList=" + objectList + ", message=" + message + ", streamUrl=" + streamUrl + ", contentId=" + contentId + ", lastInsertedId=" + lastInsertedId + '}';
    }

}
