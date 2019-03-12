package ru.alfastrah.alfadigital.extrinsic.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.alfastrah.alfadigital.extrinsic.model.rest.CheckContractForProlongationRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ProlongationControllerTest extends AbstractControllerTest {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }


    @Test
    void getContract() throws Exception {
        String uri = "/rest/contract/prolongation/check";

        CheckContractForProlongationRequest request = new CheckContractForProlongationRequest();
        request.setFio("Lastname firstname middlename");
        request.setBirthDate("1987-10-13");
        request.setTransportIdentifier("vehicleIdentifier");

        String inputJson = super.mapToJson(request);
        getLogger().info("inputJson {}", inputJson);
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
                )
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Сервис не корректно отвечает",200, status);
        String content = mvcResult.getResponse().getContentAsString();
        getLogger().info("response content {}", content);
        assertNotNull("Получили пустой ответ", content);
    }

    @Test
    void moveContractFromUnicusToPostgreSQL() throws Exception {
        String uri = "/rest/contract/prolongation/move/{contractNumber}";

        /*Map<String, String> params = new HashMap<>();
        params.put("contractNumber", "0017170039");

        String inputJson = super.mapToJson(params);
        getLogger().info("inputJson {}", inputJson);*/

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get(uri, "0018222531"))
                .andExpect(status().isOk())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals("Сервис не корректно отвечает",200, status);
        String content = mvcResult.getResponse().getContentAsString();
        getLogger().info("response content {}", content);
        assertNotNull("Получили пустой ответ", content);
    }
}
