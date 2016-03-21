package retcon.parser.predicate;

import retcon.parser.token.InputTokens;

/**
 * Created by awatkins on 3/21/2016.
 */
public class Predicate {

    private enum eWhichPred {

        P1,
        P2,
        P3,
        INVALID
    };

    public String Name;
    private eWhichPred whichPred = eWhichPred.INVALID;
    private Pred_1 pred1;
    private Pred_2 pred2;
    private Pred_3 pred3;

    public Predicate(String name, Pred_1 p) {
        Name = name;
        whichPred = eWhichPred.P1;
        pred1 = p;
        pred2 = null;
        pred3 = null;
    }

    public Predicate(String name, Pred_2 p) {
        Name = name;
        whichPred = eWhichPred.P2;
        pred1 = null;
        pred2 = p;
        pred3 = null;
    }

    public Predicate(String name, Pred_3 p) {
        Name = name;
        whichPred = eWhichPred.P3;
        pred1 = null;
        pred2 = null;
        pred3 = p;
    }

    public int getNumTokens() {
        switch (whichPred) {
            case P1:
                return 1;
            case P2:
                return 2;
            case P3:
                return 3;
        }
        return 0;
    }

    public PredicateMatch test(InputTokens tokens) {
        boolean matched = false;
        int numTokens = 0;

        switch (whichPred) {
            case P1:
                numTokens = 1;
                matched = pred1.test(tokens.Tokens[tokens.CurrPos]);
                break;

            case P2:
                numTokens = 2;
                matched = pred2.test(tokens.Tokens[tokens.CurrPos], tokens.Tokens[tokens.CurrPos + 1]);
                break;

            case P3:
                numTokens = 3;
                matched = pred3.test(tokens.Tokens[tokens.CurrPos], tokens.Tokens[tokens.CurrPos + 1], tokens.Tokens[tokens.CurrPos + 2]);
                break;
        }

        if (matched) {
            PredicateMatch result = new PredicateMatch(Name, tokens, tokens.CurrPos, numTokens);
            tokens.CurrPos += numTokens;
            return result;
        }

        return null;
    }
}
