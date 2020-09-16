package link.credit.nice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * NICE 신용정보 프로퍼티
 *
 * <p>Create by jsyang on 2020/09/03
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.nice")
public class NiceProperties {

  private final Safekey safekey = new Safekey();
  private final Npac npac = new Npac();

  @Getter
  @Setter
  public static class Safekey {
    private String siteCode;
    private String sitePasswd;
    private String returnUrl;
  }

  @Getter
  @Setter
  public static class Npac {
    private String userId;
    private String loginId;
    private String passwd;
    private String returnUrl;
    private String key;
    private String iv;
    private final SecureConnector secureConnector = new SecureConnector();

    @Getter
    @Setter
    public static class SecureConnector {
      private String host;
      private int port;
    }
  }
}
