package com.example.resttemplate.service.harbor;

import com.alibaba.fastjson.JSON;
import com.example.resttemplate.domain.*;
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
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null),
                String.class, pageNum, pageSize);
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
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null),
                String.class, projectName, pageNum, pageSize);
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
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null),
                String.class, projectName, repositoryName, pageNum, pageSize);
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
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.POST, this.getHttpEntity(HttpMethod.POST, registry), Void.class);
    }

    public void createProject(ProjectReq projectReq) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.PROJECTS, false);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.POST, this.getHttpEntity(HttpMethod.POST, projectReq), Void.class);
    }

    /**
     * 获取registry
     *
     * @param registryName
     * @return
     */
    public List<Registry> getRegistries(String registryName) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.REGISTRIES, false) + "?name={registryName}";
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null),
                String.class, registryName);
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
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.DELETE, this.getHttpEntity(HttpMethod.DELETE, null),
                Void.class, registryId);
    }

    /**
     * 创建同步策略
     *
     * @param replicationPolicy
     */
    public void createReplicationPolicy(ReplicationPolicy replicationPolicy) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.POLICIES, false);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.POST, this.getHttpEntity(HttpMethod.POST, replicationPolicy), Void.class);
    }

    /**
     * 通过名称获取同步策略
     *
     * @param policyName
     * @return
     */
    public List<ReplicationPolicy> getReplicationPolicies(String policyName) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.POLICIES, false) + "?name={policyName}";
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null),
                String.class, policyName);
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
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.DELETE, this.getHttpEntity(HttpMethod.DELETE, null),
                Void.class, policyId);
    }

    /**
     * 启动一个同步策略的任务 并执行
     *
     * @param replicationExecution
     */
    public void startReplicationExecution(ReplicationExecution replicationExecution) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.EXECUTIONS, false);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.POST, this.getHttpEntity(HttpMethod.POST, replicationExecution), Void.class);
    }

    /**
     * 获取同步策略的任务
     *
     * @param policyId
     * @return
     */
    public List<ReplicationExecution> getReplicationExecution(Integer policyId) {
        String requestHarborUrl = this.getRequestHarborUrl(HarborConstants.EXECUTIONS, false) + "?policy_id={policyId}";
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestHarborUrl, HttpMethod.GET, this.getHttpEntity(HttpMethod.GET, null),
                String.class, policyId);
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
