package ma.enset.springdata.repository;

import ma.enset.springdata.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
}
