package io.muic.cs.ooc.lazyliving.backend.repository;

import io.muic.cs.ooc.lazyliving.backend.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    User findByUsername(String username);

    List<User> findAll();

    User findByEmail(String email);

    User findByUserId(long userId);
}
