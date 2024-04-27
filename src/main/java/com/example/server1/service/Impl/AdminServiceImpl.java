package com.example.server1.service.Impl;

import com.example.server1.DTO.AdminDTO;

import com.example.server1.mapper.AdminMapper;
import com.example.server1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminDTO login(String name) {
        return adminMapper.login(name);
    }
}
