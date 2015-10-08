package mblog.base.velocity.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.node.Node;

/**
 * Created by langhsu on 2015/10/8.
 */
public class DirectiveUtils {
    public static int toInt(Object obj) {
        int i = 0;
        if (obj != null && StringUtils.isNumeric(obj.toString())) {
            i = ((Number) obj).intValue();
        }
        return i;
    }

    public static long toLong(Object obj) {
        long i = 0;
        if (obj != null && StringUtils.isNumeric(obj.toString())) {
            i = ((Number) obj).longValue();
        }
        return i;
    }

    public static Node getParameter(InternalContextAdapter context, Node node, int index) {
        if (node.jjtGetNumChildren() > index && node.jjtGetChild(index) != null) {
            return node.jjtGetChild(index);
        }
        return null;
    }

    public static int getIntParameter(InternalContextAdapter context, Node node, int index) {
        if (node.jjtGetNumChildren() > index && node.jjtGetChild(index) != null) {
            Node n = node.jjtGetChild(index);

            return toInt(n.value(context));
        }
        return 0;
    }

    public static long getLongParameter(InternalContextAdapter context, Node node, int index) {
        if (node.jjtGetNumChildren() > index && node.jjtGetChild(index) != null) {
            Node n = node.jjtGetChild(index);
            return toLong(n.value(context));
        }
        return 0;
    }

    public static String getStringParameter(InternalContextAdapter context, Node node, int index) {
        if (node.jjtGetNumChildren() > index && node.jjtGetChild(index) != null) {
            Node n = node.jjtGetChild(index);
            return (String) n.value(context);
        }
        return null;
    }

    public static Node getBody(InternalContextAdapter context, Node node) {
        if (node.jjtGetNumChildren() > 0) {
            return node.jjtGetChild(node.jjtGetNumChildren() - 1);
        }
        return null;
    }
}
