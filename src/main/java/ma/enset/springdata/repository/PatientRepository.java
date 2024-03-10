package ma.enset.springdata.repository;

import jakarta.transaction.Transactional;
import ma.enset.springdata.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    public Page<Patient> findByNameContainsIgnoreCase(String kw, Pageable pageable);

    @Query("SELECT p FROM Patient p WHERE p.name like %:kw%")
    public List<Patient> searchByKeyword(@Param("kw") String keyword);

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :#{#patient.name}, p.birthDate = :#{#patient.birthDate}, p.sick = :#{#patient.sick}, " +
            "p.score = :#{#patient.score} WHERE p.id = :#{#patient.id}")
    void updatePatient(@Param("patient") Patient patient);
}
