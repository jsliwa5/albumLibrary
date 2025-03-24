package com.example.albumlibrary.repositories;

import com.example.albumlibrary.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityByUsername(String username);

}
