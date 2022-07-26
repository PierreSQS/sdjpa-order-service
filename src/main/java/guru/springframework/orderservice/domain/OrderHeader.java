package guru.springframework.orderservice.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

/**
 * Modified by Pierrot on 7/26/22.
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

    @OneToMany(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<OrderLine> orderLines;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    @OneToOne(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "order_approval_id")
    private OrderApproval orderApproval;

    public OrderApproval getOrderApproval() {
        return orderApproval;
    }

    public void setOrderApproval(OrderApproval orderApproval) {
        this.orderApproval = orderApproval;
        orderApproval.setOrderHeader(this);
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
        if (orderLines == null) {
            orderLines = new HashSet<>();
        }
        return orderLines;
    }

    public void addOrderLines(OrderLine... newOrderLines) {
        if (orderLines == null) {
            orderLines = new HashSet<>();
        }
        Arrays.stream(newOrderLines).forEach(newOrderLine -> {
            orderLines.add(newOrderLine);
            newOrderLine.setOrderHeader(this);
        });

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderHeader that)) return false;
        if (!super.equals(o)) return false;

        if (getCustomer() != null ? !getCustomer().equals(that.getCustomer()) : that.getCustomer() != null)
            return false;
        if (getShippingAddress() != null ? !getShippingAddress().equals(that.getShippingAddress()) : that.getShippingAddress() != null)
            return false;
        if (getBillToAddress() != null ? !getBillToAddress().equals(that.getBillToAddress()) : that.getBillToAddress() != null)
            return false;
        if (getOrderStatus() != that.getOrderStatus()) return false;
        return getOrderLines() != null ? getOrderLines().equals(that.getOrderLines()) : that.getOrderLines() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + (getShippingAddress() != null ? getShippingAddress().hashCode() : 0);
        result = 31 * result + (getBillToAddress() != null ? getBillToAddress().hashCode() : 0);
        result = 31 * result + (getOrderStatus() != null ? getOrderStatus().hashCode() : 0);
        result = 31 * result + (getOrderLines() != null ? getOrderLines().hashCode() : 0);
        return result;
    }
}
