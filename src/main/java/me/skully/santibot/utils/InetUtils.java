package me.skully.santibot.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import me.skully.santibot.Main;

import static com.google.common.net.HttpHeaders.USER_AGENT;

public class InetUtils {

    public static void sendTG(String boting) {
        for(int i = 0; Main.getInstance().getConfig().getStringList("telegram.bot.ids").size() > i; i++) {
            try {
                sendTelegramMessage(Main.getInstance().getConfig().getStringList("telegram.bot.ids").get(i), boting);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void sendTelegramMessage(String userId, String text) throws Exception {
        URL obj = new URL("https://api.telegram.org/bot"+ Main.getInstance().getConfig().getString("telegram.bot.token") +"/sendMessage?chat_id="+ userId +"&text="+text+"");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Значение по умолчанию - GET
        con.setRequestMethod("GET");

        // Добавляем заголовок запроса
        con.setRequestProperty("User-Agent", USER_AGENT);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        con.disconnect();
        in.close();

    }

}
