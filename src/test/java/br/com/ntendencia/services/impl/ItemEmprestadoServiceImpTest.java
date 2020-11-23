package br.com.ntendencia.services.impl;

import br.com.ntendencia.config.ModelMapperConfig;
import br.com.ntendencia.domain.ItemEmprestado;
import br.com.ntendencia.domain.Mutuante;
import br.com.ntendencia.dto.ItemEmprestadoDTO;
import br.com.ntendencia.dto.MutuanteDTO;
import br.com.ntendencia.enums.EStatus;
import br.com.ntendencia.repositories.ItemEmprestadoRepository;
import br.com.ntendencia.services.ItemEmprestadoService;
import br.com.ntendencia.services.MutuanteService;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
public class ItemEmprestadoServiceImpTest {

    private final ModelMapper mapper = new ModelMapperConfig().mapper();
    protected static final String MOCK_FOLDER = "/mocks/itemEmprestado";
    protected static final String ITEM_EMPRESTADO_JSON = "itemEmprestado.json";
    protected static final String MUTUANTE_JSON = "mutuante.json";
    protected static final String MUTUANTE_DTO_JSON = "mutuanteDTO.json";


    @Mock
    private ItemEmprestadoRepository itemEmprestadoRepo;

    @Mock
    private MutuanteService mutuanteService;

    private ItemEmprestadoService itemEmprestadoService() {
        return new ItemEmprestadoServicesImpl(itemEmprestadoRepo, mutuanteService, mapper);
    }

    public static ItemEmprestado getMockObject() {
        return TestUtils.getMockObject(MOCK_FOLDER, ITEM_EMPRESTADO_JSON, ItemEmprestado.class);
    }

    public static Mutuante getMockObjectMutuante() {
        return TestUtils.getMockObject(MOCK_FOLDER, MUTUANTE_JSON, Mutuante.class);
    }

    public static MutuanteDTO getMockObjectMutuanteDTO() {
        return TestUtils.getMockObject(MOCK_FOLDER, MUTUANTE_DTO_JSON, MutuanteDTO.class);
    }

    protected ItemEmprestado itemEmprestado() {
        ItemEmprestado itemEmprestado = new ItemEmprestado();
        itemEmprestado.setName("Caixa de som");
        itemEmprestado.setQtdDiasDeDevolucao(2);
        return itemEmprestado;
    }

    protected ItemEmprestadoDTO itemEmprestadoDTO() {
        ItemEmprestadoDTO itemEmprestadoDTO = new ItemEmprestadoDTO();
        itemEmprestadoDTO.setName(itemEmprestado().getName());
        itemEmprestadoDTO.setQtdDiasDeDevolucao(itemEmprestado().getQtdDiasDeDevolucao());
        return itemEmprestadoDTO;
    }

    @Before
    public void setupItemEmprestadoTest() {

    }

    @Test
    public void testarSalvarSucesso() {
        // cenário
        ItemEmprestado itemEmprestadoMock = getMockObject();
        MutuanteDTO mutuanteMockDTO = getMockObjectMutuanteDTO();
        when(itemEmprestadoRepo.findAll()).thenReturn(Collections.singletonList(itemEmprestadoMock));
        when(mutuanteService.procurarPorId(Mockito.any())).thenReturn(mutuanteMockDTO);
        ItemEmprestadoDTO itemEmprestadoDTO = itemEmprestadoDTO();
        ItemEmprestado itemEmprestado = mapper.map(itemEmprestadoDTO, ItemEmprestado.class);
        ItemEmprestado itemEmprestadoSalvo = itemEmprestadoService().salvarItemEmprestado(itemEmprestadoDTO);

        // verificação
        Assert.assertEquals(Integer.valueOf(2), itemEmprestadoSalvo.getIdItemEmprestado());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarSalvarFalhaProcurarID() {
        // cenário
        ItemEmprestado itemEmprestadoMock = getMockObject();
        MutuanteDTO mutuanteMockDTO = getMockObjectMutuanteDTO();
        when(itemEmprestadoRepo.findAll()).thenReturn(Collections.singletonList(itemEmprestadoMock));
        when(mutuanteService.procurarPorId(Mockito.any())).thenReturn(null);
        ItemEmprestadoDTO itemEmprestadoDTO = itemEmprestadoDTO();
        ItemEmprestado itemEmprestado = mapper.map(itemEmprestadoDTO, ItemEmprestado.class);
        ItemEmprestado itemEmprestadoSalvo = itemEmprestadoService().salvarItemEmprestado(itemEmprestadoDTO);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarSalvarFalhaIdPreenchido() {
        // cenário
        ItemEmprestado itemEmprestadoMock = getMockObject();
        MutuanteDTO mutuanteMockDTO = getMockObjectMutuanteDTO();
        when(itemEmprestadoRepo.findAll()).thenReturn(Collections.singletonList(itemEmprestadoMock));
        when(mutuanteService.procurarPorId(Mockito.any())).thenReturn(mutuanteMockDTO);
        ItemEmprestadoDTO itemEmprestadoDTO = itemEmprestadoDTO();
        itemEmprestadoDTO.setId("1");
        ItemEmprestado itemEmprestado = mapper.map(itemEmprestadoDTO, ItemEmprestado.class);
        ItemEmprestado itemEmprestadoSalvo = itemEmprestadoService().salvarItemEmprestado(itemEmprestadoDTO);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarSalvarFalhaQtdDiasDeDevolucaoMenor() {
        // cenário
        ItemEmprestado itemEmprestadoMock = getMockObject();
        MutuanteDTO mutuanteMockDTO = getMockObjectMutuanteDTO();
        when(itemEmprestadoRepo.findAll()).thenReturn(Collections.singletonList(itemEmprestadoMock));
        when(mutuanteService.procurarPorId(Mockito.any())).thenReturn(mutuanteMockDTO);
        ItemEmprestadoDTO itemEmprestadoDTO = itemEmprestadoDTO();
        itemEmprestadoDTO.setQtdDiasDeDevolucao(1);
        ItemEmprestado itemEmprestado = mapper.map(itemEmprestadoDTO, ItemEmprestado.class);
        ItemEmprestado itemEmprestadoSalvo = itemEmprestadoService().salvarItemEmprestado(itemEmprestadoDTO);

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarSalvarFalhaDataDeEmprestimoPreenchida() {
        // cenário
        ItemEmprestado itemEmprestadoMock = getMockObject();
        MutuanteDTO mutuanteMockDTO = getMockObjectMutuanteDTO();
        when(itemEmprestadoRepo.findAll()).thenReturn(Collections.singletonList(itemEmprestadoMock));
        when(mutuanteService.procurarPorId(Mockito.any())).thenReturn(mutuanteMockDTO);
        ItemEmprestadoDTO itemEmprestadoDTO = itemEmprestadoDTO();
        itemEmprestadoDTO.setDataEmprestimo(LocalDate.now());
        ItemEmprestado itemEmprestado = mapper.map(itemEmprestadoDTO, ItemEmprestado.class);
        ItemEmprestado itemEmprestadoSalvo = itemEmprestadoService().salvarItemEmprestado(itemEmprestadoDTO);

    }

    @Test
    public void testarDeletarSucesso() {
        //mocks
        ItemEmprestado itemEmprestadoMock = getMockObject();
        // cenário
        ItemEmprestadoDTO itemEmprestadoDTO = itemEmprestadoDTO();
        itemEmprestadoDTO.setId("1");
        ItemEmprestado itemEmprestado = mapper.map(itemEmprestadoDTO, ItemEmprestado.class);
        when(itemEmprestadoRepo.findById(Mockito.anyString())).thenReturn(Optional.of(itemEmprestado));
        itemEmprestadoService().deletarItemEmprestado(itemEmprestado.getId());
        // verificação
        verify(itemEmprestadoRepo, times(1)).deleteById(String.valueOf(itemEmprestado.getId()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarDeletarFalha() {
        //mocks
        ItemEmprestado itemEmprestadoMock = getMockObject();
        // cenário
        ItemEmprestadoDTO itemEmprestadoDTO = itemEmprestadoDTO();
        itemEmprestadoDTO.setId("1");
        ItemEmprestado itemEmprestado = mapper.map(itemEmprestadoDTO, ItemEmprestado.class);
        when(itemEmprestadoRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        itemEmprestadoService().deletarItemEmprestado(itemEmprestado.getId());

    }

    @Test
    public void testarProcurarItemDTOSucesso() {
        //mocks
        ItemEmprestado itemEmprestadoMock = getMockObject();
        // cenário
        ItemEmprestadoDTO itemEmprestadoDTO = itemEmprestadoDTO();
        itemEmprestadoDTO.setId("1");
        ItemEmprestado itemEmprestado = mapper.map(itemEmprestadoDTO, ItemEmprestado.class);
        when(itemEmprestadoRepo.findById(Mockito.anyString())).thenReturn(Optional.of(itemEmprestado));
        ItemEmprestadoDTO itemEmprestadoEncontrado = itemEmprestadoService().
                procurarItemEmprestadoDTO(itemEmprestadoDTO.getId());
        // verificação
        Assert.assertEquals(String.valueOf(1), itemEmprestadoEncontrado.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarProcurarItemDTOFalha() {
        // cenário
        ItemEmprestadoDTO itemEmprestadoDTO = itemEmprestadoDTO();
        itemEmprestadoDTO.setId("1");
        when(itemEmprestadoRepo.findById(Mockito.anyString())).thenReturn(Optional.empty());
        itemEmprestadoService().procurarItemEmprestadoDTO(itemEmprestadoDTO.getId());
    }

    @Test
    public void testarBuscarTodosSucesso() {
        //mocks
        ItemEmprestado itemEmprestadoMock = getMockObject();
        // cenário
        when(itemEmprestadoRepo.findAll()).thenReturn(Collections.singletonList(itemEmprestadoMock));
        List<ItemEmprestado> list = itemEmprestadoService().listarItensEmprestados();
        // verificação
        Assert.assertEquals(1, list.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarTodosFalha() {
        when(itemEmprestadoRepo.findAll()).thenReturn(Collections.emptyList());
        List<ItemEmprestado> list = itemEmprestadoService().listarItensEmprestados();
    }

    @Test
    public void testarAlterarStatus() {
        //mocks
        ItemEmprestado itemEmprestadoMock = getMockObject();
        itemEmprestadoMock.seteStatus(EStatus.DISPONIVEL);
        ItemEmprestado itemEmprestado = itemEmprestado();
        itemEmprestado.setId("1");
        // cenário
        when(itemEmprestadoRepo.findById(itemEmprestado.getId())).thenReturn(Optional.of(itemEmprestado));
        itemEmprestadoService().alterarStatusItemEmprestado(itemEmprestado.getId(), itemEmprestadoMock.geteStatus());
        // verificação
        Assert.assertEquals(itemEmprestadoMock.geteStatus(), itemEmprestado.geteStatus());
        Assert.assertEquals(itemEmprestadoMock.getId(), itemEmprestado.getId());
    }

    @Test
    public void testarDefinirDataDeEmprestimo() {
        //mocks
        ItemEmprestado itemEmprestadoMock = getMockObject();
        itemEmprestadoMock.seteStatus(EStatus.DISPONIVEL);
        ItemEmprestado itemEmprestado = itemEmprestado();
        itemEmprestado.setId("1");
        // cenário
        itemEmprestadoService().definirDataDeEmprestimo(itemEmprestado);
        // verificação
        Assert.assertEquals(LocalDate.now(), itemEmprestado.getDataEmprestimo());
    }

    @Test
    public void testarAtualizarItemEmprestado() {
        //mocks
        ItemEmprestado itemEmprestadoMock = getMockObject();
        itemEmprestadoMock.seteStatus(EStatus.DISPONIVEL);
        ItemEmprestado itemEmprestado = itemEmprestado();
        itemEmprestado.setId("1");
        // cenário
        when(itemEmprestadoRepo.findById(Mockito.any())).thenReturn(Optional.of(itemEmprestado));
        // verificação
        Assert.assertEquals(LocalDate.now(), itemEmprestado.getDataEmprestimo());
    }

    /*
    @Test
    public void testarSalvar() {
        // cenário
        Mutuario mutuarioMock = Mockito.mock(Mutuario.class);
        //Mock retorno do find all
        when(mutuarioRepo.findAll()).thenReturn(Collections.singletonList(mutuarioMock));
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(0);
        when(mutuarioRepo.save(Mockito.any())).thenReturn(mutuario);
        Mutuario mutuarioSalvo = mutuarioService().salvarMutuario(mutuarioDTO);

        // verificação
        Assert.assertEquals(Integer.valueOf(0), mutuarioSalvo.getIdMutuario()); // não retorna o mutuante salvo dto
    }

    @Test
    public void testarDeletarSucesso() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findById(Mockito.any())).thenReturn(Optional.of(mutuario));
        mutuarioService().deleteMutuario(String.valueOf(mutuario.getIdMutuario()));

        // verificação
        verify(mutuarioRepo, times(1)).deleteById(String.valueOf(mutuario.getIdMutuario()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarDeletarFalha() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        mutuarioService().deleteMutuario(String.valueOf(mutuario.getIdMutuario()));

    }

    @Test
    public void testarBuscarIdSucesso() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findById(Mockito.any())).thenReturn(Optional.of(mutuario));
        MutuarioDTO mutuarioEncontradoDTO = mutuarioService().findById(String.valueOf(mutuario.getIdMutuario()));

        // verificação
        Assert.assertEquals("1", String.valueOf(mutuarioEncontradoDTO.getIdMutuario()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarIdFalha() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findById(Mockito.any())).thenReturn(Optional.empty());
        MutuarioDTO mutuarioEncontradoDTO = mutuarioService().findById(String.valueOf(mutuario.getIdMutuario()));
    }

    @Test
    public void testarBuscarNomeSucesso() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findByName(Mockito.any())).thenReturn(Optional.of(mutuario));
        MutuarioDTO mutuarioEncontradoDTO = mutuarioService().procurarPorNome(mutuario.getName());

        // verificação
        Assert.assertEquals("Mattheus", mutuarioEncontradoDTO.getName());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarNomeFalha() {
        // cenário
        MutuarioDTO mutuarioDTO = mutuarioDTO();
        Mutuario mutuario = mapper.map(mutuarioDTO, Mutuario.class);
        mutuario.setIdMutuario(1);
        when(mutuarioRepo.findByName(Mockito.any())).thenReturn(Optional.empty());
        MutuarioDTO mutuarioEncontradoDTO = mutuarioService().procurarPorNome(mutuario.getName());
    }

    @Test
    public void testarBuscarTodosSucesso() {
        Mutuario mutuarioMock = Mockito.mock(Mutuario.class);
        //Mock retorno do find all
        when(mutuarioRepo.findAll()).thenReturn(Collections.singletonList(mutuarioMock));
        // cenário
        List<Mutuario> list = mutuarioService().listaMutuarios();

        // verificação
        Assert.assertEquals(1, list.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarTodosFalha() {
        // cenário
        List<Mutuario> list = mutuarioService().listaMutuarios();
        when(mutuarioRepo.findAll()).thenReturn(Collections.emptyList());
    }

    @Test
    public void testarBuscarTodosDTOSucesso() {
        Mutuario mutuarioMock = Mockito.mock(Mutuario.class);
        //Mock retorno do find all
        when(mutuarioRepo.findAll()).thenReturn(Collections.singletonList(mutuarioMock));
        // cenário
        List<MutuarioDTO> list = mutuarioService().listaMutuariosDTO();

        // verificação
        Assert.assertEquals(1, list.size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testarBuscarTodosDTOFalha() {
        // cenário
        List<MutuarioDTO> list = mutuarioService().listaMutuariosDTO();
        when(mutuarioRepo.findAll()).thenReturn(Collections.emptyList());
    }

     */
}
