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
@IdClass(SickId.class)

public class Sock {
    //    private Long id;
    @Id
    private String color;
    @Id
    @Column(name = "cotton_part", nullable = false)
    private Integer cottonPart;

    @Column(name = "quantity", nullable = false)
    private Long quantity;
}
