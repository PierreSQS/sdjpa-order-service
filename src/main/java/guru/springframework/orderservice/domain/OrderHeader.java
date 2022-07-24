package guru.springframework.orderservice.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.*;

/**
 * Modified by Pierrot on 7/24/22.
 */
@Entity
@AttributeOverride(
        name = "shippingAddress.address",
        column = @Column(name = "shipping_address")
)
@AttributeOverride(
        name = "shippingAddress.city",
        column = @Column(name = "shipping_city")
)
@AttributeOverride(
        name = "shippingAddress.state",
        column = @Column(name = "shipping_state")
)
@AttributeOverride(
        name = "shippingAddress.zipCode",
        column = @Column(name = "shipping_zip_code")
)
@AttributeOverride(
        name = "billToAddress.address",
        column = @Column(name = "bill_to_address")
)
@AttributeOverride(
        name = "billToAddress.city",
        column = @Column(name = "bill_to_city")
)
@AttributeOverride(
        name = "billToAddress.state",
        column = @Column(name = "bill_to_state")
)
@AttributeOverride(
        name = "billToAddress.zipCode",
        column = @Column(name = "bill_to_zip_code")
)
public class OrderHeader extends BaseEntity {

    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billToAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderHeader", cascade = CascadeType.PERSIST)
    private Set<OrderLine> orderLines;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public void addOrderLine(OrderLine... newOrderLines) {
        if (orderLines == null) {
            orderLines = new HashSet<>();
        }

        Arrays.stream(newOrderLines).forEach(orderLine -> {
            orderLines.add(orderLine);
            orderLine.setOrderHeader(this);
        });
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(Address billToAddress) {
        this.billToAddress = billToAddress;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderHeader that = (OrderHeader) o;

        if (!Objects.equals(shippingAddress, that.shippingAddress))
            return false;
        if (!Objects.equals(billToAddress, that.billToAddress))
            return false;
        return orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        result = 31 * result + (billToAddress != null ? billToAddress.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }
}
