package com.example.bankpoc.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.bankpoc.controller.ClientController;
import com.example.bankpoc.base.BankBaseTest;
import com.example.bankpoc.models.request.ClientRequest;
import com.example.bankpoc.models.response.ClientResponse;
import com.example.bankpoc.service.implement.ClientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClientController.class)
public class ClientControllerTest extends BankBaseTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientServiceImpl clientService;

    private ClientRequest clientRequest;
    private ClientResponse clientResponse;

    @Before
    public void setUp() {
        clientRequest = new ClientRequest("Joao da Silva", "528.111.272-40");
        clientResponse = new ClientResponse("Joao da Silva", "528.111.272-40",1L);
    }

    @Test
    public void createTest_ok() throws Exception {
        when(clientService.create(any(ClientRequest.class))).thenReturn(clientResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(clientRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createTest_errorName() throws Exception {
        ClientRequest clientRequest1 = new ClientRequest("J", "528.111.272-40");
        when(clientService.create(any(ClientRequest.class))).thenReturn(clientResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(clientRequest1)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createTest_errorCpf() throws Exception {
        ClientRequest clientRequest1 = new ClientRequest("Joao da Silva", "");
        when(clientService.create(any(ClientRequest.class))).thenReturn(clientResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(clientRequest1)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createTest_ClientExists() throws Exception {
        ClientRequest clientRequest1 = new ClientRequest("Joao da Silva", "");
        when(clientService.create(any(ClientRequest.class))).thenReturn(clientResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(post("/client/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(clientRequest1)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getTest() throws Exception {
        when(clientService.findByAccountIdResponse((anyLong()))).thenReturn(clientResponse);
        mvc.perform(get("/client/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        when(clientService.update(any(ClientRequest.class),anyLong())).thenReturn(clientResponse);
        ObjectMapper mapper = new ObjectMapper();
        mvc.perform(put("/client/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(clientRequest)))
                .andExpect(status().isOk());

    }
}
