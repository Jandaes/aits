package com.liu.ticket.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jared
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryLine {
    /**
     *  车次
     */
    private String trainNumber;
    /**
     * 始发站
     */
    private String departureStation;
    /**
     * 目的站
     */
    private String destinationStation;
    /**
     * 出发时间
     */
    private String initialTime;
    /**
     * 到达时间
     */
    private String arriveTime;
    /**
     * 历时
     */
    private String last;
    /**
     * 商务特等
     */
    private String businessSeat;
    /**
     * 一等座
     */
    private String firstSeat;
    /**
     * 二等座
     */
    private String secondSeat;
    /**
     * 高级软卧
     */
    private String highSleeper;
    /**
     * 软卧
     */
    private String softSleeper;
    /**
     * 动卧
     */
    private String activeSleeper;
    /**
     * 硬卧
     */
    private String hardSleeper;
    /**
     * 软座
     */
    private String softSeat;
    /**
     * 硬座
     */
    private String hardSeat;
    /**
     * 无座
     */
    private String noSeat;
    /**
     * 其他
     */
    private String other;
    /**
     * 备注
     */
    private String remark;


}
