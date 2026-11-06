package uniba.mri.lucene;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.LetterTokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.it.ItalianAnalyzer;

import java.util.Arrays;
import java.util.List;

public class MyAnalyzerITA extends Analyzer {

    /**
     *
     */
    public static final CharArraySet STOP_WORDS;

    static {
        final List<String> stopWords = Arrays.asList(
                "a", "ad", "al", "allo", "ai", "agli", "alla", "alle",
                "da", "dal", "dallo", "dai", "dagli", "dalla", "dalle",
                "di", "del", "dello", "dei", "degli", "della", "delle",
                "in", "nel", "nello", "nei", "negli", "nella", "nelle",
                "su", "sul", "sullo", "sui", "sugli", "sulla", "sulle",
                "con", "per", "tra", "fra",
                "il", "lo", "la", "i", "gli", "le",
                "un", "uno", "una",
                "e", "ed", "o", "oppure", "ma", "però", "anche",
                "che", "chi", "cui", "come", "dove", "quando", "perché",
                "io", "tu", "lui", "lei", "noi", "voi", "loro",
                "mi", "ti", "ci", "vi", "si", "se", "ne",
                "mio", "mia", "tuo", "tua", "suo", "sua",
                "questo", "questa", "questi", "queste",
                "quello", "quella", "quelli", "quelle",
                "sono", "sei", "è", "siamo", "siete", "essere",
                "era", "erano", "ero", "eri", "eravamo", "eravate",
                "ho", "hai", "ha", "abbiamo", "avete", "hanno", "avere",
                "non", "più", "meno", "molto", "poco", "già", "ancora",
                "sempre", "mai", "solo", "tutto", "tutti", "tutte"
        );
        //final CharArraySet stopSet = new CharArraySet(stopWords, false);
        final CharArraySet stopSet = ItalianAnalyzer.getDefaultStopSet();
        STOP_WORDS = CharArraySet.unmodifiableSet(stopSet);
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer source = new WhitespaceTokenizer();
        TokenStream filter = new LowerCaseFilter(source);
        filter = new StopFilter(filter, STOP_WORDS);
        return new TokenStreamComponents(source, filter);

    }

}
