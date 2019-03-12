package ru.alfastrah.alfadigital.extrinsic.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.alfastrah.alfadigital.extrinsic.Application;
import ru.alfastrah.alfadigital.extrinsic.config.db.postgres.PostgreSQLConfig;
import ru.alfastrah.alfadigital.extrinsic.config.db.unicus.UnicusConfig;
import ru.alfastrah.alfadigital.extrinsic.model.db.postgres.PostgresContract;
import ru.alfastrah.alfadigital.extrinsic.model.db.unicus.UnicusContract;
import ru.alfastrah.alfadigital.extrinsic.repository.db.postgres.PostgreSQLRepository;
import ru.alfastrah.alfadigital.extrinsic.repository.db.unicus.UnicusRepository;
import ru.alfastrah.alfadigital.extrinsic.util.HasLogger;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration(classes = {UnicusConfig.class, PostgreSQLConfig.class})
public class MultipleDatabaseConnectionTest implements HasLogger {
    @Autowired
    private UnicusRepository unicusRepository;
    @Autowired
    private PostgreSQLRepository postgreSQLRepository;

    @Test
    @Transactional("unicusTransactionManager")
    public void selectingUnicusContract() {
        UnicusContract unicusContract = unicusRepository.getOne(47063506L);
        Assertions.assertNotNull(unicusContract, "Could not get the unicusContract");
        getLogger().info("unicusContract from unicus {}", unicusContract);
    }

    @Test
    @Transactional("postgresTransactionManager")
    public void selectingPostgresqlContract() {
        PostgresContract postgresContract = postgreSQLRepository.getOne(3395675L);
        Assertions.assertNotNull(postgresContract, "Could not get the postgresContract");
        getLogger().info("postgresContract from postgresql {}", postgresContract);
    }
}
