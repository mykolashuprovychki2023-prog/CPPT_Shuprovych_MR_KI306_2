package KI306.Shuprovych.Lab6;

public class Driver {
    public static void main(String[] args) {
        // Демонстрація з ItemA
        GenericSet<ItemA> setA = new GenericSet<>();
        setA.add(new ItemA("Alpha", 10));
        setA.add(new ItemA("Beta", 5));
        setA.add(new ItemA("Gamma", 20));
        setA.add(new ItemA("Beta", 5)); // дублікат — не додасться

        System.out.println("\nSetA contents: " + setA);
        System.out.println("Size A: " + setA.size());
        System.out.println("Min in A: " + setA.findMin()); // парний варіант: мінімум

        // Демонстрація з ItemB
        GenericSet<ItemB> setB = new GenericSet<>();
        setB.add(new ItemB("W1", 2.5));
        setB.add(new ItemB("W2", 1.2));
        setB.add(new ItemB("W3", 3.7));

        System.out.println("\nSetB contents: " + setB);
        System.out.println("Size B: " + setB.size());
        System.out.println("Min in B: " + setB.findMin());
    }
}
