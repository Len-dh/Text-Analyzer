package com.company;

import java.io.IOException;
import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) throws IOException {
        TextAnalyzer ta = null;
        try {
            ta = new TextAnalyzer("/Users/macbookretina/Desktop/Cours ESIEA/Génie Logiciel/Analyseur de texte/TestTxt.txt");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        for (TextAnalyzer.WordCounter wc : ta.topWords(2)) {
            System.out.println(wc.getWord() + " " + wc.getCount());
        }
    }
}

