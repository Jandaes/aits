##aits查询12306票务功能

[获取城市信息编码](http://localhost:8080/ticketInfo/getAllCity)

[查票api](http://localhost:8080/ticketInfo/getTicket?train_date=2017-10-20&from_station=BJP&to_station=XMS&purpose_codes=ADULT)
    
    request：
    param:
        train_date=2017-10-20    出发日期
        from_station=BJP         起始站
        to_station=XMS           目的站
        purpose_codes=ADULT      学生/成人
    
    response：
    param：
        trainNumber              车次
        departureStation         始发站
        destinationStation       目的站
        initialTime              出发时间
        arriveTime               到达时间
        last                     历时
        businessSeat             商务特等
        firstSeat                一等座
        secondSeat               二等座
        highSleeper              高级软卧
        softSleeper              软卧
        activeSleeper            动卧
        hardSleeper              硬卧
        softSeat                 软座
        hardSeat                 硬座
        noSeat                   无座
        other                    其他
        remark                   备注

    