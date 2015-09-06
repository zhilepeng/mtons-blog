package mblog.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;

/**
 * @author langhsu on 2015/7/10.
 */
public class RequestCostFilter implements Filter {
    private Logger log = Logger.getLogger(RequestCostFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String reuqestURI = httpRequest.getRequestURI();
        if (!reuqestURI.startsWith("/assets") &&  !reuqestURI.startsWith("/store")) {
            StopWatch stopWatch = new StopWatch(System.currentTimeMillis() + "");
            stopWatch.start();
            chain.doFilter(request, response);
            stopWatch.stop();

            log.debug( reuqestURI + " -> request cost - " + stopWatch.getTotalTimeMillis());
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
