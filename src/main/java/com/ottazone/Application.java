package com.ottazone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@ComponentScan
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration
@EnableCaching
@EnableRetry
@SuppressWarnings({"checkstyle:hideutilityclassconstructor"})
public class Application {

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

  /**
   * Main method, used to run the application.
   */
  public static void main(String[] args) throws UnknownHostException {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Mode.CONSOLE);
    Environment env = app.run(args).getEnvironment();
    LOG.info("Access URLs:\n----------------------------------------------------------\n\t"
        + "Local: \t\thttp://127.0.0.1:{}\n\t"
        + "External: \thttp://{}:{}\n----------------------------------------------------------",
      env.getProperty("server.port"),
      InetAddress.getLocalHost().getHostAddress(),
      env.getProperty("server.port"));
  }
}
