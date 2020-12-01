public class Utils {

    final static String BASE_URL = "https://ruswizard.site/test/";
    /*
        Используйте тот chromedriver, который подходит под вашу систему!
     */
    //final static String CHROME_DRIVER_LOCATION = "chromedriver_mac";
    //final static String CHROME_DRIVER_LOCATION = "chromedriver.exe";
    //final static String CHROME_DRIVER_LOCATION = "chromedriver_linux";

    /*
        Hope it works!
     */
    final static String CHROME_DRIVER_LOCATION = getDriverLocationOS();

    static String getDriverLocationOS() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.toLowerCase().contains("win")) {
            return "chromedriver.exe";
        }
        if (OS.toLowerCase().contains("mac")) {
            return "chromedriver_mac";
        }
        return "chromedriver_linux";
    }
}
