package com.hxyw.shareadv.utils;

import com.hxyw.shareadv.base.BaseApiCode;
import com.hxyw.shareadv.base.BaseApiResult;

/**
 * info:
 * Created by shang on 16/7/16.
 */
public class ModelHelper {


    /**
     * set
     */
    public static BaseApiResult addModelResult(BaseApiResult result, BaseApiCode baseApiCode) {
        result.setResult(baseApiCode);
        return result;
    }


    /**
     * OK
     */
    public static BaseApiResult OK(BaseApiResult result) {
        result.setResult(BaseApiCode.STATUS_200);
        return result;
    }
    /**
     * ERROR
     */
    public static BaseApiResult ERROR(BaseApiResult result) {
        result.setResult(BaseApiCode.STATUS_500);
        return result;
    }
}
