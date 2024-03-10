package ma.enset.springdata.repository;

import ma.enset.springdata.entities.Medecine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MedecineRepository extends JpaRepository<Medecine, Long> {

    public List<Medecine> findByName(String name);

}
