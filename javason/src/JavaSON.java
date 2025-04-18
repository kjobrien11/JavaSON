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
}
