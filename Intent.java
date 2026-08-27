import java.util.List;

/**
 * Represents a single "intent" the chatbot understands: a topic
 * (e.g. "greeting"), a set of example phrases users might type
 * for that topic, and a set of possible responses to give back.
 */
public class Intent {
    private final String tag;
    private final List<String> patterns;   // example training phrases
    private final List<String> responses;  // possible replies

    public Intent(String tag, List<String> patterns, List<String> responses) {
        this.tag = tag;
        this.patterns = patterns;
        this.responses = responses;
    }

    public String getTag() {
        return tag;
    }

    public List<String> getPatterns() {
        return patterns;
    }

    public List<String> getResponses() {
        return responses;
    }
}
