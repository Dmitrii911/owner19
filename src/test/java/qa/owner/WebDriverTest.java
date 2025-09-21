package qa.owner;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebDriverTest extends TestBase {

    @Test
    public void testGitHub() {
        open("/");
        String title = $("#hero-section-brand-heading").getText();
        assertEquals("Build and ship software on a single, collaborative platform", title);
    }
}
