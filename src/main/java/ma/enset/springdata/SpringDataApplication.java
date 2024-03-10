package ma.enset.springdata;

import ma.enset.springdata.entities.Patient;
import ma.enset.springdata.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
        System.out.println("Hello Spring Data");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
