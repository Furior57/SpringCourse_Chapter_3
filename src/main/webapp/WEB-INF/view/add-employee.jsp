<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<body>
<%--Ничего кардинально нового здесь для нас нет. Едиственное что тут интересно, это действие,
которое мы передаем в форму. Этим действием мы хотим добавить работника в таблицу. Для этого
нам в контроллере понадобится еще один метод, назовем его saveEmployee() и перейдем к нему.--%>
<h2>New employee info</h2><br>
<form:form action="saveEmployee" modelAttribute="emp">
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
    Salary <form:input path="salary"/>
    <br>
    <br>
    <input type="submit" value="Add">
</form:form>
</body>
</html>
