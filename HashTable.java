
public class HashTable {

    private int loadFactor;                    // the Load Factor
    private int numElements = 0;           //number of Elements in the hashtable not including the replicates
    private int tableSize;                     //number of buckets
    private DLList<String>[] hashTable;   // the hashtable array

    private void LoadFactor() { //this is the load factor calculator
        if (numElements == 0) { //this is the empty hash table
            return;
        }
        loadFactor = numElements / tableSize;
        if (loadFactor > 1.8) {     //rebuilds when the load factor gets too  big
            Rearrange();
        }
    }

    public HashTable() {   //hashtable constructor
        numElements = 0;      //intiliazes the number of elements to zero
        tableSize = 37;       //sets the table size to be 37
        hashTable = new DLList[tableSize]; //intialize the hashtable array
        for (int i = 0; i < tableSize; i++) {
            hashTable[i] = new DLList();
        }
        LoadFactor(); //update  the load factor
    }

    public HashTable(int x) {   //this is the overload constructor
        tableSize = x;
        numElements = 0;        //intializes the number of elements to zero
        hashTable = new DLList[x];
        for (int i = 0; i < x; i++) {
            hashTable[i] = new DLList();
        }
        LoadFactor();     // the loadfactor
    }

    private void Rearrange() {
        int newSize = 0;
        int tempInt;
        int[] primes = {37, 89, 199, 443, 977, 1193, 2389, 5897, 11491, 21269, 40013, 80107};   // the prime numbers grow exponentially

        for (int i = 0; i < 9; i++) {   //updates the size
            if (tableSize < primes[i]) {
                newSize = primes[i];
                break;
            }
        }

        HashTable tempTable = new HashTable(newSize);  //creates a new temporary table object

        for (int i = 0; i < tableSize; i++) {   //changes the  buckets
            for (DNode<String> j = hashTable[i].getFront(); j != null; j = j.getNext()) { //change nodes
            tempInt = j.getCounter();
            for (int k = 0; k < tempInt; k++) {  //changes the frequency
                tempTable.Hash(j.getElement());   //hashes  in  the new object
                Remove(j.getElement());     //removes the word occurence  the frequency or the node
            }
        }
    }

    hashTable =tempTable.hashTable;  //copies the information from the original object
        tableSize =tempTable.tableSize;
        loadFactor=tempTable.loadFactor;
    numElements =tempTable.numElements;

}
public void Hash(String x) {   //hashing function
    int charSum = 0;  //intitilizes the charSum to zero
    int index;

    for (int i = 0; i < x.length(); i++) {  //the summation of  the characters value
        charSum += x.charAt(i);
    }
    index = charSum % tableSize;  // the bucket number

    if (hashTable[index].find(x) != null) {    //if the element was already hashed before, this increases the  frequency of  the node
        hashTable[index].find(x).incCounter();
        return;
    } else {
        hashTable[index].insertAtRear(x);    //adds the new node
        numElements++;                   //increments the number of elements
        LoadFactor();                      //and this updates the load factor
        return;
    }
}
public void Remove(String x) {  //removes one of the occurances of the element and keeps the other
    int charSum = 0;  //intiliazes char sum to zero
    int index;
    DNode temp;
    for (int i = 0; i < x.length(); i++) { //summation of  the characters' value
        charSum += x.charAt(i);
    }
    index = charSum % tableSize;    //the bucket number

    if (hashTable[index].find(x) != null) {
        temp = hashTable[index].find(x);

        if (temp.getCounter() > 1) {
            temp.decCounter();
            return;
        }
        hashTable[index].delete(x);    //removes the node
        numElements--;                //decrements the number of elements
        LoadFactor();                   //updates the load factor
        return;
    }
}
public void mostFreq(int x) {
    String[] wordList = new String[x];     //the list of the top 'x' words
    int[] freqList = new int[x];           //frequency of the occurence of every word in the table
    DNode<String> node = null;
    int tempInt;
    String tempStr;

    for (int i =0; i < x; i++) {
        wordList[i] = "Error!";
        freqList[i] = 0;
    }
    int k = 0;
    int l = -1;


    for (int i = 0; i < tableSize && k < x; i++) {     //sets the elements of the array to the first x nodes
        for (DNode<String> j = hashTable[i].getFront(); j != null && k < x; j = j.getNext()) {
            node = j;
            wordList[k] = j.getElement();
            freqList[k] = j.getCounter();
            k++;
        }
        l++;
    }

    for (int i = 0; i < x - 1; i++) {        //sorts the array elements
        for (int j = 0; j < x - i - 1; j++) {
            if (freqList[j] < freqList[j + 1]) {
                tempInt = freqList[j];
                freqList[j] = freqList[j + 1];
                freqList[j + 1] = tempInt;

                tempStr = wordList[j];
                wordList[j] = wordList[j + 1];
                wordList[j + 1] = tempStr;
            }
        }
    }

    for (DNode<String> j = node.getNext(); j != null; j = j.getNext()) {//puts the words that occur most frequently in the array with a descending order
        if (j.getCounter() > freqList[x - 1]) {                         //only in the bucket where it is at now (where it left off in the last segment)
            freqList[x - 1] = j.getCounter();
            wordList[x - 1] = j.getElement();

            for (k = x - 2; k >= 0; k--) {
                if (freqList[k] < freqList[k + 1]) {
                    tempInt = freqList[k];
                    freqList[k] = freqList[k + 1];
                    freqList[k + 1] = tempInt;

                    tempStr = wordList[k];
                    wordList[k] = wordList[k + 1];
                    wordList[k + 1] = tempStr;

                }
            }
        }
    }

    for (int i = l; i < tableSize; i++) {         //puts the words that occur most frequently in the array with a descending order for the rest of the buckets
        for (DNode<String> j = hashTable[i].getFront(); j != null; j = j.getNext()) {
            if (j.getCounter() > freqList[x - 1]) {
                freqList[x - 1] = j.getCounter();
                wordList[x - 1] = j.getElement();

                for (k = x - 2; k >= 0; k--) {
                    if (freqList[k] < freqList[k + 1]) {
                        tempInt = freqList[k];
                        freqList[k] = freqList[k + 1];
                        freqList[k + 1] = tempInt;

                        tempStr = wordList[k];
                        wordList[k] = wordList[k + 1];
                        wordList[k + 1] = tempStr;

                    }
                }

            }

        }
    }

    for (int i = 0; i < x; i++) {   //outputs the array
        System.out.println(wordList[i] + " " + freqList[i]);
    }
  }

}













