package br.edu.ifpr.foz.gestao_fitas_vhs.entidades;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class VHS {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String imageUrl;
    private LocalDate registrationDate;

    // @ManyToOne
    // @JoinColumn(name = "category_id")
    // private Category category;

    //@Enumerated(EnumType.STRING)
    // private TapeStatus status;

}
