package ru.alfastrah.alfadigital.extrinsic.model.db.postgres;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "contract_for_prolongation", schema = "ilog")
public class PostgresContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "contract_id", nullable = false)
    private Long contractId;
    @Column(name = "contract_number", nullable = false)
    private String contractNumber;
    @Column(name = "begin_date", nullable = false)
    private LocalDateTime beginDate;
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;
    @Column(name = "fio", nullable = false)
    private String fio;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "transport_identifier", nullable = false)
    private String transportIdentifier;
    @Column(name = "transport_identifier_type", nullable = false)
    private String transportIdentifierType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getTransportIdentifier() {
        return transportIdentifier;
    }

    public void setTransportIdentifier(String transportIdentifier) {
        this.transportIdentifier = transportIdentifier;
    }

    public String getTransportIdentifierType() {
        return transportIdentifierType;
    }

    public void setTransportIdentifierType(String transportIdentifierType) {
        this.transportIdentifierType = transportIdentifierType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostgresContract postgresContract = (PostgresContract) o;
        return Objects.equals(id, postgresContract.id) &&
                Objects.equals(contractId, postgresContract.contractId) &&
                Objects.equals(contractNumber, postgresContract.contractNumber) &&
                Objects.equals(beginDate, postgresContract.beginDate) &&
                Objects.equals(endDate, postgresContract.endDate) &&
                Objects.equals(fio, postgresContract.fio) &&
                Objects.equals(birthDate, postgresContract.birthDate) &&
                Objects.equals(transportIdentifier, postgresContract.transportIdentifier) &&
                Objects.equals(transportIdentifierType, postgresContract.transportIdentifierType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contractId, contractNumber, beginDate, endDate, fio, birthDate, transportIdentifier, transportIdentifierType);
    }

    @Override
    public String toString() {
        return "PostgresContract{" +
                "id=" + id +
                ", contractId=" + contractId +
                ", contractNumber='" + contractNumber + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", fio='" + fio + '\'' +
                ", birthDate=" + birthDate +
                ", transportIdentifier='" + transportIdentifier + '\'' +
                ", transportIdentifierType='" + transportIdentifierType + '\'' +
                '}';
    }
}
