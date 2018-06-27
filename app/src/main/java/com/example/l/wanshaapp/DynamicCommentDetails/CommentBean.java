package com.example.l.wanshaapp.DynamicCommentDetails;

/**
 * Created by cap on 2018/6/8.
 */

public class CommentBean {


    /**
     * comments_id : 1
     * comments_name : 曾经的王者
     * comments_portrait : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=845455651,3513714624&fm=27&gp=0.jpg
     * comments_text : 这个游戏适合小学生耍，三岁以上就不要耍了
     * comments_time : 2018.5
     * comments_like : 0
     * comments_number : null
     */

    private String comments_id;
    private String comments_name;
    private String comments_portrait;
    private String comments_text;
    private String comments_time;
    private String comments_like;
    private Object comments_number;

    public String getComments_id() {
        return comments_id;
    }

    public void setComments_id(String comments_id) {
        this.comments_id = comments_id;
    }

    public String getComments_name() {
        return comments_name;
    }

    public void setComments_name(String comments_name) {
        this.comments_name = comments_name;
    }

    public String getComments_portrait() {
        return comments_portrait;
    }

    public void setComments_portrait(String comments_portrait) {
        this.comments_portrait = comments_portrait;
    }

    public String getComments_text() {
        return comments_text;
    }

    public void setComments_text(String comments_text) {
        this.comments_text = comments_text;
    }

    public String getComments_time() {
        return comments_time;
    }

    public void setComments_time(String comments_time) {
        this.comments_time = comments_time;
    }

    public String getComments_like() {
        return comments_like;
    }

    public void setComments_like(String comments_like) {
        this.comments_like = comments_like;
    }

    public Object getComments_number() {
        return comments_number;
    }

    public void setComments_number(Object comments_number) {
        this.comments_number = comments_number;
    }
}
