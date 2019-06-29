package com.pinyougou.util;

import entity.ResultInfo;

import java.io.Serializable;

public class ResultUtils{


    /**
     * 判断整数是否大于零
     *
     * @param number
     * @return
     */
    public static boolean isIntThanZero(int number) {
        if (number > 0) {
            return true;
        }
        return false;
    }

    /**
     * 新增 修改提示
     * @param count
     * @return
     */
    public static ResultInfo msg(int count) {
        if (isIntThanZero(count)) {
            return new ResultInfo(true, "操作成功");
        }
        return new ResultInfo(false,"操作失败");
    }

    /**
     * 删除提示
     * @param total
     * @param count
     * @return
     */
    public static ResultInfo msg(Object[] total, int count) {
        if(total.length == count){
            return new ResultInfo(true, "");
        }else{
            if(isIntThanZero(count)){
                return new ResultInfo(false, "本次共处理：" + String.valueOf(total.length) + "条，成功：" + String.valueOf(count) + "条！");
            }else{
                return new ResultInfo(false, "删除操作失败");
            }
        }
    }
}
