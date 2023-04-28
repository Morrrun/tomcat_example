package com.alexsandrov.tomcat_example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Stream;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //Получаем параметр по значению
        String param = req.getParameter("param");

        //Получаем все параметры
        Map<String, String[]> parameterMap = req.getParameterMap();

        //Получение заголовков request-а.
        //User-Agent позволяет получить информацию по устройству с которого был передан request
        req.getHeader("user-agent");

        //Указываем тип данных response
        resp.setContentType("text/html");
        //Задаем кодировку
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        //Можем передать кодировку в качестве параметра заголовка после точки с запятой
        //resp.setContentType("text/html; charset=UTF-8");

        //Устанавливаем кастомный header
        resp.setHeader("token", "12345");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h2>Привет с <i>Первого Сервлета</i></h2>");
        } catch (IOException e) {
            //Логируем наше исключение, и, ни в коем случае, не пробрасываем!
            System.out.println(e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //Приём параметров
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println(parameterMap);

        //Обработка Body-request
        try (BufferedReader reader = req.getReader();
             //Считываем построчно тело ответа
             Stream<String> lines = reader.lines()) {

            lines.forEach(System.out::println);

        } catch (IOException e) {
            //Логируем наше исключение, и, ни в коем случае, не пробрасываем!
            System.out.println(e.getMessage());
        }
    }
}
