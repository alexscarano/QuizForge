<%-- 
    Document   : QuizPage
    Created on : May 25, 2025, 7:42:16 PM
    Author     : edano
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="controller.AIparser"%>
<%@page import="controller.testAI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    testAI ai = new testAI();
    AIparser par = new AIparser();
    par.parseAI(ai.AIresponse(), "dskhjfgskdjfhgwskefse");
    String quizname = par.getParsedQuiz().getQuizName();
    String question = par.getParsedQuestions()[0].getQuestion();
    List<String> alternatives = par.getParsedQuestions()[0].getAlternatives();
    int correctanswer = par.getParsedQuestions()[0].getCorrectAnswer();
    int difficulty = par.getParsedQuestions()[0].getDifficultyLevel();
    String errorMessage = null;
    boolean result = false;
    boolean click = false;
    try{
        if(request.getParameter("Submit")!=null){
            click = true;
            int quizanswer = Integer.parseInt(request.getParameter("q"));
            if (quizanswer == correctanswer) {
                result = true;
            }
        }
    }catch(Exception ex){
        errorMessage = ex.getMessage();
    } 
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2><%=quizname%></h2>

        <p><%=question%></p>
        
        <form>
            <input type="radio" id="0" name="q" value="0">
            <label for="html"><%=alternatives.get(0)%></label><br>
            <input type="radio" id="1" name="q" value="1">
            <label for="css"><%=alternatives.get(1)%></label><br>
            <input type="radio" id="2" name="q" value="2">
            <label for="javascript"><%=alternatives.get(2)%></label><br>
            <input type="radio" id= 3" name="q" value="3">
            <label for="javascript"><%=alternatives.get(3)%></label><br><br>
            <input type="submit" value="Submit" name="Submit">
        </form>
        <h2>Difficulty: <%=difficulty%></h2>       
        <%if(errorMessage==null){%>
            <%if(result==true&&click==true){%>
                <h1>That's right :DDDDD</h1>
            <%}else if (result==false&&click==true){%>
                <h1>u r RONG :CCCC</h1>
            <%}%>
        <%}else{%>
            <div style="color:red"><%=errorMessage%></div>
        <%}%>
    </body>
</html>
