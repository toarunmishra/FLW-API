package com.iemr.flw.repo.identity;


import com.iemr.flw.domain.identity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public interface BeneficiaryConsentRepo extends JpaRepository<MBeneficiaryconsent, BigInteger> {

    @Query("SELECT t FROM MBeneficiaryconsent t WHERE t.benConsentID = :benConsentId")
    MBeneficiaryconsent getByBenConsentID(@Param("benConsentId")BigInteger benConsentId);
}
