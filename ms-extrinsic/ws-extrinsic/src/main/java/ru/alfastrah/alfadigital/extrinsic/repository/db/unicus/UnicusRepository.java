package ru.alfastrah.alfadigital.extrinsic.repository.db.unicus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.alfastrah.alfadigital.extrinsic.model.db.unicus.UnicusContract;

@Repository
public interface UnicusRepository extends JpaRepository<UnicusContract, Long> {
    @Query(value = "SELECT C.CONTRACT_ID, C.CONTRACT_NUMBER, C.BEGIN_DATE, C.END_DATE, " +
            "       UPPER(REGEXP_REPLACE(PP.LAST_NAME||(CASE WHEN PP.FIRST_NAME IS NOT NULL THEN ' '||PP.FIRST_NAME " +
            "                            END)||(CASE WHEN PP.MIDDLE_NAME IS NOT NULL THEN' '||PP.MIDDLE_NAME END), " +
            "                            '[^([:alnum:]|[:space:])]', '')) AS FIO, " +
            "       NVL(PP.BIRTH_DATE, TO_DATE('01.01.1800', 'DD.MM.YYYY')) AS BIRTH_DATE," +
            "       REGEXP_REPLACE(NVL(T.VIN,NVL(T.TRANSPORT_BODY_NUMBER,T.TRANSPORT_CHASSIS_NR)),'[^([:alnum:]|[:space:])]', " +
            "                      '') AS TRANSPORT_NUMBER, " +
            "       (CASE WHEN T.VIN IS NOT NULL THEN 'VIN' " +
            "         WHEN T.TRANSPORT_BODY_NUMBER IS NOT NULL THEN 'BODY_NUMBER' " +
            "         WHEN T.TRANSPORT_CHASSIS_NR IS NOT NULL THEN 'CHASSIS_NUMBER' END) AS NUMBER_TYPE " +
            "  FROM CONTRACT C, CONTRACT_SUBJECT CS, PHYSICAL_PERSON PP, TRANSPORT T " +
            " WHERE C.CONTRACT_NUMBER = :contractNumber " +
            "   AND C.CONTRACT_ID = CS.CONTRACT_ID " +
            "   AND CS.OWNER_SUBJECT_ID = PP.SUBJECT_ID " +
            "   AND CS.SUBJECT_ID = T.SUBJECT_ID",
            nativeQuery = true)
    UnicusContract getContractDataForIlog(@Param("contractNumber") String contractNumber);
}
