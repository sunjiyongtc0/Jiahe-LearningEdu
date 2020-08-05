package sjy.jiahe.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
@Data
public class WxConfigProperties {
    private String appId;
    private String secret;
    private String webAccessTokenhttps;
}
