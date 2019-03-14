package com.flavors.livedata;

import java.io.Serializable;

/**
 * Created by sunfusheng on 2015/2/4.
 */
public class NewsEntity implements Serializable {

    private String article;
    private String source;
    private String icon;
    private String detailurl;

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDetailurl() {
        return detailurl;
    }

    public void setDetailurl(String detailurl) {
        this.detailurl = detailurl;
    }
}
