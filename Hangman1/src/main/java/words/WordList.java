package words;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class WordList {
    public List<String> words = new ArrayList<>();

    public void wordList() {
        try {
            RandomAccessFile file = new RandomAccessFile(
                    "/home/paraply/IdeaProjects/Hangman/Hangman1/src/main/resources/word_list.txt", "r");
            String str;

            while (true) {
                try {
                    if (!((str = file.readLine()) != null)) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                words.add(str);
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}