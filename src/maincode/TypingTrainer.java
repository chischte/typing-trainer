/**
 * ********************************************************************************
 * TYPING TRAINER
 * 
 * @file TypingTrainer.java
 * @version August 2019, Zürich
 * @author Michael Wettstein 
 * @brief Interactive Typing Trainer
 * ********************************************************************************
 */

/*
 * ToDo & NOTES:
 * Stop answering time
 * Store answering time for each character
 * In calibration mode, randomly ask every charchter once
 * In drill mode, randomly ask only the then slowest answered characters
 * Calculate average answering speed
 * Stop time for calibration mode
 * Show slowest characters in red
 */

package maincode;

import java.util.Scanner;
import java.util.Random;

//******************************************************************
//ASK QUESTION:
//STOP ANSWERING TIME
//STORE ANSWERING TIME FOR EACH CHARACTER
//FIRST, ASK EVERY CHARACTER AT LEAST ONCE
//THEN, EVERY FIFTH QUESTION ASK THE CHARACTER WITH THE LONGEST ANSWERING TIME
//TODO: CREATE RUNNING AVERAGE OF ANSWERING TIME
//TODO: STOP TIME UNTIL EVERY CHARACTER HAS BEEN ASKED ONCE
//TODO SHOW SLOWEST IN RED

public class TypingTrainer {
    // ************************************************************************
    // STATIC VARIABLES:
    public static int numberOfQuestions = 5;// DEFAULT 94
    static char[] allCharsArray = new char[numberOfQuestions];
    static int[] allQuestionsArrayNo = new int[numberOfQuestions];
    static String[] allQuestionsArrayString = new String[numberOfQuestions];
    static double[] timeArray = new double[numberOfQuestions];
    static double timeElapsedSec;
    static int askedCharNo;
    static int slowestCharNo;
    static double slowestCharTime;
    static boolean allQuestionsGenerated = false;
    static boolean charArrayCreated = false;
    static boolean answeredCorrect;
    static int drillcounter = 0;
    static long timeForAllStart;
    static long start;
    static double smoothedSignsPerMinute;
    static String askedChar;

    public static void createCharArray() {

        // ALGORITHM TO CREATE AN ARRAY CONTAINING ALL CHARACTERS:
        int i;
        int index = 0;
        for (i = 33; i <= 37; i++)// default 33-126//relevant ASCII characters
        {
            char currentCharacter = (char) i;
            allCharsArray[index] = currentCharacter;
            index++;
        }

    }// END OF createCharArray()

    public static void printAllCharacters() {
        // PRINT ALL CHARACTERS:
        for (int i = 0; i < numberOfQuestions; i++) {
            System.out.println(allCharsArray[i] + " has the number: " + i);
        }
    }

    public static String[] generateQuestionStringArray() {
        // FILL ARRAY WITH VALUES BECAUSE 0 WON'T WORK
        for (int i = 0; i < numberOfQuestions; i++) {
            allQuestionsArrayNo[i] = 666;
        }
        // CREATE RANDOM VALUE ARRAY:
        Random rand = new Random();
        int randCharNo = 0;
        int currentQuestionNo = 0;
        while (currentQuestionNo < numberOfQuestions) {
            boolean currentSlotAssigned = false;
            boolean noAlreadyAssigned;
            while (currentSlotAssigned == false) {
                noAlreadyAssigned = false;
                // CREATE RANDOM VALUE
                randCharNo = rand.nextInt(numberOfQuestions);// ATTENTION RANDOM
                // EXCLUDES
                // MAX.VALUE
                // CHECK IF RAND NO HAS ALLREADY BEEN ASIGNED:
                for (int i = 0; i < numberOfQuestions; i++) {
                    if (randCharNo == allQuestionsArrayNo[i])// no has already
                    // been assigned
                    {
                        noAlreadyAssigned = true;
                    }
                }
                if (noAlreadyAssigned == false) {
                    allQuestionsArrayNo[currentQuestionNo] = randCharNo;
                    currentSlotAssigned = true;
                    currentQuestionNo++;
                }
            }
        } // END OF WHILE
          // TRANSFER NO ARRAY TO STRING
        for (int i = 0; i < numberOfQuestions; i++) {
            int currentNo = allQuestionsArrayNo[i];
            char currentChar = allCharsArray[currentNo];
            String currentString = Character.toString(currentChar);
            allQuestionsArrayString[i] = currentString;
        }
        return allQuestionsArrayString;
    }// END OF generateQuestionCharArray

    public static void printArray() {
        // PRINT ALL CHARACTERS:
        for (int i = 0; i < numberOfQuestions; i++) {
            System.out.println("Question number " + i
                    + " points to char number " + allQuestionsArrayNo[i]
                    + " = String: " + allQuestionsArrayString[i]);
        }
    }

    public static void getAnswer() {
        // KEEP ON ASKING UNTIL SOLVED:
        answeredCorrect = false;
        while (answeredCorrect == false) {
            System.out.print("Type: " + allCharsArray[askedCharNo]);
            System.out.println();
            Scanner input = new Scanner(System.in);
            String typedChar = input.nextLine();
            // CHECK IF ANSWER WAS CORRECT:
            // convert char to string to use equals method:

            if (askedChar.equals(typedChar)) {
                System.out.println("Correct");
                answeredCorrect = true;
            } else {
                System.out.println("WRONG!");
                answeredCorrect = false;
            }
        }

        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        // convert nanoseconds to seconds:
        timeElapsedSec = timeElapsed / Math.pow(10.0, 9.0);
        System.out.printf("Answering Time %.1f seconds%n", timeElapsedSec);
        answeredCorrect = false;// restart question loop

    }// END OF askAndType

    public static void saveTime() {
        // STORE ANSWERING TIME:
        timeArray[askedCharNo] = timeElapsedSec;
        if (timeElapsedSec > slowestCharTime) {
            System.out.println("THIS WAS THE SLOWEST ANSWER");
        }
        // FIND OUT SLOWEST ANSWERING TIME:
        slowestCharTime = 0;
        for (int i = 0; i < numberOfQuestions; i++) {
            double checkTime = timeArray[i];
            if (checkTime > slowestCharTime) {
                slowestCharTime = checkTime;
                slowestCharNo = i;
            }
        }
        // PRINT OUT ALL ANSWERING TIMES:
        for (int i = 0; i < numberOfQuestions; i++) {
            // System.out.printf("Time of %c
            // seconds%.1f%n",allCharsArray[i],timeArray[i]);
        }
        // SMOOTHED VELOCITY
        double currentSignsPerMinute = 60 / timeElapsedSec;
        int smoothvalue = 10;
        if (smoothedSignsPerMinute == 0.0) {
            smoothedSignsPerMinute = currentSignsPerMinute;
        } else {
            smoothedSignsPerMinute = (smoothedSignsPerMinute * (smoothvalue - 1)
                    + currentSignsPerMinute) / smoothvalue;
        }
        System.out.printf("AverageSpeed = %.0f%n", smoothedSignsPerMinute);
    }// END OF saveTime()

    // ******************************************************************
    // MAIN:
    // ******************************************************************
    public static void main(String[] args) {

        createCharArray();
        // timeForAllStart = System.nanoTime();// time to solve all questions
        // once
        generateQuestionStringArray();
        printArray();

    }// END OF MAIN
     // ******************************************************************

}// END OF CLASS
