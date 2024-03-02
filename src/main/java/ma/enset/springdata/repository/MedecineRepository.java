package ma.enset.springdata.repository;

import ma.enset.springdata.entities.Medecine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MedecineRepository extends JpaRepository<Medecine,Long> {

    @Query("SELECT m FROM Medecine m")
    public List<Medecine> getAll();

    public Optional<Medecine> findById(Long id);

    public List<Medecine> findByName(String name);

    public void deleteById(Long id);
}
