<%-- 
    Document   : studentLogin
    Created on : Feb 18, 2024, 11:23:31 AM
    Author     : akhlaq-aqidah
--%>
<%@page import="com.classRegistration.entities.Faculty" %>
<%@page import="com.classRegistration.entities.PromptMessage" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Faculty faculty = (Faculty)session.getAttribute("currentFacultyt");
    if(faculty != null){
        response.sendRedirect("class_register.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <style>
            .careTaker-background{
                background: #56dd12 !important;
            }
            .banner-background{


                clip-path: polygon(50% 0%, 97% 10%, 100% 35%, 100% 100%, 89% 91%, 50% 100%, 8% 89%, 0 100%, 0% 35%, 2% 10%);


            }
        </style>
    </head>
    <body>
        <div class="container ">
        <main class="d-flex align-items-center careTaker-background banner-background " style=" padding: 5%">
            <div class="container">
                <form action="index.jsp">
                    <div class="container">
                        <button id="submit-btn" type="submit" class="btn btn-primary careTaker-background mt-2 mx-auto">Home</button>
                    </div>
                </form>
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        
                        <div class="card-header careTaker-background text-center text-white">
                            <span class="fa fa-asl-interpreting fa-3x"></span>
                            
                            <h3>Faculty Login Here</h3>
                        </div>
                        <%
                            PromptMessage promptMessage = (PromptMessage)session.getAttribute("promptMessage");
                            if(promptMessage!=null){
                        %>
                                <div class="alert <%= promptMessage.getCssClass() %>"  role="alert">
                                    <%= promptMessage.getContent()%>
                                </div>
                        <%
                                session.removeAttribute("promptMessage");
                            }
                        %>
                        
                        <div class="card-body">
                            <form id="facultyLoginForm" action="FacultyLoginServlet"> 
                                

                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input name="fEmail" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" reguired>
                                </div>


                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input name="fPassword" type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter Password" reguired>
                                </div>

                                



                                
                                <div class="container text-center" id="loader" style="display: none;" >
                                    <span class="fa fa-refresh fa-spin fa-4x"></span>
                                    <h4>Please Wait...</h4>
                                </div>
                                
                                <div class="container text-center">
                                <button id="submit-btn" type="submit" class="btn btn-primary careTaker-background mt-2 mx-auto">Login</button>
                                </div>
                            </form>
                        </div>
                        <div class="card-footer careTaker-background">

                        </div>
                    </div>
                </div>
            </div>


        </main>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        </div>
    </body>
</html>
