package com.wn.ssp.open.sdk;

import lombok.Data;

/**
 * @author majunjie
 * @date 2022/1/19 10:48
 */
@Data
public class RequestParam {

    private String appKey;
    private long timestamp;
    private String data;
    private String sign;

}
