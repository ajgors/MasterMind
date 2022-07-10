import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {

    public static String instructions() {
        return "\nkomputer losuje kod, który gracz ma odgadnąć\n" +
                "kod składa się z 4 losowych cyfr od 1 do 6 (cyfry mogą się powtarzać)\n" +
                "np. 1234\n" +
                "1122\n" +
                "1111\n" +
                "itd.\n " +
                "Gracz ma 10 szans na odgadnięcie kodu.\n" +
                "Po każdej próbie odgadnięcia kodu  komputer ocenia\n" +
                "- ile cyfr w kodzie jest na swoim miejscu: punktacja 1\n" +
                "- ile cyfr jest w kodzie ale nie na swoim miejscu: punktacja 0\n" +
                "jeżeli podana cyfra nie występuje w kodzie nie dostaje żadnego punktu\n" +
                "punktacja jest podawana tak, żeby nie zdradzić do których miejsc w kodzie się odnosi\n" +
                "najpierw podawane są pełne punkty a potem  zera\n";
    }

    public static int computerRandomNumber() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 4; i++) {
            result += random.nextInt(1, 7);
        }

        return Integer.parseInt(result);
    }

    public static String score(int userNumber, int computerNumber) {

        String userNumberString = String.valueOf(userNumber);
        String computerNumberString = String.valueOf(computerNumber);
        String score = "";
        int[] userNumbers = new int[4];
        int[] computerNumbers = new int[4];


        for (int i = 0; i < 4; i++) {
            userNumbers[i] = Integer.parseInt(String.valueOf(userNumberString.charAt(i)));
            computerNumbers[i] = Integer.parseInt(String.valueOf(computerNumberString.charAt(i)));
        }

        // Sprawdzanie liczba na swoich miejscach
        for (int i = 0; i < 4; i++) {
            if (userNumbers[i] == computerNumbers[i]) {
                userNumbers[i] = 0;
                computerNumbers[i] = -1;
                score += 1;
            }

        }

        // Sprawdzanie liczb na nie sowich miejscach
        for (int j = 0; j < 4; j++) {
            if (userNumbers[j] != computerNumbers[j]) {
                for (int k = 0; k < 4; k++) {
                    if (userNumbers[j] == computerNumbers[k]) {
                        score += 0;
                        userNumbers[j] = -2;
                        computerNumbers[k] = -3;

                    }
                }
            }
        }


        return score;
    }

    public static void main(String[] args) {
        System.out.println("Podaj kod");
        System.out.println("Masz 10 szans na odgadnięcie liczby");
        System.out.println("Napisz help aby wyświetlić instrukcje gry");
//        int computerNumber = computerRandomNumber();
        int computerNumber = 4511;
        Scanner scanner = new Scanner(System.in);

        int counter = 1;
        while (true) {
            if (scanner.hasNextInt()) {
                int userNumber = scanner.nextInt();

                if (userNumber > 6666 || userNumber < 999 ||
                        Integer.toString(userNumber).contains("0") ||
                        Integer.toString(userNumber).contains("7") ||
                        Integer.toString(userNumber).contains("8") ||
                        Integer.toString(userNumber).contains("9")) {
                    System.out.println("Podaj poprawny kod");
                    continue;
                }

                if (userNumber != computerNumber) {
                    System.out.println("(" + counter + ")" + "[" + userNumber + "]"
                            + " punktacja " + "[" + score(userNumber, computerNumber) + "]");
                    counter++;
                    if (counter == 11) {
                        System.out.println("Niestety nie udało CI się odganąć mojego kodu.");
                        System.out.println("Mój kod to:");
                        System.out.println(computerNumber);
                        break;
                    }
                } else {
                    System.out.println("Brawo odgadłeś mój kod !");
                    break;
                }
            }
            else if (scanner.hasNext("help")) {
                System.out.println(instructions());
                scanner.nextLine();
            } else {
                System.out.println("Podaj poprawny kod");
                scanner.nextLine();
            }

        }
    }
}