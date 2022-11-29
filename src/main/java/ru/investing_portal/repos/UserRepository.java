package ru.investing_portal.repos;

import org.mapstruct.Named;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.investing_portal.models.domain.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByEmail(String email);

    @Override
    @Named("getUserReferenceById")
    User getReferenceById(Integer integer);

    Optional<User> findByEmail(String email);

}