import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * The "brain" of the chatbot. Uses lightweight NLP techniques
 * (tokenization, stop-word removal, keyword-overlap similarity)
 * to match user input against trained intent patterns, then
 * picks the best-matching intent's response.
 *
 * This is a rule-based approach rather than a trained ML model,
 * which keeps it fast, transparent, and dependency-free while
 * still demonstrating core NLP matching concepts.
 */
public class ChatbotEngine {

    private static final double MATCH_THRESHOLD = 0.3;

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "a", "an", "the", "is", "are", "am", "do", "does", "did", "i", "you", "me",
            "my", "your", "to", "of", "in", "on", "for", "and", "please", "can", "could",
            "would", "will", "it", "this", "that"
    ));

    private final KnowledgeBase knowledgeBase;
    private final Random random = new Random();

    public ChatbotEngine(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    /** Breaks a sentence into lowercase, punctuation-free, stop-word-free tokens. */
    private Set<String> tokenize(String text) {
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9\\s]", " ");
        Set<String> tokens = new HashSet<>();
        for (String word : cleaned.split("\\s+")) {
            if (!word.isBlank() && !STOP_WORDS.contains(word)) {
                tokens.add(word);
            }
        }
        return tokens;
    }

    /** Jaccard-style overlap score between two token sets: shared words / total unique words. */
    private double similarity(Set<String> a, Set<String> b) {
        if (a.isEmpty() || b.isEmpty()) return 0.0;
        Set<String> intersection = new HashSet<>(a);
        intersection.retainAll(b);
        Set<String> union = new HashSet<>(a);
        union.addAll(b);
        return (double) intersection.size() / union.size();
    }

    /**
     * Finds the best-matching intent for the user's message and
     * returns one of its trained responses. Falls back to a
     * default reply if nothing matches well enough.
     */
    public String getResponse(String userInput) {
        if (userInput == null || userInput.isBlank()) {
            return "I didn't quite catch that — could you type something?";
        }

        Set<String> inputTokens = tokenize(userInput);
        Intent bestIntent = null;
        double bestScore = 0.0;

        for (Intent intent : knowledgeBase.getIntents()) {
            for (String pattern : intent.getPatterns()) {
                double score = similarity(inputTokens, tokenize(pattern));
                if (score > bestScore) {
                    bestScore = score;
                    bestIntent = intent;
                }
            }
        }

        if (bestIntent != null && bestScore >= MATCH_THRESHOLD) {
            List<String> responses = bestIntent.getResponses();
            return responses.get(random.nextInt(responses.size()));
        }

        return fallbackResponse();
    }

    private String fallbackResponse() {
        String[] fallbacks = {
                "I'm not quite sure I understand. Could you rephrase that?",
                "Sorry, I don't have an answer for that yet. Try asking about CodeAlpha, Java, or say hi!",
                "Hmm, I don't know that one. Type 'help' to see what I can talk about."
        };
        return fallbacks[random.nextInt(fallbacks.length)];
    }
}
