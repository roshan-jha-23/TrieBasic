public class Trie2 {
private Node1 root;
Trie2(){
    root=new Node1();
}
void insert(String word){
    Node1 node=root;
    for (int i = 0; i <word.length() ; i++) {
        if(!node.containsKey(word.charAt(i))){
            node.put(word.charAt(i),new Node1());
        }
       node= node.get(word.charAt(i));
        node.increasePrefix();
    }
    node.increaseEnd();
}
int countWordsEqualTo(String word){
    Node1 node=root;
    for (int i = 0; i < word.length(); i++) {
        if(node.containsKey(word.charAt(i))){
            node=node.get(word.charAt(i));
        }else{
            return 0;
        }
    }
    return node.getEnd();
}
int countStartWith(String word){
    Node1 node=root;
    for (int i = 0; i < word.length(); i++) {
        if (node.containsKey(word.charAt(i))){
            node=node.get(word.charAt(i));
        }else{
            return 0;
        }
    }
    return node.getPrefix();
}
void erase(String word){
    Node1 node=root;
    for (int i = 0; i < word.length(); i++) {
        if(node.containsKey(word.charAt(word.charAt(i)))){
            node=node.get(word.charAt(i));
            node.reducePrefix();
        }else{
            return;
        }
    }
    node.deleteEnd();
}

}
class Node1{
    Node1 links[]=new Node1[26];
    int ew=0;
    int cp=0;
    boolean containsKey(char ch){
        return links[ch-'a']!=null;
    }
    Node1 get(char ch){
        return links[ch-'a'];
    }
    void increaseEnd(){
        ew++;
    }
    void increasePrefix(){
        cp++;
    }
    void put(char ch, Node1 node) {
        links[ch - 'a'] = node;
    }
    void deleteEnd(){
        ew--;
    }
    void reducePrefix(){
        cp--;
    }
    int getEnd(){
        return ew;
    }
    int getPrefix(){
        return cp;
    }
}
