package med.souza.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable p);

    @Query("""
            SELECT d from Doctor d
            WHERE d.active = TRUE
            AND d.specialty = :specialty
            AND d.id NOT IN(
                SELECT c.doctor.id FROM Consultation c
                WHERE c.date = :date
            )
            ORDER BY rand() LIMIT 1
            """)
    Doctor chooseAvailableDoctor(SpecialtyEnum specialty, LocalDateTime date);
}
