package com.example.l.wanshaapp.DynamicForum;

import java.util.List;

/**
 * Created by cap on 2018/5/21.
 */

public class BeanAttention {

    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * Object : {"ForumId":"up001","ForumPortrait":"http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg","ForumName":"腾讯游戏","ForumTime":"2018/6"}
         */

        private ObjectBean Object;

        public ObjectBean getObject() {
            return Object;
        }

        public void setObject(ObjectBean Object) {
            this.Object = Object;
        }

        public static class ObjectBean {
            /**
             * ForumId : up001
             * ForumPortrait : http://p2.so.qhimgs1.com/bdr/_240_/t01f61dbbc072bac262.jpg
             * ForumName : 腾讯游戏
             * ForumTime : 2018/6
             */

            private String ForumId;
            private String ForumPortrait;
            private String ForumName;
            private String ForumTime;

            public String getForumId() {
                return ForumId;
            }

            public void setForumId(String ForumId) {
                this.ForumId = ForumId;
            }

            public String getForumPortrait() {
                return ForumPortrait;
            }

            public void setForumPortrait(String ForumPortrait) {
                this.ForumPortrait = ForumPortrait;
            }

            public String getForumName() {
                return ForumName;
            }

            public void setForumName(String ForumName) {
                this.ForumName = ForumName;
            }

            public String getForumTime() {
                return ForumTime;
            }

            public void setForumTime(String ForumTime) {
                this.ForumTime = ForumTime;
            }
        }
    }
}
