package com.alexsandrov.tomcat_example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //Для указания того что передаваемые данные будут загружены
        //используется заголовок [content-disposition: attachment; filename="filename.txt"]
        //в filename указываем имя файла и его расширение
        resp.setHeader("content-disposition", "attachment; filename=\"filename.txt\"");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.write("Data from servlet!");
        } catch (IOException e) {
            //Логируем наше исключение, и, ни в коем случае, не пробрасываем!
            System.out.println(e.getMessage());
        }

    }
}
