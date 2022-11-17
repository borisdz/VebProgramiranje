package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.exceptions.CourseAlreadyExistsException;
import mk.ukim.finki.wp.lab.model.exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping()
    public String getCoursesPage(@RequestParam(required = false) String error, HttpServletRequest request, Model model) {
        if (error != null) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("coursesList", courseService.
                listAllCourses()
                .stream()
                .sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList()));
        model.addAttribute("users", request.getServletContext().getAttribute("users"));
        return "listCourses";
    }

    @PostMapping("/courses/add")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String teacherId,
                             @RequestParam(required = false) String courseId) {

        if (courseId != null) {
            try {
                Course kurs = courseService.findById(Long.parseLong(courseId));
                if (courseService.findByName(name).isPresent())
                    return "redirect:/courses?error=" + "Imeto na kurs koe probuvate da go vnesete vekje postoi.";
                kurs.setName(name);
                kurs.setDescription(description);
                kurs.setTeacher(teacherService.findById(Long.parseLong(teacherId)));
            } catch (CourseNotFoundException exception) {
                return "redirect:/courses?error=" + exception.getMessage();
            }
        } else {
            try {
                courseService.addCourse(name, description, Long.parseLong(teacherId));
            } catch (CourseAlreadyExistsException exception) {
                return "redirect:/courses?error=" + exception.getMessage();
            }
        }

        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}
