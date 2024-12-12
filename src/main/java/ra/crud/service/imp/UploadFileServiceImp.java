package ra.crud.service.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.crud.service.UploadFileService;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadFileServiceImp implements UploadFileService {

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File không hợp lệ");
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.contains(".")) {
            throw new IllegalArgumentException("Tên file không hợp lệ");
        }

        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        Map uploadParams = ObjectUtils.asMap(
                "public_id", fileName,
                "folder", "product_images"
        );

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);
        return uploadResult.get("url").toString();
    }
}
