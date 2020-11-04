package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.enums.EStatus;
import br.com.ntendencia.repositories.ContratoEmprestimoRepository;
import br.com.ntendencia.services.ContratoEmprestimoService;
import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public ContratoEmprestimo contratoEmprestimoSave(ContratoEmprestimo contratoEmprestimo) {
        List<ItemEmprestado> itemEmprestadoList = contratoEmprestimo.getItensEmprestados().stream().
                filter(
                        itemEmprestado -> !DISPONIVEL.equals(itemEmprestado.geteStatus())
                ).collect(Collectors.toList());
        if(!itemEmprestadoList.isEmpty()){
            throw new ResourceNotFoundException("Esse(s) Item(S)" +itemEmprestadoList.size() + "Emprestados e indisponivel");
        }
//        contratoEmprestimo.getItensEmprestados().forEach(itemEmprestado -> {
//            ItemEmprestado itemEmprestadoObj = itemEmprestadoServices.
//                    procurarItemEmprestado(itemEmprestado.getId()).orElseThrow();
//            if (itemEmprestadoObj.geteStatus() != DISPONIVEL) {
//                throw new ResourceNotFoundException(itemEmprestadoObj.getId());
//            }
//        });

        contratoEmprestimo.getItensEmprestados().forEach(itemEmprestado ->
        {
            itemEmprestado.seteStatus(EMPRESTADO);
            itemEmprestadoServices.alterarStatusItemEmprestado(itemEmprestado.getId(), EMPRESTADO);
            itemEmprestadoServices.definirDataDeEmprestimo(itemEmprestado);
            itemEmprestadoServices.atualizarItemEmprestado(itemEmprestado);
        });
        return contratoEmprestimoRepo.save(contratoEmprestimo);
    }

    @Override
    public void deleteContratoEmprestimo(String id) {
        if (contratoEmprestimoRepo.findById(id).isPresent()) {
            contratoEmprestimoRepo.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Não foi encontrado contrato com o id: "+id);
        }
    }

    @Override
    public List<ContratoEmprestimo> listarTodoscontratosEmprestimo() {
        return (List<ContratoEmprestimo>) contratoEmprestimoRepo.findAll();
    }
}
