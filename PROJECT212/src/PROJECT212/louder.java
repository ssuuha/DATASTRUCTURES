import java.io.File;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Scanner;
public class louder{

LinkedList <String> stopWords;
index indexObj;
InverIndex invertedObj;
int totalTokens = 0;

public louder(){
stopWords=new LinkedList <>();
indexObj=new index();
invertedObj =new InverIndex();

}
public void LoadStopWords (String fileName){
try {
File f1=new File (fileName) ;
Scanner s1=new Scanner (f1) ;
while (s1. hasNextLine () )
{
String line=s1.nextLine() ;
stopWords. insert (line);
}
}
catch (IOException e)
{
e.printStackTrace();
}
}
public void loudDocuments(String fileName){
String line= null;
try{
File f2=new File (fileName);
Scanner s2=new Scanner (f2);
s2.nextLine();
while(s2.hasNextLine()){
line=s2.nextLine();
if (line.trim().length()<3){
break;
}
 String p = line.substring(0, line.indexOf(','));
int id =Integer.parseInt(p.trim());
String content=line.substring(line.indexOf(',')+1).trim();
LinkedList<String> docWords=index_invertedindex(content,id);
Document d=new Document(id,docWords);
indexObj.addDoc(d);
//two sen
}
    } catch (Exception e) {
        System.out.println("End of file or other error encountered.");
    }
    System.out.print("total tokens is:"+totalTokens);

}//


public LinkedList<String> index_invertedindex(String content,int id){
LinkedList<String>docWords=new LinkedList<String>();
content=content.toLowerCase().replaceAll("[^a-zA-Z0-9]"," ").trim();
String[] tokens=content.split("\\s+");
for(String w:tokens){
 totalTokens += 1;

if(!searchInStopWords(w)){
docWords.insert(w);
invertedObj. insertWord(w,id);
}//if
}//for

return docWords;
}//

public boolean searchInStopWords(String w){
if (stopWords==null||stopWords.empty())
return false;
stopWords.findFirst();
while(!stopWords.last()){
if(stopWords.retrieve().equals(w)){
return true;
}
stopWords.findNext();
}
if(stopWords.retrieve().equals(w)){
return true;
}
return false;
}//




public void loadFiles(String stopFile,String docFile){
LoadStopWords(stopFile);
loudDocuments(docFile);

}//

public static void main(String[]args){
louder l =new louder();
l.loadFiles("stop.txt","dataset.csv");
l.indexObj.showDocs();
System.out.println("\n===================");
l.invertedObj.displayInvertedIndex();
}//main
public void displayStopWords(){
stopWords.display();
}

}//class