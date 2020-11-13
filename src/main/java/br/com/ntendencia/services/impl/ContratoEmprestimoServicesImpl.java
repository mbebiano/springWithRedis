package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.dto.ContratoEmprestimoDTO;
import br.com.ntendencia.repositories.ContratoEmprestimoRepository;
import br.com.ntendencia.services.ContratoEmprestimoService;
import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.ntendencia.enums.EStatus.DISPONIVEL;
import static br.com.ntendencia.enums.EStatus.EMPRESTADO;

@Service
public class ContratoEmprestimoServicesImpl implements ContratoEmprestimoService {

    @Autowired
    private ContratoEmprestimoRepository contratoEmprestimoRepo;

    @Autowired
    private ItemEmprestadoServicesImpl itemEmprestadoServices;

    @Autowired
    MutuarioServicesImpl mutuarioServices;

    @Override
    public ContratoEmprestimo contratoEmprestimoSave(ContratoEmprestimoDTO contratoEmprestimoDTO) {
        if (contratoEmprestimoDTO.getListaIdsItens().isEmpty()) {
            throw new ResourceNotFoundException("Não é permitido criar contrato de emprestimo sem itens");
        }
        if (contratoEmprestimoDTO.getIdMutuario().isBlank()) {
            throw new ResourceNotFoundException("Informe o id do mutuario");
        }
        if (contratoEmprestimoDTO.getId()!=null){
            throw new ResourceNotFoundException("Id não deve ser preenchido, será gerado pelo banco");
        }
        if (mutuarioServices.findById(contratoEmprestimoDTO.getIdMutuario())==null) {
            throw new ResourceNotFoundException("Mutuario não encontrado no banco de dados");
        }
        ContratoEmprestimo contratoEmprestimo = new ContratoEmprestimo(contratoEmprestimoDTO.getIdMutuario());
        //contratoEmprestimo.getItensEmprestados().addAll(contratoEmprestimoDTO.getItensEmprestados());
        contratoEmprestimo.getListaIdsItens().addAll(contratoEmprestimoDTO.getListaIdsItens());
        // TODO Verificar uso do filter trabalhando validação do item Emprestado
        /*
        List<ItemEmprestado> itemEmprestadoList = contratoEmprestimo.getItensEmprestados().stream().
                filter(
                        itemEmprestado -> !DISPONIVEL.equals(itemEmprestado.geteStatus())
                ).collect(Collectors.toList());
        if(!itemEmprestadoList.isEmpty()){
            throw new ResourceNotFoundException("Esse(s) Item(S)" +itemEmprestadoList.size() + "Emprestados e indisponivel");
        }*/
        contratoEmprestimo.getListaIdsItens().forEach(idItem ->{
            Optional<ItemEmprestado> itemEmprestadoObj = itemEmprestadoServices.procurarItemEmprestado(idItem);
            if (itemEmprestadoObj.get().geteStatus() != DISPONIVEL) {
                throw new ResourceNotFoundException("Item indisponivel para emprestimo");
            }
        });
        contratoEmprestimo.getListaIdsItens().forEach(idItem ->
        {
            Optional<ItemEmprestado> itemEmprestadoObj = itemEmprestadoServices.procurarItemEmprestado(idItem);
            itemEmprestadoObj.get().seteStatus(EMPRESTADO);
            itemEmprestadoServices.alterarStatusItemEmprestado(itemEmprestadoObj.get().getId(), EMPRESTADO);
            itemEmprestadoServices.definirDataDeEmprestimo(itemEmprestadoObj.get());
            itemEmprestadoServices.atualizarItemEmprestado(itemEmprestadoObj.get());
        });
    /*
        contratoEmprestimo.getItensEmprestados().forEach(itemEmprestado -> {
            ItemEmprestado itemEmprestadoObj = itemEmprestadoServices.
                    procurarItemEmprestado(itemEmprestado.getId()).orElseThrow();
            if (itemEmprestadoObj.geteStatus() != DISPONIVEL) {
                throw new ResourceNotFoundException("Item indisponivel para emprestimo");
            }
        });


        contratoEmprestimo.getItensEmprestados().forEach(itemEmprestado ->
        {
            itemEmprestado.seteStatus(EMPRESTADO);
            itemEmprestadoServices.alterarStatusItemEmprestado(itemEmprestado.getId(), EMPRESTADO);
            itemEmprestadoServices.definirDataDeEmprestimo(itemEmprestado);
            itemEmprestadoServices.atualizarItemEmprestado(itemEmprestado);
        });*/
        Integer idContrato = gerarId();
        contratoEmprestimo.setId(idContrato.toString());
        contratoEmprestimo.setIdContrato(idContrato);
        return contratoEmprestimoRepo.save(contratoEmprestimo);
    }

    @Override
    public void deleteContratoEmprestimo(String id) {
        if (contratoEmprestimoRepo.findById(id).isPresent()) {
            contratoEmprestimoRepo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Não foi encontrado contrato com o id: " + id);
        }
    }

    @Override
    public List<ContratoEmprestimo> listarTodoscontratosEmprestimo() {
        return (List<ContratoEmprestimo>) contratoEmprestimoRepo.findAll();
    }

    @Override
    public List<ContratoEmprestimoDTO> listarTodoscontratosEmprestimoDTO() {
        List<ContratoEmprestimoDTO> list = new ArrayList<>();
        for (ContratoEmprestimo contratoEmprestimo : listarTodoscontratosEmprestimo()) {
            list.add(new ContratoEmprestimoDTO(contratoEmprestimo));
        }
        return list;
    }


    @Override
    public Integer gerarId() {
        List<ContratoEmprestimo> contratosEmprestimo = listarTodoscontratosEmprestimo();
        Integer ultimoIdContrato = 0;
        Optional<ContratoEmprestimo> contratoOpt = contratosEmprestimo.stream().max(Comparator.comparingInt(ContratoEmprestimo::getIdContrato));
        if (contratoOpt.isPresent()) {
            ultimoIdContrato = contratoOpt.get().getIdContrato();
        }
        return ultimoIdContrato + 1;
    }
}
