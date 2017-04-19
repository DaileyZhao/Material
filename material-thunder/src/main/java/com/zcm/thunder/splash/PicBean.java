package com.zcm.thunder.splash;

import java.util.List;

/**
 * Created by zcm on 17-4-18.
 */

public class PicBean {
    private int code;

    private String msg;

    private List<Newslist> newslist ;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setNewslist(List<Newslist> newslist){
        this.newslist = newslist;
    }
    public List<Newslist> getNewslist(){
        return this.newslist;
    }
    public class Newslist {
        private String ctime;

        private String title;

        private String description;

        private String picUrl;

        private String url;

        public void setCtime(String ctime){
            this.ctime = ctime;
        }
        public String getCtime(){
            return this.ctime;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public void setPicUrl(String picUrl){
            this.picUrl = picUrl;
        }
        public String getPicUrl(){
            return this.picUrl;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }

    }
}
