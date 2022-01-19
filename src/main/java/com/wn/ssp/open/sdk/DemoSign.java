package com.wn.ssp.open.sdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wn.ssp.open.sdk.exception.ErrorMsg;
import com.wn.ssp.open.sdk.util.RequestUtil;
import com.wn.ssp.open.sdk.util.SignUtil;

import java.util.concurrent.TimeUnit;

import static com.wn.ssp.open.sdk.util.RequestUtil.generateRequestBody;

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

        //post请求请求样例
        String requestBody = generateRequestBody(JSON.toJSONString(serviceMode), appKey, apiSecret);
        System.out.println("最终请求报文：" + requestBody);

        //url302跳转请求样例
        String urlParam = RequestUtil.generateUrlParam(requestBody);
        System.out.println("url参数：" + urlParam);

        //验签样例
        boolean checkSign = SignUtil.checkSign(JSON.parseObject(requestBody), apiSecret);
        System.out.println("验签：" + checkSign);
    }

}
