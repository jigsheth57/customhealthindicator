package io.pivotal.demo.customhealthindicator.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class DummyHealthIndicator extends AbstractHealthIndicator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

        int errorCode = 0;
        LocalTime now = LocalTime.now();
        logger.info("Test: "+now.getMinute() % 2);
        if (now.getMinute() % 2 != 0) {
            logger.info("errorCode 1");
            errorCode = 1;
        }

        if (errorCode != 0) {
            logger.info("health down");
            builder.down().withDetail("Error Code",errorCode);
            return;
        }
        builder.up().withDetail("Error Code",errorCode);
        return;
    }
}
