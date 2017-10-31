package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.User;
import utilities.UserService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String loggedOut = request.getParameter("action");
        
        if(loggedOut != null){
            request.setAttribute("displayMessage", "Logged out successfully");
            HttpSession session = request.getSession();
            
            session.removeAttribute("user");
            session.invalidate();
            
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String checked = request.getParameter("rememberMe");
        String usernameStr = request.getParameter("username");
        String passwordStr = request.getParameter("password");
        request.setAttribute("username", usernameStr);
        request.setAttribute("password", passwordStr);
        
        UserService userService = new UserService();
        
        if(usernameStr == null || usernameStr.isEmpty() || passwordStr == null || passwordStr.isEmpty()){
            
            request.setAttribute("displayMessage", "Both values are required");
            
        } else if(userService.login(usernameStr, passwordStr) == false){
            
            request.setAttribute("displayMessage", "Invalid username or password");
            
        } else {
            
            User user = new User();
            user.setUsername(usernameStr);
            user.setPassword(passwordStr);
            
            request.setAttribute("usernameEntered", usernameStr);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            if(checked != null && checked.equals("remember")){
                
                Cookie cookie = new Cookie("username", usernameStr);
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24 * 20);
                response.addCookie(cookie);
            } else {
                
                Cookie[] cookies = request.getCookies();
                for(Cookie cookie : cookies){
                    if(cookie.getName().equalsIgnoreCase(usernameStr)){
                        cookie.setMaxAge(0);
                    }
                }
            }
            response.sendRedirect("Home");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}