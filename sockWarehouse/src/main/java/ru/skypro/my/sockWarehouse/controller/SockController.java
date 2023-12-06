package ru.skypro.my.sockWarehouse.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.my.sockWarehouse.dto.SockDTO;
import ru.skypro.my.sockWarehouse.model.Sock;
import ru.skypro.my.sockWarehouse.service.SockServiceImpl;

@RestController
@RequestMapping("api/socks/")
public class SockController {
    @Autowired
    SockServiceImpl sockService;

    @PostMapping("/income")
    public void addSocks(@RequestBody SockDTO sockDTO) {
        sockService.addSocks(sockDTO);
    }


}
