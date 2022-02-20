package interview.utils.constants;

public enum BrowserTypes {

    CHROME("chrome"), IE("ie"), FIREFOX("firefox"), OPERA("opera");

    private String browser;

    BrowserTypes(String browser){ this.browser = browser; }

    public String getBrowser() {
        return browser;
    }
}
