package config;
import org.openqa.selenium.WebDriver;
import pages.myLoft;

public class PageObjectManager {
    private WebDriver driver;
    private myLoft myLoftpage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public myLoft getMyLoft() {
        return (myLoftpage == null) ? myLoftpage = new myLoft(driver) : myLoftpage;
    }
}

