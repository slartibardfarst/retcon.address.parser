package retcon.parser.predicate;

import retcon.parser.token.InputTokens;

import java.util.Arrays;

/**
 * Created by awatkins on 3/21/2016.
 */
public class PredicateMatch {
    public String Name;
    public String[] Tokens;

    public PredicateMatch(String name, InputTokens inputTokens, int index, int len)
    {
        Name = name;
        Tokens = Arrays.copyOfRange(inputTokens.Tokens, index, index+len);
    }
}
