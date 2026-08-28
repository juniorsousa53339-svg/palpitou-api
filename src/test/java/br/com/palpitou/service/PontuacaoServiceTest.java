package br.com.palpitou.service;

import br.com.palpitou.entity.Jogo;
import br.com.palpitou.entity.Palpite;
import br.com.palpitou.entity.User;
import br.com.palpitou.repository.PalpiteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


// OBS: Para testar diretamente o método gerarRankingDaRodada,
// ele precisa estar como public no PontuacaoService.
@ExtendWith(MockitoExtension.class)
public class PontuacaoServiceTest {

    // Dependência simulada: não acessa o banco de dados real
    @Mock
    private PalpiteRepository palpiteRepository;

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

        // ACT: executar a funcionalidade que queremos testar
        Map<Long, Integer> ranking =
                pontuacaoService.gerarRankingDaRodada(1);

        // ASSERT: verificar se o resultado é o esperado
        assertEquals(10, ranking.get(1L));
        assertEquals(0, ranking.get(2L));
    }
}