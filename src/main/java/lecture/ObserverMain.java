package lecture;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ObserverMain {
    public static class Emitter { //Передатчик
        private List<Consumer<String>> subscribers = new ArrayList<>();// реакция на событие-Consumer<String>
        public void subscribe(Consumer<String> s) {// действие не вызываем, а сохраняем в список "консюмеров"
            subscribers.add(s);
        }
        public void say(String msg) {
            System.out.println("I say " + msg);
            subscribers.forEach(s -> s.accept(msg));
        }
    }
    public static class R {
        public void refute(String msg) {
            System.out.println("No! " + msg +
                " is false!");
        }
    }
    public static void main(String[] args) {
        R r = new R();
        Emitter emitter = new Emitter();
        emitter.subscribe(r::refute);
        emitter.subscribe(msg -> System.out.println(msg.toUpperCase() +
                "!!!"));
        emitter.say("Earth is round");
    }
}
// Шаблон проектирования Observer (с анг.
//наблюдатель).
//● Мы хотим эффективно доносить
//события (например, String msg) от
//объекта a до объектов b и c и только до
//них.
//● Делаем возможность у объекта b через
//метод “подписаться” на события. Теперь
//можно указать код, который надо
//выполнить у a или c если событие
//произойдёт. a запоминает своих
//подписчиков.
//● Когда событие происходит, a
//пробегается по своим подписчикам и
//вызывает указанный код обработчиков.