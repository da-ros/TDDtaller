package fin.acme.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fin.acme.bank.controller.dto.TransferRequest;
import fin.acme.bank.exception.NotEnoughFundsException;
import fin.acme.bank.model.Account;
import fin.acme.bank.service.BankTransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankTransferController.class)
@ExtendWith(MockitoExtension.class)
class BankTransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BankTransferService bankTransferServiceMock;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    @DisplayName("givenEnoughAmountWhenCallTransferThen return 200")
    void givenEnoughAmountWhenCallTransferThenReturn200() throws Exception{
        TransferRequest request = new TransferRequest("123-456","789-101",
                500.0,"Payment for services");

        mockMvc.perform(post("http://localhost:8080/bank/transfer")
                        .content(MAPPER.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("givenNotEnoughAmountWhenCallTransferThen return 400")
    void givenNotEnoughAmountWhenCallTransferThenReturn400() throws Exception{
        when(bankTransferServiceMock.transfer(anyString(), anyString(), anyDouble(), anyString())).
                thenThrow(new NotEnoughFundsException("Amount is greather than available fromAccount's balance"));

        TransferRequest request = new TransferRequest("123-456","789-101",
                700.0,"Payment for services");

        mockMvc.perform(post("http://localhost:8080/bank/transfer")
                        .content(MAPPER.writeValueAsBytes(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());

    }
}
