package words;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWord {


    public List<String> words = new ArrayList<>();


    public String randomWord() {
        List<String> lines;
        try {
            lines = Files.readAllLines(
                    Path.of("/home/paraply/IdeaProjects/Hangman/Hangman1/src/main/resources/word_list.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Random random = new Random();
        return lines.get(random.nextInt(lines.size()));
    }

}