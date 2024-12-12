package ra.crud.config;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration

public class CloundiaryConfig {
    private static final String CLOUND_NAME = "dvmyqjxzc";
    private static final String API_KEY = "763128913121858";
    private static final String API_SECRET = "7D4mU8AkbR9NiAiXJg9IPjk7Pz4";

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUND_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET
        ));
    }
}