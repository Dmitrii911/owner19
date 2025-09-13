package qa.owner.config;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
import java.util.function.Supplier;

import static com.codeborne.selenide.Configuration.baseUrl;

public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfigNew config;

    public WebDriverProvider() {
        this.config = ConfigFactory.create(WebDriverConfigNew.class, System.getProperties());
    }

    @Override
    public WebDriver get() {
        WebDriver driver = createDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createDriver() {
        String browserVersion = config.getBrowserVersion();
        switch (config.getBrowser()) {
            case CHROME -> {
                WebDriverManager.chromedriver()
                        .browserVersion(browserVersion)
                        .setup();
                return new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver()
                        .browserVersion(browserVersion)
                        .setup();
                return new FirefoxDriver();
            }
            default -> throw new RuntimeException("No such driver");
        }
    }


    public WebDriver remoteUrl() {
        if (baseUrl == null) {
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));

            Configuration.browserCapabilities = capabilities;
        }
    }
}
