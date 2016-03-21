package retcon.parser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

/**
 * Created by awatkins on 3/21/2016.
 */
public class LambdaWrapper {

    public ParseResponse HandleParseRequest(ParseRequest request, Context context) {
        LambdaLogger logger = context.getLogger();

        ParseResponse response = new ParseResponse();
        return response;
    }

    public class ParseRequest{

    }

    public class ParseResponse{

    }
}
