package tobyspring.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExRatePaymentProvider  implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) throws IOException {
        URL url = new URL(" https://open.er-api.com/v6/latest/"+ currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String response = br.lines().collect(Collectors.joining());

        br.close();

        // api response 에서 필요한 부분만 get -> json 처리할 수 있는 build.gradle 추가
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        return data.rates().get("KRW");
    }
}
