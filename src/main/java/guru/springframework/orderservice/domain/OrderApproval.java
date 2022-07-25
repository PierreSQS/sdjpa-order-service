package guru.springframework.orderservice.domain;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class OrderApproval extends BaseEntity{
    private String approvedBy;

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderApproval that = (OrderApproval) o;

        return Objects.equals(approvedBy, that.approvedBy);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (approvedBy != null ? approvedBy.hashCode() : 0);
        return result;
    }
}
