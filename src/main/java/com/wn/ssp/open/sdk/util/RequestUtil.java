package com.wn.ssp.open.sdk.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wn.ssp.open.sdk.RequestParam;

/**
 *
 * @author majunjie
 * @date 2022/1/19 10:54
 */
public class RequestUtil {

    public static String generateRequestBody(String serviceBody, String appKey, String apiSecret) {
        System.out.println("业务参数明文：" + serviceBody);
        String enData = DesUtil.encryptBy3DesHex(serviceBody, apiSecret);
        System.out.println("业务参数密文：" + enData);

        RequestParam requestParam = new RequestParam();
        requestParam.setAppKey(appKey);
        requestParam.setTimestamp(System.currentTimeMillis());
        requestParam.setData(enData);

        String sign = SignUtil.generateSign(JSON.parseObject(JSON.toJSONString(requestParam)), apiSecret);
        System.out.println("请求签名：" + sign);
        requestParam.setSign(sign);

        return JSON.toJSONString(requestParam);
    }

    /**
     * 适用于url302跳转时，在url拼参数的场景
     *
     * @param requestBody
     * @return
     */
    public static String generateUrlParam(String requestBody) {
        StringBuilder builder = new StringBuilder();
        JSONObject paramJSON = JSON.parseObject(requestBody);
        for (String key : paramJSON.keySet()) {
            builder.append(key).append("=").append(paramJSON.getString(key)).append("&");
        }

        return builder.deleteCharAt(builder.length()-1).toString();
    }

}
