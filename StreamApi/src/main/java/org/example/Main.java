package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // URL для получения данных о пользователях
        String url = "https://jsonplaceholder.typicode.com/users";

        try {
            // Создаем HTTP-клиент
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            // Выполняем запрос
            CloseableHttpResponse response = httpClient.execute(httpGet);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            // Читаем ответ
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }

            // Закрываем ресурсы
            reader.close();
            response.close();
            httpClient.close();

            // Парсим JSON данные
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> data = objectMapper.readValue(jsonBuilder.toString(), new TypeReference<List<Map<String, Object>>>() {});

            // Фильтруем объекты по условию (например, поле "username" содержит "Bret")
            List<Map<String, Object>> filteredData = data.stream()
                    .filter(item -> ((String) item.get("username")).contains("Bret")) // Замените на ваше условие
                    .collect(Collectors.toList());

            // Выводим результат на консоль
            filteredData.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}