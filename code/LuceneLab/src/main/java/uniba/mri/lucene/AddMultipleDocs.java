package uniba.mri.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddMultipleDocs {

    public static void main(String[] args) {
        String indexPath = "index";  // cartella dove salvare l’indice

        try {
            Directory dir = FSDirectory.open(Paths.get(indexPath));
            StandardAnalyzer analyzer = new StandardAnalyzer();
            IndexWriterConfig config = new IndexWriterConfig(analyzer);

            try (IndexWriter writer = new IndexWriter(dir, config)) {

                // crea più documenti
                List<Document> docs = new ArrayList<>();

                Document doc1 = new Document();
                doc1.add(new StringField("id", "1", Field.Store.NO));
                doc1.add(new TextField("title", "Alice in Wonderland", Field.Store.YES));
                doc1.add(new TextField("content", "A girl falls into a rabbit hole and discovers a fantasy world.", Field.Store.YES));
                docs.add(doc1);

                Document doc2 = new Document();
                doc2.add(new StringField("id", "2", Field.Store.NO));
                doc2.add(new TextField("title", "Through the Looking-Glass", Field.Store.YES));
                doc2.add(new TextField("content", "Alice steps through a mirror into another strange world.", Field.Store.YES));
                docs.add(doc2);

                Document doc3 = new Document();
                doc3.add(new StringField("id", "3", Field.Store.NO));
                doc3.add(new TextField("title", "Alice in Quantumland", Field.Store.YES));
                doc3.add(new TextField("content", "A journey through the quantum world with Alice.", Field.Store.YES));
                docs.add(doc3);

                // aggiunge tutti i documenti in un colpo solo
                writer.addDocuments(docs);

                // forza il commit dei dati su disco
                writer.commit();

                System.out.println("" + docs.size() + " documenti aggiunti all’indice in " + indexPath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}