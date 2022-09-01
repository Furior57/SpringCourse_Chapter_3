package com.springApplication.service.Controllers;

import com.springApplication.entity.Employee;
import com.springApplication.service.DAO.EmployeeDAO;
import com.springApplication.service.services.EmployeeService;
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
    // подтянуть все зависимости. Перейдем на 28 строку.
    @Autowired
    // Закомментируем поле типа EmployeeDAO и вместо него создадим новое с типом нашего сервиса.
    // Ниже, там где мы получаем список работников мы так же закомментируем строку и заменим ее
    // на другую, использующую сервис. Теперь мы можем не трогать контроллер, а все изменения
    // логики проводить уже в самом сервисе. Еще один момент, аннотацию @Transactional в
    // методе getAllEmployees() класса EmployeeDAOImpl мы перенесем в аналогичный метод
    // нашего сервиса, так как именно он теперь будет обращаться к базе данных, а значит ему и закрывать
    // сессию, несмотря на то, что открыта она в классе EmployeeDAOImpl.
//    private EmployeeDAO employeeDAO;
    private EmployeeService employeeService;
    // Этот метод будет выводить список со всеми работниками на экран, указываем команду
    // при которой он будет это делать, в нашем случае список будет появляться сразу после того
    // как мы зайдем на сайт.
    @RequestMapping("/")
    public String showAllEmployees(Model model) {
        // Получаем список работников.
//        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        List<Employee> allEmployees = employeeService.getAllEmployees();
        // Добавляем в model аттрибут, первый параметр имя аттрибута, второй тот список который
        // и будет этим аттрибутом.
        model.addAttribute("allEmps", allEmployees);
        // возвращаем view, создадим этот view в соответствующей папке и перейдем в него.
        return "all-employees";
    }
}
