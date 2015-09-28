/**
 * 
 */
package mblog.base.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

/**
 * @author langhsu
 *
 */
@Component
public class EmailEngine extends VelocityEngineFactoryBean {
	@Value("#{configProperties['velocity.resource']}")
	private String resourceLoaderPath;
}
