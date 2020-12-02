package com.example.bankpoc.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bankpoc.models.request.ClientRequest;
import com.example.bankpoc.models.response.ClientResponse;
import com.example.bankpoc.service.interfaceServ.ClientService;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = clientService.create(clientRequest);
        return new ResponseEntity<>(clientResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable("accountId") Integer accountId) {
        ClientResponse client = clientService.findByAccountIdResponse(Long.valueOf(accountId));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable("accountId") Integer accountId, @Valid @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = clientService.update(clientRequest, Long.valueOf(accountId));
        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }
}