package com.wn.ssp.open.sdk;

import com.wn.ssp.open.sdk.exception.SspOpenSdkException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author majunjie
 * @date 2022/1/19 10:19
 */
public class Validator {

    public static void isTrue(boolean expression, SspOpenSdkException re) {
        if (!expression) {
            throw re;
        }
    }

    /**
     * @param obj null 或者 ""
     */
    public static void notEmpty(Object obj, SspOpenSdkException re) {
        if (StringUtils.isEmpty(obj.toString())) {
            throw re;
        }
    }

    /**
     * @param obj null 或者 "" 或者 " "
     */
    public static void notBlank(Object obj, SspOpenSdkException re) {
        if (obj == null || !StringUtils.isBlank(obj.toString())) {
            throw re;
        }
    }

    public static void notNull(Object obj, SspOpenSdkException re) {
        if (obj == null) {
            throw re;
        }
    }

    public static void isNull(Object obj, SspOpenSdkException re) {
        if (obj != null) {
            throw re;
        }
    }

}
