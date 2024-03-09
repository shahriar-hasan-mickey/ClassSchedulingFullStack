/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.classRegistration.servlet;

import com.classRegistration.dao.SectionDao;
import com.classRegistration.dao.StudentDao;
import com.classRegistration.entities.ClassTimings;
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
@WebServlet(name = "ClassRegistrationServlet", urlPatterns = {"/ClassRegistrationServlet"})
public class ClassRegistrationServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            /* TODO output your page here. You may use following sample code. */
            Students students = (Students)session.getAttribute("currentStudent");
            if(students != null){
                int section = Integer.parseInt(request.getParameter("classTimingSlot"));
                SectionDao sectionDao = new SectionDao(ConnectionProvider.getConnection());
                ClassTimings classTimings = (ClassTimings)sectionDao.getSectionById(section);
//                if(classTimings!=null){
//                    if(classTimings.getNoOfSeats()>0){
//                        classTimings.setNoOfSeats(classTimings.getNoOfSeats()-1);
//                        students.setClass_section(section);
//                        PromptMessage promptMessage = new PromptMessage("Successfully Registered to Section "+section, "success", "alert-success");
//                        session.setAttribute("promptMessage", promptMessage);
//                        response.sendRedirect("student_dashboard.jsp");
//                    }else{
////                        For knowledge purpose
//                        com.classRegistration.entities.PromptMessage promptMessage = new PromptMessage("No more seats remaining for Section No. "+classTimings.getSection() + " !!  Register with another section.", "error", "alert-danger");
//                        session.setAttribute("promptMessage", promptMessage);
//                        response.sendRedirect("class_register.jsp");
//                    }   
//                }
                if(classTimings.getNoOfSeats()>0){
                    classTimings.setNoOfSeats(classTimings.getNoOfSeats() - 1);
                    Boolean sectionUpdatedSuccessfully = sectionDao.setSection(classTimings);
                    students.setClass_section(section);
                    StudentDao studentDao = new StudentDao(ConnectionProvider.getConnection());
                    Boolean studentsSectionInfoUpdatedSuccessfully = studentDao.updateStudentSection(students);
                    if(sectionUpdatedSuccessfully && studentsSectionInfoUpdatedSuccessfully){
                        
                        PromptMessage promptMessage = new PromptMessage("Successfully Registered to Section "+section, "success", "alert-success");
                        session.setAttribute("promptMessage", promptMessage);
                        response.sendRedirect("student_dashboard.jsp");
                    }else{
                        PromptMessage promptMessage = new PromptMessage("Error Occured "+section, "error", "alert-danger");
                        session.setAttribute("promptMessage", promptMessage);
                        response.sendRedirect("register.jsp");
                    }
                }else{
                    PromptMessage promptMessage = new PromptMessage("Error Occured "+section, "error", "alert-danger");
                    session.setAttribute("promptMessage", promptMessage);
                    response.sendRedirect("register.jsp");
                }
//                System.out.println("classTimings"+classTimings);
            }else{
                response.sendRedirect("studentLogin.jsp");
            }
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
