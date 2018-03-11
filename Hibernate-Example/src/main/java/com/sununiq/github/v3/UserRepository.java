package com.sununiq.github.v3;

import com.sununiq.github.common.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
