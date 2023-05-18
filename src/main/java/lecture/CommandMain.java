package lecture;

import java.util.LinkedList;

public class CommandMain {
    public interface Command {
        void execute();
    }
    public static void main(String[] args) {
        LinkedList<Command> commands = new LinkedList<>();
        commands.add(() -> System.out.println("Action 1"));
        commands.add(() -> System.out.println("Action 2"));
        commands.add(() -> System.out.println("Action 3"));
        System.out.println("Executing in reverse!");
        commands.descendingIterator()
                .forEachRemaining(Command::execute);
    }
}
//шаблон проектирования Command (с анг. команда).
//● Мы хотим действия не только
//выполнять, но и обращаться с ними как
//с объектами
//● Просто создать метод с java-кодом
//действия теперь недостаточно
//● Создадим для действия не метод, а
//объект этого действия, у которого будет
//метод при вызове которого это
//действие выполняется.
//● Теперь мы можем обращаться с
//действиями так, как обращаемся с
//объектами. Например, создать список из
//них и выполнить в обратном порядке