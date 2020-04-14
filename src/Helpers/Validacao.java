package Helpers;

public class Validacao {
    public static boolean stringCorreta(String text) {
        if (text.equals("")) {
            System.out.println("Ops! O parâmetro informado não pode ser nulo/vazio.");

            return false;
        }

        return true;
    }
}
