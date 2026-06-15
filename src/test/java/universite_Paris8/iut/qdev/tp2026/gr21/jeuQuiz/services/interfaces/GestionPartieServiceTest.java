package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.JoueurDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.entities.dtos.JoueurDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IJoueurService;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.utils.exceptions.AucunJoueurEnregistreException;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IQuestionnaireService;

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
    void setUp() {
        // On instancie le service manuellement en lui passant les mocks
        gestionPartieService = new GestionPartieServiceImpl() {
        }
    }

    @Test
    public void aucunJoueurException() throws AucunJoueurEnregistreException {

        JoueurDTO JoueurDTOAttendu = new JoueurDTO();
        when(iJoueurService.listerJoueurs()).thenThrow(new AucunJoueurEnregistreException("Aucun joueur trouvé en"));


        assertThrows(AucunJoueurEnregistreException.class,() -> gestionPartieService.determinerElementsDispoPourPartie());
    }

}