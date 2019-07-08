package com.plat.sdk.server;

public class CasePostSpeakBean {

    /**
     * msg : success
     * status : 0
     */

    private String msg;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CasePostSpeakBean{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
