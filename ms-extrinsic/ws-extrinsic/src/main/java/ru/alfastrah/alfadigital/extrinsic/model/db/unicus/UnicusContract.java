package ru.alfastrah.alfadigital.extrinsic.model.db.unicus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
//@Table(name = "CONTRACT", schema = "UNICUS_B")
public class UnicusContract implements Serializable {
    @Id
    @Column(name = "CONTRACT_ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "CONTRACT_NUMBER", unique = true, nullable = false)
    private String contractNumber;
    @Column(name = "BEGIN_DATE", unique = true, nullable = false)
    private LocalDateTime beginDate;
    @Column(name = "END_DATE", unique = true, nullable = false)
    private LocalDateTime endDate;
    @Column(name = "FIO", unique = true, nullable = false)
    private String fio;
    @Column(name = "BIRTH_DATE", unique = true, nullable = false)
    private LocalDate birthDate;
    @Column(name = "TRANSPORT_NUMBER", unique = true, nullable = false)
    private String transportIdentifier;
    @Column(name = "NUMBER_TYPE", unique = true, nullable = false)
    private String transportIdentifierType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String toString() {
        return "UnicusContract{" +
                "id=" + id +
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
