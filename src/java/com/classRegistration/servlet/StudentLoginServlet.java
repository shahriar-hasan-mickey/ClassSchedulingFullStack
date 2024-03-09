/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.classRegistration.servlet;

import com.classRegistration.dao.StudentDao;
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


/**
 *
 * @author akhlaq-aqidah
 */
@WebServlet(name = "StudentLoginServlet", urlPatterns = {"/StudentLoginServlet"})
public class StudentLoginServlet extends HttpServlet {

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
            
            String sEmail = request.getParameter("sEmail");
            String sPassword = request.getParameter("sPassword");
            if(sEmail!=null & sPassword!=null){
                StudentDao studentDao = new StudentDao(ConnectionProvider.getConnection());
                
                try{
                    Students students = studentDao.getStudentByEmailAndPassword(sEmail, sPassword);
                    if(students!=null){
                        out.println("SUCCESSFULLY LOGGED IN");
                        HttpSession session = request.getSession();
                        session.setAttribute("currentStudent", students);
                        Integer section = students.getClass_section();
//                        System.out.println(section==0);
                        if(section==0 || section==null){
                            response.sendRedirect("class_register.jsp");
                            System.out.println("LOGGED IN");
                        }else{
                            response.sendRedirect("student_dashboard.jsp");
                        }
                    }else{
                        PromptMessage promptMessage = new PromptMessage("Invalid Email or Password!", "error", "alert-danger");
                        HttpSession session = request.getSession();
                        session.setAttribute("promptMessage", promptMessage);
                        
                        response.sendRedirect("studentLogin.jsp");
                        System.out.println("INVALID CREDENTIALS");
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println("ERROR");
                }
            }
            out.println("SUCCESS");
            
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
