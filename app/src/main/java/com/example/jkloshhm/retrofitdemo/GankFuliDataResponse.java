package com.example.jkloshhm.retrofitdemo;

import java.util.List;

/**
 * @author jkloshhm 2018-05-16
 */
public class GankFuliDataResponse {

    /**
     * error : false
     * results : [{"_id":"591a4a02421aa92c794632c8","createdAt":"2017-05-16T08:38:26.35Z","desc":"5-16",
     * "publishedAt":"2017-05-16T12:10:38.580Z","source":"chrome","type":"福利",
     * "url":"http://ww1.sinaimg.cn/large/610dc034ly1ffmwnrkv1hj20ku0q1wfu.jpg",
     * "used":true,"who":"daimajai"},{"_id":"59187082421aa91c8da340d1","createdAt":
     * "2017-05-14T22:58:10.836Z","desc":"5-14","publishedAt":"2017-05-15T12:03:44.165Z","source":
     * "chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1ffla9ostxuj20ku0q2abt.jpg",
     * "used":true,"who":"带马甲"},{"_id":"59154ae7421aa90c7a8b2b0d","createdAt":"2017-05-12T13:40:55.505Z",
     * "desc":"5-13","publishedAt":"2017-05-12T13:44:54.673Z","source":"chrome","type":"福利",
     * "url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-12-18380140_455327614813449_854681840315793408_n.jpg",
     * "used":true,"who":"daimajia"},{"_id":"5913d09d421aa90c7fefdd8e","createdAt":"2017-05-11T10:46:53.608Z",
     * "desc":"5-11","publishedAt":"2017-05-11T12:03:09.581Z","source":"chrome","type":"福利",
     * "url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-11-18380166_305443499890139_8426655762360565760_n.jpg",
     * "used":true,"who":"代码家"},{"_id":"591264ce421aa90c7a8b2aec","createdAt":"2017-05-10T08:54:38.531Z",
     * "desc":"5-10","publishedAt":"2017-05-10T11:56:10.18Z","source":"chrome","type":"福利",
     * "url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-10-18382517_1955528334668679_3605707761767153664_n.jpg","used":true,"who":"带马甲"}]
     */
    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 591a4a02421aa92c794632c8
         * createdAt : 2017-05-16T08:38:26.35Z
         * desc : 5-16
         * publishedAt : 2017-05-16T12:10:38.580Z
         * source : chrome
         * type : 福利
         * url : http://ww1.sinaimg.cn/large/610dc034ly1ffmwnrkv1hj20ku0q1wfu.jpg
         * used : true
         * who : daimajai
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
