package com.aits.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author jared
 */
@Component
public class Schedule {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    //秒（0-59），分，小时（0-23），日期 天/日（1-31），日期月份（1-12），星期（1-7），年（1970-2099，可以省略）
    @Scheduled(cron = "0 0/1 * * * ?")
    public void start(){
        logger.info("我执行了一次===========");
    }
    //@Scheduled(fixedDelay = 2000)
}
