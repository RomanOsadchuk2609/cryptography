package caesar;

public class Main {
    private static final String INPUT = "Tratata".toLowerCase();

    public static void main(String[] args) {
        Encoder encoder = new Encoder(5);
        String result = encoder.encode(INPUT);
        System.out.println(result);
    }
}
