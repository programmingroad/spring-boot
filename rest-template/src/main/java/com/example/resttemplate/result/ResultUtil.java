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
                .currPageNum(pageNum)
                .pageSize(pageSize)
                .totalCount(totalCount)
                .totalPageNum((int) Math.ceil(totalCount.doubleValue() / pageSize)).build();
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
}
