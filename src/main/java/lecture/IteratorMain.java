package lecture;
//Поведенческий шаблон Iterator
//ОТВЕТ: это можно сделать через шаблон
//проектирования Iterator (с анг. итератор).
//● У нас есть класс C, который по смыслу
//является коллекцией элементов
//● Если кто-то хочет перебрать элементы
//нашей коллекции, мы выдаём ему
//“бегунок” - объект, у которого можно
//просить “дай следующий элемент в
//коллекции”. Это и будет итератором.
//● Получив итератор, пользователя больше
//не волнует то как мы внутри нашей
//коллекции храним элементы. Всё что
//ему надо - подстроить свой код под
//интерфейс взаимодействия с
//итератором.
//28
//public interface Iterator<E> {
// boolean hasNext();
// E next();
//}
//public interface Iterable<E> {
// Iterator<E> iterator();
//}
//Java уже имеет стандартный интерфейс
//Iterator, основная часть которого
//приведена выше. Он активно используется в
//коллекциях и многих других местах, а цикл
//foreach под капотом чаще всего сводится
//именно к нему.
//Получив от коллекции объект-итератор, вы
//через next() получаете следующий элемент
//в переборе, а через hasNext проверяете
//остались ли ещё неперебранные элементы.
//Iterable - интерфейс для коллекций, по
//которым можно пройтись итератором.

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorMain {

    static class Todos implements Iterable<String> {//-список дел
        private List<String> primary = new ArrayList<>();
        private List<String> secondary = new ArrayList<>();

        public Todos addPrimary(String task) {
            primary.add(task);
            return this;
        }

        public Todos addSecondary(String task) {
            secondary.add(task);
            return this;
        }

        @Override
        public Iterator<String> iterator() {// реализуем через анонимный класс
            return new Iterator<>() {
                boolean isPrimary = true;
                int nextIndex = 0;

                @Override
                public boolean hasNext() {
                    if (isPrimary) {
                        if (nextIndex < primary.size()) {
                            return true;
                        } else {
                            return !secondary.isEmpty();
                        }
                    } else {
                        return nextIndex < secondary.size();
                    }
                }

                @Override
                public String next() {
                    if (isPrimary) {
                        if (nextIndex < primary.size()) {
                            String task = primary.get(nextIndex);
                            nextIndex++;
                            return task;
                        } else {
                            isPrimary = false;
                            String task = secondary.get(0);
                            nextIndex = 1;
                            return task;
                        }
                    } else {
                        String task = secondary.get(nextIndex);
                        nextIndex++;
                        return task;
                    }
                }
            };
        }
    }

    public static void main(String[] args) {
        Todos todos = new Todos()
                .addPrimary("Run")
                .addPrimary("Working")
                .addSecondary("Shopping")
                .addPrimary("Learning");

        for (String todo : todos) {
            System.out.println("\t- " + todo);

        }
    }
}
