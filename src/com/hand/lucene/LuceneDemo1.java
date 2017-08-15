package com.hand.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class LuceneDemo1 {

	@Test
	public void createIndex() throws IOException {
		//指定索引存放的路径
		Directory directory=FSDirectory.open(new File("E:\\STUDYDATA\\tempIndex"));
		
		//创建一个标准分词器
		Analyzer analyzer=new StandardAnalyzer();
		
		//创建一个IndexWriterConfig对象
		IndexWriterConfig config=new IndexWriterConfig(Version.LATEST, analyzer);
		
		//创建IndexWriter对象
		IndexWriter indexWriterc=new IndexWriter(directory, config);
		
		//原始文档路径
		File dir=new File("E:\\BaiduNetdiskDownload\\【阶段15】Lucence\\lucene_day01\\参考资料\\参考资料\\searchsource");
		 
		for(File f:dir.listFiles())
		{
			//文件名
			String fileName=f.getName();
//			
			//文件内容
			String fileContent=FileUtils.readFileToString(f);
			
			//文件路径
			String filePath=f.getPath();
			
			//文件的大小
			long fileSize=FileUtils.sizeOf(f);
			
			//文件名域
			Field fileNameFile=new TextField("name", fileName, Store.YES);
			
			//文件内容域
			Field fileContentFile=new TextField("content", fileContent, Store.YES);
			
			//文件路径域
			Field filePathFile=new StoredField("path",filePath);
			
			//文件大小域
			Field fileSizeFile=new LongField("size", fileSize, Store.YES);
			
			//创建document对象
			Document document=new Document();
			
			document.add(fileNameFile);
			document.add(filePathFile);
			document.add(fileContentFile);
			document.add(fileSizeFile);
			indexWriterc.addDocument(document);
		}
		indexWriterc.close();
		
	}
}
