package retcon.parser.patterns;

import retcon.parser.predicate.Predicate;
import retcon.parser.predicate.PredicateMatch;
import retcon.parser.token.InputTokens;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A Pattern is an ordered list of predicates that captures one possible address pattern.
 * Input tokens are matched to pattern predicates 1:1 and so an list of n input tokens can
 * only ever match against a Pattern with the same number of tokens
 */
public class Pattern {

    private String _name;
    private int _numTokens;
    private Predicate[] _predicates;
    private String _patternId;

    public int getNumTokens() {
        return _numTokens;
    }
    public String getPatternId() {
        return _patternId;
    }
    public String getName() { return _name; }

    public Pattern(String name, Predicate[] predicates) {
        _name = name;
        _predicates = predicates;

        //compute the number of input tokens this Pattern will process
        for (Predicate currPredicate : _predicates)
            _numTokens += currPredicate.getNumTokens();

        //compute a unique, human readable name for this pattern based on predicate names
        _patternId = buildPatternId();
    }




    /**
     * check the input tokens against the predicates of this pattern
     */
    public PatternMatch test(InputTokens input, boolean debug) {
        //a pattern cannot match unless it has the same number of tokens as the input
        if (input.getNumTokens() != _numTokens) {
            return (debug) ? new PatternMatch(this, false) : null;
        }

        PatternMatch result = new PatternMatch(this, true);
        for (Predicate p : _predicates) {
            PredicateMatch predicateMatch = p.test(input);
            if (null != predicateMatch) {
                result.addPredicateMatch(predicateMatch);
            } else {
                result.setFirstFailedPredicate(p);
                return (debug) ? result : null;
            }
        }

        result.Success = true;
        return result;
    }

    public static Pattern buildPatternFromString(String s, Map<String, Predicate> predicates)
    {
        String[] parts = s.split(":-");
        String[] predicateNames = parts[1].split(",");

        List<Predicate> patternPredicates = new ArrayList<>();
        for(String currPredName: predicateNames)
        {
            Predicate p = predicates.get(currPredName.toLowerCase().trim());
            patternPredicates.add(p);
        }

        Predicate[] preds = new Predicate[patternPredicates.size()];
        Pattern result = new Pattern(parts[0], patternPredicates.toArray(preds));
        return result;
    }

    private String buildPatternId() {
        boolean firstItem = true;
        StringBuilder sb = new StringBuilder();
        sb.append(_name);
        sb.append(":-");
        for (Predicate currPredicate : _predicates) {
            if (firstItem)
                firstItem = false;
            else
                sb.append(", ");

            sb.append(currPredicate.Name);
            sb.append("/");
            sb.append(currPredicate.getNumTokens());
        }
        return sb.toString();
    }
}