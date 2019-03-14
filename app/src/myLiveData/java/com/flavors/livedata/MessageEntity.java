package com.flavors.livedata;

import java.io.Serializable;
import java.util.List;

public class MessageEntity implements Serializable {

    private int type;
    private long time;
    private int code;
    private String text;
    private String url;
    private List<NewsEntity> list;

    public MessageEntity() {
    }

    public MessageEntity(int type, long time) {
        this.type = type;
        this.time = time;
        this.text = text;
    }

    public MessageEntity(int type, long time, String text) {
        this.type = type;
        this.time = time;
        this.text = text;
    }

    public List<NewsEntity> getList() {
        return list;
    }

    public void setList(List<NewsEntity> list) {
        this.list = list;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "type=" + type +
                ", time=" + time +
                ", code=" + code +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", list=" + list +
                '}';
    }
}
