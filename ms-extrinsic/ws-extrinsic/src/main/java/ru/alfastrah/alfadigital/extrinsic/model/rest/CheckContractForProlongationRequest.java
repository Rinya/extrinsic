package ru.alfastrah.alfadigital.extrinsic.model.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CheckContractForProlongationRequest")
public class CheckContractForProlongationRequest {
    private String fio;
    private String birthDate;
    private String transportIdentifier;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getTransportIdentifier() {
        return transportIdentifier;
    }

    public void setTransportIdentifier(String transportIdentifier) {
        this.transportIdentifier = transportIdentifier;
    }

    @Override
    public String toString() {
        return "CheckContractForProlongationRequest{" +
                "fio='" + fio + '\'' +
                ", birthDate=" + birthDate +
                ", transportIdentifier='" + transportIdentifier + '\'' +
                '}';
    }
}
