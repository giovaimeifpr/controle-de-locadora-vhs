package br.edu.ifpr.foz.gestao_fitas_vhs.entidades;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Data
public class VHS {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String director;

    @Column(nullable = false, length = 100)
    private String imageUrl;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @ManyToMany
    @JoinTable(
    name = "vhs_category", // nome da tabela no banco
    joinColumns = @JoinColumn(name = "vhs_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TapeStatus status;

}
