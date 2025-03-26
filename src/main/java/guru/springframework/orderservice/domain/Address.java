package guru.springframework.orderservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Modified by Pierrot on 26-03-2025.
 */
@Embeddable
public class Address {
    @Column(name = "address",insertable = false, updatable = false)
    private String street;
    private String city;
    private String state;
    private String zipCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address1 = (Address) o;

        if (getStreet() != null ? !getStreet().equals(address1.getStreet()) : address1.getStreet() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address1.getCity()) : address1.getCity() != null) return false;
        if (getState() != null ? !getState().equals(address1.getState()) : address1.getState() != null) return false;
        return getZipCode() != null ? getZipCode().equals(address1.getZipCode()) : address1.getZipCode() == null;
    }

    @Override
    public int hashCode() {
        int result = getStreet() != null ? getStreet().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getZipCode() != null ? getZipCode().hashCode() : 0);
        return result;
    }
}
