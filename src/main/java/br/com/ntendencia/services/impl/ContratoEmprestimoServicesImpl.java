package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.dto.ContratoEmprestimoDTO;
import br.com.ntendencia.enums.CStatus;
import br.com.ntendencia.repositories.ContratoEmprestimoRepository;
import br.com.ntendencia.services.ContratoEmprestimoService;
import br.com.ntendencia.services.ItemEmprestadoService;
import br.com.ntendencia.services.MutuarioService;
import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
import br.com.ntendencia.utils.CollectionsUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.ntendencia.enums.EStatus.DISPONIVEL;
import static br.com.ntendencia.enums.EStatus.EMPRESTADO;

@Service
public class ContratoEmprestimoServicesImpl implements ContratoEmprestimoService {

    private final ContratoEmprestimoRepository contratoEmprestimoRepo;

    private final ItemEmprestadoService itemEmprestadoServices;

    private final MutuarioService mutuarioServices;

    private ModelMapper mapper = new ModelMapper();

    @Autowired
    public ContratoEmprestimoServicesImpl(ContratoEmprestimoRepository contratoEmprestimoRepo,
                                          ItemEmprestadoService itemEmprestadoServices,
                                          MutuarioService mutuarioServices,
                                          ModelMapper mapper) {
        this.contratoEmprestimoRepo = contratoEmprestimoRepo;
        this.itemEmprestadoServices = itemEmprestadoServices;
        this.mutuarioServices = mutuarioServices;
        this.mapper = mapper;
    }

    @Override
    public ContratoEmprestimo contratoEmprestimoSave(ContratoEmprestimoDTO contratoEmprestimoDTO) {
        if (contratoEmprestimoDTO.getListaIdsItens().isEmpty()) {
            throw new ResourceNotFoundException("Não é permitido criar contrato de emprestimo sem itens");
        }
        if (contratoEmprestimoDTO.getId() != null) {
            throw new ResourceNotFoundException("Id não deve ser preenchido, será gerado pelo banco");
        }
        if (mutuarioServices.findById(contratoEmprestimoDTO.getIdMutuario()) == null) {
            throw new ResourceNotFoundException("Mutuario não encontrado no banco de dados");
        }
        ContratoEmprestimo contratoEmprestimo = mapper.map(contratoEmprestimoDTO, ContratoEmprestimo.class);
        //contratoEmprestimo.getItensEmprestados().addAll(contratoEmprestimoDTO.getItensEmprestados());
        contratoEmprestimo.getListaIdsItens().addAll(contratoEmprestimoDTO.getListaIdsItens());
        //  Verificar uso do filter trabalhando validação do item Emprestado
        /*
        List<ItemEmprestado> itemEmprestadoList = contratoEmprestimo.getItensEmprestados().stream().
                filter(
                        itemEmprestado -> !DISPONIVEL.equals(itemEmprestado.geteStatus())
                ).collect(Collectors.toList());
        if(!itemEmprestadoList.isEmpty()){
            throw new ResourceNotFoundException("Esse(s) Item(S)" +itemEmprestadoList.size() + "Emprestados e indisponivel");
        }*/
        contratoEmprestimo.getListaIdsItens().forEach(idItem -> {
            ItemEmprestado itemEmprestadoObj = itemEmprestadoServices.procurarItemEmprestado(idItem);
            if (itemEmprestadoObj.geteStatus() != DISPONIVEL) {
                throw new ResourceNotFoundException("Item indisponivel para emprestimo");
            }
        });
        contratoEmprestimo.getListaIdsItens().forEach(idItem ->
        {
            ItemEmprestado itemEmprestadoObj = itemEmprestadoServices.procurarItemEmprestado(idItem);
            itemEmprestadoObj.seteStatus(EMPRESTADO);
            itemEmprestadoServices.alterarStatusItemEmprestado(itemEmprestadoObj.getId(), EMPRESTADO);
            itemEmprestadoServices.definirDataDeEmprestimo(itemEmprestadoObj);
            itemEmprestadoServices.atualizarItemEmprestado(itemEmprestadoObj);
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
        contratoEmprestimo.setStatusContrato(CStatus.CONTRATOEMDIA);
        contratoEmprestimoRepo.save(contratoEmprestimo);
        return contratoEmprestimo;
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

        //return (List<ContratoEmprestimo>) contratoEmprestimoRepo.findAll();
        return CollectionsUtils.getListFromIterable(contratoEmprestimoRepo.findAll());
    }

//    @Override
//    public List<ContratoEmprestimoDTO> listarTodoscontratosEmprestimoDTO() {
//        if(listarTodoscontratosEmprestimo().isEmpty()){
//            throw new ResourceNotFoundException("Não há contratos para exibir");
//        }
//        return listarTodoscontratosEmprestimo().stream().map(ContratoEmprestimoDTO::new).collect(Collectors.toList());
//    }

    @Override
    public List<ContratoEmprestimoDTO> listarTodoscontratosEmprestimoDTO() {
        if(listarTodoscontratosEmprestimo().isEmpty()){
            throw new ResourceNotFoundException("Não há contratos para exibir");
        }
        return listarTodoscontratosEmprestimo().stream().map(contratoEmprestimo -> {
            contratoEmprestimo.getListaIdsItens().forEach(idItem -> {
                ItemEmprestado itemEmprestadoObj = itemEmprestadoServices.procurarItemEmprestado(idItem);
                if(LocalDate.now().isAfter(itemEmprestadoObj.getDataEmprestimo().
                        plusDays(itemEmprestadoObj.getQtdDiasDeDevolucao()))){
                    contratoEmprestimo.setStatusContrato(CStatus.CONTRATOEMATRASO);
                }
            });
            return contratoEmprestimoRepo.save(contratoEmprestimo);
        }).collect(Collectors.toList()).stream().map(ContratoEmprestimoDTO::new).collect(Collectors.toList());

        //return listarTodoscontratosEmprestimo().stream().map(ContratoEmprestimoDTO::new).collect(Collectors.toList());
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
