package app.auth;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                try {
                    HttpServletRequest req = (HttpServletRequest) request;
                    HttpServletResponse res = (HttpServletResponse) response;

                    String uri = req.getRequestURI();
                    this.context.log("Requested Resource::" + uri);

                    HttpSession session = req.getSession(false);
                    String loginURI = req.getContextPath() + "/login";
                    String logoutURI = req.getContextPath() + "/logout";
                    if (req.getRequestURI().equals(loginURI) || req.getRequestURI().equals(logoutURI)) {
                        chain.doFilter(request, response);
                        return;
                    }

                    if (session == null || session.getAttribute("authenticated") == null) {
                        this.context.log("Unauthorized access request");
                        req.getRequestDispatcher("/login.jsp").forward(req, res);
                    }
                    chain.doFilter(request, response);
                }
                catch (IOException e){
                    e.printStackTrace();
                }
    }

    @Override
    public void destroy() {
        //close any resources here
    }
}

