package com.springApplication.service.services;

import com.springApplication.entity.Employee;
import com.springApplication.service.DAO.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Аннотация @Service отмечает класс, содержащий бизнес-логику. В иерархии компонентов приложения
// Service являются соединительным звеном между контроллером и DAO. @Service - это специализированный
// @Component. При поиске компонентов, Spring так же будет регистрировать все классы с этой аннотацией.

@Service
public class EmployeeServiceImpl implements EmployeeService {
    // Здесь все аналогично прошлому примеру. Прописываем поля типа EmployeeDAO, подтягиваем
    // зависимости, а после в методе getAllEmployees() возвращаем список сотрудников.
    // Теперь вернемся в MyController на 19 строку
    @Autowired
    private EmployeeDAO employeeDAO;


    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
}
