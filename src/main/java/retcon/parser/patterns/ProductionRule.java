package retcon.parser.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by awatkins on 3/21/2016.
 */
public class ProductionRule {
    private String _id;
    private String _name;
    private List<String> _symbols;
    private boolean _isTerminal;
    private boolean _isTopLevelRule;
    private static Pattern _pattern = Pattern.compile("\\[*\\s*(\\w+)\\s*\\]*");

    public String getName() {
        return _name;
    }

    public boolean getIsTerminal() {
        return _isTerminal;
    }

    public String getId() {
        return _id;
    }

    public void setIsTopLevel(boolean isTopLevel) {
        _isTopLevelRule = isTopLevel;
    }

    public boolean getIsTopLevel() {
        return _isTopLevelRule;
    }

    public List<String> getSymbols() {
        return _symbols;
    }

    public void setSymbols(List<String> symbols) {
        _symbols = new ArrayList<>();
        for (String currSymbol : symbols)
            _symbols.add(currSymbol.trim());

        _isTerminal = true; //default
        for (String currSymbol : _symbols)
            if (!isSymbolTerminal(currSymbol)) {
                _isTerminal = false;
                break;
            }

        regenerateRuleId();
    }

    public ProductionRule(ProductionRule src) {
        _name = src.getName().trim();
        _isTerminal = src._isTerminal;
        _isTopLevelRule = src._isTopLevelRule;
        setSymbols(src.getSymbols());
    }

    public ProductionRule(String name, String[] symbols) {
        this(name, Arrays.asList(symbols));
    }

    public ProductionRule(String name, List<String> symbols) {
        _name = name.trim();
        _isTopLevelRule = false;
        setSymbols(symbols);
    }

    public boolean isOptionalSymbol(String s)
    {
        return s.matches("\\[\\w+\\]");
    }

    public boolean containsOptionSymbols()
    {
        if(null != _symbols)
            for(String currSymbol: _symbols)
                if(isOptionalSymbol(currSymbol))
                    return true;

        return false;
    }

    public boolean nthSymbolIsOptional(int n)
    {
        if((null != _symbols) && (_symbols.size() > n))
            return isOptionalSymbol(_symbols.get(n));

        return false;
    }

    public void removeNthSymbol(int n)
    {
        if((null != _symbols) && (_symbols.size() > n))
            _symbols.remove(n);

        regenerateRuleId();
    }

    public void makeNthSymbolNotOptional(int n)
    {
        if((null != _symbols) && (_symbols.size() > n)) {
            String symbol = _symbols.get(n);

            Matcher matcher = _pattern.matcher(symbol);
            if(matcher.find())
                _symbols.set(n, matcher.group(1));
        }

        regenerateRuleId();
    }

    public static boolean isSymbolTerminal(String symbol) {
        return symbol.contains("/");
    }

    private void regenerateRuleId() {
        StringBuilder sb = new StringBuilder();
        sb.append(_name.toLowerCase().trim());
        sb.append(":");

        boolean first = true;
        for (String currSymbol : _symbols) {
            if (true == first)
                first = false;
            else
                sb.append(",");

            sb.append(currSymbol.toLowerCase().trim());
        }

        _id = sb.toString();
    }

}

