package com.example.l.wanshaapp.DynamicChoiceness;

import java.util.List;

/**
 * Created by cap on 2018/5/15.
 */

public class BeanChoiceness {

    private List<RowsBean> rows;

    public Boolean like;

    public Boolean step;

    public Boolean unfold;

    public int mumber;

    public int stepnumber;


    public Boolean getunfold() {
        return unfold;
    }

    public void setunfold(Boolean unfold) {
        this.unfold = unfold;
    }

    public int getstepnumber() {
        return stepnumber;
    }

    public void setstepnumber(int stepnumber) {
        this.stepnumber = stepnumber;
    }

    public int getmumber() {
        return mumber;
    }

    public void setmumber(int mumber) {
        this.mumber = mumber;
    }

    public Boolean getlike() {
        return like;
    }

    public void setlike(Boolean like) {
        this.like = like;
    }

    public Boolean getstep() {
        return step;
    }

    public void setstep(Boolean step) {
        this.step = step;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * workObject : {"UpId":"up001","UpPortrait":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=845455651,3513714624&fm=27&gp=0.jpg","UpName":"腾讯游戏","UpTime":"2018/6","UpText":"带领你的部落走向胜利！《部落冲突》是一个战争策略类游戏，在这里：你可以提高自己的野蛮人，弓箭手，野猪骑士，皮卡，龙和其他强大的军队战士与世界各地的玩家对战，提升你的世界排名与其他玩家联合起来建立强大部落与你的队友加入史诗级的部落战争对战可以通过iPad，iPhone以及安卓设备免费下载","UpIntroduce":"部落冲突开心时刻：炸弹人！兵分两路给我上呀","VideoImg":"http://coc.kunlun.com/images/top_1.jpg","VideoAddress":"https://dw-bw6.dwstatic.com/9748b96c8bf0c4518027ec75d6786f8c/5afbe5b5/83/6/1733/8512115-98-1503114648.mp4","UpLike":true,"LikeNumber":135,"UpStep":true,"StepNumber":164}
         */

        private WorkObjectBean workObject;

        public WorkObjectBean getWorkObject() {
            return workObject;
        }

        public void setWorkObject(WorkObjectBean workObject) {
            this.workObject = workObject;
        }

        public static class WorkObjectBean {
            /**
             * UpId : up001
             * UpPortrait : https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=845455651,3513714624&fm=27&gp=0.jpg
             * UpName : 腾讯游戏
             * UpTime : 2018/6
             * UpText : 带领你的部落走向胜利！《部落冲突》是一个战争策略类游戏，在这里：你可以提高自己的野蛮人，弓箭手，野猪骑士，皮卡，龙和其他强大的军队战士与世界各地的玩家对战，提升你的世界排名与其他玩家联合起来建立强大部落与你的队友加入史诗级的部落战争对战可以通过iPad，iPhone以及安卓设备免费下载
             * UpIntroduce : 部落冲突开心时刻：炸弹人！兵分两路给我上呀
             * VideoImg : http://coc.kunlun.com/images/top_1.jpg
             * VideoAddress : https://dw-bw6.dwstatic.com/9748b96c8bf0c4518027ec75d6786f8c/5afbe5b5/83/6/1733/8512115-98-1503114648.mp4
             * UpLike : true
             * LikeNumber : 135
             * UpStep : true
             * StepNumber : 164
             */

            private String UpId;
            private String UpPortrait;
            private String UpName;
            private String UpTime;
            private String UpText;
            private String UpIntroduce;
            private String VideoImg;
            private String VideoAddress;
            private boolean UpLike;
            private int LikeNumber;
            private boolean UpStep;
            private int StepNumber;

            public String getUpId() {
                return UpId;
            }

            public void setUpId(String UpId) {
                this.UpId = UpId;
            }

            public String getUpPortrait() {
                return UpPortrait;
            }

            public void setUpPortrait(String UpPortrait) {
                this.UpPortrait = UpPortrait;
            }

            public String getUpName() {
                return UpName;
            }

            public void setUpName(String UpName) {
                this.UpName = UpName;
            }

            public String getUpTime() {
                return UpTime;
            }

            public void setUpTime(String UpTime) {
                this.UpTime = UpTime;
            }

            public String getUpText() {
                return UpText;
            }

            public void setUpText(String UpText) {
                this.UpText = UpText;
            }

            public String getUpIntroduce() {
                return UpIntroduce;
            }

            public void setUpIntroduce(String UpIntroduce) {
                this.UpIntroduce = UpIntroduce;
            }

            public String getVideoImg() {
                return VideoImg;
            }

            public void setVideoImg(String VideoImg) {
                this.VideoImg = VideoImg;
            }

            public String getVideoAddress() {
                return VideoAddress;
            }

            public void setVideoAddress(String VideoAddress) {
                this.VideoAddress = VideoAddress;
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

            public boolean isUpStep() {
                return UpStep;
            }

            public void setUpStep(boolean UpStep) {
                this.UpStep = UpStep;
            }

            public int getStepNumber() {
                return StepNumber;
            }

            public void setStepNumber(int StepNumber) {
                this.StepNumber = StepNumber;
            }
        }
    }
}
