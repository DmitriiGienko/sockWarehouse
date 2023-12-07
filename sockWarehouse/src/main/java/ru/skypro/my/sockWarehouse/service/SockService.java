package ru.skypro.my.sockWarehouse.service;

import ru.skypro.my.sockWarehouse.dto.SockDTO;
import ru.skypro.my.sockWarehouse.model.Operations;
import ru.skypro.my.sockWarehouse.model.Sock;

public interface SockService {

    void addSocks(SockDTO sockDTO);
    void removeSocks(SockDTO sockDTO);
    Integer getNumberSocksRequested(String color, Operations operation, Integer cottonPart);

}
