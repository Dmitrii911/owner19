package qa.owner.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:local.properties",
        "classpath:${env}.properties"
})

public interface WebConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String browser();

    @Key("version")
    @DefaultValue("140")// По умолчанию берем последнюю доступную версию
    String getBrowserVersion();

    @Key("remoteUrl")
    String remoteUrl();
}
