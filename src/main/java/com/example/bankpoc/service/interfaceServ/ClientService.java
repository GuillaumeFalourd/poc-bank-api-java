package com.example.bankpoc.service.interfaceServ;

import com.example.bankpoc.models.entity.Client;
import com.example.bankpoc.models.request.ClientRequest;
import com.example.bankpoc.models.response.ClientResponse;

public interface ClientService {

    ClientResponse findByAccountIdResponse(Long accountId);

    Client findByAccountId(Long accountId);

    ClientResponse findByCpf(String cpf);

    ClientResponse create(ClientRequest clientRequest);

    ClientResponse update(ClientRequest clientRequest, Long accountId);

    void checkIfClientExists(Client client);

    void checkIfCPFExists(String cpf);

    void checkIfClientNotExists(Client client);
}
