package com.operation;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/addition")
public class Addition extends HttpServlet {

    public void service(HttpServletRequest req,
                        HttpServletResponse res
                        ) throws IOException {
        // what type of response that the client will received
        res.setContentType("text/html");

        // extract request
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));

        int sum = num1 + num2;

        PrintWriter print = res.getWriter();
        print.println("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link rel=\"stylesheet\" href=\"styles/style.css\">\n" +
                "    <title>My First Servlet</title>\n" +
                "</head>\n" +
                "    <body>\n" +
                "        <div class=\"container\">\n" +
                "            <h1 id=\"greeting\">Add 2 Numbers</h1>\n" +
                "\n" +
                "            <div class=\"result\">\n" + sum +
                "\n" +
                "            </div>\n" +
                "\n" +
                "            <form action=\"addition\">\n" +
                "                <label for=\"num1\">First Number</label>\n" +
                "                <input type=\"number\" name=\"num1\" required>\n" +
                "\n" +
                "                <label for=\"num2\">Second Number</label>\n" +
                "                <input type=\"number\" name=\"num2\" required>\n" +
                "\n" +
                "                <button type=\"submit\">\n" +
                "                    Get Sum\n" +
                "                </button>\n" +
                "\n" +
                "            </form>\n" +
                "        </div>\n" +
                "\n" +
                "<!--        <script src=\"scripts/script.js\"></script>-->\n" +
                "    </body>\n" +
                "</html>");
    }
}
