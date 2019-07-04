package com.flavors.livedata;


/**
 * @author ydong
 */
public class TulingParams {

    public static final String TULING_URL = "http://www.tuling123.com/openapi/";
    public static final String TULING_KEY = "467856abf9fd49d383e63b240e38b7d2";

    //public static  final String TULING_KEY = "577173e62a2ff6627b62e94d663b449c";

    public static class TulingCode {
        /**
         * 文本类数据
         */
        public static final int TEXT = 100000;
        /**
         * 网址类数据
         */
        public static final int URL = 200000;
        /**
         * 新闻
         */
        public static final int NEWS = 302000;
        /**
         * 应用、软件、下载
         */
        public static final int APP = 304000;
        /**
         * 列车
         */
        public static final int TRAIN = 305000;
        /**
         * 航班
         */
        public static final int PLANE = 306000;
        /**
         * 菜谱、视频、小说
         */
        public static final int VIDEO = 308000;
        /**
         * 酒店
         */
        public static final int HOTEL = 309000;
        /**
         * 价格
         */
        public static final int PLACE = 311000;
        /**
         * key的长度错误（32位）
         */
        public static final int ERRORKEY_LEN = 40001;
        /**
         * 请求内容为空
         */
        public static final int ERROR_TEXT_NULL = 40002;
        /**
         * key错误或帐号未激活
         */
        public static final int ERROR_KEY_ERROR = 40003;
        /**
         * 当天请求次数已用完
         */
        public static final int ERROR_OUT_OF_TIMES = 40004;
        /**
         * 暂不支持该功能
         */
        public static final int ERROR_NOT_SUPPORT = 40005;
        /**
         * 服务器升级中
         */
        public static final int ERROR_SERVER_UPDATE = 40006;
        /**
         * 服务器数据格式异常
         */
        public static final int ERROR_DATA_FORMAT = 40007;
    }
}
