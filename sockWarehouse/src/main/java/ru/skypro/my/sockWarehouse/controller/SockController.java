package ru.skypro.my.sockWarehouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skypro.my.sockWarehouse.dto.SockDTO;
import ru.skypro.my.sockWarehouse.model.Operations;
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

    @PostMapping("/outcome")
    public void removeSocks(@RequestBody SockDTO sockDTO) {
        sockService.removeSocks(sockDTO);
    }

    @GetMapping()
    public Integer getCountSocks(@RequestParam String color,
                                 @RequestParam Operations operations,
                                 @RequestParam Integer cottonPart) {
        return sockService.getNumberSocksRequested(color, operations, cottonPart);
    }


}
