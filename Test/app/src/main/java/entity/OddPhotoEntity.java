package entity;

import java.util.List;

/**
 * Created by zky62 on 2016-10-12.
 */

public class OddPhotoEntity {

    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"你们是来搞笑的吗","hashId":"190B25E454C75D24510C603FB08ADD95","unixtime":1474171515,"updatetime":"2016-09-18 12:05:15","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/190B25E454C75D24510C603FB08ADD95.gif"},{"content":"告诉我，这理发店在哪里","hashId":"FAE0B8191EF4A7A47CD9A6E5B3EC4229","unixtime":1474171515,"updatetime":"2016-09-18 12:05:15","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/FAE0B8191EF4A7A47CD9A6E5B3EC4229.jpg"},{"content":"看你摔倒我就放心了","hashId":"C408D891917A0BB8AFFBD91B49F5574F","unixtime":1474171515,"updatetime":"2016-09-18 12:05:15","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/C408D891917A0BB8AFFBD91B49F5574F.gif"},{"content":"我特么的差点就信了","hashId":"960C24C75C79B9A05A0AF03DA73F38C4","unixtime":1474169756,"updatetime":"2016-09-18 11:35:56","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/960C24C75C79B9A05A0AF03DA73F38C4.gif"},{"content":"这样的女鬼，来十个我灭十个！","hashId":"89A91F0C2DFD8A8F7F545D15405BF65B","unixtime":1474167938,"updatetime":"2016-09-18 11:05:38","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/89A91F0C2DFD8A8F7F545D15405BF65B.gif"}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * content : 你们是来搞笑的吗
         * hashId : 190B25E454C75D24510C603FB08ADD95
         * unixtime : 1474171515
         * updatetime : 2016-09-18 12:05:15
         * url : http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/190B25E454C75D24510C603FB08ADD95.gif
         */

        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;
            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
