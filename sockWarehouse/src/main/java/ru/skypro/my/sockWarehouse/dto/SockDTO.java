package ru.skypro.my.sockWarehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.my.sockWarehouse.model.Sock;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class SockDTO {
    private String color;
    private int cottonPart;
    private Long quantity;

    public static Sock getSock(SockDTO sockDTO) {
        Sock sock = new Sock();
        sock.setColor(sockDTO.getColor());
        sock.setCottonPart(sockDTO.getCottonPart());
        sock.setQuantity(sockDTO.getQuantity());
        return sock;
    }

    public static SockDTO getSock(Sock sock) {
        SockDTO sockDTO = new SockDTO();
        sockDTO.setColor(sock.getColor());
        sockDTO.setCottonPart(sock.getCottonPart());
        sockDTO.setQuantity(sock.getQuantity());
        return sockDTO;
    }
}

