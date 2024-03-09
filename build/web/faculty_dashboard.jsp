<%-- 
    Document   : studentLogin
    Created on : Feb 18, 2024, 11:23:31 AM
    Author     : akhlaq-aqidah
--%>
<%@page import="com.classRegistration.entities.Faculty" %>
<%@page import="com.classRegistration.entities.Students" %>
<%@page import="java.util.List" %>
<%@page import="com.classRegistration.entities.PromptMessage" %>
<%@page import="com.classRegistration.utilities.ConnectionProvider" %>
<%@page import="com.classRegistration.dao.SectionDao" %>
<%@page import="com.classRegistration.entities.ClassTimings" %>


<%
    Faculty faculty = (Faculty)session.getAttribute("currentFaculty");
    List<Students> registeredStudentsList = (List)session.getAttribute("registeredStudentsList");
    if(faculty == null){
        response.sendRedirect("facultyLogin.jsp");
    }else{
    System.out.println("here==>>"+registeredStudentsList);
%>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <!--<div class="container ">-->
            <main class="d-flex align-items-center careTaker-background banner-background " style=" padding: 5%">
                <div class="container">
                    <div class="container">
                        <form action="FacultyLogoutServlet">
                            <button type="submit" id="logout">Logout</button>
                        </form>
                    </div>
                    <div class="col-md-6 offset-md-3">
                        <div class="card">
                            <div class="card-header careTaker-background text-center text-white">
                                <span class="fa fa-asl-interpreting fa-3x"></span>
                                <h4>Faculty Name : <%=faculty.getfName()%></h4>
                                <h3>Class Details</h3>
                            </div>

                            <%
                            PromptMessage promptMessage = (PromptMessage)session.getAttribute("promptMessage");
                            if(promptMessage!=null){
                            %>
                                        <div class="alert <%= promptMessage.getCssClass() %> text-center"  role="alert">
                                            <%= promptMessage.getContent()%>
                                        </div>
                            <%
                                        session.removeAttribute("promptMessage");
                                    }
                            %>
                            
                            
                            <div class="container ">
                            <%
                                SectionDao sectionDao = new SectionDao(ConnectionProvider.getConnection());
                                List<ClassTimings> sections = sectionDao.getClassTimings();
                                if(sections.size()>0){
                                
                                    for(int j = 0; j < sections.size(); j++){
                            %>
                                        
                                        <div class="form-check-inline mt-2" >
                                            <input class="form-check-input" type="radio" name="flexRadioDefault" id="section<%=sections.get(j).getSection()%>" ONCLICK='getStudentBySection(<%=sections.get(j).getSection()%>)'>
                                            <label class="form-check-label" for="section<%=sections.get(j).getSection()%>">
                                              Section No. <%=sections.get(j).getSection()%>
                                            </label>
                                        </div>
                                            
                            <%
                                    }
                                }
                            %>
                            
                            </div>
                            <%
                                if(registeredStudentsList.size()>0){
                                %>
                                    <div id="profile-details" class="container mt-3">
                                        <table class="table table-hover">
                                            <tbody class="primary-background" id="studentData">
                                                
                                                <tr>
                                                    <th scope="row">Student Name</th>
                                                    <th> Student ID </th>
                                                </tr>
                                                <%
                                                    for(int i = 0; i < registeredStudentsList.size(); i++){
                                                %>
                                                        <tr>
                                                            <td><%= registeredStudentsList.get(i).getsName()%></td>
                                                            <td><%= registeredStudentsList.get(i).getsId()%></td>
                                                        </tr>
                                                <%
                                                    }
                                                %>

                                            </tbody>
                                        </table>


                                    </div>
                                                
                                                    <%
                                                        }
                                                    %>
                           
                        </div>
                    </div>
                </div>


            </main>
    <!--</div>-->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
            <script>
                
                async function getStudentBySection(section){
                    
//                    The below two lines are for future work : https://stackoverflow.com/questions/32828160/appending-parameter-to-url-without-refresh
                    var refresh = window.location.protocol + "//" + window.location.host + window.location.pathname + "?section=" + section;
                    window.history.pushState({ path: refresh }, "", refresh);
                    
                    const data = section;
                    const url = "http://localhost:8080";
                    const path = "/cse310_assignment2_noSpring/FacultyDashboardServlet";
                    const fetchOptions = {
                        method : 'POST',
                        headers : {
                            'Content-type' : '*/*',
                            'Accept' : '*/*',
                        },
                        body : data,
                    };
                    
                    const uri = url + path;
                    
                    try{
                        const response = await fetch(uri, fetchOptions);
                        if(!response.ok){
                            const error = await response.text();
                            throw new Error(error);
                        }
                        
                        const responseData = await response.text();
                        var studentData = document.getElementById("studentData");
                        studentData.innerHTML = responseData;
                    }catch(error){
                        alert(error.message);
                    }
//                    
                    
                }
                
//                var studentData = document.getElementById("studentData");
//                studentData.addEventListener("click", async function (event) {
//                    event.preventDefault();
//                    
                    
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
                    
//                });
                
            </script>
        </div>
    </body>
</html>
<%
    }
%>