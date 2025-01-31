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

import java.util.Objects;

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
        ClientEntity clientByCpf = findClientByCpf(cpf);

        return new ClientResponse(clientByCpf.getId(), clientByCpf.getName(), clientByCpf.getCpf(), clientByCpf.getEmail());
    }

    public ClientResponse update(String cpf, ClientRequest request) {
        validateCpf(cpf);
        ClientEntity clientToUpdate = findClientByCpf(cpf);

        if (Objects.nonNull(request.getEmail()) && !request.getEmail().isEmpty()) {
            validateEmail(request.getEmail());
            clientToUpdate.setEmail(request.getEmail());
        }
        if(Objects.nonNull(request.getName()) && !request.getName().isEmpty()) {
            clientToUpdate.setName(request.getName());
        }
        if(Objects.nonNull(request.getCpf()) && !request.getCpf().isEmpty()) {
            clientToUpdate.setCpf(request.getCpf());
        }

        ClientEntity updatedClient = clientRepository.save(clientToUpdate);

        return new ClientResponse(updatedClient.getId(), updatedClient.getName(), updatedClient.getCpf(), updatedClient.getEmail());
    }

    private ClientEntity findClientByCpf(String cpf) {
        return clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClientNotFoundException("Não foi encontrado cliente com cpf: " + cpf));
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
