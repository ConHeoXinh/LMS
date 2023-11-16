package longND.fpt.home.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.extern.slf4j.Slf4j;
import longND.fpt.home.service.StorageService;
import longND.fpt.home.util.Util;

@Slf4j
@Service
public class CloudinaryService implements StorageService {

	private final Cloudinary cloudinary;

	public CloudinaryService(Cloudinary cloudinary) {
		this.cloudinary = cloudinary;
	}

	@Override
	public String upload(MultipartFile file) {
		File fileObj = convertMultiPartFileToFile(file);
//
//		String text = Util.genCode(10);
//
//		String fileName = System.currentTimeMillis() + "_" + text;

		String imageUrl = null;
		try {
			Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(),
					ObjectUtils.asMap("public_id", fileObj.getName(), "overwrite", true));
			imageUrl = (String) result.get("url");
			fileObj.delete();
		} catch (Exception e) {
			log.error("Error upload file", e);
		}
		return imageUrl;
	}

	@Override
	public byte[] download(String publicId) {
		String imageUrl = cloudinary.url().generate(publicId);
		try {
			URL url = new URL(imageUrl);
			byte[] content = Files.readAllBytes(Path.of(url.toURI()));

			return content;
		} catch (Exception e) {
			log.error("Error download file", e);
		}
		return null;
	}

	@Override
	public String delete(String publicId) {
		try {
			cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
		} catch (java.io.IOException e) {
			log.error("Error delete file", e);
		}
		return publicId + " removed ...";
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try {
			FileOutputStream fos = new FileOutputStream(convertedFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (Exception e) {
			log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}

	@Override
	public String getNameImage(String publicId) {
		String imageUrl = cloudinary.url().generate(publicId);
		
		List<String> list = List.of(imageUrl.split("/"));
		
		String nameImage = list.get(11);
		return nameImage;
	}

}
