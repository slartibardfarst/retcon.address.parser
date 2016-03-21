package retcon.parser.patterns;

import junit.framework.TestCase;
import org.junit.Test;
import retcon.parser.Geo;
import retcon.parser.ParseDebug;
import retcon.parser.QueryParser;
import retcon.parser.predicate.Predicate;
import retcon.parser.token.InputTokens;

import java.util.List;
import java.util.Map;

/**
 * Created by awatkins on 3/21/2016.
 */
public class PatternManagerTest extends TestCase {

    @Test
    public void testTokenize() throws Exception {
        InputTokens tokenizedQuery = InputTokens.tokenize("Ave, North Vancouver");
        assertEquals(4, tokenizedQuery.getNumTokens());
    }


    @Test
    public void testListRules() throws Exception {

        String s = "";
        s += "address :- city, zip;";

        //terminal symbols
        s += "city :- city/1;";
        s += "city :- city/2;";
        s += "zip  :- zip/1;";

        List<ProductionRule> rules = PatternManager.PatternManagerTestClass.testListRules(s);
        assertEquals(4, rules.size());
    }

    @Test
    public void testExpandRules() throws Exception {

        String s = "";
        s += "address :- city, state, zip;";
        s += "city_geo :- city, state;";

        //terminal symbols
        s += "city :- city/1;";
        s += "city :- city/2;";
        s += "state :- state/1;";
        s += "state :- state/2;";
        s += "zip  :- zip/1;";

        List<Pattern> actualPatterns = PatternManager.PatternManagerTestClass.testListPatterns(s);
        assertEquals(8, actualPatterns.size());
    }

    @Test
    public void testOptionalSymbols1() throws Exception {

        String s = "";
        s += "address_line :- street_number, street, [unit];";

        s += "street :- [street_direction], street_name, street_suffix, [street_post_direction];";

        //terminal symbols
        s += "street_number :- street_no/1;";
        s += "street_name :- street/1;";
        s += "street_name :- street/2;";
        s += "street_suffix :- street_suffix/1;";
        s += "street_direction :- street_direction/1;";
        s += "street_post_direction :- street_post_direction/1;";
        s += "unit :- post_unit/2;";

        List<Pattern> actualPatterns = PatternManager.PatternManagerTestClass.testListPatterns(s);
        assertEquals(16, actualPatterns.size());
    }

    @Test
    public void testOptionalSymbols2() throws Exception {

        String s = "";
        s += "address_line :- street_number, street_name, [unit];";

        //terminal symbols
        s += "street_number :- street_no/1;";
        s += "street_name :- street/1;";
        s += "street_name :- street/2;";
        s += "unit :- post_unit/2;";

        List<Pattern> actualPatterns = PatternManager.PatternManagerTestClass.testListPatterns(s);
        assertEquals(4, actualPatterns.size());
    }

    @Test
    public void testOptionalSymbols3() throws Exception {
        String s = "";
        s += "address :- street_suffix, [street_post_direction], [comma], city;";

        //terminal symbols
        s += "street_suffix :- street_suffix/1;";
        s += "street_post_direction :- street_post_direction/1;";
        s += "city :- city/1;";
        s += "city :- city/2;";
        s += "comma :- comma/1;";

        List<Pattern> actualPatterns = PatternManager.PatternManagerTestClass.testListPatterns(s);
        assertEquals(8, actualPatterns.size());

        QueryParser qp = new QueryParser(actualPatterns);

        ParseDebug dbg = new ParseDebug();
        Geo[] result1 = qp.parse("Ave North Vancouver", dbg);

        Geo[] result2 = qp.parse("Ave, North Vancouver", dbg);
    }

    @Test
    public void testReadAndWriteToFile1() throws Exception {

        String s = "";
        s += "address_line :- street_number, street_name, [unit];";

        //terminal symbols
        s += "street_number :- street_no/1;";
        s += "street_name :- street/1;";
        s += "street_name :- street/2;";
        s += "unit :- post_unit/2;";

        Map<String, Predicate> predicates = PatternManager.PatternManagerTestClass.getPredicates();
        List<Pattern> patterns = PatternManager.PatternManagerTestClass.testListPatterns(s);

        String tempFile = "./patterns.csv";
        PatternManager.writePatternsToFile(tempFile, patterns);
        List<Pattern> rehydratedPatterns = PatternManager.readPatternsFromFile(tempFile, predicates);
        PatternManager.deleteFile(tempFile);
    }

    @Test
    public void testReadAndWriteToFile2() throws Exception {
        Map<String, Predicate> predicates = PatternManager.PatternManagerTestClass.getPredicates();
        List<Pattern> patterns = PatternManager.setupPatterns();

        PatternManager.writePatternsToFile("/users/awatkins/patterns3.csv", patterns);
        List<Pattern> rehydratedPatterns = PatternManager.readPatternsFromFile("/users/awatkins/patterns3.csv", predicates);
    }

}