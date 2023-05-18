package lecture;

import java.util.Arrays;
import java.util.List;

public class CoRMain {
    public interface Processor {
        boolean process(String msg);
    }

    static final List<Processor> PROCESSORS = Arrays.asList(
            (msg) -> {
                if (msg.startsWith("Hello")) {
                    System.out.println("Hi, customer!");
                    return true;
                }
                return false;
            },
            (msg) -> {
                if (msg.startsWith("Привет")) {
                    System.out.println("Здравствуй, клиент!");
                    return true;
                }
                return true;
            },
            (msg) -> {
                System.out.println("Админ, мы этого языка не знаем!");
                return true;
            }
    );

    public static void main(String[] args) {
        String msg = "Привет, дружок";
        for (Processor processor : PROCESSORS)
            if (processor.process(msg))
                break;
    }
}
// шаблон проектирования Chain of responsibility
// (с анг. цепочка ответственности).
//● Мы хотим сделать слишком большую
//обработку
//● Разделим её на части, логику каждой
//части поместим в отдельный объектобработчик
//● Пройдёмся по нашим обработчикам
//и последовательно попытаемся их
//применить
//● Могут быть вариации: с прерыванием
//после первого успеха (как в примере)
//или же с обязательным применением
//всех обработчиков