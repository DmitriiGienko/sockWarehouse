package ru.skypro.my.sockWarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.my.sockWarehouse.dto.SockDTO;
import ru.skypro.my.sockWarehouse.model.Operations;
import ru.skypro.my.sockWarehouse.model.Sock;
import ru.skypro.my.sockWarehouse.model.SockId;
import ru.skypro.my.sockWarehouse.repository.SocksRepository;

import java.util.Objects;

import static ru.skypro.my.sockWarehouse.dto.SockDTO.getSock;

@Service
public class SockServiceImpl implements SockService {
    @Autowired
    SocksRepository socksRepository;

    @Override
    public void addSocks(SockDTO sockDTO) {
        Sock sock = socksRepository
                .findById(new SockId(sockDTO.getColor(), sockDTO.getCottonPart()))
                .orElse(null);
        if (sock != null) {
            sock.setQuantity(sock.getQuantity() + sockDTO.getQuantity());
            socksRepository.save(sock);
        } else socksRepository.save(getSock(sockDTO));

    }

    @Override
    public void removeSocks(SockDTO sockDTO) {

    }

    @Override
    public int getNumberSocksRequested(String color, Operations operation, Integer cottonPart) {
        return 0;
    }
}
