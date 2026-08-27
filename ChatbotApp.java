import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * CodeAlpha Java Internship - Task 3: AI Chatbot
 *
 * A rule-based Java chatbot with lightweight NLP (tokenizing,
 * stop-word removal, keyword-overlap matching) that answers
 * FAQs through a Swing GUI, logging every exchange to disk.
 */
public class ChatbotApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Fall back to default look and feel if system L&F isn't available
        }

        SwingUtilities.invokeLater(() -> {
            KnowledgeBase knowledgeBase = new KnowledgeBase();
            ChatbotEngine engine = new ChatbotEngine(knowledgeBase);
            ChatWindow window = new ChatWindow(engine);
            window.setVisible(true);
        });
    }
}
