package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.dto.MutuanteDTO;
import br.com.ntendencia.repositories.MutuanteRepository;
import br.com.ntendencia.services.MutuanteService;
import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MutuanteServicesImpl implements MutuanteService {

    private final MutuanteRepository mutuanteRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public MutuanteServicesImpl(MutuanteRepository mutuanteRepo,
                                ModelMapper modelMapper) {
        this.mutuanteRepo = mutuanteRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteMutuante(String id) {
        mutuanteRepo.deleteById(id);
    }

    @Override
    public MutuanteDTO procurarPorId(String id) {
        Optional<Mutuante> mutuanteOBJ = mutuanteRepo.findById(id);
        if (mutuanteOBJ.isPresent()) {
            return new MutuanteDTO(mutuanteOBJ.get());
        }
        throw new ResourceNotFoundException("ID" + id + " Não encontrado no sistema.");
    }

    @Override
    public List<Mutuante> listaMutuantes() {
        if (mutuanteRepo.findAll().iterator().hasNext()) {
            return (List<Mutuante>) mutuanteRepo.findAll();
        } else {
            throw new ResourceNotFoundException("Não há mutuantes registrados");
        }
    }

    @Override
    public List<MutuanteDTO> listaMutuantesDTO() {
        if (mutuanteRepo.findAll().iterator().hasNext()) {
            List<MutuanteDTO> listDTO = new ArrayList<>();
            for (Mutuante mutuante : mutuanteRepo.findAll()) {
                listDTO.add(new MutuanteDTO(mutuante));
            }
            return listDTO;
        } else {
            throw new ResourceNotFoundException("Não há mutuantes registrados");
        }
    }

    @Override
    public Integer gerarId() {
        List<Mutuante> mutuante = (List<Mutuante>) mutuanteRepo.findAll();
        Integer ultimoIdMutuante = 0;
        Optional<Mutuante> mutuanteOpt = mutuante.stream().max(Comparator.comparingInt(Mutuante::getIdMutuante));
        if (mutuanteOpt.isPresent()) {
            ultimoIdMutuante = mutuanteOpt.get().getIdMutuante();
        }
        return ultimoIdMutuante + 1;
    }

    @Override
    public Mutuante salvarMutuante(MutuanteDTO mutuanteDTO) {
        if (mutuanteDTO.getId() != null || mutuanteDTO.getIdMutuante() != null) {
            throw new ResourceNotFoundException("Id não pode ser inserido, será gerado de forma incremental");
        }
        Mutuante mutuante = modelMapper.map(mutuanteDTO, Mutuante.class);
        Integer idMutuante = gerarId();
        mutuante.setIdUsuario(idMutuante.toString());
        mutuante.setIdMutuante(idMutuante);
        return mutuanteRepo.save(mutuante);
    }

    @Override
    public MutuanteDTO procurarPorNome(String nome) {
        Optional<Mutuante> mutuanteOpt = mutuanteRepo.findByName(nome);
        if (mutuanteOpt.isPresent()) {
            return new MutuanteDTO(mutuanteOpt.get());
        }
        throw new ResourceNotFoundException(nome + " Não encontrado no sistema.");
    }
}
