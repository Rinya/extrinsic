package ru.alfastrah.alfadigital.extrinsic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alfastrah.alfadigital.extrinsic.model.db.postgres.PostgresContract;
import ru.alfastrah.alfadigital.extrinsic.model.db.unicus.UnicusContract;
import ru.alfastrah.alfadigital.extrinsic.repository.db.postgres.PostgreSQLRepository;
import ru.alfastrah.alfadigital.extrinsic.repository.db.unicus.UnicusRepository;

import java.time.LocalDate;

@Service
public class ContractServiceImpl implements ContractService {
    private final PostgreSQLRepository postgreSQLRepository;
    private final UnicusRepository unicusRepository;

    @Autowired
    public ContractServiceImpl(PostgreSQLRepository postgreSQLRepository, UnicusRepository unicusRepository) {
        this.postgreSQLRepository = postgreSQLRepository;
        this.unicusRepository = unicusRepository;
    }

    @Override
    public PostgresContract getContract(String fio, LocalDate birthDate, String transportIdentifier) {
        return postgreSQLRepository.findByFioAndBirthDateAndTransportIdentifier(fio, birthDate, transportIdentifier);
    }

    @Override
    public PostgresContract moveContractFromUnicusToPostgreSQL(String contractNumber) {
        PostgresContract contract = postgreSQLRepository.findByContractNumber(contractNumber);

        if (contract == null) {
            UnicusContract unicusContract = unicusRepository.getContractDataForIlog(contractNumber);
            if (unicusContract != null) {
                PostgresContract postgresContract = convertUnicusContractToPostgreSQL(unicusContract);
                postgreSQLRepository.saveAndFlush(postgresContract);
                return postgresContract;
            } else {
                getLogger().warn("Contract is not found in unicus with number {}", contractNumber);
                return null;
            }
        } else {
            getLogger().info("Contract exist in postgres");
            return contract;
        }
    }

    private PostgresContract convertUnicusContractToPostgreSQL(UnicusContract unicusContract) {
        PostgresContract postgresContract = new PostgresContract();
        postgresContract.setContractId(unicusContract.getId());
        postgresContract.setContractNumber(unicusContract.getContractNumber());
        postgresContract.setBeginDate(unicusContract.getBeginDate());
        postgresContract.setEndDate(unicusContract.getEndDate());
        postgresContract.setFio(unicusContract.getFio());
        postgresContract.setBirthDate(unicusContract.getBirthDate());
        postgresContract.setTransportIdentifier(unicusContract.getTransportIdentifier());
        postgresContract.setTransportIdentifierType(unicusContract.getTransportIdentifierType());

        return postgresContract;
    }
}
