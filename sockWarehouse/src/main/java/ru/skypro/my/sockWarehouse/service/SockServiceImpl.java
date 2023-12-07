package ru.skypro.my.sockWarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skypro.my.sockWarehouse.dto.SockDTO;
import ru.skypro.my.sockWarehouse.exceptions.SockInputsException;
import ru.skypro.my.sockWarehouse.exceptions.SockPresentsException;
import ru.skypro.my.sockWarehouse.exceptions.SockQuantityException;
import ru.skypro.my.sockWarehouse.model.Operations;
import ru.skypro.my.sockWarehouse.model.Sock;
import ru.skypro.my.sockWarehouse.model.SockId;
import ru.skypro.my.sockWarehouse.repository.SocksRepository;

import static ru.skypro.my.sockWarehouse.dto.SockDTO.getSock;

@Service
public class SockServiceImpl implements SockService {
    @Autowired
    SocksRepository socksRepository;

    @Override
    public void addSocks(SockDTO sockDTO) {
        if (!validateInputs(sockDTO)) {
            throw new SockInputsException();
        }
        Sock sock = socksRepository
                .findById(getSockId(sockDTO))
                .orElse(null);
        if (sock != null) {
            sock.setQuantity(sock.getQuantity() + sockDTO.getQuantity());
            socksRepository.save(sock);
        } else socksRepository.save(getSock(sockDTO));
    }

    @Override
    public void removeSocks(SockDTO sockDTO) {

        Sock sock = socksRepository
                .findById(getSockId(sockDTO))
                .orElseThrow(SockPresentsException::new);
        if (sockDTO.getQuantity() > sock.getQuantity()) {
            throw new SockQuantityException();
        }
        sock.setQuantity(sock.getQuantity() - sockDTO.getQuantity());
        socksRepository.save(sock);
    }

    @Override
    public Integer getNumberSocksRequested(String color, Operations operation, Integer cottonPart) {

        Integer result;
        switch (operation) {
            case EQUAL -> result = socksRepository.sumOfSocksEqual(color.toLowerCase().trim(), cottonPart);
            case LESS_THAN -> result = socksRepository.sumOfSocksLessThan(color.toLowerCase().trim(), cottonPart);
            case MORE_THAN -> result = socksRepository.sumOfSocksMoreThan(color.toLowerCase().trim(), cottonPart);
            default -> result = 0;
        }
        return result;
    }

    public SockId getSockId(SockDTO sockDTO) {
        return new SockId(sockDTO.getColor().toLowerCase().trim(),
                sockDTO.getCottonPart());
    }

    public boolean validateInputs(SockDTO sockDTO) {
        return sockDTO.getQuantity() > 0 && (sockDTO.getCottonPart() >= 0
                && sockDTO.getCottonPart() <= 100)
                && !sockDTO.getColor().isEmpty();
    }
}
