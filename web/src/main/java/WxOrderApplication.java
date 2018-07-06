import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.spf.*")
@SpringBootApplication
public class WxOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxOrderApplication.class, args);
    }
}
