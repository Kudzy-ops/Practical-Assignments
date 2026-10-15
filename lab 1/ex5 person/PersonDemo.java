class Address {
    String street;
    String city;
}

class Person {
    String name;
    Address address;      // a field that refers to another object
}

public class PersonDemo {
    public static void main(String[] args) {
        Address a = new Address();
        a.street = "12 Samora Machel Avenue";
        a.city = "Harare";

        Person p1 = new Person();
        p1.name = "Tariro";
        p1.address = a;

        // chained dot: person -> address -> city
        System.out.println(p1.name + " lives in " + p1.address.city);

        Person p2 = new Person();
        p2.name = "Farai";    // address is never assigned, so it is null

        try {
            System.out.println(p2.address.city);   // follows a null reference
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + p2.name + " has no address");
        }

        // the safe way: check before following the reference
        if (p2.address != null) {
            System.out.println(p2.address.city);
        } else {
            System.out.println("Safe check: " + p2.name + " has no address on record");
        }
    }
}
