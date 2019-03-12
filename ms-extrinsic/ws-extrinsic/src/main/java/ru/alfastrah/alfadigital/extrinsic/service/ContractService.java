package ru.alfastrah.alfadigital.extrinsic.service;

import org.springframework.stereotype.Service;
import ru.alfastrah.alfadigital.extrinsic.model.db.postgres.PostgresContract;
import ru.alfastrah.alfadigital.extrinsic.util.HasLogger;

import java.time.LocalDate;

@Service
public interface ContractService extends HasLogger {
    PostgresContract getContract(String fio, LocalDate birthDate, String transportIdentifier);
    PostgresContract moveContractFromUnicusToPostgreSQL(String contractNumber);
}
