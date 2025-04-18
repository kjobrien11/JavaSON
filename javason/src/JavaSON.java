import java.util.HashMap;
import java.util.Map;

public class JavaSON {

    private String rawJson;
    private Map<String, Object> json;

    public JavaSON(String rawJson) {
        this.rawJson = rawJson;
        json = new HashMap<>();
    }

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    public void parseJson(){
        String key = "";
        for (int i = 0; i < rawJson.length(); i++) {

            if(rawJson.charAt(i) == ' '){
                continue;
            }
//            else if (rawJson.charAt(i) == '"'){
//                int nextQuoteIndex= rawJson.indexOf('"', i + 1);
//                key = rawJson.substring(i + 1, nextQuoteIndex);
//                System.out.println(key);
//                i = nextQuoteIndex;
//            }
            else if (Character.isLetterOrDigit(rawJson.charAt(i))) {
                int index = i;
                StringBuilder value = new StringBuilder();
                while (Character.isLetterOrDigit(rawJson.charAt(index)) || rawJson.charAt(index) == '.'){
                    value.append(rawJson.charAt(index));
                    index++;
                }
                System.out.println(value.toString());
                i = index;

                if (key.isEmpty()) {
                    key = value.toString();
                } else {
                    json.put(key, value.toString());
                    key = "";
                }
            }
        }
        System.out.println(json);
    }

    public String get(String key) {
        return (String) json.get(key);
    }
}
