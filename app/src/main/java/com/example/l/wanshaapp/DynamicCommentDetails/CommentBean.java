package com.example.l.wanshaapp.DynamicCommentDetails;

/**
 * Created by cap on 2018/6/8.
 */

public class CommentBean {
    private String CommentName;
    private String CommentTime;
    private String Comment;
    private String content;
    private String CommentPortrait;

    public CommentBean(String CommentName, String content, String CommentTime, String CommentPortrait) {
        this.CommentName = CommentName;
        this.content = content;
        this.CommentTime = CommentTime;
        this.CommentPortrait=CommentPortrait;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentname() {
        return CommentName;
    }

    public void setCommentname(String commentname) {
        CommentName = commentname;
    }

    public String getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(String commentTime) {
        CommentTime = commentTime;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getCommentPortrait() {
        return CommentPortrait;
    }

    public void setCommentPortrait(String commentPortrait) {
        CommentPortrait = commentPortrait;
    }
}
