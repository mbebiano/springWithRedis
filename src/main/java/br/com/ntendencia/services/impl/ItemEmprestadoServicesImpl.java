package br.com.ntendencia.services.impl;

import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.enums.EStatus;
import br.com.ntendencia.repositories.ItemEmprestadoRepository;
import br.com.ntendencia.services.ItemEmprestadoService;
import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
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
public class ItemEmprestadoServicesImpl implements ItemEmprestadoService {

    @Autowired
    private ItemEmprestadoRepository itemEmprestadoRepo;
    @Autowired
    private MutuanteServicesImpl mutuanteService;

    @Override
    public ItemEmprestado salvarItemEmprestado(ItemEmprestado itemEmprestado) {
        String id = itemEmprestado.getIdMutuante();

        if (mutuanteService.procurarPorId(id) == null) {
            throw new ResourceNotFoundException("Id de mutuante: " + id + "não foi encontrado");
        }
        if (itemEmprestado.getId() != null) {
            throw new ResourceNotFoundException("Id será gerado pelo banco");
        }
        if (itemEmprestado.getQtdDiasDeDevolucao() <= 1) {
            throw new ResourceNotFoundException("Qtd dias deve ser igual ou superior a 2");
        }
        itemEmprestado.seteStatus(DISPONIVEL);// Setar como disponível ou indisponível if
        Integer idItemEmprestado = gerarId();
        itemEmprestado.setId(idItemEmprestado.toString());
        itemEmprestado.setIdItemEmprestado(idItemEmprestado);
        return itemEmprestadoRepo.save(itemEmprestado);

    }


    @Override
    public void deletarItemEmprestado(String id) {
        if (procurarItemEmprestado(id).isPresent()) {
            itemEmprestadoRepo.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Não foi encontrado item com o id: " + id);
        }
    }

    @Override
    public List<ItemEmprestado> listarItensEmprestados() {

        if (itemEmprestadoRepo.findAll().iterator().hasNext()) {
            return (List<ItemEmprestado>) itemEmprestadoRepo.findAll();
        } else {
            throw new ResourceNotFoundException("Não foi encontrado itens registrados");
        }
    }

    @Override
    public Optional<ItemEmprestado> procurarItemEmprestado(String id) {
        if (itemEmprestadoRepo.findById(id).isPresent()) {
            return itemEmprestadoRepo.findById(id);
        } else {
            throw new ResourceNotFoundException("Item id: " + id + " não foi encontrado.");
        }
    }

    @Override
    public void alterarStatusItemEmprestado(String id, EStatus status) {
        ItemEmprestado obj = procurarItemEmprestado(id).orElseThrow();
        obj.seteStatus(status);
    }

    @Override
    public Integer gerarId() {
        List<ItemEmprestado> itemEmprestadoList = listarItensEmprestados();
        Integer ultimoIdItemEmprestado = 0;
        Optional<ItemEmprestado> itemEmprestadoOpt = itemEmprestadoList.stream().max(Comparator.comparingInt(ItemEmprestado::getIdItemEmprestado));
        if (itemEmprestadoOpt.isPresent()) {
            ultimoIdItemEmprestado = itemEmprestadoOpt.get().getIdItemEmprestado();
        }
        return ultimoIdItemEmprestado + 1;
    }

    @Override
    public void definirDataDeEmprestimo(ItemEmprestado itemEmprestado) {
        itemEmprestado.setDataEmprestimo(LocalDate.now());
    }

    @Override
    public ItemEmprestado atualizarItemEmprestado(ItemEmprestado obj) {
        Optional<ItemEmprestado> novoObj = procurarItemEmprestado(obj.getId());
        atualizarDadoItemEmprestado(novoObj, obj);
        return itemEmprestadoRepo.save(obj);
    }

    @Override
    public void atualizarDadoItemEmprestado(Optional<ItemEmprestado> novoObj, ItemEmprestado obj) {
        if (novoObj.isPresent()) {
            novoObj.get().setName(obj.getName());
            novoObj.get().setDataEmprestimo(obj.getDataEmprestimo());
            novoObj.get().seteStatus(obj.geteStatus());
        } else {
            throw new ResourceNotFoundException("Item emprestado não encontrado para atualizar");
        }
    }

    @Override
    public List<ItemEmprestado> listarItensEmAtraso() {
        List<ItemEmprestado> paraEmprestar = listarItensEmprestados();
/*       List<ItemEmprestado> itensStatusEmprestadoAtrasado = new ArrayList<>();
//        paraEmprestar.forEach(itemEmprestado -> {
//            if (itemEmprestado.geteStatus() == EMPRESTADO) {
//                if (itemEmprestado.getDataEmprestimo().
//                        plusDays(itemEmprestado.getQtdDiasDeDevolucao()).
//                        isBefore(LocalDate.now())) {
//                    itensStatusEmprestadoAtrasado.add(itemEmprestado);
//                }
//
//            }
        });*/

        List<ItemEmprestado> itensStatusEmprestadoAtrasado = paraEmprestar.stream().filter(itemEmprestado -> {
            if (itemEmprestado.geteStatus() == EMPRESTADO && itemEmprestado.getDataEmprestimo().
                    plusDays(itemEmprestado.getQtdDiasDeDevolucao()).isBefore(LocalDate.now())) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        if (itensStatusEmprestadoAtrasado.isEmpty()) {
            throw new ResourceNotFoundException("Não há itens em atraso");
        }
        return itensStatusEmprestadoAtrasado;
    }
}
