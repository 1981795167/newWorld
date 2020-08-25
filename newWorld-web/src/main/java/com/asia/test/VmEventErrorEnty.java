package com.asia.test;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VmEventErrorEnty  {

    private String deviceId;

    private String type_name;

    private String area;

    private Integer errorCode;

    private String operation_stratus;


    private String category;

    private Date startTime;

    private Date endTime;

    private Long spanTime;

    private Long spanTimeHour;

    private Long spanTimeMinute;

    private Long spanTimeDay;

}
