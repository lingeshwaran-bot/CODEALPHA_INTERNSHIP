# CodeAlpha_AIChatbot

A rule-based **AI Chatbot** built in Java with a Swing GUI, for the CodeAlpha Java Programming Internship (Task 3).

## What it does
- Chats with you in a real-time GUI window (not just the console)
- Uses lightweight **NLP techniques**: tokenization, stop-word removal, and keyword-overlap similarity matching to understand what you typed
- Responds using **rule-based FAQ logic** — trained on topics like greetings, CodeAlpha, Java, jokes, and more
- Falls back gracefully with "I don't understand" replies when nothing matches well
- **Logs every conversation** to `chat_log.txt` using file I/O

## How it's built (structure)
| File | Responsibility |
|---|---|
| `Intent.java` | One topic the bot knows: a tag, example phrases, and possible responses |
| `KnowledgeBase.java` | The bot's full FAQ "training data" — add new `Intent`s here to teach it more |
| `ChatbotEngine.java` | The NLP core: tokenizes input, removes stop words, scores similarity against trained patterns, picks the best response |
| `ChatWindow.java` | The Swing GUI: chat display, input box, Send button, and file logging |
| `ChatbotApp.java` | `main()` — launches the GUI window |

This covers the task's requirements: **NLP techniques** (tokenizing + similarity scoring), **rule-based answers**, **trained FAQ responses**, and a **GUI for real-time interaction**.

## How to run it

You need a JDK installed (any version 8+; check with `java -version` and `javac -version`).

From inside the folder with all the `.java` files:
```
javac *.java
java ChatbotApp
```

A window will pop up. Type a message in the box at the bottom and press Enter (or click Send).

### Try asking it:
- "hi" / "hello"
- "what is your name?"
- "tell me about CodeAlpha"
- "what is java"
- "how do I submit my task"
- "tell me a joke"
- "bye"

## Teaching it new topics
Open `KnowledgeBase.java` and add a new line like:
```java
addIntent("weather",
    Arrays.asList("what's the weather", "is it raining"),
    Arrays.asList("I can't check live weather, but I hope it's nice out!")
);
```
Recompile, and the bot instantly knows the new topic — no other code changes needed.

## What to do next for your submission

1. Create a GitHub repo named exactly: `CodeAlpha_AIChatbot`
2. Upload all `.java` files and this README to that repo.
3. Record a short screen video showing the GUI chatting back and forth, post it on **LinkedIn** tagging `@CodeAlpha` with your GitHub link.
4. Fill out the **Submission Form** in your CodeAlpha WhatsApp group.
5. Remember: you need **2–3 tasks total** for the certificate. If Task 2 (Stock Trading Platform) is your other one, you're already at 2 — you're good to submit!
