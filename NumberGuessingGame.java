import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int targetNumber;
    private int maxAttempts = 10;
    private int score = 0;
    private int attempts = 0;

    private JTextField guessTextField;
    private JLabel resultLabel;

    public NumberGuessingGame() {
        super("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        // Generate a random number between 1 and 100
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 100:");
        guessTextField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        resultLabel = new JLabel();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(instructionLabel);
        panel.add(guessTextField);
        panel.add(guessButton);
        panel.add(resultLabel);

        add(panel);
        setVisible(true);
    }

    private void handleGuess() {
        try {
            int userGuess = Integer.parseInt(guessTextField.getText());
            attempts++;

            if (userGuess == targetNumber) {
                score += (maxAttempts - attempts + 1) * 10;
                showResult("Congratulations! You guessed the correct number.");
            } else if (attempts < maxAttempts) {
                String message = (userGuess < targetNumber) ? "Too low. Try again!" : "Too high. Try again!";
                showResult(message);
            } else {
                showResult("Game over. The correct number was: " + targetNumber + "\nYour score: " + score);
                dispose();
            }

            guessTextField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showResult(String message) {
        resultLabel.setText("<html>" + message + "</html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}
