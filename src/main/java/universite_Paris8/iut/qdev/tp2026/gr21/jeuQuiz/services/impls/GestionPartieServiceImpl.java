package universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.impls;

import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.ElementDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.JoueurDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.entities.dtos.QuestionnaireDispoDTO;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.services.interfaces.GestionPartieService;
// À remplacer plus tard par la vraie interface du SI Joueur
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.utils.exceptions.AucuneQuestionException;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuiz.utils.exceptions.ChargementImpossibleException;
import universite_Paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IJoueurService;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.entities.dtos.QuestionnaireDTO;
import universite_paris8.iut.qdev.tp2026.gr21.jeuQuizz.services.interfaces.IQuestionnaireService;

import java.util.ArrayList;
import java.util.List;

public class GestionPartieServiceImpl implements GestionPartieService {

    // On utilise maintenant LA VRAIE interface du SI Questionnaire
    private IQuestionnaireService questionnaireService;
    private final IJoueurService siJoueur;

    public GestionPartieServiceImpl(IQuestionnaireService questionnaireService, IJoueurService siJoueur) {
        this.questionnaireService = questionnaireService;
        this.siJoueur = siJoueur;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ElementDispoDTO determinerElementsDispo() {

        ElementDispoDTO elementDispoDTO = new ElementDispoDTO();

        // =========================================================
        // ETAPE 1 : APPEL DU SI METIER QUESTIONNAIRE
        // =========================================================

        List<QuestionnaireDTO> listQuestionnairesBruts = null;

        try {
            // On appelle la vraie méthode
            listQuestionnairesBruts = questionnaireService.fournirListeQuestionnaires();
        } catch (ChargementImpossibleException e) {
            // Si le SI n'arrive pas à charger, on lève notre exception métier SINT
            throw new AucuneQuestionException("Il n'y a pas de question : charger un fichier de question");
        }

        // Si la liste est nulle ou vide (même si le chargement a réussi)
        if (listQuestionnairesBruts == null || listQuestionnairesBruts.isEmpty()) {
            throw new AucuneQuestionException("Il n'y a pas de question : charger un fichier de question");
        }

        // MAPPING : On convertit les QuestionnaireDTO (du SI) en QuestionnaireDispoDTO (de notre SINT)
        List<QuestionnaireDispoDTO> listQuestionnairesDispo = new ArrayList<>();

        for (QuestionnaireDTO q : listQuestionnairesBruts) {
            QuestionnaireDispoDTO qDTO = new QuestionnaireDispoDTO();
            // J'utilise ici les noms supposés de vos Getters dans QuestionnaireDTO
            // Adaptez-les si les attributs s'appellent différemment dans QuestionnaireDTO
            qDTO.setIdQuestionnair(q.getIdQuestionnair());
            qDTO.setLangue(q.getLangue());
            qDTO.setNbQstSimple(q.getNbQstSimple());
            qDTO.setNbQstIntermediaire(q.getNbQstIntermediaire());
            qDTO.setNbQstExpert(q.getNbQstExpert());

            listQuestionnairesDispo.add(qDTO);
        }

        // On affecte la liste convertie à notre objet final
        elementDispoDTO.setListQuestionnaire(listQuestionnairesDispo);


        // =========================================================
        // ETAPE 2 : APPEL DU SI JOUEUR
        // =========================================================

        List<JoueurDispoDTO> listJoueurs =
                (List<JoueurDispoDTO>) siJoueur.listerJoueur();

        // Vérification avec notre exception personnalisée
        if (listJoueurs == null || listJoueurs.isEmpty()) {
            throw new AucunJoueurException("Il n'y a aucun joueur : créer un joueur pour lancer une partie");
        }

        elementDispoDTO.setListJoueurDispo(listJoueurs);

        // =========================================================
        // FIN DU FLUX
        // =========================================================

        return elementDispoDTO;
    }

    @Override
    public ElementDispoDTO determinerElementsDispoPourPartie() {
        return null;
    }
}