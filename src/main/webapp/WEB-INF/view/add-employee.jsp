<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
<%--Ничего кардинально нового здесь для нас нет. Едиственное что тут интересно, это действие,
которое мы передаем в форму. Этим действием мы хотим добавить работника в таблицу. Для этого
нам в контроллере понадобится еще один метод, назовем его saveEmployee() и перейдем к нему.--%>
<h2>Employee info</h2><br>
<form:form action="saveEmployee" modelAttribute="emp">
    <%--Таким образом мы прописываем скрытую форму, отображаться она не будет, но мы
    можем свободно пользоваться информацией которую она содержит. Как мы будем ей пользоваться?
    Перейдем в класс EmployeeDAOImpl к методу saveEmployee()--%>
    <form:hidden path="id"/>
    Name <form:input path="name"/>
    <br>
    <br>
    Surname <form:input path="surname"/>
    <br>
    <br>
    Department <form:select path="department">
    <form:option value="IT" label="IT"/>
    <form:option value="Sales" label="Sales"/>
    <form:option value="HR" label="HR"/>
    </form:select>
    <br>
    <br>
    Salary <form:input path="salary"/>
    <br>
    <br>
    <input type="submit" value="OK">
</form:form>
</body>
</html>
