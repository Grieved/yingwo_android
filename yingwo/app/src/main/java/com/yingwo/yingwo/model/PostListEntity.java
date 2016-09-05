package com.yingwo.yingwo.model;

import java.util.List;

/**
 * Created by wangyu on 9/3/16.
 */

public class PostListEntity {
    /**
     * info : [{"id":"5","user_id":"26","post_id":"1","create_time":"1472913289","content":"123675577977878","img":"http://obabu2buy.bkt.clouddn.com/FoHwTr5pYHWjGIMNdfWVwrRJra0j,http://obabu2buy.bkt.clouddn.com/FnCFL7GII1a0Ae-6FwE4JR5awZg9,http://obabu2buy.bkt.clouddn.com/Fi6fBoVQAMjxtXNCU8k9hCljNa_9,http://obabu2buy.bkt.clouddn.com/FgdloRlk87Xaj36obf1FpqwtET-R,http://obabu2buy.bkt.clouddn.com/FkzFVBh0vw8JXauwypEAYldvcKSt,http://obabu2buy.bkt.clouddn.com/FtdockIoq7QAaAdSoxuXieQ6pkX9,http://obabu2buy.bkt.clouddn.com/Fu-_wEGG1eXej0HFEH3y8egT72hk","del":"0","user_name":null,"user_face_img":null},{"id":"3","user_id":"26","post_id":"1","create_time":"1472911170","content":"哈哈哈哈哈","img":"","del":"0","user_name":null,"user_face_img":null},{"id":"2","user_id":"4","post_id":"1","create_time":"1472873086","content":"回复","img":"","del":"0","user_name":null,"user_face_img":null},{"id":"1","user_id":"0","post_id":"1","create_time":"1472872766","content":"回复","img":"","del":"0","user_name":null,"user_face_img":null}]
     * status : 1
     * url :
     */

    private int status;
    private String url;
    /**
     * id : 5
     * user_id : 26
     * post_id : 1
     * create_time : 1472913289
     * content : 123675577977878
     * img : http://obabu2buy.bkt.clouddn.com/FoHwTr5pYHWjGIMNdfWVwrRJra0j,http://obabu2buy.bkt.clouddn.com/FnCFL7GII1a0Ae-6FwE4JR5awZg9,http://obabu2buy.bkt.clouddn.com/Fi6fBoVQAMjxtXNCU8k9hCljNa_9,http://obabu2buy.bkt.clouddn.com/FgdloRlk87Xaj36obf1FpqwtET-R,http://obabu2buy.bkt.clouddn.com/FkzFVBh0vw8JXauwypEAYldvcKSt,http://obabu2buy.bkt.clouddn.com/FtdockIoq7QAaAdSoxuXieQ6pkX9,http://obabu2buy.bkt.clouddn.com/Fu-_wEGG1eXej0HFEH3y8egT72hk
     * del : 0
     * user_name : null
     * user_face_img : null
     */

    private List<InfoBean> info;

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

    public static class InfoBean {
        private String id;
        private String user_id;
        private String post_id;
        private String create_time;
        private String content;
        private String img;
        private String del;
        private Object user_name;
        private Object user_face_img;

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

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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

        public String getDel() {
            return del;
        }

        public void setDel(String del) {
            this.del = del;
        }

        public Object getUser_name() {
            return user_name;
        }

        public void setUser_name(Object user_name) {
            this.user_name = user_name;
        }

        public Object getUser_face_img() {
            return user_face_img;
        }

        public void setUser_face_img(Object user_face_img) {
            this.user_face_img = user_face_img;
        }
    }
}
