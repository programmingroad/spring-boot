package com.example.resttemplate.service.harbor;

/**
 * @author: liubq
 * @create: 2020/09/07 18:05
 * @description:
 **/
public interface HarborConstants {

    /**
     * 推送镜像任务成功标识
     */
    String POLICY_TASK_SUCCESS = "Succeed";

    /**
     * 推送镜像任务进行中标识
     */
    String POLICY_TASK_INPROGRESS = "InProgress";

    /**
     * API 前缀
     */
    String API_PREFIX = "/api/v2.0";

    /**
     * projects url
     */
    String PROJECTS = API_PREFIX + "/projects";

    /**
     * repositories url
     */
    String REPOSITORIES = PROJECTS + "/{projectName}/repositories";

    /**
     * artifacts url
     */
    String ARTIFACTS = REPOSITORIES + "/{repositoryName}/artifacts";

    /**
     * replication url
     */
    String REPLICATION = API_PREFIX + "/replication";

    /**
     * policies url
     */
    String POLICIES = REPLICATION + "/policies";

    /**
     * executions url
     */
    String EXECUTIONS = REPLICATION + "/executions";

    /**
     * registries url
     */
    String REGISTRIES = API_PREFIX + "/registries";
}
