package ru.alfastrah.alfadigital.extrinsic.repository.db.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alfastrah.alfadigital.extrinsic.model.db.postgres.PostgresContract;

import java.time.LocalDate;

@Repository
public interface PostgreSQLRepository extends JpaRepository<PostgresContract, Long> {
    PostgresContract findByFioAndBirthDateAndTransportIdentifier(@Param("fio") String fio,
                                                                 @Param("birthDate") LocalDate birthDate,
                                                                 @Param("transportIdentificator") String transportIdentificator);

    PostgresContract findByContractNumber(@Param("contractNumber") String contractNumber);
}
