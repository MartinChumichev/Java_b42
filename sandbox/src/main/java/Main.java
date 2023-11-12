public class Main {
    public static void main(String[] args) {
        var a = 1;
        var b = 0;
        if (b == 0) {
            System.out.println("Делить на 0 нельзя");
        } else {
            int c = divide(a, b);
            System.out.println("Привет!");
        }
    }

    private static int divide(int a, int b) {
        var c = a / b;
        return c;
    }
}
