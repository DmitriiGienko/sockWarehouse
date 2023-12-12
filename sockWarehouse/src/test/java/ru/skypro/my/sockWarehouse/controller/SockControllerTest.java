package ru.skypro.my.sockWarehouse.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.skypro.my.sockWarehouse.controller.TestPrepare.getJsonObject;

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

    @DisplayName("Кол-во носков, оператор EQUAL")
    @Test
    void shouldGetCountSocksWithEqual_Ok() throws Exception {

        testPrepare.getDB();

        mockMvc.perform(get("/api/socks/")
                        .param("color", "red")
                        .param("operations", String.valueOf(Operations.EQUAL))
                        .param("cottonPart", String.valueOf(70)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value("100"));
    }

    @DisplayName("Кол-во носков, оператор MORE_THAN")
    @Test
    void shouldGetCountSocksWithMoreThan_Ok() throws Exception {

        testPrepare.getDB();

        mockMvc.perform(get("/api/socks/")
                        .param("color", "red")
                        .param("operations", String.valueOf(Operations.MORE_THAN))
                        .param("cottonPart", String.valueOf(35)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value("150"));
    }

    @DisplayName("Кол-во носков, оператор LESS_THAN")
    @Test
    void shouldGetCountSocksWithLessThan_Ok() throws Exception {

        testPrepare.getDB();

        mockMvc.perform(get("/api/socks/")
                        .param("color", "red")
                        .param("operations", String.valueOf(Operations.LESS_THAN))
                        .param("cottonPart", String.valueOf(50)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value("50"));
    }

    @DisplayName("Кол-во носков, при отсутствии на складе заданного цвета")
    @Test
    void shouldGetCountSocksOtherColor_Ok() throws Exception {

        testPrepare.getDB();

        mockMvc.perform(get("/api/socks/")
                        .param("color", "blue")
                        .param("operations", String.valueOf(Operations.LESS_THAN))
                        .param("cottonPart", String.valueOf(50)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value("0"));
    }

    @DisplayName("Кол-во носков, при отсутствии на складе заданного CottonPart")
    @Test
    void shouldGetCountSocksCottonPartAbsent_Ok() throws Exception {

        testPrepare.getDB();

        mockMvc.perform(get("/api/socks/")
                        .param("color", "red")
                        .param("operations", String.valueOf(Operations.LESS_THAN))
                        .param("cottonPart", String.valueOf(40)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value("0"));
    }


}
