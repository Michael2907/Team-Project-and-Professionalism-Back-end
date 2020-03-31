package com.groupProject.ANPRAPI.Repository;

import com.groupProject.ANPRAPI.Domain.Blacklist;
import com.groupProject.ANPRAPI.Domain.BlacklistPK;
import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Domain.UserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BlacklistRepository extends JpaRepository<Blacklist, BlacklistPK>, JpaSpecificationExecutor<Blacklist> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM BLACKLIST_TABLE WHERE numberPlate = :numberPlate", nativeQuery = true)
    void delete(@Param("numberPlate") String numberPlate);
}