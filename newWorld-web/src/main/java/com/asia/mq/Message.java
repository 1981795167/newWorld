package com.asia.mq;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Xavier.liu
 * @date 2020/5/14 16:33
 */
@Data
@Builder
public class Message implements Serializable {

    private long msgSeq;

    private String msgBody;

    private long deliverTag;
}
