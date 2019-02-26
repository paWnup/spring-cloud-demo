package com.ftx.solution.common.base.util;

import com.ftx.solution.common.base.constant.Globals;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author puan
 * @date 2018-11-15 11:31
 **/
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultMap {

    private ResultMap(int status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    /**
     * 标记请求是否处理成功，0-失败；1-成功
     */
    private int status;

    /**
     * 返回的数据，一般请求处理成功才会有数据
     */
    private Object data;

    /**
     * 处理结果描述
     */
    private String remark;

    public static ResultMap getFailedResultMap() {
        return new ResultMap(Globals.NO, "操作失败");
    }

    public static ResultMap getFailedResultMap(String remark) {
        return new ResultMap(Globals.NO, remark);
    }

    public static ResultMap getSuccessResultMap() {
        return new ResultMap(Globals.YES, "操作成功");
    }


    public static ResultMap getSuccessResultMap(Object data) {
        return new ResultMap(Globals.YES, data, "操作成功");
    }

    public static ResultMap getSuccessResultMap(Object data, String remark) {
        return new ResultMap(Globals.YES, data, remark);
    }
}
