/** 
 * ********************************************************************************
 * @file RandomQuestionGenerator.java
 * @brief Generates the questions for the GUI
 * ********************************************************************************
 */

package maincode;

import java.util.Random;

public class RandomQuestionGenerator {

  private static Random rand = new Random();

  // VARIABLES:
  static boolean allQuestionsGenerated = false;
  static boolean charArrayCreated = false;
  static boolean answeredCorrect;
  static int numberOfQuestions = 12;// DEFAULT 94
  static int drillcounter = 0;
  static int askedCharNo;
  static int slowestCharNo;
  static int fastestAnswer;
  static int numberOfFlopTenChars=5; //for testing purposes the flopTen can have less than 10 chars
  static long timeForAllStart;
  static long start;
  static double smoothedSignsPerMinute;

  static double timeElapsedSec;
  static double slowestCharTime;
  static String askedChar;

  // ARRAYS:
  static int[] allQuestionsArrayNo = new int[numberOfQuestions];
  static char[] allCharsArray = new char[numberOfQuestions];
  static double[] timeArray = new double[numberOfQuestions];
  static String[] allQuestionsArrayString = new String[numberOfQuestions];
  static int[] flopTenArray = new int[numberOfFlopTenChars];

  public static void createFlopTen() {
    /*
     * This function creates an array containing the question numbers of the currently slowest
     * answered questions.
     */

    // FIND FASTEST ANSWER:
    long previousAnsweringTime = 10000;
    // ten seconds should be long enough to not be the fastest answer
    for (int i = 0; i < numberOfQuestions; i++) {
      if (TypingTrainer.answeringTimeArray[i] < previousAnsweringTime) {
        fastestAnswer = i;
        previousAnsweringTime = TypingTrainer.answeringTimeArray[i];
      }
    }
    // ASSIGN FASTEST ANSWER TO ALL INDEXES TO MAKE SORT ALGORITHM POSSIBLE;
    for (int i = 0; i < numberOfFlopTenChars; i++) {
      flopTenArray[i] = fastestAnswer;
    }

    // FIND AND STORE SLOWEST ANSWER (HIGHEST TIME) TO INDEX ZERO:
    for (int i = 0; i < numberOfQuestions; i++) {
      if (TypingTrainer.answeringTimeArray[i] > previousAnsweringTime) {
        flopTenArray[0] = i;
        previousAnsweringTime = TypingTrainer.answeringTimeArray[i];
      }
    }

    // SORT AND STORE THE OTHER VALUES
    // assign the whole flop ten:
    for (int flopIndex = 1; flopIndex < numberOfFlopTenChars; flopIndex++) {

      // check all questions
      for (int i = 0; i < numberOfQuestions; i++) {
        // the value is not already stored in the highscore:
        if (TypingTrainer.answeringTimeArray[i] < TypingTrainer.answeringTimeArray[flopTenArray[flopIndex
            - 1]]) {
          // the value is bigger than the stored one:
          if (TypingTrainer.answeringTimeArray[i] > TypingTrainer.answeringTimeArray[flopTenArray[flopIndex]]) {
            // store the value:
            flopTenArray[flopIndex] = i;
          }
        }
      }
    }
  }

  public static void createCharArray() {
    // CREATE AN ARRAY CONTAINING ALL CHARACTERS:
    int ascii;
    int index = 0;
    for (ascii = 100; ascii <= (100 + numberOfQuestions - 1); ascii++)
    // default 94 questions = ASCII 33-126
    {
      char currentCharacter = (char) ascii;
      allCharsArray[index] = currentCharacter;
      index++;
    }
  }

  public static String[] generateQuestionStringArray() {
    // FILL ARRAY WITH VALUES BECAUSE 0 WON'T WORK:
    for (int i = 0; i < numberOfQuestions; i++) {
      allQuestionsArrayNo[i] = 666;
    }

    // CREATE RANDOM VALUE ARRAY:
    int randCharNo = 0;
    int currentQuestionNo = 0;
    while (currentQuestionNo < numberOfQuestions) {
      boolean currentSlotAssigned = false;
      boolean noAlreadyAssigned;
      while (!currentSlotAssigned) {
        noAlreadyAssigned = false;

        // CREATE RANDOM VALUE:
        randCharNo = rand.nextInt(numberOfQuestions);
        // CHECK IF RAND NO HAS ALLREADY BEEN ASIGNED:
        for (int i = 0; i < numberOfQuestions; i++) {
          if (randCharNo == allQuestionsArrayNo[i])// no has already been assigned
          {
            noAlreadyAssigned = true;
          }
        }
        if (!noAlreadyAssigned) {
          allQuestionsArrayNo[currentQuestionNo] = randCharNo;
          currentSlotAssigned = true;
          currentQuestionNo++;
        }
      }
    }
    // TRANSFER NO ARRAY TO STRING:
    for (int i = 0; i < numberOfQuestions; i++) {
      int currentNo = allQuestionsArrayNo[i];
      char currentChar = allCharsArray[currentNo];
      String currentString = Character.toString(currentChar);
      allQuestionsArrayString[i] = currentString;
    }
    return allQuestionsArrayString;
  }

  private static void printArray() {
    // PRINT ALL CHARACTERS:
    for (int i = 0; i < numberOfQuestions; i++) {
      System.out.println("Question number " + i + " points to char number " + allQuestionsArrayNo[i]
          + " = String: " + allQuestionsArrayString[i]);
    }
  }

  public static void main(String[] args) {

    createCharArray();
    generateQuestionStringArray();
    printArray();
    createFlopTen();
  }
}