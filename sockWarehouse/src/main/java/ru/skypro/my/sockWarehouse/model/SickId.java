package ru.skypro.my.sockWarehouse.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SickId implements Serializable {
    private String color;
    private int cottonPart;
}
