package ru.skypro.my.sockWarehouse.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import ru.skypro.my.sockWarehouse.model.Sock;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class SockDTO {

//    @NotBlank(message = "Обязательное поле")
    private String color;

//    @NotBlank(message = "Обязательное поле")
//    @PositiveOrZero(message = "Значение не может быть отрицательным")
    private Integer cottonPart;

//    @NotBlank(message = "Обязательное поле")
//    @Positive(message = "Значение должно быть положительным")
    private Long quantity;

    public static Sock getSock(SockDTO sockDTO) {
        Sock sock = new Sock();
        sock.setColor(sockDTO.getColor().toLowerCase().trim());
        sock.setCottonPart(sockDTO.getCottonPart());
        sock.setQuantity(sockDTO.getQuantity());
        return sock;
    }

    public static SockDTO getSockDTO(Sock sock) {
        SockDTO sockDTO = new SockDTO();
        sockDTO.setColor(sock.getColor());
        sockDTO.setCottonPart(sock.getCottonPart());
        sockDTO.setQuantity(sock.getQuantity());
        return sockDTO;
    }
}

