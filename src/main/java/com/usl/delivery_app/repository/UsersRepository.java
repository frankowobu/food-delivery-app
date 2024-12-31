package com.usl.delivery_app.repository;

import com.usl.delivery_app.data.Usersdata.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
     Users findByEmail(String email);

}
