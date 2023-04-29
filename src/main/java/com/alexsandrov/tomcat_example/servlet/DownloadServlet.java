package com.alexsandrov.tomcat_example.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    /**
     * Для указания того что передаваемые данные будут загружены
     * используется заголовок [content-disposition: attachment; filename="filename.txt"]
     * в filename указываем имя файла и его расширение
     * Пример:
     *      resp.setHeader("content-disposition", "attachment; filename=\"filename.txt\"");
     *      resp.setContentType("text/plain");
     *      resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

     *      //Передаем информацию для загрузки
     *      try (var printWriter = resp.getWriter()) {
     *          printWriter.write("Data from servlet!");
     *      } catch (IOException e) {
     *          //Логируем наше исключение, и, ни в коем случае, не пробрасываем!
     *          System.out.println(e.getMessage());
     *      }
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("content-disposition", "attachment; filename=\"filename.txt\"");
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        //Передаем файл для загрузки
        try (var outputStream = resp.getOutputStream();
             var inputStream = DownloadServlet.class.getClassLoader().getResourceAsStream("json/first.json")) {
            outputStream.write(inputStream != null ? inputStream.readAllBytes() : new byte[0]);

        } catch (IOException e) {
            //Логируем наше исключение, и, ни в коем случае, не пробрасываем!
            System.out.println(e.getMessage());
        }

    }
}
