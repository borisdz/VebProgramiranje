package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.impl.CourseServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "", urlPatterns = "/studentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseServiceImpl courseService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseServiceImpl courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        Long courseId = Long.parseLong((String) req.getSession().getAttribute("courseId"));
        String username = (String) req.getSession().getAttribute("username");
        courseService.addStudentInCourse(username, courseId);
        context.setVariable("courses", courseService.listAll());
        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }
}
