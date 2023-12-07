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

    private Integer cottonPart;

    private Long quantity;

    public static Sock getSock(SockDTO sockDTO) {
        Sock sock = new Sock();
        sock.setColor(sockDTO.getColor().toLowerCase().trim());
        sock.setCottonPart(sockDTO.getCottonPart());
        sock.setQuantity(sockDTO.getQuantity());
        return sock;
    }

}

