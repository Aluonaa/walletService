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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet("/register/*")
public class SecServlet extends HttpServlet {

    //private PersonService personService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String login = req.getParameter("login");
        //resp.getWriter().write(login);

        PersonServiceImpl personService = new PersonServiceImpl(new PersonRepositoryImpl(),
                new BankAccountServiceImpl(new BankAccountRepositoryImpl(),
                        new TransactionServiceImpl(new TransactionRepositoryImpl())));

        Person person = personService.getPersonByLogin(login);
        if(person == null){
            resp.getWriter().write("Пользователь не найден");
        }
        else resp.getWriter().write(String.valueOf(person));
//        ObjectMapper objectMapper = new ObjectMapper();
//      resp.getWriter().write(String.valueOf(person));  String postJson = mapper.writeValueAsString(post);
//        try (final BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
//            String inputLine;
//            final StringBuilder content = new StringBuilder();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            resp.getWriter().write(String.valueOf(content));
//        } catch (final Exception ex) {
//            ex.printStackTrace();
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();

        PersonServiceImpl personService = new PersonServiceImpl(new PersonRepositoryImpl(),
                new BankAccountServiceImpl(new BankAccountRepositoryImpl(),
                        new TransactionServiceImpl(new TransactionRepositoryImpl())));
        try {
            Person person = objectMapper.readValue(req.getInputStream(), Person.class);
            Response<Person> response = personService.createPerson(person);

            if (response.isStatus()) {
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
