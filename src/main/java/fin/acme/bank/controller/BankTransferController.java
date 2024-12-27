package fin.acme.bank.controller;

import fin.acme.bank.controller.dto.TransferRequest;
import fin.acme.bank.exception.NotEnoughFundsException;
import fin.acme.bank.exception.NotFoundAccountException;
import fin.acme.bank.service.BankTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/bank")

public class BankTransferController {
    @Autowired
    private BankTransferService bankTransferService;

    @PostMapping("/transfer")
    public ResponseEntity<Object> transfer(@RequestBody TransferRequest transferRequest) {
        return status(HttpStatus.BAD_GATEWAY).build();
    }
}