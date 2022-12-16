package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.exceptions.InvalidStudentException;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentEnrollmentSummaryServlet", value = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummaryServlet extends HttpServlet{
    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            resp.setCharacterEncoding("UTF-8");
            String username = req.getParameter("student");
            Long course = Long.parseLong((String) req.getSession().getAttribute("courseId"));
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("course", courseService.addStudentInCourse(username, course));
            req.getSession().invalidate();
            springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
        } catch (InvalidStudentException e){
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("hasError", true);
            context.setVariable("error", e.getMessage());
            context.setVariable("coursesList", courseService.listAllCourses());
            req.getSession().invalidate();
            springTemplateEngine.process("listCourses.html", context, resp.getWriter());
        }
    }
}
