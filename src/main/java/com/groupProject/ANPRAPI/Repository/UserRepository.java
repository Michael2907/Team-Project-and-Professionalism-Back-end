package com.groupProject.ANPRAPI.Repository;

import com.groupProject.ANPRAPI.Domain.User;
import com.groupProject.ANPRAPI.Domain.UserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, UserPK>, JpaSpecificationExecutor<User> {
}