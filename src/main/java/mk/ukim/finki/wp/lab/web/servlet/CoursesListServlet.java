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

@WebServlet(name="courses-servlet", urlPatterns = "/listCourses")
public class CoursesListServlet extends HttpServlet {

    private final CourseServiceImpl courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public CoursesListServlet(CourseServiceImpl courseService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("courses", (Object) courseService.listAll());
        this.springTemplateEngine.process("listCourses.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("courseId", req.getParameter("courseId"));
        resp.sendRedirect("/addStudent");
    }
}
