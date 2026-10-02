package br.com.palpitou.service;

import br.com.palpitou.dto.request.PalpiteRequest;
import br.com.palpitou.dto.response.PalpiteResponse;
import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Palpite;
import br.com.palpitou.entity.User;
import br.com.palpitou.mapper.PalpiteMapper;
import br.com.palpitou.repository.JogoRepository;
import br.com.palpitou.repository.PalpiteRepository;
import br.com.palpitou.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PalpiteServiceTest {

    @Mock
    private PalpiteRepository palpiteRepository;

    @Mock
    private JogoRepository jogoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PalpiteMapper palpiteMapper;

    @InjectMocks
    private PalpiteService palpiteService;

    @Test
    public void deveSalvarPalpiteComSucesso() {
        // ARRANGE
        Jogo jogo = new Jogo();
        jogo.setId(1L);
        jogo.setDataHora(LocalDateTime.now().plusHours(2));

        User user = new User();
        user.setId(10L);
        user.setNome("Maria");

        PalpiteRequest request = new PalpiteRequest();
        request.setJogoId(1L);
        request.setUserId(10L);
        request.setGolsMandante(2);
        request.setGolsVisitante(1);

        Palpite palpite = new Palpite();
        palpite.setId(5L);
        palpite.setGolsMandante(2);
        palpite.setGolsVisitante(1);
        palpite.setJogo(jogo);
        palpite.setUser(user);

        PalpiteResponse responseEsperado = new PalpiteResponse();
        responseEsperado.setGolsMandante(2);
        responseEsperado.setGolsVisitante(1);
        responseEsperado.setUserId(10L);

        when(jogoRepository.findById(1L)).thenReturn(Optional.of(jogo));
        when(userRepository.findById(10L)).thenReturn(Optional.of(user));
        when(palpiteRepository.existsByUserIdAndJogoId(10L, 1L)).thenReturn(false);
        when(palpiteMapper.toEntity(request, jogo, user)).thenReturn(palpite);
        when(palpiteRepository.save(palpite)).thenReturn(palpite);
        when(palpiteMapper.toResponse(palpite)).thenReturn(responseEsperado);

        // ACT
        PalpiteResponse response = palpiteService.salvar(request);

        // ASSERT
        assertNotNull(response);
        assertEquals(2, response.getGolsMandante());
        assertEquals(1, response.getGolsVisitante());
        assertEquals(10L, response.getUserId());
    }

    @Test
    public void deveListarTodosOsPalpites() {
        // ARRANGE
        Jogo jogo = new Jogo();
        jogo.setId(1L);
        jogo.setDataHora(LocalDateTime.now().plusHours(3));

        User user = new User();
        user.setId(20L);

        Palpite palpite = new Palpite();
        palpite.setId(7L);
        palpite.setGolsMandante(1);
        palpite.setGolsVisitante(0);
        palpite.setJogo(jogo);
        palpite.setUser(user);

        PalpiteResponse response = new PalpiteResponse();
        response.setGolsMandante(1);
        response.setGolsVisitante(0);
        response.setUserId(20L);

        when(palpiteRepository.findAll()).thenReturn(List.of(palpite));
        when(palpiteMapper.toResponse(palpite)).thenReturn(response);

        // ACT
        List<PalpiteResponse> respostas = palpiteService.listarTodos();

        // ASSERT
        assertNotNull(respostas);
        assertEquals(1, respostas.size());
        assertEquals(1, respostas.get(0).getGolsMandante());
    }
}

