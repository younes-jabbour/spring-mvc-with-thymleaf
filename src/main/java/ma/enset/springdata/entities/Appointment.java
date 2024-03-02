package ma.enset.springdata.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data @AllArgsConstructor @NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String time;
    @Enumerated(EnumType.STRING)
    private status status;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Medecine medecine;

    @OneToOne (mappedBy = "appointment", fetch = FetchType.LAZY)
    private Consultation consultation;
}
