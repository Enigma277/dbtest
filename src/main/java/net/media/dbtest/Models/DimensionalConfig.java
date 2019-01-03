package net.media.dbtest.Models;

public class DimensionalConfig {

    public String countryCode = null;
    public String customerRegistrationId = null;
    public String device = null;
    public String domain = null;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCustomerRegistrationId() {
        return customerRegistrationId;
    }

    public void setCustomerRegistrationId(String customerRegistrationId) {
        this.customerRegistrationId = customerRegistrationId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "DimensionalConfig{" +
                "countryCode='" + countryCode + '\'' +
                ", customerRegistrationId='" + customerRegistrationId + '\'' +
                ", device='" + device + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
