package guru.springframework.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Objects;
import java.util.Set;

@Entity
public class Customer extends BaseEntity {
    private String contactInfo;

    @OneToMany(mappedBy = "customer",targetEntity = OrderHeader.class)
    private Set<OrderHeader> orderHeaders;

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Set<OrderHeader> getOrderHeaders() {
        return orderHeaders;
    }

    public void setOrderHeaders(Set<OrderHeader> orderHeaders) {
        this.orderHeaders = orderHeaders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        return Objects.equals(contactInfo, customer.contactInfo);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (contactInfo != null ? contactInfo.hashCode() : 0);
        return result;
    }
}
