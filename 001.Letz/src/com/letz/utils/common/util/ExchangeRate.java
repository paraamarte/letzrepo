/**
 * @Class Name : ExchangeRate.java
 * @Description : 일본, 미국, 중국의 현재일 환율 정보 조회
 *              SingleTon Design Pattern 이용
 * @author UZEN / BILL
 * @since 2016. 4. 14.
 * @version 1.0
 * @see Copyright(c) 2016 신세계인터내셔널. All rights reserved
 */

package com.sivillage.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sivillage.common.constant.ConfigKeys;

public class ExchangeRate {

    private static final Logger log = LoggerFactory.getLogger(ExchangeRate.class);

    private volatile static ExchangeRate instance;
    private static Map<String, String> exchangeRateMap = new HashMap<String, String>();

    private static long startl = System.currentTimeMillis();
    private static long ONE_HOUR = 1L;

    private ExchangeRate() {
    }

    public static ExchangeRate getInstance() {
        if (instance == null) {
            synchronized (ExchangeRate.class) {
                if (instance == null) {
                    instance = new ExchangeRate();
                }
            }
        }

        return instance;
    }

    public static void getExchangeRate() {
        String url = ConfigUtil.getString(ConfigKeys.EXCHANGE_RATE_URL);
        String param = "?toCurrency=28&fromAmount=1&toAmount=&currencyType=1&refreshCharts=false&dateConvert=&fromCurrency=";
        url += param;

        if (oneHourElapsed()) { // 1시간단위로 환율정보 갱신
            for (String currencyNation : ConfigUtil.getList(ConfigKeys.EXCHANGE_RATE_CURRENCYS)) {
                String[] temps = currencyNation.split("/");
                String currency = temps[0];
                String nation = temps[1];

                try {
                    HttpClient client = HttpClientBuilder.create().build();
                    HttpGet request = new HttpGet(url + currency);
                    // request.addHeader("accept", "application/json");
                    request.addHeader("X-Requested-With", "XMLHttpRequest");
                    request.addHeader("User-Agent", "Mozilla/5.0");
                    HttpResponse response = client.execute(request);
                    String json = IOUtils.toString(response.getEntity().getContent()).trim();
                    log.debug("json = " + json);

                    JSONObject object = new JSONObject(json.substring(json.indexOf("{")));
                    exchangeRateMap.put(nation, object.getString("calculatedAmount"));
                    log.debug("calculatedAmount = " + object.getString("calculatedAmount"));

                } catch (Exception ex) {
                    log.error(ex.toString());
                }
            }
        }
    }

    private static boolean oneHourElapsed() {
        long endl = System.currentTimeMillis();

        long diff = (endl - startl) / (1000 * 60 * 60);

        if (diff > ONE_HOUR) {
            startl = endl;
            return true;
        }

        return false;
    }
}
