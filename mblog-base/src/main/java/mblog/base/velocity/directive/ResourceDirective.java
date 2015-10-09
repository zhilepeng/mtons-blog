/**
 * 
 */
package mblog.base.velocity.directive;

import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.tools.view.ViewToolContext;

import mblog.base.context.Global;
import mblog.base.velocity.utils.DirectiveUtils;

/**
 * @author langhsu
 *
 */
public class ResourceDirective extends Directive {
    @Override
    public String getName() {
        return "resource";
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        String src = DirectiveUtils.getStringParameter(context, node, 0);
        ViewToolContext viewContext = (ViewToolContext)context.getInternalUserContext();
        
        String base = viewContext.getRequest().getContextPath();
        
        // 判断是否启用图片域名
        if (Global.getImageDomain()) {
        	base = Global.getImageHost();
        }
        
        StringBuffer buf = new StringBuffer();
        
        buf.append(base);
        buf.append(src);
        writer.write(buf.toString());
        return true;
    }
}
