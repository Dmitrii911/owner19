package qa.owner;

import com.codeborne.selenide.Configuration;
import com.google.common.base.Strings;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa.owner.config.WebConfig;

import java.util.Map;

public class TestBase {

    static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
    @BeforeAll
    public static void configureEnv () {

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.browser();

        String remoteUrl = config.remoteUrl();
        if (!Strings.isNullOrEmpty(remoteUrl)) {
            Configuration.remote = remoteUrl;
            Configuration.browserVersion = config.getBrowserVersion();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}