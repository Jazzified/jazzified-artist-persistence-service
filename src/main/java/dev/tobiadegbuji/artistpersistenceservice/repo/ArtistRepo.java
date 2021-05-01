package dev.tobiadegbuji.artistpersistenceservice.repo;


import dev.tobiadegbuji.artistpersistenceservice.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, UUID> {


}
