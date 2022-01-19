package com.wn.ssp.open.sdk.util;

import com.wn.ssp.open.sdk.Validator;
import com.wn.ssp.open.sdk.exception.ErrorMsg;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * 签名工具
 *
 * @author majunjie
 * @date 2022/1/19 10:06
 */
public class SignUtil {

    public static void main(String[] args) {
        System.out.println(32);
    }

    /**
     * 生成签名
     *
     * @param requestParams 请求数据（不含签名）
     * @param apiSecret     密文数据
     * @return
     */
    public static String generateSign(Map<String, Object> requestParams, String apiSecret) {
        Map rqParams = getRqParams(requestParams, apiSecret);
        return (String) rqParams.get("sign");
    }

    public static Map getRqParams(Map<String, Object> requestParams, String apiSecret) {
        Validator.notNull(requestParams, ErrorMsg.NEW("请求参数为空"));
        Validator.notEmpty(apiSecret, ErrorMsg.NEW("秘钥为空"));
        //参数转成map
        //剔除sign
        requestParams.remove("sign");
        //加入apiSecret
        requestParams.put("apiSecret", apiSecret);
        //自然排序后拼接参数值
        String signString = map2SignString(requestParams);
        System.out.println("签名原串: " + signString);
        //生成签名
        String sign = Md5Util.getMD5(signString);
        System.out.println("签名: " + sign);
        //删除秘钥
        requestParams.remove("apiSecret");
        //加入签名
        requestParams.put("sign", sign);
        return requestParams;
    }

    /**
     * 生成签名
     *
     * @param requestParams 接收数据（含签名）
     * @param apiSecret     密文数据
     * @return true/false
     */
    public static boolean checkSign(Map<String, Object> requestParams, String apiSecret) {
        Validator.notNull(requestParams, ErrorMsg.NEW("接收数据为空"));
        Validator.notEmpty(apiSecret, ErrorMsg.NEW("秘钥为空"));
        String sign = (String) requestParams.get("sign");
        long timestamp = (long) requestParams.get("timestamp");
        String newSign = generateSign(requestParams, apiSecret);
        boolean result = sign.equals(newSign);
        long interval = System.currentTimeMillis() - timestamp;
        Validator.isTrue(Math.abs(interval) <= TimeUnit.MINUTES.toMillis(3), ErrorMsg.NEW("签名时差超过3分钟"));
        return result;
    }

    public static String map2SignString(Map map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> paramMap = sortByKey(map);
        StringBuilder content = new StringBuilder();
        for (String key : paramMap.keySet()) {
            String value = paramMap.get(key).toString();
            if (StringUtils.isNotBlank(value)) {
                content.append(value);
            }
        }
        return content.toString();
    }

    public static Map<String, Object> sortByKey(Map map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<>(Comparator.naturalOrder());
        sortMap.putAll(map);
        return sortMap;
    }

}
