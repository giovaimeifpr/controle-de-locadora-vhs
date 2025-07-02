package br.edu.ifpr.foz.gestao_fitas_vhs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import br.edu.ifpr.foz.gestao_fitas_vhs.entidades.VHS;
import br.edu.ifpr.foz.gestao_fitas_vhs.repository.VHSRepository;

@Service
public class VHSService {
    
    @Autowired
    VHSRepository vhsRepository;

    public List<VHS> findAll() {
        return vhsRepository.buscarTodasComCategorias();
    }

    public List<VHS> findByTittle(String tittle) {
        return vhsRepository.findByTittleContainingIgnoreCase(tittle);
    }

    public VHS findByCodeBar(Integer codebar) {
        return vhsRepository.findByCodebar(codebar).orElse(null);
    }

    public void salvarFita(VHS vhs) {
        vhsRepository.save(vhs);
    }

    public void deletarFita(Long id) {
        vhsRepository.deleteById(id);
    }   

    public VHS findById(Long id) {
        return vhsRepository.findById(id).orElse(null);
    }

}
