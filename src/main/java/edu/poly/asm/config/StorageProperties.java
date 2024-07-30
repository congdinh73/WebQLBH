package edu.poly.asm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
@Data
public class StorageProperties {
    private String localtion;
}
