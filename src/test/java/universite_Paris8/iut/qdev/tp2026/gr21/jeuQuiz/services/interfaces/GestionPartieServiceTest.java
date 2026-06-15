package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.ElementDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.JoueurDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.impls.GestionPartieServiceImpl;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.utils.exceptions.AucunJoueurException;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.utils.exceptions.AucuneQuestionException;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.entities.dtos.JoueurDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IJoueurService;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.utils.exceptions.AucunJoueurEnregistreException;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.entities.dtos.QuestionnaireDTO;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IQuestionnaireService;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.utils.exceptions.ChargementImpossibleException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GestionPartieServiceTest {
    @Mock
    IJoueurService iJoueurService;

    @Mock
    IQuestionnaireService iQuestionnaireService;

    GestionPartieService gestionPartieService;

    @BeforeEach
    void setUp() throws ChargementImpossibleException, AucunJoueurEnregistreException {

        gestionPartieService = new GestionPartieServiceImpl(iQuestionnaireService, iJoueurService);
    }

    @Test
    public void aucunJoueurException() throws AucunJoueurEnregistreException, ChargementImpossibleException {

        when(iQuestionnaireService.fournirListeQuestionnaires()).thenReturn(List.of(new QuestionnaireDTO()));

        when(iJoueurService.listerJoueurs()).thenThrow(new AucunJoueurEnregistreException("Aucun joueur trouvé"));

        assertThrows(AucunJoueurException.class, () -> gestionPartieService.determinerElementsDispoPourPartie());
    }


    @Test
    public void aucuneQuestionException() throws AucunJoueurEnregistreException, ChargementImpossibleException {

        when(iQuestionnaireService.fournirListeQuestionnaires()).thenThrow(new AucuneQuestionException("aucun questionnaire trouvé"));

        assertThrows(AucuneQuestionException.class, () -> gestionPartieService.determinerElementsDispoPourPartie());
    }

    @Test
    public void determinerElementsDispoPourPartie() throws Exception {

        // préparation
        JoueurDTO joueurDTO = new JoueurDTO();
        QuestionnaireDTO questionnaireDTO = new QuestionnaireDTO();

        when(iJoueurService.listerJoueurs()).thenReturn(List.of(joueurDTO));

        when(iQuestionnaireService.fournirListeQuestionnaires()).thenReturn(List.of(questionnaireDTO));

        ElementDispoDTO resultat = gestionPartieService.determinerElementsDispoPourPartie();

        // résultat des appels
        assertNotNull(resultat);

        // On vérifie que les listes à l'intérieur des DTOs ne sont pas vides
        assertFalse(resultat.getListJoueurDispo().isEmpty(), "La liste des joueurs ne doit pas être vide");
        assertFalse(resultat.getListQuestionnaire().isEmpty(), "La liste des questionnaires ne doit pas être vide");
        assertEquals(1, resultat.getListJoueurDispo().size(), "Il doit y avoir exactement 1 joueur disponible");
        assertEquals(1, resultat.getListQuestionnaire().size(), "Il doit y avoir exactement 1 questionnaire disponible");

    }

}