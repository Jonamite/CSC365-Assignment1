# CSC365 Assignment 1

## Requirements

### This assignment asks you to create a web page categorization program.

- The program reads 10 (or more) web pages. The urls for these web pages can be maintained in a control file that is read when the program starts. (Wikipedia is recommended source.) For each page, the program maintains frequencies of words. (You can add any other collected information as well if you like.)
- The user can enter any other URL, and the program reports which other known page is most closely related, using a similarity metric of your choosing, based on alternatives discussed in class.

### The implementation restrictions are:

- Use existing library collections for all data structures, except for a custom hash table class you implement for maintaining word frequencies.
- Establish a similarity metric. This must be in part based on word-frequencies (TF-IDF is recommended), but may include other attributes. Use jsoup or any other html parser to extract words or other components. You may use global frequency based on either on the words you scan across each page, or from a corpus such as https://www.english-corpora.org/
- A GUI allows a user to indicate one entity, and displays one or more similar ones.

The presentation details are up to you. Use Swing, JavaFX, or Android components for the GUI.
