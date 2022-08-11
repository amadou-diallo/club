/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Member;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kadiatou
 */
public class MemberUpdateServlet extends HttpServlet {

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
        
      String URL = "/MemberScreen.jsp";
      String msg = "", sql = "", lnm="", fnm="", mnm="";
      long newpwd = 0;
      
      
      String dbURL = "jdbc:mysql://localhost:3306/club";
      String dbUser= "root";
      String dbPass = "Bigbagnan1";
      
      try {
          Member m = (Member) request.getSession().getAttribute("m");
          Member n = new Member();
          n.setMemid(m.getMemid());
          n.setStatus(m.getStatus());
          n.setMemdt(m.getMemdt());
          n.setPassattempt(m.getPassword());
      
          try {
              lnm = request.getParameter("lastname");
              if (!lnm.isEmpty()) {
                  n.setLastname(lnm);
              } else {
                  msg += "Last name is missing";
              }
                  
          } catch(Exception e) {
              msg += "Error : " + e.getMessage() + "<br>";
           }
          
          try {
              fnm = request.getParameter("firstname");
              if (!fnm.isEmpty()) {
                  n.setFirstname(fnm);
              } else {
                  msg += "First name is missing";
              }
                  
          } catch(Exception e) {
              msg += "Error : " + e.getMessage() + "<br>";
           }
          
          try {
              mnm = request.getParameter("middlename");
              if (!mnm.isEmpty()) {
                  n.setMiddlename(mnm);
              } else {
                  msg += "Middle name is missing";
              }
                  
          } catch(Exception e) {
              msg += "Error : " + e.getMessage() + "<br>";
           }
          
           try {
              newpwd = Long.parseLong(request.getParameter("psswd"));
              if (newpwd > 0) {
                  n.setPassword(newpwd);
              }else {
                  msg += "Illegal password: must be > 0 <br>";
              }
          } catch (NumberFormatException e) {
              msg += "Illegal password: not numeric.<br>";
          }
           
           if (msg.isEmpty()) {
               //update member based on n
               Connection conn =
                       DriverManager.getConnection(dbURL, dbUser, dbPass);
               sql = "UPDATE tblmembers SET " +
                       " LastName = ?, " +
                       " FirstName = ?, " +
                       " MiddleName = ?, " +
                       " Password = ? " +
                       " WHERE MemId = ?";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setString(1, n.getLastname());
               ps.setString(2, n.getFirstname());
               ps.setString(3, n.getMiddlename());
               ps.setLong(4, n.getPassword());
               ps.setString(5, n.getMemid());
               int rc = ps.executeUpdate();
               if (rc == 0) {
                   msg += "Member was not updated!<br>";
                   
               } else if (rc == 1) {
                   msg += "<Member Updated!<br>";
                   m = n;
                   request.getSession().setAttribute("m", m);
                           
               } else {
                   msg += "Warning: multiple db records changed.<br>";
                           
               }
             }
         } catch (SQLException e) {
             msg += "sql error: " + e.getMessage() + "<br>";     
         } catch(Exception e) {
             msg += "Servlet error: " + e.getMessage() + " <br>";
         }
         request.setAttribute("msg", msg);
         
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
