package Modul_9;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    static Set<Order> setOfOrders = new TreeSet<>();
    static List<Order> listOfOrders = new ArrayList<>();
    static List<User> listOfUsers = new ArrayList<>();


    public static void main(String[] args) {
        creatingListOfOrders();
        creatingSetOfOrders();
        sortByPrice();
        sortByShopAndCity();
        sortByPriceAndCity();
        deletingDuplicates();
        deletingBySpecificPrice();
        separateByCurrency();
        lookingForPetrov();
        maxPrice();
        deletingUSD();
        uniqueCities();
    }

    static public List<User> creatingListOfUsers() {
        listOfUsers.add(new User(1, "Birgit", "Wolf", "Wien", 5000));
        listOfUsers.add(new User(3, "Ann", "Smith", "Prague", 4400));
        listOfUsers.add(new User(2, "Petra", "Szabo", "Wien", 3000));
        listOfUsers.add(new User(4, "Sabine", "Schmidt", "Tirol", 5010));
        listOfUsers.add(new User(5, "David", "Pfiller", "Tirol", 105000));
        listOfUsers.add(new User(6, "Phillip", "Cooper", "Villah", 65000));
        listOfUsers.add(new User(7, "Bruno", "Massi", "Kyiv", 25000));
        listOfUsers.add(new User(8, "Oleg", "Petrov", "Prague", 35000));
        listOfUsers.add(new User(9, "Tanya", "Sidorova", "Kyiv", 150));
        listOfUsers.add(new User(10, "Oksana", "Ivanova", "Kyiv", 1500));
        return listOfUsers;
    }

    static public List<Order> creatingListOfOrders() {
        creatingListOfUsers();
        listOfOrders.add(new Order(101, 1034, Currency.EUR, "Loafers", "Veromoda", listOfUsers.get(0)));
        listOfOrders.add(new Order(102, 254, Currency.EUR, "ChelsyBoots", "Geox", listOfUsers.get(1)));
        listOfOrders.add(new Order(103, 334, Currency.USD, "Espadrilles", "Zara", listOfUsers.get(2)));
        listOfOrders.add(new Order(103, 334, Currency.USD, "Espadrilles", "Zara", listOfUsers.get(2)));
        listOfOrders.add(new Order(104, 254, Currency.USD, "Loafers", "Mango", listOfUsers.get(3)));
        listOfOrders.add(new Order(105, 254, Currency.USD, "Boots", "H&M", listOfUsers.get(4)));
        listOfOrders.add(new Order(106, 254, Currency.EUR, "Boots", "H&M", listOfUsers.get(5)));
        listOfOrders.add(new Order(106, 254, Currency.EUR, "Boots", "H&M", listOfUsers.get(5)));
        listOfOrders.add(new Order(107, 1500, Currency.USD, "Boots", "H&M", listOfUsers.get(6)));
        listOfOrders.add(new Order(108, 1230, Currency.UAH, "Turnschuhe", "Tommy_Hilfiger", listOfUsers.get(7)));
        listOfOrders.add(new Order(109, 2540, Currency.UAH, "Balerinas", "Tom_Tailor", listOfUsers.get(8)));
        listOfOrders.add(new Order(110, 2356, Currency.UAH, "Pumps", "Tommy_Hilfiger", listOfUsers.get(9)));
        return listOfOrders;
    }

    static public Set<Order> creatingSetOfOrders() {
//        creatingListOfOrders();
        setOfOrders.addAll(listOfOrders);
        return setOfOrders;
    }

    static public List<Order> sortByPrice() {
        Collections.sort(listOfOrders, (a, b) -> a.getPrice().compareTo(b.getPrice()));
        System.out.println("The List sorted by Price");
        System.out.println(listOfOrders);
        return listOfOrders;
    }

    static public List<Order> sortByPriceAndCity() {
        List<Order> sortedList= listOfOrders.stream().sorted(
                (a, b) -> a.getPrice() - b.getPrice() == 0 ? a.getUser().getCity().compareTo(b.getUser().getCity()) : a.getPrice().compareTo(b.getPrice())
        ).collect(Collectors.toList());
        System.out.println("The List sorted by Price And City");
        System.out.println(sortedList);
        return sortedList;
    }

    static public List<Order> sortByShopAndCity() {
        List<Order> sortedList = listOfOrders.stream().sorted((o1, o2) ->
                o1.getShopIdentificator().compareTo(o2.getShopIdentificator()) == 0 ? o1.getUser().getCity().compareTo(o2.getUser().getCity()) : o1.getShopIdentificator().compareTo(o2.getShopIdentificator())
        ).collect(Collectors.toList());
        System.out.println("The List sorted by Shop And City");
        System.out.println(sortedList);
        return sortedList;
    }

    static public void deletingDuplicates() {

        List<Order> resultList = listOfOrders.stream().distinct().collect(Collectors.toList());
        System.out.println("Duplicates are deleted");
        System.out.println(resultList);
    }

    static public void deletingBySpecificPrice() {
        listOfOrders = listOfOrders.stream().filter(a-> a.getPrice() > 1500).collect(Collectors.toList());

        System.out.println("Items with the price less then 1500, deleted");
        System.out.println(listOfOrders);
    }

    static public void separateByCurrency() {
        List<Order> listUSDOrders = listOfOrders.stream().filter(a -> a.getCurrency() == Currency.USD).collect(Collectors.toList());
        List<Order> listUAHOrders = listOfOrders.stream().filter(a -> a.getCurrency() == Currency.UAH).collect(Collectors.toList());
        System.out.println("List with UAH");
        System.out.println(listUAHOrders);
        System.out.println("List with USD");
        System.out.println(listUSDOrders);
    }

    static public boolean lookingForPetrov() {
        List<Order> listOfPetrov = listOfOrders.stream().filter(a -> a.getUser().getLastName() == "Petrov").collect(Collectors.toList());
        listOfPetrov.stream().forEach(System.out::println);

        boolean result = false;
//        listOfPetrov.isEmpty() ? result = false : result = true;

        return result;
    }

    static public Order maxPrice() {
        Order orderMaxPrice = setOfOrders.stream().max((a, b) -> a.getPrice().compareTo(b.getPrice())).get();
        System.out.println("The maximum price is " + orderMaxPrice.getPrice() + " " + orderMaxPrice.getCurrency());
        return orderMaxPrice;
    }

    static public void deletingUSD() {
        listOfOrders = listOfOrders.stream().filter(a-> a.getCurrency() != Currency.USD).collect(Collectors.toList());
        System.out.println("Items with the price in USD are deleted");
        System.out.println(listOfOrders);
    }

    static public Map<String, List<User>> uniqueCities() {
        Map<String, List<User>> uniqCitiesMap = listOfUsers.stream().collect(Collectors.groupingBy(User::getCity));

        System.out.println(uniqCitiesMap);
        return uniqCitiesMap;
    }
}