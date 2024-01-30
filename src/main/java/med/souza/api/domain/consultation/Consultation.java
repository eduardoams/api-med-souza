package med.souza.api.domain.consultation;

import jakarta.persistence.*;
import lombok.*;
import med.souza.api.domain.doctor.Doctor;
import med.souza.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_consultation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private ReasonEnum reason;

    private Boolean active;

    public void cancel(ConsultationCancelData data) {
        this.reason = data.reason();
        this.active = false;
    }
}
