package com.wn.ssp.open.sdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import static com.wn.ssp.open.sdk.util.RequestUtil.generateFinalRq;

/**
 * @author majunjie
 * @date 2022/1/19 10:54
 */
public class DemoSign {

    public static void main(String[] args) {
        String appKey = "testAppKey";
        String apiSecret = "Ib2G2YZkKtbqc9IUcG96Bhl71yqj8qdy";

        //业务参数
        JSONObject serviceMode = new JSONObject();
        serviceMode.put("param1", "aaa");
        serviceMode.put("param2", "bbb");
        serviceMode.put("param3", "ccc");

        String generateFinalRq = generateFinalRq(JSON.toJSONString(serviceMode), appKey, apiSecret);
        System.out.println("最终请求报文：" + generateFinalRq);
    }

}
