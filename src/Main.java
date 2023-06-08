import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        Stream<Person> children1 = persons.stream();
        Stream<Person> children2 = children1.filter(a -> a.getAge() < 18);
        long count = children2.count();
        System.out.println(count); // кол-во людей младше 18 лет

        Stream<Person> military1 = persons.stream();
        Stream<Person> military2 = military1.filter(a -> a.getSex() == Sex.MAN);
        Stream<Person> military3 = military2.filter(a -> a.getAge() > 18 && a.getAge() < 27);
//        List<Person> a = military3.collect(Collectors.toList()); //проверка
//        System.out.println(a);//проверка
        Stream<String> military4 = military3.map(Person::getFamily);
        List<String> military5 = military4.collect(Collectors.toList());
        System.out.println(military5); // список призывников

        Stream<Person> labor1 = persons.stream();
        Stream<Person> labor2 = labor1.filter(a -> a.getEducation() == Education.HIGHER);
        Stream<Person> labor3 = labor2.filter(a -> a.getSex() == Sex.MAN && a.getAge() > 18 && a.getAge() < 65 || a.getSex() == Sex.WOMAN && a.getAge() > 18 && a.getAge() < 60);
        Stream<Person> labor4 = labor3.sorted(Comparator.comparing(Person::getFamily));
        List<Person> labor5 = labor4.collect(Collectors.toList());
        System.out.println(labor5); //отсортированный по фамилии список потенциально работоспособных людей


    }
}
