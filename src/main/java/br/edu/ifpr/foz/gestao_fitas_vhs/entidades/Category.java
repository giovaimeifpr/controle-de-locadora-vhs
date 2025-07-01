package br.edu.ifpr.foz.gestao_fitas_vhs.entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import jakarta.persistence.ManyToMany;
import java.util.Set;

import jakarta.persistence.Column;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<VHS> vhsList;
}
