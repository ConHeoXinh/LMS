package longND.fpt.home.modal;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity
@Table(name = "orderbook")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Column(name = "order_items")
	private List<OrderItem> orderItems;

	private LocalDateTime checkoutDate;

	private LocalDateTime returnDate;

	private double totalPrice;

	private double totalDiscountedPrice;

	private double discounte;

	private boolean orderStatus;

	private int totalItem;

	private LocalDateTime createdAt;

//	@ManyToOne
//	@JoinColumn(name = "employee_id", referencedColumnName = "id")
//	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private User employee;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "voucher_id", referencedColumnName = "id")
//	private Voucher voucher;
}
