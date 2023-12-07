package ru.skypro.my.sockWarehouse.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@IdClass(SockId.class)

public class Sock {
    @Id
    private String color;
    @Id
    @Column(name = "cotton_part")
    private Integer cottonPart;

    @Column(name = "quantity")
    private Long quantity;
}
