package ru.skypro.my.sockWarehouse.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.my.sockWarehouse.model.Operations;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.skypro.my.sockWarehouse.controller.TestPrepare.getJsonObject;
import static ru.skypro.my.sockWarehouse.controller.TestPrepare.getJsonObjectOperation;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Import(TestPrepare.class)
class SockControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TestPrepare testPrepare;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Добавление на склад - Ok")
    @Test
    void shouldAddSocks_Ok() throws Exception {

        mockMvc.perform(post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("color1", 50, 100L).toString()))
                .andExpect(status().isOk());
    }

    @DisplayName("Добавление на склад - неправильные данные cottonPart")
    @Test
    void shouldNotAddSocks_cottonPartErrInputs() throws Exception {

        mockMvc.perform(post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("color1", -50, 100L).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Введены неверные данные"));
    }

    @DisplayName("Добавление на склад - неправильные данные color")
    @Test
    void shouldNotAddSocks_colorPartErrInputs() throws Exception {

        mockMvc.perform(post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("", 50, 100L).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Введены неверные данные"));
    }

    @DisplayName("Добавление на склад - неправильные данные quantity")
    @Test
    void shouldNotAddSocks_quantityPartErrInputs() throws Exception {

        mockMvc.perform(post("/api/socks/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("color1", 50, 0L).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Введены неверные данные"));
    }


    @DisplayName("Выдача со склада - Ок")
    @Test
    void shouldRemoveSocks_Ok() throws Exception {
        testPrepare.getDB();

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("red", 70, 50L).toString()))
                .andExpect(status().isOk());
    }

    @DisplayName("Выдача со склада - нет на складе")
    @Test
    void shouldNotRemoveSocks_ColorOrCottonPartNotFound() throws Exception {
        testPrepare.getDB();

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("blue", 70, 50L).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Насков с такими данными нет на складе"));
    }

    @DisplayName("Выдача со склада - недостаточно на складе")
    @Test
    void shouldNotRemoveSocks_QuantityError() throws Exception {
        testPrepare.getDB();

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("red", 70, 120L).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Недостаточно носков на складе"));
    }

    @DisplayName("Выдача со склада - неправильные данные cottonPart")
    @Test
    void shouldNotRemoveSocks_cottonPartErrInputs() throws Exception {

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("color1", -50, 100L).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Введены неверные данные"));
    }

    @DisplayName("Выдача со склада - неправильные данные color")
    @Test
    void shouldNotRemoveSocks_colorPartErrInputs() throws Exception {

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("", 50, 100L).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Введены неверные данные"));
    }

    @DisplayName("Выдача со склада - неправильные данные quantity")
    @Test
    void shouldNotRemoveSocks_quantityPartErrInputs() throws Exception {

        mockMvc.perform(post("/api/socks/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObject("color1", 50, 0L).toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Введены неверные данные"));
    }


    @Test
    void getCountSocks() throws Exception {

        testPrepare.getDB();

        mockMvc.perform(get("/api/socks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonObjectOperation("red", Operations.EQUAL, 70).toString()))
                .andExpect(status().isOk());
    }


}
