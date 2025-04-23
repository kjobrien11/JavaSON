import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws Exception {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://reqres.in/api/users?page=1"))
//                .build();
//
//        HttpResponse<String> response = client.send(request,
//                HttpResponse.BodyHandlers.ofString());
//
//        System.out.println("Status code: " + response.statusCode());
//        System.out.println("Response body: " + response.body());
//        String json = "{\"color style\": \"red\", \"number\": 12, \"active\": true, \"name\": \"KJ OBrien\", \"GPA\": 3.90}";
        String json = "{\"style\": \"red\", \"number\": 125.3, \"$$&*#**  23421 FFD\" : true}";
        JavaSON javason = new JavaSON(json);
        javason.parseJson();

        String json1 = "{ \"name\": \"Alice\", \"age\": 30, \"active\": true }";
        String json2 = "{ \"city\": \"New York\", \"temperature\": 21.5, \"raining\": false }";
        String json3 = "{ \"username\": \"user_01\", \"score\": 1000, \"banned\": null }";
        String json4 = "{ \"flag\": true, \"count\": 0, \"message\": \"Done\" }";
        String json5 = "{ \"code\": \"X123\", \"valid\": false, \"attempts\": 3 }";
        javason.setRawJson(json1);
        javason.parseJson();
        javason.resetJson();
        javason.setRawJson(json2);
        javason.parseJson();
        javason.resetJson();
        javason.setRawJson(json3);
        javason.parseJson();
        javason.resetJson();
        javason.setRawJson(json4);
        javason.parseJson();
        javason.resetJson();
        javason.setRawJson(json5);
        javason.parseJson();
        javason.resetJson();




    }

}