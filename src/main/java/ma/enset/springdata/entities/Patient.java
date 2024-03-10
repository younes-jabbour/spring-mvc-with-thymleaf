package ma.enset.springdata.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Builder
/**
 * Builder : design pattern
 */

@Table(name = "PATIENTS")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 4, max = 20)
    private String name;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Boolean sick;
    @Min(10)
    private int score;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Collection<Appointment> appointmentList;
}
