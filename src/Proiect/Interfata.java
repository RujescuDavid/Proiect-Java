package Proiect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfata extends JFrame {

    private Meniu meniu;

    public Interfata(Meniu meniu) {
        this.meniu = meniu;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Evidenta Studentilor");

        int selectieProfil = selecteazaProfil();
        if (selectieProfil == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "La revedere!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] message = {
                "Email:", emailField,
                "Parola:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Autentificare", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String email = emailField.getText();
            char[] password = passwordField.getPassword();
            String parola = new String(password);

            if (selectieProfil == 0) {
                if (autentificareStudent(email, parola)) {
                    afiseazaInterfataStudent();
                } else {
                    JOptionPane.showMessageDialog(null, "Ati introdus date incorecte! La revedere!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            } else {
                if (autentificareProfesor(email, parola)) {
                    afiseazaInterfataProfesor();
                } else {
                    JOptionPane.showMessageDialog(null, "Ati introdus date incorecte! La revedere!", "Eroare", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
            }
        }
    }

    private int selecteazaProfil() {
        Object[] options = {"Student", "Profesor"};
        return JOptionPane.showOptionDialog(null, "Selecteaza un profil:", "Selectare Profil", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private boolean autentificareStudent(String email, String parola) {
        return Autentificare.autentifica(email, parola, "student");
    }

    private boolean autentificareProfesor(String email, String parola) {
        return Autentificare.autentifica(email, parola, "profesor");
    }

    private void afiseazaInterfataPrincipala() {
        JButton buttonAdauga = new JButton("Adauga student");
        JButton buttonEditare = new JButton("Editare date student");
        JButton buttonSterge = new JButton("Sterge student");
        JButton buttonCauta = new JButton("Cauta student");
        JButton buttonVizualizare = new JButton("Vizualizare studenti");

        buttonAdauga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                meniu.adauga();
            }
        });

        buttonEditare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                meniu.actualizeaza();
            }
        });

        buttonSterge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                meniu.sterge();
            }
        });

        buttonCauta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                meniu.cauta();
            }
        });

        buttonVizualizare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                meniu.vizualizare();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.add(buttonAdauga);
        panel.add(buttonEditare);
        panel.add(buttonSterge);
        panel.add(buttonCauta);
        panel.add(buttonVizualizare);

        getContentPane().add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void afiseazaInterfataPrincipala2() {
        JButton buttonCauta = new JButton("Cauta student");
        JButton buttonVizualizare = new JButton("Vizualizare studenti");


        buttonCauta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                meniu.cauta();
            }
        });

        buttonVizualizare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                meniu.vizualizare();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(buttonCauta);
        panel.add(buttonVizualizare);

        getContentPane().add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void afiseazaInterfataProfesor() {
        JOptionPane.showMessageDialog(null, "Bine ati venit, profesor!", "Autentificare reusita", JOptionPane.INFORMATION_MESSAGE);
        afiseazaInterfataPrincipala();
    }
    
    private void afiseazaInterfataStudent() {
        JOptionPane.showMessageDialog(null, "Bine ati venit, Student!", "Autentificare reusita", JOptionPane.INFORMATION_MESSAGE);
        afiseazaInterfataPrincipala2();
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Meniu meniu = new Meniu();
                Interfata gui = new Interfata(meniu);
            }
        });
    }

	@Override
	public String toString() {
		return "Interfata [meniu=" + meniu + "]";
	}
}
