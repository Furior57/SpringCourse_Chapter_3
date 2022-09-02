package com.springApplication.service.Controllers;

import com.springApplication.entity.Employee;
import com.springApplication.service.DAO.EmployeeDAO;
import com.springApplication.service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    // Определим новый метод. Вызываться он будет по тому адресу который мы передали
    // в настройках кнопки Add. Что мы хотим? Мы хотим добавить работника в таблицу.
    // То есть нам необходим объект Model, его мы передадим в метод аргументом, и
    // объект Employee, который мы аттрибутом в этот Model передадим, для дальнейшего
    // использования. Employee создадим внутри метода используя конструктор без параметров.
    // Теперь создадим view add-employee и перейдем в него.
    @RequestMapping("/addNewEmployee")
    public String addNewEmployee(Model model) {

        Employee employee = new Employee();
        model.addAttribute("emp", employee);

        return "add-employee";
    }
    // А вот сейчас будет несколько запутанно. Вспоминаем как устроена наша программа.
    // Наш контроллер передает запрос в сервис, а значит нам необходимо создать в нем
    // метод для сохранения работника, сначала мы определим этот метод в интерфейсе
    // EmployeeService, а потом имплиментируем в самом сервисе, не забыв при этом поставить
    // аннотацию @Transactional, чтобы не беспокоиться о закрытиях сессий.
    // Вспоминаем дальше, как работает наш сервис. Он сам ничего не делает, он обращается
    // к классам ответственным за обработку данных, у нас это employeeDAO. А значит
    // в этом классе мы тоже создаем метод saveEmployee() внутри которого уже строим
    // логику. Там все просто, получаем сессию, сохраняем объект, который заботливо передали
    // по цепочке из контроллера. Не забудем, что в интерфейсе EmployeeDAO нам так же нужно
    // определить метод, для порядка. Здесь же мы возвратом перенаправляем на стартовую
    // страницу вот такой записью "redirect:/".
    @RequestMapping("/saveEmployee")
    // И еще одно, чтобы получить из view объект, мы пользуемся аннотацией @ModelAttribute,
    // в которой прописываем название аттрибута созданного выше, после аннотации указываем тип
    // этого аттрибута и имя.
    public String saveEmployee(@ModelAttribute("emp") Employee employee) {

        employeeService.saveEmployee(employee);

        return "redirect:/";
    }

}
