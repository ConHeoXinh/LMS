package longND.fpt.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {

	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary("cloudinary://943412152311641:lHskbw-VHRn7QKGKf58y7s32RMY@dboo9wwlk");
	}
}
