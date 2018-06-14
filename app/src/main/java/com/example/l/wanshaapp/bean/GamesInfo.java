package com.example.l.wanshaapp.bean;

public class GamesInfo {


    /**
     * game_name : 火花人生
     * game_introduction : 爆竹的生活并不容易!
     * publisher : 傻逼公司
     * game_type1 : 益智
     * game_type2 : 解密
     * game_type3 : null
     * game_type4 : null
     * grade : 7.8
     * game_details : 一旦点燃了生命的火花，人生就开始倒计时。\n" +
     "\n" +
     "  《火花人生》是一款画面靓丽、讲述爆竹命运的平台游戏。踏上寻找长乐的旅程，传说那是一支导火线从未点燃过的神秘爆竹。不过，人生若无火花,岂不枉来一遭？!
     * game_videourl : http://sj.video.5054399.com/sjyx/huohrsrsios/huohrsrsios.mp4
     * game_recommend : 《火花人生》是一款画面非常唯美的休闲手游。游戏中玩家将扮演一个被点燃引线的爆竹在指定时间内要跑到终点，当然路上会有各种障碍或者沟壑，玩家必须眼疾手快使出十八般武功才能取得最好的成绩！感兴趣的小伙伴不妨下载试玩一番！\n" +
     "\n" +"Welcome to the 爆竹世界\n" +
     "在这个瞬然易逝的烟花纷飞的世界里，寻找属于自己的芳华
     * game_downloadurl : http://shouji.360tpcdn.com/180123/3e2ca8de45d69f9d0ef4bd01e0f96357/h.h.r.s.sparklife.other_1.apk
     * game_pictureurl : http://"+mContext.getString(R.string.netip)+":8080/AndroidServers/images/rankinghot_01.jpg
     */

    private String game_name;
    private String game_introduction;
    private String publisher;
    private String game_type1;
    private String game_type2;
    private String game_type3;
    private String game_type4;
    private String grade;
    private String game_details;
    private String game_videourl;
    private String game_recommend;
    private String game_downloadurl;
    private String game_pictureurl;

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_introduction() {
        return game_introduction;
    }

    public void setGame_introduction(String game_introduction) {
        this.game_introduction = game_introduction;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGame_type1() {
        return game_type1;
    }

    public void setGame_type1(String game_type1) {
        this.game_type1 = game_type1;
    }

    public String getGame_type2() {
        return game_type2;
    }

    public void setGame_type2(String game_type2) {
        this.game_type2 = game_type2;
    }

    public Object getGame_type3() {
        return game_type3;
    }

    public void setGame_type3(String game_type3) {
        this.game_type3 = game_type3;
    }

    public Object getGame_type4() {
        return game_type4;
    }

    public void setGame_type4(String game_type4) {
        this.game_type4 = game_type4;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGame_details() {
        return game_details;
    }

    public void setGame_details(String game_details) {
        this.game_details = game_details;
    }

    public String getGame_videourl() {
        return game_videourl;
    }

    public void setGame_videourl(String game_videourl) {
        this.game_videourl = game_videourl;
    }

    public String getGame_recommend() {
        return game_recommend;
    }

    public void setGame_recommend(String game_recommend) {
        this.game_recommend = game_recommend;
    }

    public String getGame_downloadurl() {
        return game_downloadurl;
    }

    public void setGame_downloadurl(String game_downloadurl) {
        this.game_downloadurl = game_downloadurl;
    }

    public String getGame_pictureurl() {
        return game_pictureurl;
    }

    public void setGame_pictureurl(String game_pictureurl) {
        this.game_pictureurl = game_pictureurl;
    }
}
