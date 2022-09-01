public class Lesson_48_Annotation_Service {
    // Структура нашей программы на данный момент выглядит так:
    // у нас есть DAO интерфейс с одним методом, который имплементирует DAO класс,
    // контроллер обращается к этому классу, получает данные и передает их во view.
    // А теперь давайте представим, что таких DAO классов больше одного. Контроллер будет
    // вынужден одновременно работать с ними всеми, да мы можем запустить это все через
    // многопоточность, но масштабироваться такая программа все равно будет плохо.
    // Выход из это ситуации предоставляет новый для нас паттерн разработки, он включает в себя
    // такое понятие как Service. Service - это та часть кода в которой заключена бизнес логика,
    // контроллер передает в него запрос, а он уже сам решает к каким ресурсам обращаться для его
    // выполнения. В нашем примере так и останется один DAO класс, но нужно изначально
    // правильно строить наше приложение.

    // В пакет service мы добавили пакет services, да это неправильно, но оставим уже так, не будем рефакторить.
    // В нем мы создадим интерфейс EmployeeService, а затем имплиментируем его в классе
    // EmployeeServiceImpl. В интерфейсе будет точно такой же метод, как и в DAO, getAllEmployees().
    // Возвращать он будет список работников, перейдем в класс имплиментацию EmployeeServiceImpl.
}