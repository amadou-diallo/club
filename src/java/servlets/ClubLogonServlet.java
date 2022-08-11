
package servlets;

import business.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kadiatou
 */
public class ClubLogonServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      String URL = "/Logon.jsp", msg = "";
      String uid = "", sql = "";
      long patt = 0;
      Member m;
      
      String dbURL = "jdbc:mysql://localhost:3306/club";
      String dbUser= "root";
      String dbPass = "Bigbagnan1";
      
      try {
          uid = request.getParameter("userid").trim();
          patt = Long.parseLong(request.getParameter("password").trim());
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
          Statement s = conn.createStatement();
          sql = "SELECT * FROM tblMembers WHERE MemID = '" + uid + "'";
          ResultSet r = s.executeQuery(sql);
          if (r.next()) {
              m = new Member();
              m.setMemid(uid);
              m.setPassword(patt);
              m.setPassattempt(patt);
              if (m.isAuthenticated()) {
                  m.setLastname(r.getString("LastName"));
                  m.setFirstname(r.getString("firstName"));
                  m.setMiddlename(r.getString("MiddleName"));
                  m.setStatus(r.getString("Status"));
                  m.setMemdt(r.getString("MemDt"));
                  URL = "/MemberScreen.jsp";
                  
                  msg += "user " + uid + " Authenticated!<br>";
                  request.getSession().setAttribute("m", m);
                  
              } else {
                  msg += "User " + uid + " found but not Authenticated<br>";
              }
          } else {
              msg += "Member not found<br>";
          }
          r.close();
          s.close();
          conn.close();
          
      } catch (NumberFormatException e) {
          msg += "Password not numeric.<br>";
      } catch (SQLException e) {
          msg += "SQL error: " + e.getMessage() + " " + sql + "<br>";
          
      } catch (Exception e) {
          msg += "Servlet error: " + e.getMessage();
      }
      
      request.setAttribute("msg", msg);
      Cookie userid = new Cookie("userid", uid);
      userid.setMaxAge(60*10);
      userid.setPath("/");
      response.addCookie(userid);
      
      RequestDispatcher disp = 
              getServletContext().getRequestDispatcher(URL);
      disp.forward(request, response);
              
      
     
      
              
              
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
 