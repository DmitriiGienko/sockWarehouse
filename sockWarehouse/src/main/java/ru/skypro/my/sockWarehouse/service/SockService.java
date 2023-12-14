package ru.skypro.my.sockWarehouse.service;

import ru.skypro.my.sockWarehouse.dto.SockDTO;
import ru.skypro.my.sockWarehouse.model.Operations;

public interface SockService {

    void addSocks(SockDTO sockDTO);
    void removeSocks(SockDTO sockDTO);
    Integer getNumberSocksRequested(String color, Operations operation, Integer cottonPart);

}
