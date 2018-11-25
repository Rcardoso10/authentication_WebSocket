package com.vtUnitri.Websocket.repositories;

import com.vtUnitri.Websocket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save (User user);

    void delete (Long id);

    User findUserByUserName (String userName);

    @Query(value = "select user from User user where user.email =:email")
    User findByEmail (@Param( "email" ) String email);
}
