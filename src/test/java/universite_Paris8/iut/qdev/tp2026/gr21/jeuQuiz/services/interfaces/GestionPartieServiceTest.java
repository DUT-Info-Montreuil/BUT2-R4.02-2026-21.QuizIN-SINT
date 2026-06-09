package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.interfaces;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IJoueurService;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IQuestionnaireService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GestionPartieServiceTest {


    @Mock
    IJoueurService iJoueurService;

    @Mock
    IQuestionnaireService iQuestionnaireService;

    GestionPartieService objetsATester;

    @Test
    public void aucunJoueurException(){

    }

}