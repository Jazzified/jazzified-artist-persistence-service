package dev.tobiadegbuji.artistpersistenceservice.repo;

import dev.tobiadegbuji.artistpersistenceservice.domain.Artist;
import dev.tobiadegbuji.artistpersistenceservice.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreRepo extends JpaRepository<Genre, UUID>  {
}
