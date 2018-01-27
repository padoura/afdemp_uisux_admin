package org.afdemp.uisux.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.afdemp.uisux.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUtility {
	
	public static boolean trySaveImage(Product product) {
		MultipartFile productImage = product.getProductImage();
		if (productImage != null && !productImage.isEmpty()){
			try {
				byte[] bytes = productImage.getBytes();
				String name = product.getId() + ".png";
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/product/" + name)));
				stream.write(bytes);
				stream.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}
