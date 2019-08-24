/** 
 * ********************************************************************************
 * @file RandomQuestionGenerator.java
 * @brief Generates the questions for the GUI
 * ********************************************************************************
 */

package maincode;

import java.util.Random;

public class RandomQuestionGenerator {

  // VARIABLES:
  static boolean allQuestionsGenerated = false;
  static boolean charArrayCreated = false;
  static boolean answeredCorrect;
  static int numberOfQuestions = 5;// DEFAULT 94
  static int drillcounter = 0;
  static int askedCharNo;
  static int slowestCharNo;
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

  private static Random rand = new Random();

  public static void createCharArray() {

    // ALGORITHM TO CREATE AN ARRAY CONTAINING ALL CHARACTERS:
    int i;
    int index = 0;
    for (i = 70; i <= (70 + numberOfQuestions - 1); i++)// default 94 questions = ASCII 33-126
    {
      char currentCharacter = (char) i;
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
    // TRANSFER NO ARRAY TO STRING
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
  }
}