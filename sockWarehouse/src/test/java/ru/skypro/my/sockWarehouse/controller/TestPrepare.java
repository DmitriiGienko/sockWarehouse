package ru.skypro.my.sockWarehouse.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.my.sockWarehouse.model.Sock;
import ru.skypro.my.sockWarehouse.repository.SocksRepository;

public class TestPrepare {

    @Autowired
    SocksRepository socksRepository;


    public void getDB() {

        Sock sock1 = new Sock();
        sock1.setColor("red");
        sock1.setCottonPart(70);
        sock1.setQuantity(100L);

        Sock sock2 = new Sock();
        sock2.setColor("red");
        sock2.setCottonPart(40);
        sock2.setQuantity(50L);

        socksRepository.save(sock1);
        socksRepository.save(sock2);
    }


    public static JSONObject getJsonObject(String color, Integer cottonPart, Long quantity) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("color", color);
        jsonObject.put("cottonPart", cottonPart);
        jsonObject.put("quantity", quantity);

        return jsonObject;
    }

}

