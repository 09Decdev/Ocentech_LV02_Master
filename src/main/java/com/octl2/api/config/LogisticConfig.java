package com.octl2.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "logistics")
public class LogisticConfig {
//    @Value(value = "${logistics.mapping-level}")
    private int mappingLevel;
}
