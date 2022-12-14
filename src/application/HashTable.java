package application;

class HashNode {

    String Key;
    public int Frequency;
    //Frequency divide by total words of website
    public float termFrequency;

    // Constructor accepting word as parameter. It will accept word to the key and set the frequency as 1
    public HashNode(String key) {
        this.Key = key;
        this.Frequency = 1;
    }

    // To set the frequency
    public void SetFrequency(int f) {
        Frequency = f;
    }
}

public class HashTable {
    HashNode Table[];
    String WebsiteLink;
    int DistinctWords;
    // TOTAL words
    int TotalWords;

    HashTable() {
        Table = new HashNode[1000000];

        // Assign them null and distinct words as 0
        for (int i = 0; i < Table.length; i++) {
            Table[i] = null;
        }
        DistinctWords = 0;
    }

    // Function to add word. It accepts string as word
    void AddWord(String word) {
        // Convert it into lowercase
        word = word.toLowerCase();
        // Increase the total words
        TotalWords++;
        // Get the hash value and take mode by the size of table
        int index = HashFunction(word) % 1000000;
        // While A empty HashNode is not found or the HashNode having same value is not found
        while (Table[index] != null) {
            if (Table[index].Key.equals(word)) {
                // If word is already present then increase its frequency
                Table[index].Frequency = Table[index].Frequency + 1;
                // return
                return;
            }
            // check next node
            ++index;
            // if end is reached then go to start
            if (index == 1000000) // "wrap around" if we've reached the end of the hash table
                index = 0;
        }
        // if word is not present then increase distinct word
        DistinctWords++;
        // create new hash node
        Table[index] = new HashNode(word);
    }

    // hash function
    int HashFunction(String s) {
        int size_hash = 5381;
        return s.length() / 5381;
    }

    // Set term frequency of each word
    void SetTermFrequency() {
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] != null) {
                Table[i].termFrequency = Table[i].Frequency / TotalWords;
            }
        }
    }

    //Similarity Metric
    public String FindSimilarity(HashTable Websites[]) {
        // make new array  and assign them 0
        float SimilarityCount[] = new float[Websites.length];
        for (int i = 0; i < Websites.length; i++) {
            SimilarityCount[i] = 0;
        }

        // Iterate each table
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] != null) {
                //check word in each website
                for (int y = 0; y < Websites.length; y++) {
                    // if word is found
                    int z = Websites[y].FindKey(Table[i].Key);
                    // increase the similarity count of that websites links
                    SimilarityCount[y] = (SimilarityCount[y] + (Table[i].Frequency * z));
                }
            }
        }
        // Iterate each website data
        float SimilarityCountCategory[] = new float[Websites.length / 4];
        for (int i = 0; i < SimilarityCountCategory.length; i++) {
            SimilarityCountCategory[i] = 0;
        }
        int z = 0;
        for (int i = 0; i < Websites.length; i++) {
            if (i % 4 == 0 && i != 0) {

                z++;
            }
            //	System.out.println(z);

            SimilarityCountCategory[z] = SimilarityCountCategory[z] + SimilarityCount[i];


            System.out.println("Similarity count of website " + Websites[i].WebsiteLink + " is " + SimilarityCount[i]);
        }
        int max = 0;
        for (int i = 1; i < SimilarityCountCategory.length; i++) {
            if (SimilarityCountCategory[i] > SimilarityCountCategory[max])
                max = i;

        }

        int SimilarWebsite = (int) Math.floor(Math.random() * (((max * 4) + 3) - (max * 4) + (max * 4)));

        //int SimilarWebsite=(max*4) + (int)(Math.random() * (((max*4)+3)) - (max*4) + 1);
        return Websites[SimilarWebsite].WebsiteLink;
    }

    // find word and return its frequency
    public int FindKey(String word) {
        int frequency = 0;
        // table length
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] != null) {
                if (Table[i].Key.equals(word)) {
                    frequency = Table[i].Frequency;
                }
            }
        }
        return frequency;
    }
}