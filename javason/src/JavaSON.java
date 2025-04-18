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
        for (int i = 0; i < rawJson.length(); i++) {
            if(rawJson.charAt(i) == '{'){
                continue;
            }else if (rawJson.charAt(i) == '"'){
                char quote = '"';
                int nextIndex = rawJson.indexOf(quote, i + 1);
                String key = rawJson.substring(i + 1, nextIndex);
                System.out.println(key);
                i = nextIndex;
            }
        }
    }
}
