package uniba.mri.lucene;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.LetterTokenizer;

import java.util.Arrays;
import java.util.List;

public class MyAnalyzer extends Analyzer {

    /**
     *
     */
    public static final CharArraySet STOP_WORDS;

    static {
        final List<String> stopWords = Arrays.asList("a", "an", "and", "are", "the", "is", "but", "by", "org");
        final CharArraySet stopSet = new CharArraySet(stopWords, false);
        STOP_WORDS = CharArraySet.unmodifiableSet(stopSet);
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer source = new LetterTokenizer();
        TokenStream filter = new LowerCaseFilter(source);
        filter = new StopFilter(filter, STOP_WORDS);
        return new TokenStreamComponents(source, filter);

    }

}
