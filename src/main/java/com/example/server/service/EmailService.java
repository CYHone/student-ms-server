package com.example.server.service;

import com.example.server.DTO.EmailDTO;

public interface EmailService {
    public void sendMsg(EmailDTO emailDTO);
}
