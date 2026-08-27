import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple Swing GUI so the user can chat with the bot in
 * real time, rather than through the console.
 */
public class ChatWindow extends JFrame {

    private final ChatbotEngine engine;
    private final JTextPane chatArea;
    private final JTextField inputField;
    private static final String LOG_FILE = "chat_log.txt";
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ChatWindow(ChatbotEngine engine) {
        super("CodeAlpha AI Chatbot");
        this.engine = engine;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        chatArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout(6, 0));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        inputField.addActionListener(this::onSend); // Enter key sends message

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(this::onSend);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        appendMessage("CodeBot", "Hi! I'm your CodeAlpha chatbot. Ask me something — try 'hi', 'help', or 'tell me about CodeAlpha'.");
    }

    private void onSend(ActionEvent e) {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        appendMessage("You", userText);
        logToFile("You", userText);
        inputField.setText("");

        String reply = engine.getResponse(userText);
        appendMessage("CodeBot", reply);
        logToFile("CodeBot", reply);

        if (userText.equalsIgnoreCase("bye") || userText.equalsIgnoreCase("exit")
                || userText.equalsIgnoreCase("quit")) {
            inputField.setEnabled(false);
        }
    }

    private void appendMessage(String sender, String message) {
        StyledDocument doc = chatArea.getStyledDocument();
        Style style = chatArea.addStyle("style", null);
        StyleConstants.setBold(style, true);
        StyleConstants.setForeground(style, sender.equals("You")
                ? new Color(30, 90, 200) : new Color(20, 140, 90));

        try {
            doc.insertString(doc.getLength(), sender + ": ", style);
            StyleConstants.setBold(style, false);
            StyleConstants.setForeground(style, Color.BLACK);
            doc.insertString(doc.getLength(), message + "\n\n", style);
        } catch (BadLocationException ex) {
            // Should not happen with valid offsets; ignore safely.
        }
        chatArea.setCaretPosition(doc.getLength());
    }

    /** Appends each exchange to a local log file (file I/O). */
    private void logToFile(String sender, String message) {
        String timestamp = LocalDateTime.now().format(TIME_FORMAT);
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("[" + timestamp + "] " + sender + ": " + message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Could not write to chat log: " + e.getMessage());
        }
    }
}
