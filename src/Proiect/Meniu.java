package Proiect;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Meniu {

	public static void vizualizare() {
		JFrame frame = new JFrame("Afisare Studenti");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			File file = new File("studenti.txt");
			Scanner scanner = new Scanner(file);

			StringBuilder studentiText = new StringBuilder();
			while (scanner.hasNextLine()) {
				String linie = scanner.nextLine();
				studentiText.append(linie).append("\n");
			}
			scanner.close();

			JTextArea textArea = new JTextArea(studentiText.toString());
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setFont(new Font("Arial", Font.PLAIN, 12));

			// Setează alinierea textului la centru
			textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
			textArea.setAlignmentY(Component.CENTER_ALIGNMENT);

			JScrollPane scrollPane = new JScrollPane(textArea);

			frame.getContentPane().add(scrollPane);
			frame.setSize(400, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Fișierul nu a fost găsit: " + e.getMessage(), "Eroare",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void adauga() {
		JFrame frame = new JFrame("Adaugare student");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		panel.add(new JLabel("Numar matricol:"));
		JTextField nrMatricolField = new JTextField();
		panel.add(nrMatricolField);

		panel.add(new JLabel("Nume:"));
		JTextField numeField = new JTextField();
		panel.add(numeField);

		panel.add(new JLabel("Prenume:"));
		JTextField prenumeField = new JTextField();
		panel.add(prenumeField);

		panel.add(new JLabel("Email:"));
		JTextField emailField = new JTextField();
		panel.add(emailField);

		panel.add(new JLabel("Specializare:"));
		JTextField specializareField = new JTextField();
		panel.add(specializareField);

		panel.add(new JLabel("Anul de studiu:"));
		JTextField anStudiuField = new JTextField();
		panel.add(anStudiuField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Adauga student", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			String nrMatricol = nrMatricolField.getText();

			if (studentulExist(nrMatricol)) {
				JOptionPane.showMessageDialog(null, "Studentul cu numărul matricol " + nrMatricol + " există deja!",
						"Avertisment", JOptionPane.WARNING_MESSAGE);
				return;
			}

			String nume = numeField.getText();
			String prenume = prenumeField.getText();
			String email = emailField.getText();
			String specializare = specializareField.getText();
			String anStudiu = anStudiuField.getText();

			Student student = new Student(nume, prenume, email, specializare, anStudiu, nrMatricol);

			try (BufferedWriter writer = new BufferedWriter(new FileWriter("studenti.txt", true))) {
				writer.write(student.toString());
				writer.newLine();
				JOptionPane.showMessageDialog(null, "Student adaugat cu succes!", "Succes",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Eroare la adaugarea studentului!", "Eroare",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
			}
		}
	}

	private static boolean studentulExist(String nrMatricol) {
		try (BufferedReader br = new BufferedReader(new FileReader("studenti.txt"))) {
			String linie;
			while ((linie = br.readLine()) != null) {
				if (linie.contains("Numar matricol: " + nrMatricol)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Eroare la verificarea existenței studentului!", "Eroare",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return false;
	}

	public static void cauta() {
		// Creăm o fereastră de dialog pentru introducerea numărului matricol căutat
		JFrame frame = new JFrame("Cautare student");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));

		panel.add(new JLabel("Numar matricol:"));
		JTextField nrMatricolField = new JTextField();
		panel.add(nrMatricolField);

		int result = JOptionPane.showConfirmDialog(null, panel, "Cautare student", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			// Colectăm numărul matricol introdus
			String nrMatricolCautat = nrMatricolField.getText();

			try {
				File file = new File("studenti.txt");
				Scanner scanner = new Scanner(file);

				boolean studentGasit = false;

				while (scanner.hasNextLine()) {
					String linie = scanner.nextLine();
					if (linie.startsWith("Numar matricol: " + nrMatricolCautat)) {
						// Extragem numele, prenumele, emailul, specializarea și anul de studii din
						// linia găsită
						String linie1 = scanner.nextLine();
						String nume = extrageInformatie(linie1, "Nume: ");
						String linie2 = scanner.nextLine();
						String prenume = extrageInformatie(linie2, "Prenume: ");
						String linie3 = scanner.nextLine();
						String email = extrageInformatie(linie3, "Email: ");
						String linie4 = scanner.nextLine();
						String specializare = extrageInformatie(linie4, "Specializare: ");
						String linie5 = scanner.nextLine();
						String anStudiu = extrageInformatie(linie5, "An de studiu: ");

						// Afișăm rezultatul într-o fereastră de dialog
						String mesaj = String.format(
								"Student gasit!\nNume: %s\nPrenume: %s\nEmail: %s\nSpecializare: %s\nAnul de studiu: %s",
								nume, prenume, email, specializare, anStudiu);
						JOptionPane.showMessageDialog(null, mesaj, "Rezultat Cautare", JOptionPane.INFORMATION_MESSAGE);
						studentGasit = true;
						break; // Întrerupem bucla după găsirea primului student cu numărul matricol căutat
					}
				}

				if (!studentGasit) {
					JOptionPane.showMessageDialog(null, "Niciun student gasit cu numarul matricol: " + nrMatricolCautat,
							"Rezultat Cautare", JOptionPane.INFORMATION_MESSAGE);
				}

				scanner.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Fișierul nu a fost găsit: " + e.getMessage(), "Eroare",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private static String extrageInformatie(String linie, String prefix) {
		// Extrage informația după prefix din linia dată
		int startIndex = linie.indexOf(prefix) + prefix.length();
		return linie.substring(startIndex).trim();
	}

	public static void actualizeaza() {
		// Actualizare student existent
		JTextField nrMatricolField1 = new JTextField();
		Object[] message1 = { "Introduceti numarul matricol al studentului de actualizat:", nrMatricolField1 };

		int option1 = JOptionPane.showConfirmDialog(null, message1, "Actualizare Student",
				JOptionPane.OK_CANCEL_OPTION);
		if (option1 == JOptionPane.OK_OPTION) {
			String nrMatricolCautat = nrMatricolField1.getText().trim();

			try (BufferedReader br = new BufferedReader(new FileReader("studenti.txt"));
					BufferedWriter writer = new BufferedWriter(new FileWriter("temp_studenti.txt"))) {

				String linie;
				boolean studentGasit = false;

				while ((linie = br.readLine()) != null) {
					if (linie.startsWith("Numar matricol: ")
							&& linie.substring("Numar matricol: ".length()).trim().equals(nrMatricolCautat)) {
						for (int i = 0; i < 8; i++) {
							br.readLine();
						}
						studentGasit = true;
					} else {
						writer.write(linie);
						writer.newLine();
					}
				}

				if (!studentGasit) {
					JOptionPane.showMessageDialog(null, "Niciun student gasit cu numarul matricol " + nrMatricolCautat,
							"Actualizare Student", JOptionPane.ERROR_MESSAGE);
					return;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			Path tempPath = Paths.get("temp_studenti.txt");
			try {
				Files.move(tempPath, Paths.get("studenti.txt"), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Eroare la actualizarea studentului!", "Eroare",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		// Adaugare date noi
		JTextField nrMatricolField2 = new JTextField();
		JTextField numeField = new JTextField();
		JTextField prenumeField = new JTextField();
		JTextField emailField = new JTextField();
		JTextField specializareField = new JTextField();
		JTextField anStudiuField = new JTextField();

		Object[] message2 = { "Numar matricol:", nrMatricolField2, "Nume:", numeField, "Prenume:", prenumeField,
				"Email:", emailField, "Specializare:", specializareField, "Anul de studiu:", anStudiuField };

		int option2 = JOptionPane.showConfirmDialog(null, message2, "Adaugare student", JOptionPane.OK_CANCEL_OPTION);

		if (option2 == JOptionPane.OK_OPTION) {
			String nrMatricol = nrMatricolField2.getText();
			String nume = numeField.getText();
			String prenume = prenumeField.getText();
			String email = emailField.getText();
			String specializare = specializareField.getText();
			String anStudiu = anStudiuField.getText();

			Student student = new Student(nume, prenume, email, specializare, anStudiu, nrMatricol);

			try (BufferedWriter writer = new BufferedWriter(new FileWriter("studenti.txt", true))) {
				writer.write(student.toString());
				writer.newLine();
				JOptionPane.showMessageDialog(null, "Date modificate cu succes!", "Succes",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Eroare la modificarea datelor!", "Eroare",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Modificarea datelor a fost anulata.", "Anulare",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void sterge() {
		JTextField nrMatricolField = new JTextField();
		Object[] message = { "Introduceti numarul matricol al studentului de sters:", nrMatricolField };

		int option = JOptionPane.showConfirmDialog(null, message, "Stergere Student", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String nrMatricolCautat = nrMatricolField.getText().trim();

			try (BufferedReader br = new BufferedReader(new FileReader("studenti.txt"));
					BufferedWriter writer = new BufferedWriter(new FileWriter("temp_studenti.txt"))) {

				String linie;
				boolean studentGasit = false;

				while ((linie = br.readLine()) != null) {
					if (linie.startsWith("Numar matricol: ")
							&& linie.substring("Numar matricol: ".length()).trim().equals(nrMatricolCautat)) {
						for (int i = 0; i < 8; i++) {
							br.readLine();
						}
						studentGasit = true;
					} else {
						writer.write(linie);
						writer.newLine();
					}
				}

				if (!studentGasit) {
					JOptionPane.showMessageDialog(null, "Niciun student gasit cu numarul matricol " + nrMatricolCautat,
							"Stergere Student", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Student sters cu succes!", "Stergere Student",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			Path tempPath = Paths.get("temp_studenti.txt");
			try {
				Files.move(tempPath, Paths.get("studenti.txt"), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Eroare la actualizarea fisierului.");
			}
		}
	}
}
