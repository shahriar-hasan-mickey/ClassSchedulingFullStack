<%-- 
    Document   : studentLogin
    Created on : Feb 18, 2024, 11:23:31 AM
    Author     : akhlaq-aqidah
--%>
<%@page import="com.classRegistration.entities.Students" %>
<%@page import="com.classRegistration.entities.PromptMessage" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Students students = (Students)session.getAttribute("currentStudent");
    if(students != null){
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
                background: #56d1f7 !important;
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
                            <h3>Student Login Here</h3>
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
                            <form id="studentLoginForm" action="StudentLoginServlet"> 
                                

                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input name="sEmail" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" reguired>
                                </div>


                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input name="sPassword" type="password" class="form-control" id="exampleInputPassword1" placeholder="Enter Password" reguired>
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
            <script>
//                var form = document.getElementById("studentLoginForm");
//                form.addEventListener("submit", async function (event) {
//                    event.preventDefault();
//                    const data = new FormData(this);
//                    const url = "http://localhost:8080";
//                    const path = "/cse310_assignment2_noSpring/StudentLoginServlet";
//                    const fromDataObject = await Object.fromEntries(data.entries());
//                    const formDataJsonString = await JSON.stringify(fromDataObject);
//                    alert(formDataJsonString);
//                    const fetchOptions = {
//                        method : 'POST',
//                        headers: {
//                            'Content-Type' : '*/*',
//                            'Accept' : '*/*',
//                        },
//                        body : formDataJsonString,
//                    };
//                    
//                    const uri = url + path;
//                    
//                    try{
//                        const response = await fetch(uri, fetchOptions);
//                        if(!response.ok){
//                            const error = await response.text();
//                            throw new Error(error);
//                        }
//                        
//                        const responseData = await response.text();
//                        alert(responseData);
//                    }catch (error){
//                        alert(error.message);
//                    }
//                    
//                });
                
            </script>
        </div>
    </body>
</html>
