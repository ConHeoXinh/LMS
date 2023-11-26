package longND.fpt.home.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import longND.fpt.home.modal.Order;
import longND.fpt.home.repository.OrderRepository;
import longND.fpt.home.util.JavaMail;

@Service
public class ReturnBookEventListener {

	@Autowired
	private OrderRepository orderRepo;

	private final int DAY_NEED_RETURN = 1;

	private final String SUBJECT_EMAIL = "LMS - DEADLINE TO RETURN BOOK!!!!!";
	private final String BODY_EMAIL = "Please return book like return date your return date is: ";

	@Scheduled(fixedRate = 864000000) // 5 milliseconds can change to hours
	public void checkReturnDate() {

		List<Order> orders = orderRepo.findAll();
		for (Order o : orders) {
			if (o.getReturnDate() != null && o.isReturnOrder() != false) {
				LocalDateTime currentDate = LocalDateTime.now();
				Duration duration = Duration.between(currentDate, o.getReturnDate());
				Duration threshold = Duration.ofDays(DAY_NEED_RETURN);
				if (duration.compareTo(threshold) <= 0) {
					try {
						if (o.getEmployee() != null) {
							JavaMail.sendVerifyEmail(o.getEmployee().getEmail(), SUBJECT_EMAIL,
									BODY_EMAIL + o.getReturnDate());
						} else {
							JavaMail.sendVerifyEmail(o.getUser().getEmail(), SUBJECT_EMAIL,
									BODY_EMAIL + o.getReturnDate());
						}
					} catch (EmailException e) {
						System.out.println(e);
						System.out.println("ERROR INTERNET CONNECTION TO SEND EMAIL");
					}
					orderRepo.save(o);
					System.out.println("Send notification one day before return date.");
				}
			}
		}
	}
}
