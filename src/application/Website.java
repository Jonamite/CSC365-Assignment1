package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Website {
    String Websites[];
    HashTable HashTables[];
    HashTable UserWebsiteHashTable;
    int CurrentWebsite;
    int TotalWebsites;

    Website() throws IOException {
        //Load all the website urls
        LoadWebsiteLinks();

        HashTables = new HashTable[TotalWebsites];
        CurrentWebsite = 0;
        // CREATE USER LINK HASH TABLE
        UserWebsiteHashTable = new HashTable();


        for (int i = 0; i < TotalWebsites; i++)
            HashTables[i] = new HashTable();


        for (int i = 0; i < TotalWebsites; i++) {
            System.out.println("LOADING Website " + i);
            LoadWebsite();
            HashTables[CurrentWebsite].SetTermFrequency();
            CurrentWebsite++;
        }
    }

    public void LoadWebsiteLinks() throws FileNotFoundException {
        LinkedList<String> Websiteslinks = new LinkedList();
        //Load websites from file
        TotalWebsites = 0;
        int i = 0;
        File myObj = new File("Websites.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            Websiteslinks.add(data);
            TotalWebsites++;
            //System.out.println(data);
        }
        Websites = new String[TotalWebsites];

        for (int i1 = 0; i1 < Websiteslinks.size(); i1++) {
            Websites[i1] = Websiteslinks.get(i1);
        }


    }

    public void LoadWebsite() throws IOException {
        // SCRAP THE Website DATA USING JSOUP
        HashTables[CurrentWebsite].WebsiteLink = Websites[CurrentWebsite];

        Document doc = Jsoup.connect(Websites[CurrentWebsite]).get();
        String Words = doc.getAllElements().text();


        String[] arrOfStr = Words.split(" ");
        for (String a : arrOfStr) {

            HashTables[CurrentWebsite].AddWord(a);
        }

        arrOfStr = Words.split(",");
        for (String a : arrOfStr)
            HashTables[CurrentWebsite].AddWord(a);


        Words = doc.body().text();


        arrOfStr = Words.split(" ");
        for (String a : arrOfStr) {

            HashTables[CurrentWebsite].AddWord(a);
        }

        arrOfStr = Words.split(",");
        for (String a : arrOfStr)
            HashTables[CurrentWebsite].AddWord(a);
    }

    public String InputFromUser(String web) throws IOException {
        // SCRAP Website USING JSOUP
        Document doc = Jsoup.connect(web).get();
        String Words = doc.getAllElements().text();
        String[] arrOfStr = Words.split(" ");
        for (String a : arrOfStr)
            UserWebsiteHashTable.AddWord(a);

        arrOfStr = Words.split(",");
        for (String a : arrOfStr)
            UserWebsiteHashTable.AddWord(a);


        Words = doc.body().text();
        arrOfStr = Words.split(" ");
        for (String a : arrOfStr)
            UserWebsiteHashTable.AddWord(a);

        arrOfStr = Words.split(",");
        for (String a : arrOfStr)
            UserWebsiteHashTable.AddWord(a);

        UserWebsiteHashTable.SetTermFrequency();
        return UserWebsiteHashTable.FindSimilarity(HashTables);
    }
}