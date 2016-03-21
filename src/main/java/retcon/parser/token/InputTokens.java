package retcon.parser.token;

/**
 * Created by awatkins on 3/21/2016.
 */
public class InputTokens {

    public String[] Tokens;
    public int CurrPos;

    public InputTokens(String[] src) {
        for(int i = 0; i < src.length; i++)
            src[i] = src[i].trim();

        Tokens = src;
        CurrPos = 0;
    }

    public int getNumTokens() {
        return Tokens.length;
    }

    public void resetCurrPos() {
        CurrPos = 0;
    }

    public static InputTokens tokenize(String inputString) {
        inputString = inputString.replaceAll("(?i)NULL","");
        inputString = inputString.replaceAll("\\s+[\\-]+\\s+"," ");
        inputString = inputString.replaceAll("\\.","");
        inputString = inputString.replaceAll(";",",");
        inputString = inputString.replaceAll(",", " , ");
        inputString = inputString.replaceAll(",\\s*,", ",");
        inputString = inputString.trim();
        inputString = inputString.replaceAll("^,+", "");
        inputString = inputString.replaceAll(",$", "");
        inputString = inputString.trim();
        String[] tokens = inputString.split("\\s+");

        InputTokens result = new InputTokens(tokens);
        return result;
    }
}
