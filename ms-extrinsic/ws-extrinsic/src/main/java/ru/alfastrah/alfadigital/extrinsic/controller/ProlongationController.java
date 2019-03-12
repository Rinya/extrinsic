package ru.alfastrah.alfadigital.extrinsic.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.alfastrah.alfadigital.aop.metrics.CollectorMetrics;
import ru.alfastrah.alfadigital.extrinsic.model.db.postgres.PostgresContract;
import ru.alfastrah.alfadigital.extrinsic.model.rest.CheckContractForProlongationRequest;
import ru.alfastrah.alfadigital.extrinsic.service.ContractService;
import ru.alfastrah.alfadigital.extrinsic.util.HasLogger;

import java.time.LocalDate;

@RestController
@RequestMapping("/rest/contract/prolongation")
public class ProlongationController implements HasLogger {
    private final ContractService contractService;

    @Autowired
    public ProlongationController(ContractService contractService) {
        this.contractService = contractService;
    }

    @CollectorMetrics(name = "EXTRINSIC.IS_EXIST.CONTRACT.PROLONGATION")
    @PostMapping(path = "check", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    String isExistContractForProlongation(@RequestBody CheckContractForProlongationRequest request) {
        PostgresContract postgresContract = contractService.getContract(StringUtils.upperCase(request.getFio()),
                LocalDate.parse(request.getBirthDate()), StringUtils.upperCase(request.getTransportIdentifier()));

        getLogger().info("contractForProlongation= {}", postgresContract);
        return postgresContract != null ? postgresContract.getContractNumber() : "";
    }


    @CollectorMetrics(name = "EXTRINSIC.MOVE.CONTRACT")
    @GetMapping(path = "/move/{contractNumber}")
    public @ResponseBody
    String moveContractFromUnicusToPostgreSQL(@PathVariable("contractNumber") String contractNumber) {
        PostgresContract contract = contractService.moveContractFromUnicusToPostgreSQL(contractNumber);

        getLogger().info("moved contract {}", contract);
        return contract != null && contract.getId() != null ? "Договор перемещен и имеет id = " + contract.getId() :
                "По какой-то причине не удалось переместить договор :(";
    }
}
