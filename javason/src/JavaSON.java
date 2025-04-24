import java.util.HashMap;
import java.util.Map;

public class JavaSON {

    private String rawJson;
    private Map<String, Object> json;
    private record ValueAndPostion(String value, int position) {}

    public JavaSON(String rawJson) {
        this.rawJson = rawJson;
        json = new HashMap<>();
    }

    public void parseJson(){
        String key = "";
        String value = "";
        String[] keyValuePairs = splitKeyValuePairs(rawJson);
        for (int i = 0; i < keyValuePairs.length; i++) {
            key = extractKey(keyValuePairs[i].split(":")[0]);
            value = extractValue(keyValuePairs[i].split(":")[1]);
            json.put(key, value);
        }
        System.out.println(json);
    }

    private String[] splitKeyValuePairs(String json){
        return json.split(",");
    }

    private String extractKey(String json){

        StringBuilder value = new StringBuilder();
        int index = json.indexOf('"')+1;
        while (index < json.length() && json.charAt(index) != '"') {
            value.append(json.charAt(index));
            index++;
        }
        return value.toString();
    }

    private String extractValue(String json){
        StringBuilder value = new StringBuilder();
        int index = json.indexOf('"')+1;

        //value is a string so parse it as is
        if(index != 0){
            while (index < json.length() && json.charAt(index) != '"') {
                value.append(json.charAt(index));
                index++;
            }
            return value.toString();
        //value is true, false, or null so return that value
        }else if(isTrueFalseNull(json)){
            return trueFalseNullValue(json);
        // value is a number so return the trimmed version
        }else{
            return extractNumber(json);
        }
        //TODO add support for objects and arrays
    }

    private String extractNumber(String json){
        int index = 0;
        StringBuilder value = new StringBuilder();
        while (index < json.length()) {
            if(Character.isDigit(json.charAt(index)) || json.charAt(index) == '-' || json.charAt(index) == '.' || json.charAt(index) == 'E' || json.charAt(index) == 'e') {
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
