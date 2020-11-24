package br.com.ntendencia.services.impl;

import br.com.ntendencia.config.ModelMapperConfig;
import br.com.ntendencia.domain.ContratoEmprestimo;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuario;
import br.com.ntendencia.dto.ContratoEmprestimoDTO;
import br.com.ntendencia.dto.MutuarioDTO;
import br.com.ntendencia.enums.EStatus;
import br.com.ntendencia.repositories.ContratoEmprestimoRepository;
import br.com.ntendencia.services.ContratoEmprestimoService;
import br.com.ntendencia.services.ItemEmprestadoService;
import br.com.ntendencia.services.MutuarioService;
import br.com.ntendencia.services.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;
import utils.TestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class ContratoEmprestimoServicesImpTest {

    private final ModelMapper mapper = new ModelMapperConfig().mapper();
    protected static final String MOCK_FOLDER = "/mocks/contratoEmprestimo";
    protected static final String ITEM_EMPRESTADO_JSON = "itemEmprestado.json";
    protected static final String MUTUARIO_JSON = "mutuario.json";
    protected static final String CONTRATO_EMPRESTIMO_JSON = "contratoEmprestimo.json";

    @Mock
    private ContratoEmprestimoRepository contratoRepo;
    @Mock
    private ItemEmprestadoService itemService;
    @Mock
    private MutuarioService mutuarioService;

    private ContratoEmprestimoService contratoEmprestimoService() {
        return new ContratoEmprestimoServicesImpl(contratoRepo, itemService, mutuarioService, mapper);
    }
    public static ItemEmprestado getMockObjectItemEmprestado() {
        return TestUtils.getMockObject(MOCK_FOLDER, ITEM_EMPRESTADO_JSON, ItemEmprestado.class);
    }
    public static Mutuario getMockObjectMutuario() {
        return TestUtils.getMockObject(MOCK_FOLDER, MUTUARIO_JSON, Mutuario.class);
    }
    public static ContratoEmprestimo getMockObject() {
        return TestUtils.getMockObject(MOCK_FOLDER, CONTRATO_EMPRESTIMO_JSON, ContratoEmprestimo.class);
    }
    protected ContratoEmprestimo contratoEmprestimo() {
        ContratoEmprestimo contratoEmprestimo = new ContratoEmprestimo();
        contratoEmprestimo.setIdMutuario("1");
        return contratoEmprestimo;
    }

    protected ContratoEmprestimoDTO contratoEmprestimoDTO() {
        ContratoEmprestimoDTO contratoEmprestimoDTO = new ContratoEmprestimoDTO();
        contratoEmprestimoDTO.setId(contratoEmprestimo().getId());
        contratoEmprestimoDTO.setIdMutuario(contratoEmprestimo().getIdMutuario());
        return contratoEmprestimoDTO;
    }

    @Before
    public void setupContratoEmprestimoTest() {
    }

    @Test
    public void testarSalvar() {
        // mocks
        ItemEmprestado itemEmprestadoMock = getMockObjectItemEmprestado();
        MutuarioDTO mutuarioDTO = new MutuarioDTO(getMockObjectMutuario());
        ContratoEmprestimo contratoEmprestimoMock = getMockObject();
        // cenário
        ContratoEmprestimoDTO contratoEmprestimoDTO = contratoEmprestimoDTO();
        contratoEmprestimoDTO.getListaIdsItens().add(itemEmprestadoMock.getId());
        ContratoEmprestimo contratoEmprestimo = mapper.map(contratoEmprestimoDTO, ContratoEmprestimo.class);
        when(contratoRepo.findAll()).thenReturn(Collections.singletonList(contratoEmprestimoMock));
        when(contratoRepo.save(Mockito.any())).thenReturn(contratoEmprestimo);
        when(mutuarioService.findById(Mockito.anyString())).thenReturn(mutuarioDTO);
        when(itemService.procurarItemEmprestado(Mockito.anyString())).thenReturn(itemEmprestadoMock);
        ContratoEmprestimo contratoSalvo = contratoEmprestimoService().contratoEmprestimoSave(contratoEmprestimoDTO);

        // verificação
        Assert.assertEquals(Integer.valueOf(1), contratoSalvo.getIdContrato());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarSalvarFalhaListaVazia() {
        // mocks
        ItemEmprestado itemEmprestadoMock = getMockObjectItemEmprestado();
        MutuarioDTO mutuarioDTO = new MutuarioDTO(getMockObjectMutuario());
        ContratoEmprestimo contratoEmprestimoMock = getMockObject();
        // cenário
        ContratoEmprestimoDTO contratoEmprestimoDTO = contratoEmprestimoDTO();
        contratoEmprestimoDTO.getListaIdsItens();
        ContratoEmprestimo contratoEmprestimo = mapper.map(contratoEmprestimoDTO, ContratoEmprestimo.class);
        when(contratoRepo.findAll()).thenReturn(Collections.singletonList(contratoEmprestimoMock));
        when(contratoRepo.save(Mockito.any())).thenReturn(contratoEmprestimo);
        when(mutuarioService.findById(Mockito.anyString())).thenReturn(mutuarioDTO);
        when(itemService.procurarItemEmprestado(Mockito.anyString())).thenReturn(itemEmprestadoMock);
        ContratoEmprestimo contratoSalvo = contratoEmprestimoService().contratoEmprestimoSave(contratoEmprestimoDTO);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarSalvarFalhaMutuarioNaoEncontrado() {
        // mocks
        ItemEmprestado itemEmprestadoMock = getMockObjectItemEmprestado();
        MutuarioDTO mutuarioDTO = new MutuarioDTO(getMockObjectMutuario());
        ContratoEmprestimo contratoEmprestimoMock = getMockObject();
        // cenário
        ContratoEmprestimoDTO contratoEmprestimoDTO = contratoEmprestimoDTO();
        contratoEmprestimoDTO.getListaIdsItens().add(itemEmprestadoMock.getId());
        ContratoEmprestimo contratoEmprestimo = mapper.map(contratoEmprestimoDTO, ContratoEmprestimo.class);
        when(contratoRepo.findAll()).thenReturn(Collections.singletonList(contratoEmprestimoMock));
        when(contratoRepo.save(Mockito.any())).thenReturn(contratoEmprestimo);
        when(mutuarioService.findById(Mockito.anyString())).thenReturn(null);
        when(itemService.procurarItemEmprestado(Mockito.anyString())).thenReturn(itemEmprestadoMock);
        ContratoEmprestimo contratoSalvo = contratoEmprestimoService().contratoEmprestimoSave(contratoEmprestimoDTO);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarSalvarIdPreenchido() {
        // mocks
        ItemEmprestado itemEmprestadoMock = getMockObjectItemEmprestado();
        MutuarioDTO mutuarioDTO = new MutuarioDTO(getMockObjectMutuario());
        ContratoEmprestimo contratoEmprestimoMock = getMockObject();
        // cenário
        ContratoEmprestimoDTO contratoEmprestimoDTO = contratoEmprestimoDTO();
        contratoEmprestimoDTO.setId("1");
        contratoEmprestimoDTO.getListaIdsItens().add(itemEmprestadoMock.getId());
        ContratoEmprestimo contratoEmprestimo = mapper.map(contratoEmprestimoDTO, ContratoEmprestimo.class);
        when(contratoRepo.findAll()).thenReturn(Collections.singletonList(contratoEmprestimoMock));
        when(contratoRepo.save(Mockito.any())).thenReturn(contratoEmprestimo);
        when(mutuarioService.findById(Mockito.anyString())).thenReturn(mutuarioDTO);
        when(itemService.procurarItemEmprestado(Mockito.anyString())).thenReturn(itemEmprestadoMock);
        ContratoEmprestimo contratoSalvo = contratoEmprestimoService().contratoEmprestimoSave(contratoEmprestimoDTO);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarSalvarItemIndisponivel() {
        // mocks
        ItemEmprestado itemEmprestadoMock = getMockObjectItemEmprestado();
        MutuarioDTO mutuarioDTO = new MutuarioDTO(getMockObjectMutuario());
        ContratoEmprestimo contratoEmprestimoMock = getMockObject();
        // cenário
        itemEmprestadoMock.seteStatus(EStatus.EMPRESTADO);
        ContratoEmprestimoDTO contratoEmprestimoDTO = contratoEmprestimoDTO();
        contratoEmprestimoDTO.getListaIdsItens().add(itemEmprestadoMock.getId());
        ContratoEmprestimo contratoEmprestimo = mapper.map(contratoEmprestimoDTO, ContratoEmprestimo.class);
        when(contratoRepo.findAll()).thenReturn(Collections.singletonList(contratoEmprestimoMock));
        when(contratoRepo.save(Mockito.any())).thenReturn(contratoEmprestimo);
        when(mutuarioService.findById(Mockito.anyString())).thenReturn(mutuarioDTO);
        when(itemService.procurarItemEmprestado(Mockito.anyString())).thenReturn(itemEmprestadoMock);
        ContratoEmprestimo contratoSalvo = contratoEmprestimoService().contratoEmprestimoSave(contratoEmprestimoDTO);
    }


    @Test
    public void testarDeletarSucesso() {
        // cenário
        ContratoEmprestimo contratoEmprestimo = getMockObject();
        when(contratoRepo.findById(Mockito.any())).thenReturn(Optional.of(contratoEmprestimo));
        contratoEmprestimoService().deleteContratoEmprestimo(String.valueOf(contratoEmprestimo.getId()));

        // verificação
        verify(contratoRepo, times(1)).deleteById(String.valueOf(contratoEmprestimo.getId()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarDeletarFalha() {
        // cenário
        ContratoEmprestimo contratoEmprestimo = getMockObject();
        when(contratoRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        contratoEmprestimoService().deleteContratoEmprestimo(String.valueOf(contratoEmprestimo.getId()));
    }
    @Test
    public void testarBuscarTodos() {
        ContratoEmprestimo contratoMock = getMockObject();
        //Mock retorno do find all
        when(contratoRepo.findAll()).thenReturn(Collections.singletonList(contratoMock));
        // cenário
        List<ContratoEmprestimo> list = contratoEmprestimoService().listarTodoscontratosEmprestimo();

        // verificação
        Assert.assertEquals(1, list.size());
    }
    @Test
    public void testarBuscarTodosDTO() {
        ContratoEmprestimo contratoMock = getMockObject();
        ItemEmprestado itemEmprestadoMock = getMockObjectItemEmprestado();
        contratoMock.setId("1");
        contratoMock.setIdContrato(1);
        //Mock retorno do find all
        when(contratoRepo.findAll()).thenReturn(Collections.singletonList(contratoMock));
        when(itemService.procurarItemEmprestado(Mockito.anyString())).thenReturn(itemEmprestadoMock);
        // cenário
        List<ContratoEmprestimoDTO> list = contratoEmprestimoService().listarTodoscontratosEmprestimoDTO();

        // verificação
        Assert.assertEquals(1, list.size());
    }
}