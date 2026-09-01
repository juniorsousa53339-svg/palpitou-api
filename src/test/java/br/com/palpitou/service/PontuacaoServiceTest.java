package br.com.palpitou.service;

import br.com.palpitou.dto.RankingResponse;
import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Palpite;
import br.com.palpitou.entity.User;
import br.com.palpitou.mapper.RankingMapper;
import br.com.palpitou.repository.PalpiteRepository;
import br.com.palpitou.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


// OBS: Para testar diretamente o método gerarRankingDaRodada,
// ele precisa estar como public no PontuacaoService.
@ExtendWith(MockitoExtension.class)
public class PontuacaoServiceTest {

    // Dependência simulada: não acessa o banco de dados real
    @Mock
    private PalpiteRepository palpiteRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RankingMapper rankingMapper;

    // Classe que será testada, recebendo as dependências simuladas
    @InjectMocks
    private PontuacaoService pontuacaoService;

    @Test
    public void deveGerarRankingDaRodadaOrdenadoPorPontuacao() {

        // ARRANGE: preparar os dados necessários para o cenário
        Jogo jogo = new Jogo();
        jogo.setRodada(1);
        jogo.setGolsMandante(2);
        jogo.setGolsVisitante(1);

        User usuario1 = new User();
        usuario1.setId(1L);

        User usuario2 = new User();
        usuario2.setId(2L);

        Palpite palpite1 = new Palpite();
        palpite1.setGolsMandante(2);
        palpite1.setGolsVisitante(1);
        palpite1.setJogo(jogo);
        palpite1.setUser(usuario1);

        Palpite palpite2 = new Palpite();
        palpite2.setGolsMandante(0);
        palpite2.setGolsVisitante(1);
        palpite2.setJogo(jogo);
        palpite2.setUser(usuario2);

        // Configurar as respostas esperadas do Repository simulado
        when(
                palpiteRepository.findByJogoRodada(1)
        ).thenReturn(
                List.of(palpite1, palpite2)
        );

        when(
                palpiteRepository.findByJogoRodadaAndUserId(
                        1,
                        usuario1.getId()
                )
        ).thenReturn(
                List.of(palpite1)
        );

        when(
                palpiteRepository.findByJogoRodadaAndUserId(
                        1,
                        usuario2.getId()
                )
        ).thenReturn(
                List.of(palpite2)
        );

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(usuario1));

        when(userRepository.findById(2L))
                .thenReturn(Optional.of(usuario2));

        // ACT: executar a funcionalidade que queremos testar
        Map<Long, Integer> ranking =
                pontuacaoService.gerarRankingDaRodada(1);

        // ASSERT: verificar se o resultado é o esperado
        assertEquals(10, ranking.get(1L));
        assertEquals(0, ranking.get(2L));
    }

    @Test
    public void deveBuscarRankingDaRodada() {

        // ARRANGE
        Jogo jogo = new Jogo();
        jogo.setRodada(1);
        jogo.setGolsMandante(2);
        jogo.setGolsVisitante(1);

        User usuario1 = new User();
        usuario1.setId(1L);
        usuario1.setNome("Luciano");

        User usuario2 = new User();
        usuario2.setId(2L);
        usuario2.setNome("João");

        Palpite palpite1 = new Palpite();
        palpite1.setGolsMandante(2);
        palpite1.setGolsVisitante(1);
        palpite1.setJogo(jogo);
        palpite1.setUser(usuario1);

        Palpite palpite2 = new Palpite();
        palpite2.setGolsMandante(0);
        palpite2.setGolsVisitante(1);
        palpite2.setJogo(jogo);
        palpite2.setUser(usuario2);

        // PalpiteRepository
        when(palpiteRepository.findByJogoRodada(1))
                .thenReturn(List.of(palpite1, palpite2));

        when(palpiteRepository.findByJogoRodadaAndUserId(1, 1L))
                .thenReturn(List.of(palpite1));

        when(palpiteRepository.findByJogoRodadaAndUserId(1, 2L))
                .thenReturn(List.of(palpite2));

        // UserRepository
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(usuario1));

        when(userRepository.findById(2L))
                .thenReturn(Optional.of(usuario2));

        // RankingMapper
        RankingResponse response1 = new RankingResponse();
        response1.setNome("Luciano");
        response1.setPontuacao(10);
        response1.setPosicao(1);

        RankingResponse response2 = new RankingResponse();
        response2.setNome("João");
        response2.setPontuacao(0);
        response2.setPosicao(2);

        when(rankingMapper.toResponse(usuario1, 10, 1))
                .thenReturn(response1);

        when(rankingMapper.toResponse(usuario2, 0, 2))
                .thenReturn(response2);

        // ACT
        List<RankingResponse> ranking =
                pontuacaoService.buscarRankingDaRodada(1);

        // ASSERT
        assertEquals(2, ranking.size());

        assertEquals("Luciano", ranking.get(0).getNome());
        assertEquals(10, ranking.get(0).getPontuacao());
        assertEquals(1, ranking.get(0).getPosicao());

        assertEquals("João", ranking.get(1).getNome());
        assertEquals(0, ranking.get(1).getPontuacao());
        assertEquals(2, ranking.get(1).getPosicao());
    }
}