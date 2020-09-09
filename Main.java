
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HashTable newHash = new HashTable();  //intializes a hashtable

        Scanner infile = null;        //the scanner for input
        PrintWriter write = null;    //the writer named 'write' for output

        try {
            infile = new Scanner(new FileReader("text.txt"));  //this opens text file named 'text file'
        }
        catch (Exception ex_open) {    //if this file is not opened correctly or a problem occurs, the program goes here
            System.out.println("Bad news: <" + "text.txt" + "> did not open for reading");
        }

        try {
            while (infile.hasNext()) {  // Reads in one string from the file at a time until the end of the text in file
                newHash.Hash(infile.next());
            }
        }
        catch (Exception IO_error) {   // if the program is unable to read the text file the program goes here
            System.out.println("I/O error!!");
        }
        try {
            write = new PrintWriter("text.txt"); // this clears the file
        }
        catch (Exception ex_open) {                //if the program cannot open the text file for writing
            System.out.println("Bad News: <" + "text.txt" + "> did not open for writing");
        }
        System.out.println("Please input the number of words to be check");

        Scanner scanner = new Scanner(System.in);    //intiliazes a scanner
        int input = scanner.nextInt();   //Get N

        try {
            newHash.mostFreq(input);       // output N
        }
        catch (Exception Freq_Check) {     //if output of N fails this is from the empty text file (possibly)
            System.out.println("Null pointer error. Most likely the result of an empty text file.");
        }
        try {
            write.close();   //closes  the file
            infile.close();
        }
        catch (Exception fileClose_Error) {  //goes here if there is a problem closing the file
            System.out.println("Closing file Error!");
        }

        }
    }





















