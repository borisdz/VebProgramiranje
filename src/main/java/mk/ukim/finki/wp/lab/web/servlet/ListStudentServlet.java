package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.impl.StudentServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="student-servlet", urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {

    private final StudentServiceImpl studentService;
    private final SpringTemplateEngine springTemplateEngine;

    public ListStudentServlet(StudentServiceImpl studentService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("students", studentService.listAll());
        this.springTemplateEngine.process("listStudents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("username", req.getParameter("username"));
        resp.sendRedirect("/StudentEnrollmentSummary");
    }
}
