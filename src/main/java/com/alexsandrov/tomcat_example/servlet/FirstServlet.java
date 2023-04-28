package com.alexsandrov.tomcat_example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//Аннотация что-бы связать определенный путь с нашим Servlet-ом
@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    //Первый этап ЖЦ Servlet
    //(инициализация [загрузка класса Servlet-а, создание объекта Servlet-а, и вызов данного метода])
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    //Второй этап ЖЦ Servlet
    //(обработка входящих запросов[requestInitialisation, requestDestroyed])
    //Не переопределяется, т.к. использует механизм else-if для определения типа HTTP-метода,
    //вместо него переопределяем такие методы как doGet(), doPost и т.д.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
    //Третий этап ЖЦ Servlet
    //(уничтожение работающего экземпляра по завершению работы приложения)
    @Override
    public void destroy() {
        super.destroy();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h2>Hello from <i>First Servlet</i></h2>");
        }
    }
}
