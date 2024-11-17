public class InverIndex{

LinkedList <Word> invertedIndex;



public InverIndex(){
invertedIndex=new LinkedList <Word>();

}
public boolean searchWord(String t){

if(invertedIndex==null ||invertedIndex.empty() )
return false;
invertedIndex.findFirst();
while(!invertedIndex.last()){
if (invertedIndex.retrieve ().text.equals(t)){
return true;
}//if
invertedIndex.findNext();

}//while
if (invertedIndex.retrieve ().text.equals(t)){
return true;
}
return false;
}
public void insertWord(String text,int id){
if(!searchWord(text)){
Word w = new Word(text);
w.ids.insert(id);
invertedIndex.insert(w);
}//if
else{
Word currentWord=invertedIndex.retrieve ();
currentWord.addID(id);
}//else

}
public void displayInvertedIndex(){

if(invertedIndex==null)
return;
else if (invertedIndex.empty())
return;

invertedIndex.findFirst();
while(!invertedIndex.last()){
invertedIndex.retrieve ().display ();
invertedIndex.findNext();
}//while
invertedIndex.retrieve ().display ();
}
public boolean contains(String t) {
        if (invertedIndex == null || invertedIndex.empty()) {
        return false; 
        }
        
        LinkedList<String> wordList = new LinkedList<>();
        invertedIndex.findFirst();
        while (!invertedIndex.last()) {
            wordList.insert(invertedIndex.retrieve().text);
            invertedIndex.findNext();
        }
        wordList.insert(invertedIndex.retrieve().text);
        return invertedIndex.existsInList(wordList, t);
    }
    
    public Word retrieve(String term) {
        invertedIndex.findFirst();
        while (!invertedIndex.last()) {
            if (invertedIndex.retrieve().text.equals(term)) {
                return invertedIndex.retrieve();
            }
            invertedIndex.findNext();
        }
        if (invertedIndex.retrieve().text.equals(term)) {
            return invertedIndex.retrieve();
        }
        return null;
    }
}