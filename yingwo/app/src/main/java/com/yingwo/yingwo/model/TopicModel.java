package com.yingwo.yingwo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FJS0420 on 2016/9/2.
 */

public class TopicModel {


    /**
     * info : [{"id":"38","user_id":"4","topic_id":"0","school_id":"0","content":"斤斤计较","img":"http://obabu2buy.bkt.clouddn.com/FiIy0ccX6udWVjkpbyX0rkW9hNxK,http://obabu2buy.bkt.clouddn.com/Fl98npesheCYp4JUsSd4qdA-kfdF,http://obabu2buy.bkt.clouddn.com/FmeKBdu1m3mSjwqN6ivVaJpy2woI,http://obabu2buy.bkt.clouddn.com/Fj03XLZd-ZSYbYiz0Pxtxj66UV0m,http://obabu2buy.bkt.clouddn.com/Fm0yJm6VZk28dB0ys41ogLoZrclE,","create_time":"1472752348","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null},{"id":"37","user_id":"4","topic_id":"0","school_id":"0","content":"红红火火","img":"http://obabu2buy.bkt.clouddn.com/Fh78IPa8lNMB2n63RY2NksEcAkQW,,http://obabu2buy.bkt.clouddn.com/FiIy0ccX6udWVjkpbyX0rkW9hNxK,,http://obabu2buy.bkt.clouddn.com/Fl98npesheCYp4JUsSd4qdA-kfdF,,http://obabu2buy.bkt.clouddn.com/FrCKesnpPDi0dhs_Mc4sCBpekUqO,,","create_time":"1472752184","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null},{"id":"36","user_id":"4","topic_id":"0","school_id":"0","content":"广告歌","img":"http://obabu2buy.bkt.clouddn.com/Fh78IPa8lNMB2n63RY2NksEcAkQW,http://obabu2buy.bkt.clouddn.com/FiIy0ccX6udWVjkpbyX0rkW9hNxK,","create_time":"1472751243","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null},{"id":"35","user_id":"4","topic_id":"0","school_id":"0","content":"更好","img":"http://obabu2buy.bkt.clouddn.com/Fh78IPa8lNMB2n63RY2NksEcAkQW,","create_time":"1472750986","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null},{"id":"34","user_id":"4","topic_id":"0","school_id":"0","content":"个广告歌广告歌","img":"http://obabu2buy.bkt.clouddn.com/FsOVxGZFQVnWhRRSSTTLxJZZpmcc,http://obabu2buy.bkt.clouddn.com/FrCKesnpPDi0dhs_Mc4sCBpekUqO,","create_time":"1472750844","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null},{"id":"33","user_id":"4","topic_id":"0","school_id":"0","content":"gggggg个","img":"http://obabu2buy.bkt.clouddn.com/FiIy0ccX6udWVjkpbyX0rkW9hNxK,","create_time":"1472750568","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null},{"id":"32","user_id":"4","topic_id":"0","school_id":"0","content":"hhhh6666","img":"http://obabu2buy.bkt.clouddn.com/Fu1Y0a43lIsv8nUAYQZoaIWdWL4V,","create_time":"1472750275","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null},{"id":"28","user_id":"3","topic_id":"0","school_id":"0","content":"44","img":"http://obabu2buy.bkt.clouddn.com/FqbVnhsIBKuOYz8TuvEwgNblZsjS","create_time":"1472702367","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null},{"id":"27","user_id":"3","topic_id":"0","school_id":"0","content":"3333","img":"http://obabu2buy.bkt.clouddn.com/FqbVnhsIBKuOYz8TuvEwgNblZsjS","create_time":"1472702344","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null},{"id":"26","user_id":"3","topic_id":"0","school_id":"1","content":"2223","img":"http://obabu2buy.bkt.clouddn.com/FoCBJYZsKiJaFBts2dDHdEPtKtCB,http://obabu2buy.bkt.clouddn.com/FgX_ppD-KcEWlfQgH4tafvP89Trn,http://obabu2buy.bkt.clouddn.com/FoTCOusjg0guZPOeRHVD5E55Xjaw,http://obabu2buy.bkt.clouddn.com/FqiRJH3fOPGLvTN-5xtXR5Eia87D","create_time":"1472700724","like_cnt":"0","reply_cnt":"0","del":"0","top":"0","user_name":null,"user_face_img":null,"topic_title":null}]
     * status : 1
     * url :
     */

    private int status;
    private String url;
    /**
     * id : 38
     * user_id : 4
     * topic_id : 0
     * school_id : 0
     * content : 斤斤计较
     * img : http://obabu2buy.bkt.clouddn.com/FiIy0ccX6udWVjkpbyX0rkW9hNxK,http://obabu2buy.bkt.clouddn.com/Fl98npesheCYp4JUsSd4qdA-kfdF,http://obabu2buy.bkt.clouddn.com/FmeKBdu1m3mSjwqN6ivVaJpy2woI,http://obabu2buy.bkt.clouddn.com/Fj03XLZd-ZSYbYiz0Pxtxj66UV0m,http://obabu2buy.bkt.clouddn.com/Fm0yJm6VZk28dB0ys41ogLoZrclE,
     * create_time : 1472752348
     * like_cnt : 0
     * reply_cnt : 0
     * del : 0
     * top : 0
     * user_name : null
     * user_face_img : null
     * topic_title : null
     */

    public List<InfoBean> info;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean implements Serializable {
        private String id;
        private String user_id;
        private String topic_id;
        private String school_id;
        private String content;
        private String img;
        private String create_time;
        private String like_cnt;
        private String reply_cnt;
        private String del;
        private String top;
        private String user_name;
        private String user_face_img;
        private String topic_title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(String topic_id) {
            this.topic_id = topic_id;
        }

        public String getSchool_id() {
            return school_id;
        }

        public void setSchool_id(String school_id) {
            this.school_id = school_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getLike_cnt() {
            return like_cnt;
        }

        public void setLike_cnt(String like_cnt) {
            this.like_cnt = like_cnt;
        }

        public String getReply_cnt() {
            return reply_cnt;
        }

        public void setReply_cnt(String reply_cnt) {
            this.reply_cnt = reply_cnt;
        }

        public String getDel() {
            return del;
        }

        public void setDel(String del) {
            this.del = del;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_face_img() {
            return user_face_img;
        }

        public void setUser_face_img(String user_face_img) {
            this.user_face_img = user_face_img;
        }

        public String getTopic_title() {
            return topic_title;
        }

        public void setTopic_title(String topic_title) {
            this.topic_title = topic_title;
        }
    }
}
