package org.example.w2.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.ReqCounter;

@WebListener
@Log4j2
public class LoadListener implements ServletContextListener, ServletRequestListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();

        ReqCounter counter = new ReqCounter();
        context.setAttribute("counter", counter);

        log.info("contextInitialized");
        log.info("contextInitialized");
        log.info("contextInitialized");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("contextDestroyed");
        log.info("contextDestroyed");
        log.info("contextDestroyed");

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        log.info(sre.getServletRequest().getRemoteAddr());

        Object counterObj = sre.getServletRequest().getServletContext().getAttribute("counter");

        if(counterObj != null) {
            ReqCounter counter = (ReqCounter) counterObj;
            counter.inc();
        }


    }
}
