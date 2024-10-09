package com.hassan.Repo;


import com.hassan.Model.Users;
import com.hassan.Record.UsersRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    @Query("select new com.hassan.Record.UsersRecord(" +
            " u.userName," +
            " u.address, " +
            " u.phoneNumber, " +
            " u.email, " +
            " u.password, " +
            " u.cin, " +
            " u.role) " +
            " from Users u " +
            " where u.userName like :userName")
    Optional<UsersRecord> findByName(@Param("userName") String name);


    @Query("select u from Users u where u.email like :email")
    Optional<Users> findByEmail(String email);

    @Query("select u from Users u where u.email like :email")
    Users findEmail(String email);

}
