package ru.skypro.my.sockWarehouse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@IdClass(SockId.class)
@Table(name = "sock")

public class Sock {
    @Id
    private String color;

    @Id
    @Column(name = "cotton_part")
    private Integer cottonPart;

    @Column(name = "quantity")
    private Long quantity;
}
