package com.example.resttemplate.service;

import com.example.resttemplate.domain.OuterResp;
import com.example.resttemplate.domain.Project;
import com.example.resttemplate.domain.ProjectReq;
import com.example.resttemplate.dto.ProjectDTO;
import com.example.resttemplate.result.Result;
import com.example.resttemplate.result.ResultUtil;
import com.example.resttemplate.service.harbor.HarborApiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: liubq
 * @create: 2020/11/17 17:38
 * @description:
 **/

@Service
public class RestTemplateApiService {

    private final HarborApiService harborApiService;

    public RestTemplateApiService(HarborApiService harborApiService) {
        this.harborApiService = harborApiService;
    }

    public Result<List<ProjectDTO>> getProjects(Integer pageNum, Integer pageSize) {
        OuterResp<Project> projectOuterResp = harborApiService.getProjects(pageNum, pageSize);
        List<ProjectDTO> projectDTOS = projectOuterResp.getList().stream().map(project -> {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setName(project.getName());
            return projectDTO;
        }).collect(Collectors.toList());
        return ResultUtil.success(projectDTOS, pageNum, pageSize, projectOuterResp.getTotalCount());
    }

    public Result create(ProjectReq projectReq) {
        harborApiService.createProject(projectReq);
        return ResultUtil.success(null);
    }
}
