package ma.enset.springdata.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity

@Data @AllArgsConstructor @NoArgsConstructor

public class Medecine {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String specialite;

    @OneToMany(mappedBy = "medecine", fetch = FetchType.LAZY)
    private Collection<Appointment> appointment;

    @Override
    public String toString() {
        return "Medecine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialite='" + specialite + '\'' +
                // don't print the appointment property here
                '}';
    }
}
