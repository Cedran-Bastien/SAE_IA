package Defis2.ia.framework.recherche;

import Defis2.ia.framework.common.Action;
import Defis2.ia.framework.common.BaseProblem;
import Defis2.ia.framework.common.State;

import java.util.ArrayList;

/**
* Représente un problème de recherche. 
* <p> Cette classe hérite de {@link ia.framework.common.BaseProblem}
* en lui ajoutant le but à chercher et le coût des actions.  
*/

public abstract class SearchProblem extends BaseProblem {
    
    /** 
     * Test si état final (but)
     * @param s Un état à tester
     * @return Vrai si c'est un but
     */
    public abstract boolean isGoalState(State s);

    /**
     * Retourne le coût de faire une action dans un état. L'action n'est pas exécutée. 
     * @param s L'état en question 
     * @param a L'action en question
     * @return Le coût 
     */
    public abstract double getActionCost(State s, Action a);
}
