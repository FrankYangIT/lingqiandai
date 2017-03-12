package com.frank.mybizhi.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public class SearchMainListBean {

    /**
     * code : E00000000
     * msg : 操作成功。
     * data : [{"keyword":"文字","imgs":["http://bzpic.spriteapp.cn/picture2/M00/00/1C/wKiFRlKKWfOAbdHzAAG73_7b9kM47.jpeg","http://bzpic.spriteapp.cn/picture2/M00/00/1B/wKiFWVKKWfKAB_7pAAKM8JmRhbY85.jpeg","http://bzpic.spriteapp.cn/picture2/M00/00/1B/wKiFRlKKWfGAUA5fAABrBhZ4evE07.jpeg"]},{"keyword":"植物","imgs":["http://bzpic.spriteapp.cn/picture2/M00/00/17/wKiFRlKKWaGAfE_4AAFIBkqlj9w10.jpeg","http://bzpic.spriteapp.cn/picture1/M00/04/02/wKiFR1KKVXyASCzSAAQm7lM1TSA158.jpg","http://bzpic.spriteapp.cn/picture1/M00/04/02/wKiFQ1KKVX2Aa3t3AAVlPDyKMwg616.jpg"]},{"keyword":"动物","imgs":["http://bzpic.spriteapp.cn/picture2/M00/00/13/wKiFWVKKWYqALEHuAAEZtIhY32839.jpeg","http://bzpic.spriteapp.cn/picture1/M00/04/0E/wKiFQ1KKVf6ASFTcAAHFZPCPlyE880.jpg","http://bzpic.spriteapp.cn/picture1/M00/03/E4/wKiFR1KKVD-AC810AAKgUbJy1O0432.jpg"]},{"keyword":"可爱","imgs":["http://bzpic.spriteapp.cn/picture2/M00/00/1B/wKiFWVKKWfCAa4tSAADHMcuOBLk55.jpeg","http://bzpic.spriteapp.cn/picture1/M00/04/59/wKiFQ1KKWceASemaAAFq6Is-86A02.jpeg","http://bzpic.spriteapp.cn/picture1/M00/04/56/wKiFR1KKWbmAHQssAAHCX9cV5d403.jpeg"]}]
     */

    private String code;
    private String msg;
    /**
     * keyword : 文字
     * imgs : ["http://bzpic.spriteapp.cn/picture2/M00/00/1C/wKiFRlKKWfOAbdHzAAG73_7b9kM47.jpeg","http://bzpic.spriteapp.cn/picture2/M00/00/1B/wKiFWVKKWfKAB_7pAAKM8JmRhbY85.jpeg","http://bzpic.spriteapp.cn/picture2/M00/00/1B/wKiFRlKKWfGAUA5fAABrBhZ4evE07.jpeg"]
     */

    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String keyword;
        private List<String> imgs;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
