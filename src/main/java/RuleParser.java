import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.petitparser.parser.Parser;
import org.petitparser.parser.combinators.SettableParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.petitparser.parser.primitive.CharacterParser.*;

public class RuleParser {

    Parser number                           = digit().plus()
            .flatten()
            .trim()
            .map((String value) -> Integer.parseInt(value));

    Parser inOperator                       = letter("I").seq(letter("N"))
            .flatten()
            .trim();

    Parser orOperator                       = of('|').seq(of('|'))
            .flatten()
            .trim();

    Parser andOperator                      = of('&').seq(of('&'))
            .flatten()
            .trim();

    Parser equalFnOperator                  = letter("E").seq(letter("Q"),
            letter("U"),
            letter("A"),
            letter("L"),
            letter("S"))
            .flatten()
            .trim();

    Parser valueOperator                    = letter("V").seq(letter("A"),
            letter("L"),
            letter("U"),
            letter("E"))
            .flatten()
            .trim();

    Parser responseOperator                 = letter("R").seq(letter("E"),
            letter("S"),
            letter("P"),
            letter("O"),
            letter("N"),
            letter("S"),
            letter("E"))
            .flatten()
            .trim();

    Parser concatOperator                   = letter("C").seq(letter("O"),
            letter("N"),
            letter("C"),
            letter("A"),
            letter("T"))
            .flatten()
            .trim();

    Parser delimiter                        = of('/').seq(of(',')
            .or(of('-')
                    .or(of(':'))))
            .flatten()
            .trim();

    Parser  extraxtOperator                 = letter("E").seq(letter("X"),
            letter("T"),
            letter("R"),
            letter("A"),
            letter("C"),
            letter("T"))
            .flatten()
            .trim();
    Parser notNullOperator                  =   letter("N").seq(letter("O"),
            letter("T"),
            letter("N"),
            letter("U"),
            letter("L"),
            letter("L"))
            .flatten()
            .trim();

    Parser valueString                      = letter()
            .or(digit())
            .or(whitespace())
            .or(of('-'))
            .or(of('!'))
            .or(of('@'))
            .or(of('#'))
            .or(of('$'))
            .or(of('%'))
            .or(of('^'))
            .or(of(':'))
            .or(of('?'))
            .or(of('['))
            .or(of(']'))
            .or(of('.')).plus().flatten().trim();

    Parser containsOperator                 =   letter("C").seq(letter("O"),
            letter("N"),letter("T"),letter("A"),letter("I"),letter("N"),(letter("S"))).flatten().trim();
    Parser atIndexOperator                  =   letter("A").seq(letter("T"),letter("I"),letter("N"),letter("D"),letter("E"),letter("X")).flatten().trim();

    Parser lengthOperator                   =   letter("L").seq(letter("E"), letter("N"), letter("G"), letter("T"), letter("H")).flatten().trim();

    Parser fetchAllOperator                 =   letter("F").seq(letter("E"), letter("T"), letter("C"), letter("H"), letter("A"), letter("L"), letter("L")).flatten().trim();
    Parser fieldOperator                            =letter("F").seq(letter("I"),
            letter("E"),
            letter("L"),
            letter("D"))
            .flatten()
            .trim();

    Parser indexOperator                    = letter("I").seq(letter("N"), letter("D"), letter("E"), letter("X")).flatten().trim();
    SettableParser indexFnBlock             = SettableParser.undefined();

    SettableParser fetchAllBlock            = SettableParser.undefined();

    SettableParser lengthFnBlock            = SettableParser.undefined();

    SettableParser containsBlock            = SettableParser.undefined();
    SettableParser atIndexBlock             = SettableParser.undefined();

    SettableParser valueStringCombo         = SettableParser.undefined();
    SettableParser valueStringComma         = SettableParser.undefined();

    SettableParser lt                       = SettableParser.undefined();
    SettableParser btw                      = SettableParser.undefined();
    SettableParser gt                       = SettableParser.undefined();

    SettableParser term                     = SettableParser.undefined();
    SettableParser prod                     = SettableParser.undefined();
    SettableParser prim                     = SettableParser.undefined();

    SettableParser params                   = SettableParser.undefined();
    SettableParser paramComma               = SettableParser.undefined();
    SettableParser paramsCombination        = SettableParser.undefined();
    SettableParser in                       = SettableParser.undefined();
    SettableParser equality                 = SettableParser.undefined();
    SettableParser grammarCombination       = SettableParser.undefined();
    SettableParser combAndParser            = SettableParser.undefined();
    SettableParser combOrParser             = SettableParser.undefined();
    SettableParser boundParser              = SettableParser.undefined();
    SettableParser ifOperator               = SettableParser.undefined();
    SettableParser thenOperator             = SettableParser.undefined();
    SettableParser elseOperator             = SettableParser.undefined();
    SettableParser ifBlock                  = SettableParser.undefined();
    SettableParser ifElseParser             = SettableParser.undefined();
    SettableParser equalityFn              = SettableParser.undefined();
    SettableParser equalityFnBlock          = SettableParser.undefined();
    SettableParser responseOf              = SettableParser.undefined();
    SettableParser responseOfFnBlock        = SettableParser.undefined();
    SettableParser valueOfFnBlock           = SettableParser.undefined();
    SettableParser fieldBlock               = SettableParser.undefined();
    SettableParser concatBlock              = SettableParser.undefined();
    SettableParser extractBlock             = SettableParser.undefined();
    SettableParser notEqualityFnBlock       = SettableParser.undefined();
    SettableParser notNullFunctionBlock     = SettableParser.undefined();

    Parser numParser;

    public RuleParser()
    {
        params.set(letter().or(digit()).plus().flatten().trim());
        paramComma.set(params.seq(of(',')).flatten().trim());
        paramsCombination.set(paramComma.plus().seq(params).or(paramComma.plus()).flatten().trim());


        responseOf.set(letter("R").seq(letter("E"),letter("S"),letter("P"),letter("O") ,letter("N") ,letter("S"), letter("E")).flatten().trim());
        responseOfFnBlock.set(responseOperator.seq(of('('), params, of(')')));



        equalityFn.set(letter("E").seq(letter("Q"),letter("U"),letter("A"),letter("L") ,letter("S")).flatten().trim());

        ifOperator.set(letter("I").seq(letter("F"))
                .flatten()
                .trim());

        ifBlock.set(ifOperator.seq(of('('),combOrParser.end(), of(')')));

        thenOperator.set(letter("T")
                .seq(letter("H"),
                        letter("E"),
                        letter("N"))
                .flatten()
                .trim());

        elseOperator.set(letter("E").seq(letter("L"),
                letter("S"),
                letter("E"))
                .flatten()
                .trim());



        concatBlock.set(concatOperator.seq(of('('), paramComma.plus(),params, of(')')));

    }//end constructor


    ///
    //setFieldFunctionBlock
    ///
    public void setFieldFunctionBlock(JSONObject jsonObject)
    {


        fieldBlock.set(fieldOperator.seq(of('('),valueString.plus().flatten(),of(')')).map((List<String> values) ->
        {



            String path [] = values.get(2).split("\\.");
            JSONObject outerJson = jsonObject;
            JSONObject innerJSON = jsonObject;
            try {
                for (int pathIndex = 0; pathIndex < path.length - 1; pathIndex++) {
                    outerJson = innerJSON;

                    if (path[pathIndex].contains("["))
                    {
                        String key = path[pathIndex].substring(0,path[pathIndex].indexOf("["));
                        int index = Integer.parseInt(path[pathIndex].substring(path[pathIndex].indexOf("[")+1, path[pathIndex].indexOf("]")));

                        JSONArray jsonArray = null;
                        jsonArray =  outerJson.optJSONArray(key);

                        if (jsonArray == null)
                            jsonArray = new JSONArray(outerJson.getString(key));

                        innerJSON = jsonArray.getJSONObject(index);


                    }
                    else
                    {
                        String value = outerJson.optString(path[pathIndex]);

                        if (value != null)
                            innerJSON = new JSONObject(value);
                        else
                            innerJSON = outerJson.getJSONObject(path[pathIndex]);

                    }

                }

                if (path[path.length - 1].contains("["))
                {
                    String key = path[path.length - 1].substring(0,path[path.length - 1].indexOf("["));
                    int index = Integer.parseInt(path[path.length - 1].substring(path[path.length - 1].indexOf("[")+1, path[path.length - 1].indexOf("]")));

                    JSONArray jsonArray = null;
                    jsonArray =  outerJson.optJSONArray(key);

                    if (jsonArray == null)
                        jsonArray = new JSONArray(outerJson.getString(key));

                    if (jsonArray.opt(index) == null)
                        return "null";

                    return jsonArray.get(index).toString();
                }
                else
                {
                    if (innerJSON.opt(path[path.length - 1]) == null)
                        return "null";

                    return innerJSON.get(path[path.length - 1]).toString();
                }

            }
            catch (JSONException jse)
            {
                jse.printStackTrace();
                return "null";
            }


        }));

    }//end setFieldFunctionBlock


    private void  setContainsFunctionBlock(JSONObject jsonObject)
    {
        containsBlock.set(containsOperator.seq(of('('),
                valueString,
                valueString,
                of(')'))
                .map((List<String> values) ->{
                    try {
                        JSONArray jsonArray = jsonObject.getJSONArray(values.get(2));
                        for (int index = 0; index <jsonArray.length(); index++)
                        {
                            if (jsonArray.getString(index).equals(values.get(3)))
                                return true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return false;
                    }

                    return false;
                }));
    }


    private void setAtIndexFunctionBlock(JSONObject jsonObject)
    {
        atIndexBlock.set(atIndexOperator.seq(of('('),
                valueString,
                number,
                of(')'))
                .map((List<String> values) -> {
                    try {
                        JSONArray jsonArray = jsonObject.getJSONArray(values.get(2));
                        return jsonArray.getString(Integer.parseInt(values.get(3)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return "null";
                    }
                }));
    }


    private void lengthFunctionBlock(JSONObject jsonObject)
    {
        lengthFnBlock.set(lengthOperator.seq(of('('),
                valueString,
                of(')'))
                .map((List<String> values) ->{
                    try {
                        JSONArray jsonArray = null;
                        jsonArray =  jsonObject.optJSONArray(values.get(2));
                        if (jsonArray == null)
                            jsonArray = new JSONArray(jsonObject.getString(values.get(2)));
                        return jsonArray.length();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        return 0;
                    }
                }));
    }

    private void  setNotNullFunctionBlock(JSONObject jsonObject)
    {
        notNullFunctionBlock.set(notNullOperator.seq(of('('),fieldBlock.or(valueString).or(params),of(')')).map((List<String> value) ->{
            return !(value.get(2).equals("null") || value.get(2).equals("") || value.get(2).equals(" "));
        }));
    }


    private List<String> evaluateListExpression(String ruleString, JSONObject jsonObject)
    {
        fetchAllBlock.set(fetchAllOperator.seq(of('('),valueString.seq(of(',')).plus().flatten().trim(),delimiter.star().flatten(), of(')'))
                .map((List<String> values) ->{
                    List<String> resultList = new ArrayList<>();

                    String[] tokens = values.get(2).split(",");

                    String delim = "";
                    if (values.get(3).contains("/"))
                        delim = values.get(3).replace("/","");

                    String output = "";
                    try {
                        JSONArray jsonArray = jsonObject.getJSONArray(values.get(2).replace(",",""));
                        for(int index = 0; index < jsonArray.length(); index++)
                        {
                            JSONObject currentJSON = jsonArray.getJSONObject(index);
                            output = "";
                            for (String token : tokens) {
                                output += currentJSON.get(token).toString() + delim;
                                resultList.add(output);
                            }

                        }

                        return resultList;

                    } catch (JSONException e) {
                        e.printStackTrace();
                        return  resultList;

                    }

                })
                .or(fetchAllOperator.seq(of('('),valueString,of(','),of(')'))
                        .map((List<String> values)->{

                            List<String> resultList = new ArrayList<>();
                            try {
                                JSONArray jsonArray = jsonObject.getJSONArray(values.get(2).replace(",",""));
                                for(int index = 0; index < jsonArray.length(); index++)
                                {
                                    resultList.add(jsonArray.get(index).toString());
                                }

                                return resultList;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                return  resultList;

                            }

                        }))
        );

        return fetchAllBlock.parse(ruleString).get();
    }



    private void setEqualitFunctionBlock()
    {
        equalityFnBlock.set(equalFnOperator.seq(of('('),
                responseOfFnBlock.or(fieldBlock).or(valueString).or(params),
                of(','),
                responseOfFnBlock.or(fieldBlock).or(valueString).or(params),
                of(')'))
                .map((List<String> values) -> {
                    return values.get(2).trim().equals(values.get(4).trim());
                }));

    }//end mapEqualitFunctionBlock


    ///
    //setConbimationAndOrBlock
    ///
    private void setConbimationAndOrBlock()
    {
        grammarCombination.set(in.or(equality).or(btw).or(lt).or(gt).or(equalityFnBlock).or(notEqualityFnBlock).or(notNullFunctionBlock));

        combOrParser.set(combAndParser.trim().seq(orOperator).seq(combOrParser).trim()
                .map((List<Boolean>values) ->{
                    return values.get(0) || values.get(2);
                }).or(combAndParser));

        combAndParser.set(boundParser.seq(andOperator).seq(combAndParser).trim()
                .map((List<Boolean>values) ->{
                    return values.get(0) && values.get(2);
                }).or(boundParser));

        boundParser.set((of('(').trim().seq(combOrParser).seq(of(')').trim())).map((List<Boolean>values) ->{
            return values.get(1);
        }).or(grammarCombination));

    }//end setConbimationAndOrBlock


    ///
    //mapResponseFunctionBlockToString
    ///
    private void setResponseFunctionBlockToString(JSONObject jsonObject)
    {
        responseOfFnBlock.set(responseOperator.seq(of('('), valueString.or(params) , of(')')).map((List<String> values)->{
            String response = null;
            try {
                response = jsonObject.getString(values.get(2));
            }catch (JSONException jse){}

            if (response != null)
                return response;
            return values.get(2);
        }));

    }//end mapResponseFunctionBlockToString


    ///
    //mapValueOfFunctionBlockToString
    ///
    private void setValueOfFunctionBlockToString(JSONObject jsonObject)
    {
        valueOfFnBlock.set(valueOperator.seq(of('('), valueString.or(params), of(')')).map((List<String> values)->{
            String result = null;


            result = jsonObject.optString(values.get(2));
//            Log.e("mTest", "result value"+result);

            if (result != null) return result;

            return "null";
        }));


        valueStringComma.set(valueOfFnBlock.seq(of(',')).flatten().trim());

        valueStringCombo.set(valueStringComma.plus().seq(valueOfFnBlock).or(valueOfFnBlock));
    }//end mapValueOfFunctionBlockToString


    ///
    //evaluating arithmetic expression
    ///
    public int evaluateArithmeticExpression(String arithmeticExpression)
    {
        term.set(prod.seq(of('+').trim()).seq(term).map((List<Integer> values) -> {
            return values.get(0) + values.get(2);
        }).or(prod));

        prod.set(prim.seq(of('*').trim()).seq(prod).map((List<Integer> values) -> {
            return values.get(0) * values.get(2);
        }).or(prim));

        prim.set((of('(').trim().seq(term).seq(of(')').trim()).map((List<Integer> values) -> {
            return values.get(1);
        })).or(number));

        Parser numParser = term.end();

        return numParser.parse(arithmeticExpression).get();

    }//end evaluateArithmeticExpression



    ///
    //evaluating arithmetic expression
    ///
    public   void setArithmeticExpression()
    {
        term.set(prod.seq(of('+').trim()).seq(term).map((List<Integer> values) -> {
            return values.get(0) + values.get(2);
        }).or(prod));

        prod.set(prim.seq(of('*').trim()).seq(prod).map((List<Integer> values) -> {
            return values.get(0) * values.get(2);
        }).or(prim));

        prim.set((of('(').trim().seq(term).seq(of(')').trim()).map((List<Integer> values) -> {
            return values.get(1);
        })).or(number));



    }//end evaluateArithmeticExpression


    ///
    //mapConcatOperator
    ///
    private void  setConcatOperator()
    {

        concatBlock.set(concatOperator.seq(of('('),
                fieldBlock.trim().seq(of(',')).map((List<String> values)-> values.get(0))
                        .or(extractBlock.trim().seq(of(',')).map((List<String> values)-> values.get(0)))
                        .or(indexFnBlock.trim().seq(of(',')).map((List<String> values)-> values.get(0)))
                        .or(valueString.seq(of(',')).map((List<String> values)-> values.get(0)))
                        .plus().map((List<String> values)->{
                    String output = "";
                    for(String currentValue : values)
                        output = output + currentValue+",";

                    return output;
                }),
                delimiter.star().flatten(),
                of(')'))
                .map((List<String> values) ->{

                    String tokens []  = values.get(2).split(",");

                    String delim = "";
                    if (values.get(3).contains("/"))
                        delim = values.get(3).replace("/","");

                    String output = "";
                    for (int index = 0; index < tokens.length; index++)
                    {
                        if (index < tokens.length -1)
                            output = output+tokens[index]+delim;
                        else
                            output = output + tokens[index];
                    }



                    return output;

                }));
    }//end evaluateConcatOperator


    ///
    //setIfElseOperator
    ///
    private void  setIfElseOperator()
    {


        numParser = term.end().map((Integer value) -> String.valueOf(value));

        ifBlock.set(ifOperator.seq(of('('), boundParser.map((Boolean result)-> String.valueOf(result)), of(','),
                concatBlock.or(fieldBlock).or(extractBlock).or(numParser).or(responseOfFnBlock).or(ifElseParser),of(')'))
                .map((List <String> values)->{

                            if (Boolean.parseBoolean(values.get(2)))
                                return values.get(4);
                            else
                                return "null";

                        }
                ));




        //.or(ifElseParser)



        ifElseParser.set(ifOperator.seq(of('('), boundParser.map((Boolean result)-> String.valueOf(result)), of(','),
                concatBlock.or(fieldBlock).or(extractBlock).or(numParser).or(responseOfFnBlock).or(ifElseParser),
                of(','),
                concatBlock.or(fieldBlock).or(extractBlock).or(numParser).or(responseOfFnBlock).or(ifElseParser),
                of(')'))
                .map((List <String> values)->{

                    if (Boolean.parseBoolean(values.get(2)))
                        return values.get(4);
                    else
                        return values.get(6);

                }).or(ifBlock));


    }//end setIfElseOperator


    private void setExtractionBlock()
    {
        extractBlock.set(extraxtOperator.seq(of('(').trim(), valueString.or(params),of(')'))
                .map((List<String> values)->{
                    return values.get(2);
                }));


    }


    private void setNotEqualitFunctionBlock()
    {
        notEqualityFnBlock.set(notEqualFnOperator.seq(of('('),
                responseOfFnBlock.or(fieldBlock).or(valueString).or(params),
                of(','),
                responseOfFnBlock.or(fieldBlock).or(valueString).or(params),
                of(')'))
                .map((List<String> values) -> (!(values.get(2).trim().equals(values.get(4).trim())))));

    }

    Parser notEqualFnOperator = letter("N").seq(letter("O"), letter("T"), letter("E"), letter("Q"),
            letter("U"),
            letter("A"),
            letter("L"),
            letter("S"))
            .flatten()
            .trim();



    ///
    //evaluate expression and returns boolean result
    ///
    public boolean evaluateBoolean(String expresion,JSONObject jsonObject)
    {
        //2 < 3
        lt.set(number.seq(of('<').trim(),
                number)
                .map((List<Integer> values) ->{
                    return values.get(0) < values.get(2);
                }));

        //3 > 2
        gt.set(number.seq(of('>').trim(),
                number)
                .map((List<Integer> values) ->{
                    return values.get(0) > values.get(2);
                }));


        //2 < 3 < 5
        btw.set(number.seq(of('<').trim()
                ,number
                , of('<').trim(),number)
                .map((List<Integer> values) ->
                {
                    return (values.get(0) < values.get(2))
                            && (values.get(2) < values.get(4));
                }));

        //"(($A.answer<25
        in.set(inOperator.seq(of('('),
                paramsCombination ,
                of(')').trim())
                .map((List<String> values) ->
                        {
                            String debracketed = values.get(2).replace("[", "").replace("]", "");
                            String trimmed = debracketed.replaceAll("\\s+", "");
                            ArrayList<String> paramValues = new ArrayList<String>(Arrays.asList(trimmed.split(",")));



                            for (String value : paramValues)
                                if (value.equals(paramValues.get(0))
                                        && paramValues.indexOf(value) != 0)
                                    return true;

                            return false;
                        }

                ));

        setFieldFunctionBlock(jsonObject);
        setExtractionBlock();
        setResponseFunctionBlockToString(jsonObject);
        setConcatOperator();
        setNotNullFunctionBlock(jsonObject);
        setNotEqualitFunctionBlock();
        setEqualitFunctionBlock();
        setConbimationAndOrBlock();
        setIfElseOperator();

        Parser finalParser = combOrParser.or(grammarCombination);


        return finalParser.parse(expresion).get();

    }//end evaluateBoolean


    private void setIndexFunctionBlock()
    {
        indexFnBlock.set(indexOperator.seq(of('('), valueString,of(','), valueString, of(')')).map((List<String> values) ->{


            if (values.get(2).contains("."))
            {
                String [] paramAryList = values.get(2).split(".");

                for (String params : paramAryList)
                {
                    if (params.contains(values.get(4)))
                    {
                        return String.valueOf(Integer.parseInt(params.substring(params.indexOf("[")+1, params.indexOf("]")))+1)      ;
                    }
                }
            }
            else{
                return String.valueOf(Integer.parseInt(values.get(2).substring(values.get(2).indexOf("[")+1, values.get(2).indexOf("]"))) +1)  ;


            }

            return "-1";

        }));
    }
    ///
    //evaluate the expression and returns the string value
    ///
    public String evaluateExpression(String expression, JSONObject jsonObject)
    {
        setIndexFunctionBlock();
        lengthFunctionBlock(jsonObject);
        setFieldFunctionBlock(jsonObject);
        setResponseFunctionBlockToString(jsonObject);
        setExtractionBlock();
        setEqualitFunctionBlock();
        setNotEqualitFunctionBlock();
        setNotNullFunctionBlock(jsonObject);
        setConcatOperator();
        setConbimationAndOrBlock();
        setIfElseOperator();
        setArithmeticExpression();


        SettableParser stringEvaulator = SettableParser.undefined();
        stringEvaulator.set(ifElseParser.
                or(lengthFnBlock)
                .or(notEqualityFnBlock)
                .or(equalityFnBlock)
                .or(concatBlock)
                .or(fieldBlock)
                .or(responseOfFnBlock)
                .or(indexFnBlock)
                .or(numParser)
                .or(extractBlock)
                .or(notNullFunctionBlock));



        return ""+stringEvaulator.parse(expression).get();

    }//end evaluateExpression


    public static void main(String [] args)
    {

    }
}