package utils;

import java.io.IOException;
import java.util.Properties;

public enum ApplicationProperties {

    INSTANCE;

    private final Properties properties;

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public String getCustomerEmail() {
        return properties.getProperty("customerEmail");
    }

    public String getMasterCardNo() {
        return properties.getProperty("masterCardNo");
    }

    public String getDateAndMonth() {
        return properties.getProperty("dateAndMonth");
    }

    public String getCVV() {
        return properties.getProperty("cvv");
    }

    public String getZipCode() {
        return properties.getProperty("zipCode");
    }


}
