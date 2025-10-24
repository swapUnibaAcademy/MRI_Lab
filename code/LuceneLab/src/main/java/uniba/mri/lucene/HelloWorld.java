package uniba.mri.lucene;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class HelloWorld {

    /**
     * @param args the command line arguments
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        try {
            //Open a directory from the file system (index directory)
            FSDirectory fsdir = FSDirectory.open(new File("./resources/documenti_news").toPath());

            //IndexWriter configuration
            IndexWriterConfig iwc = new IndexWriterConfig(new StandardAnalyzer());

            //Index directory is created if not exists or overwritten
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);


            //Create IndexWriter
            IndexWriter writer = new IndexWriter(fsdir, iwc);
            //Create document and add fields

            Document doc = new Document();
            doc.add(
                    new TextField("titolo", "Articolo Web Numero 1", Field.Store.YES)
            );

            doc.add(new TextField("introduzione", "questa è l'introduzione del mio documento", Field.Store.YES));
            doc.add(new TextField("contenuto", "questo è il contenuto del mio documento", Field.Store.NO));
            doc.add(new TextField("commenti", "questo è un commento di un utente di esempio", Field.Store.NO));

            //add document to index
            writer.addDocument(doc);

            /**Document doc2 = new Document();
            doc2.add(
                    new TextField("titolo", "Articolo Web Numero 2", Field.Store.YES)
            );

            doc2.add(new TextField("introduzione", "un'altra introduzione del documento", Field.Store.YES));
            doc2.add(new TextField("contenuto", "ancora del contenuto del mio documento", Field.Store.NO));
            doc2.add(new TextField("commenti", "commento utente", Field.Store.NO));

            //add document to index
            writer.addDocument(doc2);

            Document doc3 = new Document();
            doc3.add(
                    new TextField("titolo", "Articolo Web Numero 3", Field.Store.YES)
            );

            doc3.add(new TextField("introduzione", "ultima introduzione", Field.Store.YES));
            doc3.add(new TextField("contenuto", "il testo del documento", Field.Store.NO));
            doc3.add(new TextField("commenti", "commento utente", Field.Store.NO));

            //add document to index
            writer.addDocument(doc3);**/

            //close IndexWriter
            writer.close();

            // Crea un IndexReader
            IndexReader reader = DirectoryReader.open(fsdir);

            // Conta il numero di documenti attivi (non cancellati)
            int numDocs = reader.numDocs();
            System.out.println("Numero di documenti nell'indice: " + numDocs);

        } catch (IOException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
