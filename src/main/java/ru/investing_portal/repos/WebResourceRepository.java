package ru.investing_portal.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.investing_portal.models.domain.WebResource;

@Repository
public interface WebResourceRepository extends JpaRepository<WebResource, Integer> {
}
