package APP;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class FetchHtml {
    private static String get_title(String url){
        InputStream response = null;
        try {
            response = new URL(url).openStream();
            Scanner scanner = new Scanner(response);
            String responseBody = scanner.useDelimiter("\\A").next();
            String title=responseBody.substring(responseBody.indexOf("<title>") + 7,responseBody.indexOf("</title>"));
            System.out.println(title);
            return title;

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "Coronavirus Update (Live): 0 Cases and 0 Deaths";
    }

    public static void fetch_html() throws IOException {
        System.setProperty("webdriver.gecko.driver", ".\\bin\\firefox.exe");
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setJavascriptEnabled(false);
        FirefoxOptions firefox = new FirefoxOptions();
        firefox.merge(dc);
        firefox.setHeadless(true);
        WebDriver driver=new FirefoxDriver(firefox);
        driver.get("https://www.worldometers.info/coronavirus/");
        String title = driver.getTitle();
        System.out.println(title);
        driver.close();
        String[] data=title.split(" ");
        int cases=Integer.parseInt(data[3].replace(",",""));
        int deaths=Integer.parseInt(data[6].replace(",",""));
        Varibles.cases_list.add(cases);
        Varibles.deaths_list.add(deaths);
        String pattern = "hh-dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        Varibles.dates_list.add(date);
    }

}
