package ma.enset.springdata.repository;
import ma.enset.springdata.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    @Query("SELECT p FROM Patient p")
    public List<Patient> getAll();

    public List<Patient> findByName(String nom);

    @SuppressWarnings("NullableProblems")
    public Optional<Patient> findById(Long id);

    @Query("SELECT p FROM Patient p WHERE p.name like %:kw%")
    public List<Patient> searchByKeyword(@Param("kw") String keyword);

    public void deleteById(Long id);

    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    public void updateNameById(@Param("name") String name,@Param("id") Long id);
}
