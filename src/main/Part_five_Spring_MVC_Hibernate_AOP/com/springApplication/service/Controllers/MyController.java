package com.springApplication.service.Controllers;

import com.springApplication.entity.Employee;
import com.springApplication.service.DAO.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
// У нас есть DAO класс, который получает список всех работников, теперь нам необходимо
// обработать этот список в контроллере и передать его во view
@Controller
public class MyController {
    // Первым делом мы создадим поле обращаясь к которому мы и будем получать список работников.
    // Как можно догадаться это будет наш EmployeeDAO, помечаем ег аннотацией @Autowired, чтобы
    // подтянуть все зависимости.
    @Autowired
    private EmployeeDAO employeeDAO;
    // Этот метод будет выводить список со всеми работниками на экран, указываем команду
    // при которой он будет это делать, в нашем случае список будет появляться сразу после того
    // как мы зайдем на сайт.
    @RequestMapping("/")
    public String showAllEmployees(Model model) {
        // Получаем список работников.
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        // Добавляем в model аттрибут, первый параметр имя аттрибута, второй тот список который
        // и будет этим аттрибутом.
        model.addAttribute("allEmps", allEmployees);
        // возвращаем view, создадим этот view в соответствующей папке и перейдем в него.
        return "all-employees";
    }
}
