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
        System.out.println(json);
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
        }else if(isTrueFalseNull(json)){
            return "true";
        }

        return value.toString();
    }

    private boolean isTrueFalseNull(String value){
        return value.contains("null") || value.contains("false") || value.contains("true");
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
}
