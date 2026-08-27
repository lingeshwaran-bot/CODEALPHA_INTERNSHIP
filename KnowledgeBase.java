import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Holds every intent the chatbot has been "trained" on.
 * This is the bot's FAQ knowledge — add a new Intent here any
 * time you want it to learn a new topic, no code logic changes needed.
 */
public class KnowledgeBase {

    private final List<Intent> intents = new ArrayList<>();

    public KnowledgeBase() {
        train();
    }

    public List<Intent> getIntents() {
        return intents;
    }

    private void addIntent(String tag, List<String> patterns, List<String> responses) {
        intents.add(new Intent(tag, patterns, responses));
    }

    private void train() {
        addIntent("greeting",
            Arrays.asList("hi", "hello", "hey", "good morning", "good evening", "yo", "hiya"),
            Arrays.asList("Hello! How can I help you today?", "Hi there! What can I do for you?",
                    "Hey! Ask me anything.")
        );

        addIntent("goodbye",
            Arrays.asList("bye", "goodbye", "see you", "see you later", "exit", "quit", "talk later"),
            Arrays.asList("Goodbye! Have a great day.", "See you later!", "Bye! Feel free to come back anytime.")
        );

        addIntent("thanks",
            Arrays.asList("thanks", "thank you", "thanks a lot", "appreciate it", "thank you so much"),
            Arrays.asList("You're welcome!", "Happy to help!", "Anytime!")
        );

        addIntent("bot_identity",
            Arrays.asList("what is your name", "who are you", "what are you", "your name"),
            Arrays.asList("I'm a rule-based Java chatbot built for the CodeAlpha internship!",
                    "I'm CodeBot — your friendly Java-powered assistant.")
        );

        addIntent("capabilities",
            Arrays.asList("what can you do", "help", "how can you help me", "what do you do",
                    "give me options"),
            Arrays.asList("I can chat with you, answer FAQs, and demonstrate simple rule-based NLP. Try asking me about CodeAlpha, Java, or just say hi!",
                    "I respond to greetings, farewells, and a few FAQ topics — try asking about the internship or Java.")
        );

        addIntent("about_codealpha",
            Arrays.asList("what is codealpha", "tell me about codealpha", "codealpha internship",
                    "about the internship", "what is this internship"),
            Arrays.asList("CodeAlpha is a software development company offering internships in Java, web development, and more, with hands-on real-world projects.",
                    "CodeAlpha runs internship programs where you build real projects, like this very chatbot, to gain practical experience.")
        );

        addIntent("submission_process",
            Arrays.asList("how do i submit my task", "submission process", "how to submit",
                    "where do i submit my project"),
            Arrays.asList("Upload your code to GitHub as CodeAlpha_ProjectName, post a LinkedIn video tagging @CodeAlpha, then submit through the form shared in your WhatsApp group.")
        );

        addIntent("java_question",
            Arrays.asList("what is java", "why use java", "is java good", "tell me about java"),
            Arrays.asList("Java is a popular, object-oriented, platform-independent programming language used for everything from Android apps to enterprise backends.",
                    "Java is known for being reliable, portable ('write once, run anywhere'), and widely used in industry.")
        );

        addIntent("mood_check",
            Arrays.asList("how are you", "how are you doing", "how's it going", "you good"),
            Arrays.asList("I'm just a program, but I'm running smoothly! How about you?",
                    "Doing great, thanks for asking! What can I help with?")
        );

        addIntent("joke",
            Arrays.asList("tell me a joke", "make me laugh", "say something funny", "joke please"),
            Arrays.asList("Why do programmers prefer dark mode? Because light attracts bugs!",
                    "Why did the Java developer wear glasses? Because they couldn't C#!")
        );

        addIntent("compliment",
            Arrays.asList("you are smart", "good bot", "you are helpful", "nice job", "well done"),
            Arrays.asList("Thank you! I try my best.", "That means a lot, thanks!")
        );
    }
}
