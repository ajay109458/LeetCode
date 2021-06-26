package challenges;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONChallenge {

    public static void main(String[] args) throws IOException{

        String jsonString = getUsersJSONString(USERS_URL);

        List<Map<String, String>> result = getUsersPropertyMap(jsonString);

        System.out.println(result);

    }

    public static List<Map<String, String>> getUsersPropertyMap(String jsonData) {
        List<Object> records =  new Gson().fromJson(jsonData, List.class);
        List<Map<String, String>> result = new ArrayList<>();

        for(Object obz : records) {
            result.add(flattenMap("", obz));
        }
        return result;
    }

    private static Map<String, String> flattenMap(String key, Object data) {

        Map<String, String> result = new HashMap<>();

        if (data instanceof LinkedTreeMap) {
            LinkedTreeMap<String, Object> propertyMap = (LinkedTreeMap)data;

            for(Map.Entry<String, Object> entry : propertyMap.entrySet()) {

                String keyValue = ("".equals(key)) ? entry.getKey() : key + "." + entry.getKey();

                Map<String, String> tempMap = flattenMap(keyValue, entry.getValue());

                if (tempMap.isEmpty()) {
                    result.put(keyValue, entry.getValue().toString());
                } else {
                    for(Map.Entry<String, String> e : tempMap.entrySet()) {
                        result.put(e.getKey(), e.getValue());
                    }
                }
            }
        }

        return result;
    }


    private static String USERS_URL = "https://raw.githubusercontent.com/arcjsonapi/ApiSampleData/master/api/users";

    public static String getUsersJSONString(String connectionUrl) throws IOException, IOException {
        URL url = new URL(connectionUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader ioStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(ioStreamReader);

            StringBuffer buffer = new StringBuffer();

            String line;

            while((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();

            return buffer.toString();
        }

        return null;
    }

}
