package retcon.parser;

import retcon.parser.patterns.Pattern;
import retcon.parser.patterns.PatternManager;
import retcon.parser.patterns.PatternMatch;
import retcon.parser.token.InputTokens;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by awatkins on 3/21/2016.
 */
public class QueryParser {
    private List<Pattern> _patterns;

    public QueryParser() {
        _patterns = PatternManager.setupPatterns();
    }

    public QueryParser(String patternsFilename) {
        _patterns = PatternManager.setupPatterns(patternsFilename);
    }

    public QueryParser(List<Pattern> patterns) {
        _patterns = patterns;
    }

    public Geo[] parse(String query) {
        return parse(query, null);
    }

    public Geo[] parse(String query, ParseDebug dbg) {
        //testWebRequest();

        if (null != dbg) {
            dbg.startStopwatch();
        }
        InputTokens tokenizedQuery = InputTokens.tokenize(query);
        List<PatternMatch> allMatches = testPatterns(tokenizedQuery, dbg);
        List<PatternMatch> filteredMatches = discardNonsenseMatches(allMatches);

        if (null != dbg) {
            dbg.stopStopwatch();
            dbg.setInputQuery(query);
            dbg.setInputTokens(tokenizedQuery);
            dbg.buildListOfMatchedPatterns();
        }

        List<Geo> result = new ArrayList<>();
        if (null != filteredMatches)
            for (PatternMatch currMatch : filteredMatches)
                result.add(new Geo(currMatch));

        return result.toArray(new Geo[0]);
    }


    private List<PatternMatch> testPatterns(InputTokens inputTokens, ParseDebug dbg) {
        List<PatternMatch> successfulMatches = new ArrayList<>();

        for (Pattern currPattern : _patterns) {
            inputTokens.resetCurrPos();
            PatternMatch patternMatch = currPattern.test(inputTokens, (dbg != null));

            if ((null != patternMatch) && (patternMatch.Success))
                successfulMatches.add(patternMatch);

            if (null != dbg)
                dbg.addPatternMatchResult(patternMatch);
        }

        return successfulMatches;
    }


    private List<PatternMatch> discardNonsenseMatches(List<PatternMatch> allMatches) {
        List<PatternMatch> result = new ArrayList<>();
        for (PatternMatch currMatch : allMatches)
            if (currMatch.isSensibleMatch())
                result.add(currMatch);

        return result;
    }

    private void testWebRequest() {
        String url = "http://www.google.com";
        System.out.println("about to count chars from this URL: " + url);
        System.out.println(String.format("read this many chars: %d", testWebRequest(url)));

        url = "http://parseresi.geo-dev.moveaws.com/admin/index.html";
        System.out.println("about to count chars from this URL: " + url);
        System.out.println(String.format("read this many chars: %d", testWebRequest(url)));
    }


    private int testWebRequest(String url) {
        try {
            URL aws = new URL(url);
            URLConnection ac = aws.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            ac.getInputStream()));
            String inputLine;

            int numChars = 0;
            while ((inputLine = in.readLine()) != null)
                numChars += inputLine.length();
            in.close();

            return numChars;
        } catch (Exception ex) {
            System.out.println("Got exception in testWebRequest: " + ex.getMessage());
            return -1;
        }
    }
}
