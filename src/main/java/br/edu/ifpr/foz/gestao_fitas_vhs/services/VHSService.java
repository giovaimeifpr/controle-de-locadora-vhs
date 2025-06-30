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
        return vhsRepository.findAll();
    }


}
