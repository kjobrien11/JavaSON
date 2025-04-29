import java.util.*;

public class JavaSON {

    private String rawJson;
    private Map<String, Object> json;

    public JavaSON(String rawJson) {
        this.rawJson = rawJson;
        json = new HashMap<>();
    }

    public void parseJson() {
        String key = "";
        String value = "";
        String[] keyValuePairs = splitKeyValuePairs(rawJson);
        for (String keyValuePair : keyValuePairs) {
            int splitValue = keyValuePair.indexOf(":");
            key = extractKey(keyValuePair.substring(0, splitValue));
            value = extractValue(keyValuePair.substring(splitValue));
            json.put(key, value);
        }
        System.out.println(json);
    }

    private String[] splitKeyValuePairs(String json) {
        List<String> pairs = new ArrayList<>();
        boolean inString = false;
        boolean inObject = false;
        StringBuilder current = new StringBuilder();

        for (int i = 1; i < json.length() - 1; i++) {
            char currentChar = json.charAt(i);
            if (currentChar == '\\' && i + 1 < json.length() && json.charAt(i + 1) == '"') {
                current.append(currentChar).append(json.charAt(++i));
                continue;
            }
            if (currentChar == '"') {
                inString = !inString;
            }

            if (currentChar == '{') {
                inObject = true;
            }

            if (currentChar == '}') {
                inObject = false;
            }


            if (currentChar == ',' && !inString && !inObject) {
                pairs.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(currentChar);
            }
        }
        if (!current.isEmpty()) {
            pairs.add(current.toString().trim());
        }
//        for (String pair : pairs) {
//            System.out.println(pair);
//        }
        return pairs.toArray(new String[0]);
    }

    private String extractKey(String json) {

        StringBuilder value = new StringBuilder();
        int index = json.indexOf('"') + 1;
        while (index < json.length() && json.charAt(index) != '"') {
            value.append(json.charAt(index));
            index++;
        }
        return value.toString();
    }

    private String extractValue(String json) {
        StringBuilder value = new StringBuilder();
        int index = json.indexOf('"') + 1;

        //value is a string so parse it as is
        if (index != 0) {
            while (index < json.length() && json.charAt(index) != '"') {
                value.append(json.charAt(index));
                index++;
            }
            return value.toString();
            //value is true, false, or null so return that value
        }else if (isTrueFalseNull(json)) {
            return trueFalseNullValue(json);
            // value is a number
        }else {
            return extractNumber(json);
        }
        //TODO add support for objects and arrays
    }


    private String extractNumber(String json){
        int index = 0;
        StringBuilder value = new StringBuilder();
        while (index < json.length()) {
            if(Character.isDigit(json.charAt(index)) || json.charAt(index) == '-' || json.charAt(index) == '+' || json.charAt(index) == '.' || json.charAt(index) == 'E' || json.charAt(index) == 'e') {
                value.append(json.charAt(index));
            }
            index++;
        }
        return value.toString();
    }

    private boolean isTrueFalseNull(String value){
        StringBuilder valueBuilder = new StringBuilder();
        for(int i = 0; i < value.length(); i++){
            if(Character.isAlphabetic(value.charAt(i))){
                valueBuilder.append(value.charAt(i));
            }
        }
        return valueBuilder.toString().equals("null") || valueBuilder.toString().equals("false") || valueBuilder.toString().equals("true");
    }
    private String trueFalseNullValue(String value){
        StringBuilder valueBuilder = new StringBuilder();
        for(int i = 0; i < value.length(); i++){
            if(Character.isAlphabetic(value.charAt(i))){
                valueBuilder.append(value.charAt(i));
            }
        }
        if(valueBuilder.toString().equals("null") || valueBuilder.toString().equals("false") || valueBuilder.toString().equals("true")){
            return valueBuilder.toString();
        }else{
            return "SOMETHING WENT WRONG IN NULL TRUE FALSE";
        }
    }

    public String get(String key) {
        return (String) json.get(key);
    }

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    public void resetJson(){
        json = new HashMap<>();
    }
}
