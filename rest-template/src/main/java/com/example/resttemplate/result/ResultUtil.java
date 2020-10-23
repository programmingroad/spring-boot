package com.example.resttemplate.result;

/**
 * @author: liubq
 * @create: 2020/09/08 10:34
 * @description:
 **/
public class ResultUtil {

    public static Result success(Object data, Integer pageNum, Integer pageSize, Long totalCount) {
        Result result = success(data);
        PageInfo pageInfo = PageInfo.builder()
                .currPageNum(getPageNum(pageNum))
                .pageSize(getPageSize(pageSize))
                .totalCount(totalCount)
                .totalPageNum((int) Math.ceil(totalCount.doubleValue() / getPageSize(pageSize))).build();
        result.setPageInfo(pageInfo);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setStatus(Constants.EXECUTE_SUCCESS);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static Result failed() {
        return failed("failed");
    }

    public static Result failed(String message) {
        Result result = new Result();
        result.setStatus(Constants.EXECUTE_ERROR);
        result.setMessage(message);
        return result;
    }

    /**
     * 获取分页的start
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public static Integer getStart(Integer pageNum, Integer pageSize) {
        return (getPageNum(pageNum) - 1) * getPageSize(pageSize);
    }

    /**
     * 获取pageNum pageNum==null 返回默认值
     *
     * @param pageNum
     * @return
     */
    public static Integer getPageNum(Integer pageNum) {
        return pageNum == null || pageNum <= 0 ? Constants.DEFAULT_PAGE_NUM : pageNum;
    }

    /**
     * 获取pageSize pageSize==null 返回默认值
     *
     * @param pageSize
     * @return
     */
    public static Integer getPageSize(Integer pageSize) {
        return pageSize == null || pageSize <= 0 ? Constants.DEFAULT_PAGE_SIZE : pageSize;
    }
}
