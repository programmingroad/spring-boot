package com.example.resttemplate.service;

import com.alibaba.fastjson.JSON;
import com.example.resttemplate.domain.*;
import com.example.resttemplate.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

/**
 * @author: liubq
 * @create: 2020/09/07 11:26
 * @description: harbor version: 2.0  泛型使用 JSON.parseObject(responseEntity.getBody(), new TypeReference<NodeRedResult<List<KemInfo>>>() {
 * });
 **/
@Slf4j
@Service
public class HarborApiService {

    @Value("${harbor.username}")
    private String username;

    @Value("${harbor.password}")
    private String password;

    @Value("${harbor.url}")
    private String harborUrl;

    private final RestTemplate restTemplate;

    private final String X_TOTAL_COUNT = "X-Total-Count";

    public HarborApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 获取请求地址 是否分页
     *
     * @param url
     * @param isPage
     * @return
     */
    private String getRequestHarborUrl(String url, Boolean isPage) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.harborUrl);
        sb.append(url);
        if (isPage) {
            sb.append("?page={pageNum}&page_size={pageSize}");
        }
        return sb.toString();
    }

    /**
     * get projects
     *
     * @return
     */
    public OuterResp<Project> getProjects(Integer pageNum, Integer pageSize) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.PROJECTS, true);
        log.info("restTemplate call harbor's projects api: Request url={}, method={}, pageNum={}, pageSize={}", requestHarborUrl, HttpMethod.GET, pageNum, pageSize);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null), String.class,
                ResultUtil.getPageNum(pageNum), ResultUtil.getPageSize(pageSize));
        log.info("restTemplate call harbor's projects api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
        List<Project> projects = JSON.parseArray(responseEntity.getBody(), Project.class);
        List<String> xTotalCount = responseEntity.getHeaders().get(this.X_TOTAL_COUNT);
        return OuterResp.<Project>builder().list(projects).totalCount(this.getTotalCount(xTotalCount)).build();
    }

    /**
     * get repositories by projectName
     *
     * @param projectName
     * @return
     */
    public OuterResp<Repository> getRepositories(String projectName, Integer pageNum, Integer pageSize) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.REPOSITORIES, true);
        log.info("restTemplate call harbor's repositories api: Request url={}, method={}, projectName={}, pageNum={}, pageSize={}", requestHarborUrl, HttpMethod.GET,
                projectName, pageNum, pageSize);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null), String.class, projectName,
                ResultUtil.getPageNum(pageNum), ResultUtil.getPageSize(pageSize));
        log.info("restTemplate call harbor's repositories api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
        List<Repository> repositoryResps = JSON.parseArray(responseEntity.getBody(), Repository.class);
        List<String> xTotalCount = responseEntity.getHeaders().get(this.X_TOTAL_COUNT);
        return OuterResp.<Repository>builder().list(repositoryResps).totalCount(this.getTotalCount(xTotalCount)).build();
    }

    /**
     * get artifacts by projectName and repositoryName
     *
     * @param projectName
     * @param repositoryName
     * @return
     */
    public OuterResp<Artifact> getArtifacts(String projectName, String repositoryName, Integer pageNum, Integer pageSize) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.ARTIFACTS, true);
        log.info("restTemplate call harbor's artifacts api: Request url={}, method={}, projectName={}, repositoryName={}, pageNum={}, pageSize={}", requestHarborUrl,
                HttpMethod.GET, projectName, repositoryName,
                pageNum, pageSize);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null), String.class, projectName, repositoryName,
                ResultUtil.getPageNum(pageNum), ResultUtil.getPageSize(pageSize));
        log.info("restTemplate call harbor's artifacts api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
        List<Artifact> artifactResps = JSON.parseArray(responseEntity.getBody(), Artifact.class);
        List<String> xTotalCount = responseEntity.getHeaders().get(this.X_TOTAL_COUNT);
        return OuterResp.<Artifact>builder().list(artifactResps).totalCount(this.getTotalCount(xTotalCount)).build();
    }

    /**
     * 创建registry
     *
     * @param registry
     */
    public void createRegistry(Registry registry) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.REGISTRIES, false);
        log.info("restTemplate call harbor's registries api: Request url={}, method={}, registry={}", requestHarborUrl, HttpMethod.POST, registry);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.POST, this.getHttpEntity(HttpMethod.POST, registry), Void.class);
        log.info("restTemplate call harbor's registries api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
    }

    /**
     * 获取registry
     *
     * @param registryName
     * @return
     */
    public List<Registry> getRegistries(String registryName) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.REGISTRIES, false) + "?name={registryName}";
        log.info("restTemplate call harbor's registries api: Request url={}, method={}, registryName={}", requestHarborUrl, HttpMethod.GET, registryName);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null),
                String.class, registryName);
        log.info("restTemplate call harbor's registries api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
        List<Registry> registries = JSON.parseArray(responseEntity.getBody(), Registry.class);
        return registries;
    }

    /**
     * 删除registry
     *
     * @param registryId
     */
    public void deleteRegistry(Integer registryId) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.REGISTRIES, false) + "/{registryId}";
        log.info("restTemplate call harbor's registries api: Request url={}, method={}, registryId={}", requestHarborUrl, HttpMethod.DELETE, registryId);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.DELETE, this.getHttpEntity(HttpMethod.DELETE, null),
                Void.class, registryId);
        log.info("restTemplate call harbor's registries api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
    }

    /**
     * 创建同步策略
     *
     * @param replicationPolicy
     */
    public void createReplicationPolicy(ReplicationPolicy replicationPolicy) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.POLICIES, false);
        log.info("restTemplate call harbor's policies api: Request url={}, method={}, replicationPolicy={}", requestHarborUrl, HttpMethod.POST, replicationPolicy);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.POST, this.getHttpEntity(HttpMethod.POST, replicationPolicy), Void.class);
        log.info("restTemplate call harbor's policies api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
    }

    /**
     * 通过名称获取同步策略
     *
     * @param policyName
     * @return
     */
    public List<ReplicationPolicy> getReplicationPolicies(String policyName) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.POLICIES, false) + "?name={policyName}";
        log.info("restTemplate call harbor's policies api: Request url={}, method={}, policyName={}", requestHarborUrl, HttpMethod.GET, policyName);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null),
                String.class, policyName);
        log.info("restTemplate call harbor's policies api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
        List<ReplicationPolicy> replicationPolicies = JSON.parseArray(responseEntity.getBody(), ReplicationPolicy.class);
        return replicationPolicies;
    }

    /**
     * 删除 policy
     *
     * @param policyId
     */
    public void deleteReplicationPolicy(Integer policyId) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.POLICIES, false) + "/{policyId}";
        log.info("restTemplate call harbor's policies api: Request url={}, method={}, policyId={}", requestHarborUrl, HttpMethod.DELETE, policyId);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.DELETE, this.getHttpEntity(HttpMethod.DELETE, null),
                Void.class, policyId);
        log.info("restTemplate call harbor's policies api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
    }

    /**
     * 启动一个同步策略的任务 并执行
     *
     * @param replicationExecution
     */
    public void startReplicationExecution(ReplicationExecution replicationExecution) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.EXECUTIONS, false);
        log.info("restTemplate call harbor's executions api: Request url={}, replicationExecution={}", requestHarborUrl, replicationExecution);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.POST, this.getHttpEntity(HttpMethod.POST, replicationExecution), Void.class);
        log.info("restTemplate call harbor's executions api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
    }

    /**
     * 获取同步策略的任务
     *
     * @param policyId
     * @return
     */
    public List<ReplicationExecution> getReplicationExecution(Integer policyId) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.EXECUTIONS, false) + "?policy_id={policyId}";
        log.info("restTemplate call harbor's executions api: Request url={}, method={}, policyId={}", requestHarborUrl, HttpMethod.GET, policyId);
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null),
                String.class, policyId);
        log.info("restTemplate call harbor's executions api: Response HttpStatus={}, body={}", responseEntity.getStatusCode(), responseEntity.getBody());
        List<ReplicationExecution> replicationExecutions = JSON.parseArray(responseEntity.getBody(), ReplicationExecution.class);
        return replicationExecutions;
    }


    private Long getTotalCount(List<String> xTotalCount) {
        return (xTotalCount != null && xTotalCount.size() >= 1) ? Long.valueOf(xTotalCount.get(0)) : 0;
    }

    private HttpEntity getHttpEntity(HttpMethod httpMethod, Object paramBody) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String input = this.username + ":" + this.password;
        String encoding = Base64.getEncoder().encodeToString(input.getBytes());
        httpHeaders.set("Authorization", "Basic " + encoding);
        switch (httpMethod) {
            case POST:
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return new HttpEntity(paramBody, httpHeaders);
        }
        return new HttpEntity(httpHeaders);
    }
}
