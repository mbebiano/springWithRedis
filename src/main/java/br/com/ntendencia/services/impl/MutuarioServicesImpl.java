package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.MutuarioDTO;
import br.com.ntendencia.repositories.MutuarioRepository;
import br.com.ntendencia.services.MutuarioService;
import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MutuarioServicesImpl implements MutuarioService {

    @Autowired
    private MutuarioRepository mutuarioRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void deleteMutuario(String id) {
        if (mutuarioRepo.findById(id).isPresent()) {
            mutuarioRepo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Mutuario id: " + id + " não foi encontrado.");
        }
    }

    @Override
    public MutuarioDTO findById(String id) {
        return new MutuarioDTO(mutuarioRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException
                        ("Mutuario id: " + id + " não foi encontrado.")));
    }

    @Override
    public List<MutuarioDTO> listaMutuariosDTO() {
        if (mutuarioRepo.findAll().iterator().hasNext()) {
            List<MutuarioDTO> listDTO = new ArrayList<>();
            for (Mutuario mutuario : mutuarioRepo.findAll()) {
                listDTO.add(new MutuarioDTO(mutuario));
            }
            return listDTO;
        } else {
            throw new ResourceNotFoundException("Não há mutuarios registrados");
        }
    }

    @Override
    public List<Mutuario> listaMutuarios() {
        if (mutuarioRepo.findAll().iterator().hasNext()) {
            return (List<Mutuario>) mutuarioRepo.findAll();
        } else {
            throw new ResourceNotFoundException("Não há mutuarios registrados");
        }
    }

    @Override
    public Integer gerarId() {
        List<Mutuario> mutuarios = (List<Mutuario>) mutuarioRepo.findAll();
        Integer ultimoIdMutuario = 0;
        Optional<Mutuario> mutuarioOpt = mutuarios.stream().max(Comparator.comparingInt(Mutuario::getIdMutuario));
        if (mutuarioOpt.isPresent()) {
            ultimoIdMutuario = mutuarioOpt.get().getIdMutuario();
        }
        return ultimoIdMutuario + 1;
    }

    @Override
    public Mutuario salvarMutuario(MutuarioDTO mutuarioDTO) {
        Mutuario mutuario = modelMapper.map(mutuarioDTO, Mutuario.class);
        Integer idMutuario = gerarId();
        mutuario.setIdUsuario(idMutuario.toString());
        mutuario.setIdMutuario(idMutuario);
        return mutuarioRepo.save(mutuario);
    }

    @Override
    public MutuarioDTO procurarPorNome(String nome) {
        if (mutuarioRepo.findByName(nome).isPresent()) {
            return new MutuarioDTO(mutuarioRepo.findByName(nome).orElseThrow());
        } else {
            throw new ResourceNotFoundException("Mutuario com nome: " + nome + " não encontrado");
        }
    }

}
