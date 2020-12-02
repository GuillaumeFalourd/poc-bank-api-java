package com.example.bankpoc.service.implement;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.bankpoc.exception.BusinessException;
import com.example.bankpoc.exception.NotFoundException;
import com.example.bankpoc.models.entity.Account;
import com.example.bankpoc.models.entity.Client;
import com.example.bankpoc.models.request.ClientRequest;
import com.example.bankpoc.models.response.ClientResponse;
import com.example.bankpoc.repository.ClientRepository;
import com.example.bankpoc.service.interfaceServ.AccountService;
import com.example.bankpoc.service.interfaceServ.ClientService;

@Component
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ClientResponse create(ClientRequest clientRequest) {
        this.checkIfCPFExists(clientRequest.getCpf());
        Account account = accountService.create(new Account(LocalDateTime.now()));
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setCpf(clientRequest.getCpf());
        client.setAccountId(account.getId());
        client.setCreatedAt(LocalDateTime.now());
        clientRepository.save(client);
        return new ClientResponse(client.getName(), client.getCpf(), client.getAccountId());
    }

    @Override
    public ClientResponse findByAccountIdResponse(Long accountId) {
        Client client = clientRepository.findByAccountId(accountId);
        this.checkIfClientNotExists(client);
        return new ClientResponse(client.getName(), client.getCpf(), client.getAccountId());
    }

    @Override
    public Client findByAccountId(Long accountId) {
        Client client = clientRepository.findByAccountId(accountId);
        this.checkIfClientNotExists(client);
        return client;
    }

    @Override
    public ClientResponse findByCpf(String cpf) {
        Client client = clientRepository.findByCpf(cpf);
        this.checkIfClientNotExists(client);
        return new ClientResponse(client.getName(), client.getCpf(), client.getAccountId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ClientResponse update(ClientRequest clientRequest, Long accountId) {
        Client clientUpdated = this.findByAccountId(accountId);
        clientUpdated.setName(clientRequest.getName());
        clientUpdated.setCpf(clientRequest.getCpf());
        clientRepository.save(clientUpdated);
        return new ClientResponse(clientUpdated.getName(), clientUpdated.getCpf(), clientUpdated.getAccountId());
    }

    @Override
    public void checkIfCPFExists(String cpf) {
        Client client = clientRepository.findByCpf(cpf);
        this.checkIfClientExists(client);
    }

    @Override
    public void checkIfClientExists(Client client) {
        if (client != null)
            throw new BusinessException("Cliente já possui cadastro no banco de dados");
    }

    @Override
    public void checkIfClientNotExists(Client client) {
        if (client == null)
            throw new NotFoundException("Conta Inexistente");
    }
}