package ma.enset.springdata.repository;
import ma.enset.springdata.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @SuppressWarnings("NullableProblems")
    public Optional<Patient> findById(Long id);

    @Query("SELECT p FROM Patient p WHERE p.name like %:kw%")
    public List<Patient> searchByKeyword(@Param("kw") String keyword);

    @Query("SELECT p FROM Patient p")
    public List<Patient> getAll();

    public Patient findByName(String nom);

    public List<Patient> findByNameContains(String nom);

    public void deleteById(Long id);


}
