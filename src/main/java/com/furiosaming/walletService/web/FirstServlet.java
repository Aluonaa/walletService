package com.furiosaming.walletService.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
        if (visitCounter == null) {
            visitCounter = 1;
        } else {
            visitCounter++;
        }
        session.setAttribute("visitCounter", visitCounter);
        String username = req.getParameter("username");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        if (username == null) {
            printWriter.write("Hello, Anonymous" + "<br>");
        } else {
            printWriter.write("Hello, " + username + "<br>");
        }
        printWriter.write("Page was visited " + visitCounter + " times.");
        printWriter.close();
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json;charset=UTF-8");
//        PersonDto personDto = new PersonDto();
//        req.setAttribute("person", personDto);
//    }

    //    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.setContentType("application/json;charset=UTF-8");
//        PersonDto personDto = new PersonDto();
//        req.setAttribute("person", personDto);
//    }
}