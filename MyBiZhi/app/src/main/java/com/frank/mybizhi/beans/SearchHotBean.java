package com.frank.mybizhi.beans;

import java.util.List;

/**
 * Created by Administrator on 2016/10/14.
 */

public class SearchHotBean {

    /**
     * code : E00000000
     * msg : 操作成功。
     * data : [{"keyword":"汽车","imgs":["http://bzpic.spriteapp.cn/picture2/M00/0C/39/wKiFWVRsDzGAeSvwAAFtGFI8Ifg950.jpg"]},{"keyword":"卡通","imgs":["http://bzpic.spriteapp.cn/picture1/M00/10/7C/wKiFQ1RsDwOASA8lAACq2T3Mves198.jpg"]},{"keyword":"风景","imgs":["http://bzpic.spriteapp.cn/picture1/M00/10/7A/wKiFR1RsDtuAcAGBAAHZTmz6tkQ868.jpg"]},{"keyword":"动物","imgs":["http://bzpic.spriteapp.cn/picture2/M00/0C/38/wKiFRlRsDrSAF7BYAAFA6M31RQ0397.jpg"]}]
     */

    private String code;
    private String msg;
    /**
     * keyword : 汽车
     * imgs : ["http://bzpic.spriteapp.cn/picture2/M00/0C/39/wKiFWVRsDzGAeSvwAAFtGFI8Ifg950.jpg"]
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
