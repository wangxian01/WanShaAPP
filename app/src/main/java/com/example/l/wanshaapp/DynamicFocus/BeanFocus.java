package com.example.l.wanshaapp.DynamicFocus;

import java.util.List;

/**
 * Created by cap on 2018/5/28.
 */

public class BeanFocus {


    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * FocusObject : {"UpId":"upxgz002","Portrait":"http://p3.so.qhmsg.com/t01bb2a6057a5fb3d48.jpg","FocusName":"游戏小公举","FocusTime":"2018/6","FocusFrom":"来自腾讯光子工作室","FocusText":"《王者荣耀》是腾讯第一5V5团队公平竞技手游，于10月28日开启不限号测试！5V5王者峡谷（含迷雾模式）、5V5深渊大乱斗、以及3V3、1V1等多样模式一键体验，热血竞技尽享快感！海量英雄随心选择，精妙配合默契作战！10秒实时跨区匹配，与好友组队登顶最强王者！操作简单易上手，一血、五杀、超神，极致还原经典体验！实力操作公平对战，回归MOBA初心！","Inintroduce ":"剑仙李白潇洒游走，全场收割十步杀一人！","Introduction ":"剑仙李白潇洒游走，全场收割十步杀一人！","ImgAddress":"http://upload.gezila.com/data/20160912/25941473646354.jpg","UpLike":true,"LikeNumber":69}
         */

        private FocusObjectBean FocusObject;

        public FocusObjectBean getFocusObject() {
            return FocusObject;
        }

        public void setFocusObject(FocusObjectBean FocusObject) {
            this.FocusObject = FocusObject;
        }

        public static class FocusObjectBean {
            /**
             * UpId : upxgz002
             * Portrait : http://p3.so.qhmsg.com/t01bb2a6057a5fb3d48.jpg
             * FocusName : 游戏小公举
             * FocusTime : 2018/6
             * FocusFrom : 来自腾讯光子工作室
             * FocusText : 《王者荣耀》是腾讯第一5V5团队公平竞技手游，于10月28日开启不限号测试！5V5王者峡谷（含迷雾模式）、5V5深渊大乱斗、以及3V3、1V1等多样模式一键体验，热血竞技尽享快感！海量英雄随心选择，精妙配合默契作战！10秒实时跨区匹配，与好友组队登顶最强王者！操作简单易上手，一血、五杀、超神，极致还原经典体验！实力操作公平对战，回归MOBA初心！
             * Inintroduce  : 剑仙李白潇洒游走，全场收割十步杀一人！
             * Introduction  : 剑仙李白潇洒游走，全场收割十步杀一人！
             * ImgAddress : http://upload.gezila.com/data/20160912/25941473646354.jpg
             * UpLike : true
             * LikeNumber : 69
             */

            private String UpId;
            private String Portrait;
            private String FocusName;
            private String FocusTime;
            private String FocusFrom;
            private String FocusText;
            private String Inintroduce;
            private String Introduction;
            private String ImgAddress;
            private boolean UpLike;
            private int LikeNumber;

            public String getUpId() {
                return UpId;
            }

            public void setUpId(String UpId) {
                this.UpId = UpId;
            }

            public String getPortrait() {
                return Portrait;
            }

            public void setPortrait(String Portrait) {
                this.Portrait = Portrait;
            }

            public String getFocusName() {
                return FocusName;
            }

            public void setFocusName(String FocusName) {
                this.FocusName = FocusName;
            }

            public String getFocusTime() {
                return FocusTime;
            }

            public void setFocusTime(String FocusTime) {
                this.FocusTime = FocusTime;
            }

            public String getFocusFrom() {
                return FocusFrom;
            }

            public void setFocusFrom(String FocusFrom) {
                this.FocusFrom = FocusFrom;
            }

            public String getFocusText() {
                return FocusText;
            }

            public void setFocusText(String FocusText) {
                this.FocusText = FocusText;
            }

            public String getInintroduce() {
                return Inintroduce;
            }

            public void setInintroduce(String Inintroduce) {
                this.Inintroduce = Inintroduce;
            }

            public String getIntroduction() {
                return Introduction;
            }

            public void setIntroduction(String Introduction) {
                this.Introduction = Introduction;
            }

            public String getImgAddress() {
                return ImgAddress;
            }

            public void setImgAddress(String ImgAddress) {
                this.ImgAddress = ImgAddress;
            }

            public boolean isUpLike() {
                return UpLike;
            }

            public void setUpLike(boolean UpLike) {
                this.UpLike = UpLike;
            }

            public int getLikeNumber() {
                return LikeNumber;
            }

            public void setLikeNumber(int LikeNumber) {
                this.LikeNumber = LikeNumber;
            }
        }
    }
}
