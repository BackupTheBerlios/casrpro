package ar.com.survey.persistence.hibernate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

/**
 * A servlet filter that provides a thread-bound session-per-request.
 * <p>
 * This filter should be used if your <tt>hibernate.current_session_context_class</tt>
 * configuration is set to <tt>thread</tt> and you are not using JTA/CMT. You can use
 * this filter for a thread-bound <tt>Session</tt>, either with resource-local transactions
 * (direct JDBC) or user-managed JTA transactions. Set your
 * <tt>hibernate.transaction.factory_class</tt> accordingly.
 * <p>
 * An alternative, more flexible solution is <tt>TransactionInterceptor</tt>
 * that can be applied to any pointcut with JBoss AOP.
 * <p>
 * Note that you should not use this interceptor out-of-the-box with enabled optimistic
 * concurrency control. Apply your own compensation logic for failed conversations, this
 * is totally dependent on your applications design.
 *
 * @see org.hibernate.ce.auction.persistence.TransactionInterceptor
 *
 * @author christian@hibernate.org
 */
public class HibernateThreadFilter implements Filter {
	/**
	 * Logger for this class
	 */
	private static final Logger log = Logger
			.getLogger(HibernateThreadFilter.class);

    private SessionFactory sf;

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        try {
            log.debug("Starting a database transaction");
            sf.getCurrentSession().beginTransaction();

            // Call the next filter (continue request processing)
            chain.doFilter(request, response);

            // Commit and cleanup
            log.debug("Committing the database transaction");
            sf.getCurrentSession().getTransaction().commit();

        } catch (StaleObjectStateException staleEx) {
            log.error("This interceptor does not implement optimistic concurrency control!");
            log.error("Your application will not work until you add compensation actions!");
            // Rollback, close everything, possibly compensate for any permanent changes
            // during the conversation, and finally restart business conversation. Maybe
            // give the user of the application a chance to merge some of his work with
            // fresh data... what you do here depends on your applications design.
            throw staleEx;
        } catch (Throwable ex) {
            // Rollback only
            ex.printStackTrace();
            try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    log.debug("Trying to rollback database transaction after exception");
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
                log.error("Could not rollback transaction after exception!", rbEx);
            }

            // Let others handle it... maybe another interceptor for exceptions?
            // throw new ServletException(ex);
            HttpServletRequest req = (HttpServletRequest) request;
            RequestDispatcher rd = req.getRequestDispatcher("fill.do?method=clientError");
            rd.forward(request, response);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Initializing filter, obtaining Hibernate SessionFactory from HibernateUtil");
        sf = HibernateUtil.getSessionFactory();
    }

    public void destroy() {}

}