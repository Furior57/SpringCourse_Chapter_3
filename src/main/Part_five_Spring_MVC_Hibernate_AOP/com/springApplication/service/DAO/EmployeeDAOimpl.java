package com.springApplication.service.DAO;

import com.springApplication.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
// Что будет делать этот класс? Он будет подключаться к базе данных. Как мы помним подключение
// происходит с помощью SessionFactory. При определении контекста приложения мы заранее указали в конфиге
// бины отвечающие за подключение к базе данных и создание сессии. Сделано это было для того,
// чтобы нам не приходилось каждый раз волноваться о закрытии сессии и писать повторяющийся код.
// Здесь же в классе мы создаем поле типа SessionFactory и помечаем его аннотацией @Autowired,
// так как сам бин создающий сессию не умеет подключаться к БД, в нем просто прописана зависимость
// от бина который это умеет. Это стандартные настройки, запоминать их написание не обязательно,
// Они кочуют из проекта в проект в неизменном виде. Кстати в бине sessionFactory мы заранее указали
// пакет в котором он будет искать Entity объекты.

// DAO объекты точно так же являются бинами, а значит нам необходимо пометить их аннотацией
// @Component, но для этих объектов, как и для контроллеров есть собственная аннотация
// @Repository - это специализированный @Component, используется исключительно для DAO.
// При поиске компонентов Spring так же зарегистрирует класс с этой аннотацией в контейнере.
// Конечно это далеко не единственная функция этой аннотации, но сейчас просто остановимся на том,
// что DAO объекты маркируются именно так.
// Перейдем к методу getAllEmployees()
@Repository
public class EmployeeDAOimpl implements EmployeeDAO{

    @Autowired
    private SessionFactory sessionFactory;
    // Мы пометили этот метод аннотацией @Transactional, импортированной из библиотеки
    // springframework. Эта аннотация означает, что Spring берет на себя ответственность
    // за открытие и закрытие транзакций, а нам лишь достаточно получить объект сессии.
    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        // Получаем сессию
        Session session = sessionFactory.getCurrentSession();
        // А здесь с помощью запроса получаем всех работников из таблиц, причем после from
        // мы указываем не название таблицы, а название объекта который получаем, а вторым параметром
        // указываем какой именно класс будет получать эту информацию. Результат получаем методом
        // getResultList()
//        List<Employee> allEmployees = session.createQuery(
//                "from Employee", Employee.class).getResultList();
        // Однако мы можем разделить эту операцию на две части, для лучшей читаемости, эта
        // возможность описана в документации Hibernate. Сначала мы определяем сам запрос:
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        // А потом выполняем его, здесь сразу же и вернем результат:
        return query.getResultList();
        // Теперь перейдем к контроллеру MyController.
    }
}
