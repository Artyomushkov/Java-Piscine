package ex00;

public class Program {
    public static void main(String[] args) {
        int number = 479598;
        int res = 0;
        for (int i = 0; i < 6; ++i) {
            int buf = number / 10;
            int digit = number - buf * 10;
            res += digit;
            number = buf;
        }
        System.out.println(res);
    }
}
