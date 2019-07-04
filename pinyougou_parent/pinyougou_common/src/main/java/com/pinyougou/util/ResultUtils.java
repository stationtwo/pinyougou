package com.pinyougou.util;

import entity.Result;

import java.util.List;

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
    public static Result msg(int count) {
        if (isIntThanZero(count)) {
            return new Result(true, "操作成功");
        }
        return new Result(false,"操作失败");
    }

    public static Result msg(List<?> total, int count) {
        if(total.size() == count){
            return new Result(true, count+"条数据操作成功");
        }else{
            if(isIntThanZero(count)){
                return new Result(false, "本次共处理：" + String.valueOf(total.size()) + "条，成功：" + String.valueOf(count) + "条！");
            }else{
                return new Result(false, "操作失败");
            }
        }
    }

    /**
     * 删除提示
     * @param total
     * @param count
     * @return
     */
    public static Result msg(Object[] total, int count) {
        if(total.length == count){
            return new Result(true, count+"条数据操作成功");
        }else{
            if(isIntThanZero(count)){
                return new Result(false, "本次共处理：" + String.valueOf(total.length) + "条，成功：" + String.valueOf(count) + "条！");
            }else{
                return new Result(false, "操作失败");
            }
        }
    }
}
