/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.classRegistration.servlet;

import com.classRegistration.dao.FacultyDao;
import com.classRegistration.dao.StudentDao;
import com.classRegistration.entities.Faculty;
import com.classRegistration.entities.PromptMessage;
import com.classRegistration.entities.Students;
import com.classRegistration.utilities.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author akhlaq-aqidah
 */
@WebServlet(name = "FacultyLoginServlet", urlPatterns = {"/FacultyLoginServlet"})
public class FacultyLoginServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String fEmail = request.getParameter("fEmail");
            String fPassword = request.getParameter("fPassword");
            
            FacultyDao facultyDao = new FacultyDao(ConnectionProvider.getConnection());
            Faculty faculty = (Faculty)facultyDao.getFacultyByEmailAndPassword(fEmail, fPassword);
            
            if(faculty != null){
                PromptMessage promptMessage = new PromptMessage("SUCCESSFULLY LOGGED IN", "success", "alert-success");
                
                
                StudentDao studentDao = new StudentDao(ConnectionProvider.getConnection());
                List<Students> registeredStudentsList = studentDao.getAllRegisteredStudentsList();
                
                session.setAttribute("promptMessage", promptMessage);
                session.setAttribute("currentFaculty", faculty);
                session.setAttribute("registeredStudentsList", registeredStudentsList);
                
//                System.out.println("[ faculty login ]"+registeredStudentsList);
                response.sendRedirect("faculty_dashboard.jsp");
            }else{
                PromptMessage promptMessage = new PromptMessage("Something Went Wrong", "error", "alert-danger");
                session.setAttribute("promptMessage", promptMessage);
                response.sendRedirect("facultyLogin.jsp");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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
