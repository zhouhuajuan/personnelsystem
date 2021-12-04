package com.personnelsystem.event.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.personnelsystem.event.entity.Education;
import com.personnelsystem.event.mapper.EducationMapper;
import com.personnelsystem.event.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: personnelsystem1
 * @description:
 * @author: 周华娟
 * @create: 2021-12-02 15:13
 **/

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationMapper educationMapper;

    @Override
    public List<Education> getAllEdu() {
        QueryWrapper<Education> wrapper = new QueryWrapper<>();
        return educationMapper.selectList(wrapper);
    }
}
