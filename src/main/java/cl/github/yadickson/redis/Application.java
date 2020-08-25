package cl.github.yadickson.redis;

import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Main application class.
 *
 * @author Yadickson Soto
 */
@SpringBootApplication
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Application extends SpringBootServletInitializer {

    /**
     * Main application method.
     *
     * @param argv arguments.
     */
    @Generated
    public static void main(String[] argv) {
        SpringApplication.run(Application.class);
    }

}
