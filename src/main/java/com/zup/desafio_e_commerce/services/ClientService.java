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

    public ClientResponse save(ClientRequest request) {
        ClientEntity newClient = new ClientEntity(request.getName(), request.getCpf(), request.getEmail());
        validateCpf(request.getCpf());
        validateEmail(request.getEmail());
        ClientEntity savedClient = clientRepository.save(newClient);

        return new ClientResponse(savedClient.getId(), savedClient.getName(), savedClient.getCpf(), savedClient.getEmail());
    }

    public ClientResponse findByCpf(String cpf) {
        validateCpf(cpf);
        ClientEntity clientByCpf = clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException("Não foi encontrado cliente com cpf: " + cpf));

        return new ClientResponse(clientByCpf.getId(), clientByCpf.getName(), clientByCpf.getCpf(), clientByCpf.getEmail());
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
