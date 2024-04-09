package Proiect;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Autentificare {

    private static final String fisier_autentificare = "autentificare.txt";

    public static boolean autentifica(String email, String parola, String tipUtilizator) {
        try (BufferedReader br = new BufferedReader(new FileReader(fisier_autentificare))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] detaliiAutentificare = linie.split(",");
                if (detaliiAutentificare.length == 3) {
                    String tipUtilizatorFisier = detaliiAutentificare[0].trim();
                    String emailFisier = detaliiAutentificare[1].trim();
                    String parolaFisier = detaliiAutentificare[2].trim();

                    if (email.equals(emailFisier) && parola.equals(parolaFisier) && tipUtilizator.equals(tipUtilizatorFisier)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
