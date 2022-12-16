package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CourseEnrollmentSummaryServlet", value = "/CourseEnrollmentSummary")
public class CourseEnrollmentSummaryServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;

    public CourseEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.listAll();
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("studentsList", students);
        springTemplateEngine.process("selectStudent.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("selectedStudent").split("-")[2];
        Student toFind = studentService.findByUsername(username);
        resp.setCharacterEncoding("UTF-8");
        List<Course> coursesToShow = new ArrayList<>();
        for (Course c : courseService.listAllCourses()) {
            List<Student> studentsInCourse = c.getStudents();
            if (studentsInCourse.contains(toFind))
                coursesToShow.add(c);
        }
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("coursesList", coursesToShow);
        context.setVariable("student", toFind);
        springTemplateEngine.process("coursesByStudent.html", context, resp.getWriter());
    }
}