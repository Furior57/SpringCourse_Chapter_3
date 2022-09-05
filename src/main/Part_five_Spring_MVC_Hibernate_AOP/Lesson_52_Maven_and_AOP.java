public class Lesson_52_Maven_and_AOP {
    // Работа с аспектами в maven несколько проще, чем просто с AspectJ.
    // Приступим к реализации. Сначала добавим зависимость. В maven repository ищем
    // AspectJ Weaver 1.9.6(версия не важна, но у лектора такая, а мы уже уяснили, как важно
    // соблюдать версии) и добавляем в наш проект.
    // Теперь добавим конфигурацию в applicationContext. Там мы добавили пространство имен
    // xmlns:aop="http://www.springframework.org/schema/aop"
    // И прописали местоположение схемы
    // http://www.springframework.org/schema/aop
    // http://www.springframework.org/schema/aop/spring-aop.xsd
    // А так же после строчки <context:component-scan base-package="com.springApplication" />
    // Прописали <aop:aspectj-autoproxy/>, эта строка нужна чтобы спринг корректно обрабатывал
    // наши классы-аспекты.
    // Теперь создадим пакет com.springApplication.service.Aspects, а внутри него создадим аспекты.
    // Первый - MyLoggingAspect, он будет вести логи всех dao классов. Перейдем в этот метод.
}
