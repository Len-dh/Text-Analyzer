package com.company;



import javax.swing.text.ElementIterator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.security.PrivateKey;
import java.util.*;

/**
 * La classe TextAnalyseur va nous permettre de creer les caracteres speciaux comme des separateur et de supprimer les
 * contenants de chiffres.
 *
 * Tout est affectue dans la méthode TextAnalyseur :  public TextAnalyzer(String FileName) throws IOException
 * qui va dans un premier lire le fichier
 * Une fois tout ses mots determines, ils sont sotcker dans une Collection appellee Hashmap
 * qui va associer les mots à un compteur qui permette d'obtenir le nombre d'apparition d'un mot.
 *
 * Nous avons donc en clef de notre Hashmap les mots et enb valeurs le compteur associe, soit le nombre d'occurence qui
 * sera triee a l'aide d'un LinkedHashMap.
 */

public class TextAnalyzer {
    private LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
    private LinkedHashMap<String, Integer> NewreverseSortedMap = new LinkedHashMap<>();
    private ArrayList<LinkedHashMap> ArrayReverse = new ArrayList<>();
    private HashMap<String, Integer> ListH = new HashMap<>();
    private String clé;
    private int counter;
    StringBuffer   sb = new StringBuffer();

    /**
     *La Classe WordCounter permettant d associer un mot à un compteur, avec deux getters nommés
     * getWord() et getCount().
     */

    class WordCounter {

        private String word;
        private int counter;


        public WordCounter(String word, int counter){
            this.word = word;
            this.counter = counter;
        }
        public String getWord() {
            return word;
        }

        public int getCount() {
            return counter;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public void setCounter(int counter) {
            this.counter = counter;
        }

    }


    public TextAnalyzer(String FileName) throws IOException {
        String word = "";
        int counter = 0;
        BufferedReader csvfile = null;
        try {

            csvfile = new BufferedReader(new FileReader(FileName));
        }catch (FileNotFoundException e){
            throw new IOException();
        }


        String lines;
        ListH.clear();

        StringTokenizer st;

        while ((lines = csvfile.readLine()) != null) {
            st = new StringTokenizer(lines, " ,.;:_-+*/\\.;\n\"{}()=><\t!?");
            while (st.hasMoreTokens()) {

                word = st.nextToken().toLowerCase();
                if (ListH.containsKey(word)) {

                    counter = ListH.get(word);
                    counter++;
                } else counter = 1;


                boolean test = false;
                for (int pos = 0; pos < word.length(); pos++) {
                    if (Character.isDigit((word.charAt(pos)))) {

                        test = true;

                    }
                }
                if (test) word = "";





                ListH.put(word, counter);
                System.out.println(ListH);

            }
        }

        final Enumeration<String> lesMots = Collections.enumeration(ListH.keySet());
        while (lesMots.hasMoreElements()) {
            word = lesMots.nextElement();
            counter = (Integer) ListH.get(word);
        }
        ListH.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> this.reverseSortedMap.put(x.getKey(), x.getValue()));



    }

    /**
     *La méthode WordCounter[] topWords(int n) va associer une nouvelle collection LinkedHashMap qui va entrer que les
     * n mots de plus haute occurence. A part si le dernier mot de liste contient la même occurences que
     * d'autres mots qui ne sont pas encore enregistres dans la LinkHahMap. Il va donc rajouter les mots de même
     * ocrurence que le n ième element de la liste. Comme demandé dans le TD, on ne valorise pas un éléments par
     * rapport à un autre si ils ont la même occurence.
     */

    public WordCounter[] topWords(int n) {
        String word = "";
        int i = 0;
        int occur =0, n2=0;

            Iterator<Map.Entry<String, Integer>> itr = reverseSortedMap.entrySet().iterator();
        HashMap<String,Integer> Hvide = new HashMap();
        final Enumeration<String> lesMots = Collections.enumeration(Hvide.keySet());
        while (lesMots.hasMoreElements()) {

        }

        Hvide.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> this.NewreverseSortedMap.put(x.getKey(), x.getValue()));


            while(itr.hasNext()) {
                Map.Entry<String, Integer> entry = itr.next();
                if (occur != entry.getValue())
                {
                    occur = entry.getValue();
                    n2++; //nombre d'occurence différente, => rentrer tout les mots de ses occurences
                }
                if(n2<n+1) {
                        NewreverseSortedMap.put(entry.getKey(), entry.getValue());
                        i++;

                    }
            }
        Iterator<Map.Entry<String, Integer>> itr2 = NewreverseSortedMap.entrySet().iterator();

            WordCounter[] wc = new WordCounter[i];
            i =0;

            while (itr2.hasNext()){
                Map.Entry<String, Integer> entry = itr2.next();
                wc[i] = new WordCounter(entry.getKey(),entry.getValue());
                i++;
            }

        return wc;
    }

}












