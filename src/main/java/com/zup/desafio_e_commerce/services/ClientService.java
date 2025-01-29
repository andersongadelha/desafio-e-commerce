package com.zup.desafio_e_commerce.services;

import br.com.caelum.stella.validation.CPFValidator;
import com.zup.desafio_e_commerce.dtos.ClientRequest;
import com.zup.desafio_e_commerce.dtos.ClientResponse;
import com.zup.desafio_e_commerce.exceptions.ClientNotFoundException;
import com.zup.desafio_e_commerce.exceptions.InvalidValueException;
import com.zup.desafio_e_commerce.models.ClientEntity;
import com.zup.desafio_e_commerce.repositories.ClientRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientResponse save(ClientRequest requisicao) {
        ClientEntity newClient = new ClientEntity(requisicao.getNome(), requisicao.getCpf(), requisicao.getEmail());
        validateCpf(requisicao.getCpf());
        validateEmail(requisicao.getEmail());
        ClientEntity savedClient = clientRepository.save(newClient);

        return new ClientResponse(savedClient.getId(), savedClient.getName(), savedClient.getCpf(), savedClient.getEmail());
    }

    public ClientResponse findById(Long id) {
        ClientEntity clientById = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Não foi encontrado cliente com id: " + id));

        return new ClientResponse(clientById.getId(), clientById.getName(), clientById.getCpf(), clientById.getEmail());
    }

    private void validateCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        if (!cpfValidator.isEligible(cpf)) {
            throw new InvalidValueException("O CPF informado é inválido.");
        }
    }

    private void validateEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(email)) {
            throw new InvalidValueException("O email informado é inválido.");
        }
    }
}
