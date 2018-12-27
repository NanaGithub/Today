package com.example.jnn.today.sqlite;

import android.content.Context;

/**
 * 推单序列dao
 */
public class OrderSequenceDao {
    /**
     * 表名
     */
    private String TABLE_NAME = "order_sequence";
    /**
     * 唯一标识
     */
    private final static String FIELD_ID = "_id";
    /**
     * 订单id字段
     */
    private final static String FIELD_ORDER_ID = "_order_id";
    /**
     * 订单状态字段
     */
    private final static String FIELD_ORDER_STATE = "_order_state";
    /**
     * 订单类型字段
     */
    private final static String FIELD_ORDER_TYPE = "_order_type";
    /**
     * 起点纬度字段
     */
    private final static String FIELD_ORDER_START_LAT = "_order_start_lat";
    /**
     * 起点经度字段
     */
    private final static String FIELD_ORDER_START_LON = "_order_start_lon";
    /**
     * 起点位置字段
     */
    private final static String FIELD_ORDER_START_POINT = "_order_start_point";
    /**
     * 终点位置字段
     */
    private final static String FIELD_ORDER_END_POINT = "_order_end_point";
    /**
     * 用车时间字段
     */
    private final static String FIELD_ORDER_CONTINUE = "_order_continue";
    /**
     * 预约时间字段
     */
    private final static String FIELD_ORDER_REAL_DATE = "_order_real_date";
    /**
     * 创建时间字段 ---  被转化为时间戳以后
     */
    private final static String FIELD_ORDER_TRANSLATE_DATE = "_order_translate_date";
    /**
     * 创建预估价格开关
     */
    private final static String FIELD_ORDER_ESTIMATE_SWITCH = "_order_estimate_switch";
    /**
     * 创建预估价格字段
     */
    private final static String FIELD_ORDER_ESTIMATE_PRICE = "_order_estimate_price";
    /**
     * 语音单流水号字段
     */
    private final static String FIELD_ORDER_STATIC_NO = "_order_static_no";
    /**
     * 语音单用户电话字段
     */
    private final static String FIELD_ORDER_PASSENGER_PHONE = "_order_passenger_phone";
    /**
     * 语音单语音文件地址字段
     */
    private final static String FIELD_ORDER_VOICE = "_order_voice";
    /**
     * 拼车单数据
     */
    private final static String FIELD_ORDER = "_order";
    /**
     * 拼车单编号
     */
    private final static String FIELD_ORDER_MERGE_ID = "_order_merge_id";
    /**
     * 双通道-推送渠道
     */
    private final static String FIELD_PUSH_SOURCE = "_push_source";
    /**
     * 双通道-单轮标识
     */
    private final static String FIELD_UUID = "_uuid";
    /**
     * 逻辑删除标识
     */
    private final static String FIELD_LOCAL_YN = "_local_yn";
    /**
     * 推送渠道
     */
    private final static String FIELD_PUSH_CHANNEL = "_push_channel";
    /**
     * 推单轮数
     */
    private final static String FIELD_ORDER_PUSH_TIMES = "_order_times";
    /**
     * 一键叫车标记
     */
    private final static String FIELD_ORDER_QUICK = "_order_quick";

    private final OrderDBHelper helper;

    public OrderSequenceDao(Context context) {
        helper = OrderDBHelper.getInstance(context);
    }

}
