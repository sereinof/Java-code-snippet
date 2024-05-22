package person.man.zhou;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class LuceneIndexer {

    private static final Logger logger = LoggerFactory.getLogger(LuceneIndexer.class);

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Directory directory;
    private IndexWriter indexWriter;
    private IndexSearcher indexSearcher;
    private Analyzer analyzer;

    @PostConstruct
    public void init() throws IOException {
        directory = new MMapDirectory();
        analyzer = new StandardAnalyzer(Version.LUCENE_48);
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, analyzer);
        indexWriter = new IndexWriter(directory, config);
        // 假设loadDocs是一个加载文档的方法
        loadDocs();
        indexWriter.commit();
        IndexReader reader = DirectoryReader.open(directory);
        indexSearcher = new IndexSearcher(reader);
    }

    private void loadDocs() throws IOException {
        // 加载文档到索引
    }

    public void updateIndex(long id, String title, String status, long time) throws IOException {
        readWriteLock.writeLock().lock();
        try {
            // 删除旧文档
            indexWriter.deleteDocuments(new Term("id", String.valueOf(id)));
            // 添加新文档
            Document doc = new Document();
            doc.add(new Field("id", String.valueOf(id), Field.Store.YES, Field.Index.NOT_ANALYZED));
            doc.add(new Field("title", title, Field.Store.YES, Field.Index.ANALYZED));
            doc.add(new Field("status", status, Field.Store.YES, Field.Index.NOT_ANALYZED));
            doc.add(new Field("time", String.valueOf(time), Field.Store.YES, Field.Index.NOT_ANALYZED));
            indexWriter.addDocument(doc);
            indexWriter.commit();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public List<Document> searchDocs(String title, String[] statuses, long startTime, long endTime, int page, int pageSize) throws IOException {
        readWriteLock.readLock().lock();
        try {
            List<Document> results = new ArrayList<>();
            BooleanQuery.Builder builder = new BooleanQuery.Builder();
            // 构建查询条件
            if (title != null && !title.isEmpty()) {
                builder.add(new QueryWrapper(new TermQuery(new Term("title", title)), Occur.SHOULD));
            }
            if (statuses != null && statuses.length > 0) {
                TermRangeQuery statusQuery = new TermRangeQuery("status", Arrays.toString(statuses), true, true);
                builder.add(statusQuery, Occur.SHOULD);
            }
            if (startTime > 0 && endTime > 0) {
                builder.add(new TermRangeQuery("time", String.valueOf(startTime), String.valueOf(endTime), true, true), Occur.MUST);
            }
            Query query = builder.build();
            IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory));
            TopDocs topDocs = searcher.search(query, page * pageSize);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                results.add(doc);
            }
            return results;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public long countDocs(Query query) throws IOException {
        readWriteLock.readLock().lock();
        try {
            IndexReader reader = DirectoryReader.open(directory);
            IndexSearcher searcher = new IndexSearcher(reader);
            return searcher.count(query);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    // 确保在销毁时释放资源
    public void destroy() throws IOException {
        readWriteLock.writeLock().lock();
        try {
            if (indexWriter != null) {
                indexWriter.close();
            }
            if (directory != null) {
                directory.close();
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
