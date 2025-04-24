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


//        String json = "{\"style\": \"red\", \"number\": 125.3, \"$$&*#**  23421 FFD\" : true}";
        String json = "{"
                + "\"name\": \"Alice\", "
                + "\"age\": 30, "
                + "\"address\": {"
                + "\"street\": \"123 Main St\", "
                + "\"city\": \"Wonderland\", "
                + "\"zip\": \"12345\""
                + "}, "
                + "\"active\": true"
                + "}";
        JavaSON javason = new JavaSON(json);
        javason.parseJson();
        boolean testSuite = false;

        String[] jsonTests = {
                "{ \"name\": \"Alice     \", \"age\": 30, \"active\": true }",
                "{ \"city\": \"New York\", \"temperature\": 21.5, \"raining\": false }",
                "{ \"username\": \"user_01\", \"score\": 1000, \"banned\": null }",
                "{ \"flag\": true, \"count\": 0, \"message\": \"Done\" }",
                "{ \"code\": \"X123\", \"valid\": false, \"attempts\": 3 }",
                "{ \"value1\": 1.23e4, \"value2\": 4E-3, \"value3\": 5.67e+2 }",
                "{ \"name\": \"Smith, John\", \"location\": \"New York, NY\" }",
                "{ \"quote\": \"To be, or not to be\", \"author\": \"Shakespeare\" }",
                "{ \"groceries\": \"milk, eggs, bread\", \"status\": \"      pending       \" }"
        };

        if(testSuite){
            for (String test : jsonTests) {
                javason.setRawJson(test);
                javason.parseJson();
                javason.resetJson();
            }
        }






    }

}