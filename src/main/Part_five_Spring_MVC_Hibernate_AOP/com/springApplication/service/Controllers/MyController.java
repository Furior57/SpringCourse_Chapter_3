package com.springApplication.service.Controllers;

import com.springApplication.entity.Employee;
import com.springApplication.service.DAO.EmployeeDAO;
import com.springApplication.service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    // Настало время настроить само изменение данных о работнике. Что нам для этого нужно?
    // Прежде всего нам нужен id работника, его мы параметром прописали во view.
    // Здесь мы получаем его следующим образом: передаем аргументом в метод id, перед типом
    // этого аргумента указываем аннотацию @RequestParam в которую передаем имя параметра из view
    // вызвавшего метод updateEmployee. Хорошо, у нас есть id, как нам его применить?
    // Нам необходимо получить из базы данных работника с этим id. Для этого мы должны определить
    // новый метод в наших интерфейсах EmployeeService и EmployeeDAO и имплементировать его
    // в соответствующих классах. По аналогии с прошлым примером EmployeeService лишь вызывает этот метод
    // из EmployeeDAOImpl, назовем метод getEmployee() и перейдем к нему в класс EmployeeDAOImpl
    @RequestMapping("/updateInfo")
    public String updateEmployee(@RequestParam("empId") int id, Model model) {
        // А вот здесь начинаются неочевидные вещи.
        // Первое - мы получили данные о работнике и
        // по нажатию кнопки проваливаемся в этот метод. Здесь мы передаем эти данные в Model
        // аттрибутом. При добавлении, обязательно, имя аттрибута нужно указать то же, что и во
        // view, так как add-employee работает с аттрибутом с названием emp.
        // Второе - на данный момент у нас откроется окно add-employee и при нажатии кнопки OK
        // мы вызовем метод, который будет сохранять нового работника, а не модифицировать
        // существующего. Как это исправить обсудим чуть позже.
        // Третье - несмотря на то, что мы увидим данные нужного нам работника у нас остается
        // проблема их изменения. Программа понятия не имеет какого именно работника ей нужно
        // изменить. view не имеет никаких данных об id. Это нам тоже необходимо исправить.
        // Сначала добавим данные об id. Перейдем в add-employee на 11 строку.
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("emp",employee);

        return "add-employee";
    }
    // Опять же здесь по аналогии мы создаем цепочку методов в интерфейсах и их имплементации в
    // классах, нас интересует EmployeeDAOImpl, перейдем в него к методу deleteEmployee()
    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id) {
        employeeService.deleteEmployee(id);

        return "redirect:/";
    }



}
