package ru.skypro.my.sockWarehouse.service;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SockServiceImpl implements SockService {
    @Autowired
    SocksRepository socksRepository;

    /**
     * Добавление носков на склад
     */
    @Override
    public void addSocks(SockDTO sockDTO) {
        if (validateInputs(sockDTO)) {
            log.warn("Неверный ввод {} пар носков цвета {} состав хлопка {}%",
                    sockDTO.getQuantity(), sockDTO.getColor(), sockDTO.getCottonPart());
            throw new SockInputsException();
        }
        Sock sock = socksRepository
                .findById(getSockId(sockDTO))
                .orElse(null);
        log.info("Добавление на склад {} пар носков цвета {} состав хлопка {}%",
                sockDTO.getQuantity(), sockDTO.getColor(), sockDTO.getCottonPart());

        if (sock != null) {
            sock.setQuantity(sock.getQuantity() + sockDTO.getQuantity());
            socksRepository.save(sock);
        } else socksRepository.save(getSock(sockDTO));
    }

    /**
     * Выдача носков со склада
     */
    @Override
    public void removeSocks(SockDTO sockDTO) {

        if (validateInputs(sockDTO)) {
            log.warn("Неверный ввод {} пар носков цвета {} состав хлопка {}%",
                    sockDTO.getQuantity(), sockDTO.getColor(), sockDTO.getCottonPart());
            throw new SockInputsException();
        }

        Sock sock = socksRepository
                .findById(getSockId(sockDTO))
                .orElseThrow(SockPresentsException::new);
        if (sockDTO.getQuantity() > sock.getQuantity()) {
            throw new SockQuantityException();
        }

        log.info("Выдано со склада {} пар носков цвета {} состав хлопка {}%",
                sockDTO.getQuantity(), sockDTO.getColor(), sockDTO.getCottonPart());

        sock.setQuantity(sock.getQuantity() - sockDTO.getQuantity());
        socksRepository.save(sock);
    }

    /**
     * Получение кол-ва пар носков на складе по цвету и составу
     */
    @Override
    public Integer getNumberSocksRequested(String color, Operations operation, Integer cottonPart) {

        Integer result = 0;
        switch (operation) {
            case EQUAL -> {
                log.info("Запрос - сколько пар носков цвета {} с составом хлопка {}%",
                        color, cottonPart);
                result = socksRepository.sumOfSocksEqual(color.toLowerCase().trim(), cottonPart)
                        .orElse(0);
            }
            case LESS_THAN -> {
                log.info("Запрос - сколько пар носков цвета {} с составом хлопка меньше {}%",
                        color, cottonPart);
                result = socksRepository.sumOfSocksLessThan(color.toLowerCase().trim(), cottonPart)
                        .orElse(0);
            }
            case MORE_THAN -> {
                log.info("Запрос - сколько пар носков цвета {} с составом хлопка больше {}%",
                        color, cottonPart);
                result = socksRepository.sumOfSocksMoreThan(color.toLowerCase().trim(), cottonPart)
                        .orElse(0);
            }
        }
        log.info("На запрос возвращено - {}", result);
        return result;
    }

    /**
     * Получение Id
     */
    public SockId getSockId(SockDTO sockDTO) {
        return new SockId(sockDTO.getColor().toLowerCase().trim(),
                sockDTO.getCottonPart());
    }

    /**
     * Валидация введенных данных пользователем
     */
    public boolean validateInputs(SockDTO sockDTO) {
        return sockDTO.getQuantity() <= 0 || (sockDTO.getCottonPart() < 0
                || sockDTO.getCottonPart() > 100)
                || sockDTO.getColor().isEmpty();
    }
}
