package com.furiosaming.walletService.web;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furiosaming.walletService.persistence.model.Person;
import com.furiosaming.walletService.repository.impl.BankAccountRepositoryImpl;
import com.furiosaming.walletService.repository.impl.PersonRepositoryImpl;
import com.furiosaming.walletService.repository.impl.TransactionRepositoryImpl;
import com.furiosaming.walletService.service.response.Response;
import com.furiosaming.walletService.service.service.PersonService;
import com.furiosaming.walletService.service.service.impl.BankAccountServiceImpl;
import com.furiosaming.walletService.service.service.impl.PersonServiceImpl;
import com.furiosaming.walletService.service.service.impl.TransactionServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class SecServlet extends HttpServlet {

    //private PersonService personService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        personService = new PersonServiceImpl(new PersonRepositoryImpl(),
//                new BankAccountServiceImpl(new BankAccountRepositoryImpl(),
//                        new TransactionServiceImpl(new TransactionRepositoryImpl())));

    }


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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();

        App app = new App();

        PersonServiceImpl personService = new PersonServiceImpl(new PersonRepositoryImpl(),
                new BankAccountServiceImpl(new BankAccountRepositoryImpl(),
                        new TransactionServiceImpl(new TransactionRepositoryImpl())));
        try {
            Person person = objectMapper.readValue(req.getInputStream(), Person.class);
            PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
            //Response<Person> response = personService.createPerson(person);

            if (true) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"message\": \"Registration successful\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Registration failed\"}");
            }
        } catch (JsonParseException | JsonMappingException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid JSON format\"}");
        }
    }
}
