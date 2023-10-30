package longND.fpt.home.util;

import org.springframework.security.core.context.SecurityContextHolder;

import longND.fpt.home.security.jwt.UserDetailsImpl;

public class SecurityUtils {
	public static UserDetailsImpl getPrincipal() {
		return (UserDetailsImpl) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
	}
}
