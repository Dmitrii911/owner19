package qa.owner.config;

import org.aeonbits.owner.Config;

import java.net.URL;

public interface WebDriverConfigNew extends Config {

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    Browser getBrowser();

    @Key("version")
    @DefaultValue("latest")// По умолчанию берем последнюю доступную версию
    String getBrowserVersion();

    @Key("remoteUrl")
    @DefaultValue("http://localhost:4444")
    URL getRemoteUrl();
}
