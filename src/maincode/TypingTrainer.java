/**
 * ********************************************************************************
 * TYPING TRAINER
 * ********************************************************************************
 * @file TypingTrainer.java
 * @author Michael Wettstein 
 * @version August 2019, Zürich
 * @brief Interactive Typing Trainer
 * ********************************************************************************
 * Program Steps:
 * Create an array containing all characters to be asked
 * Randomize the order of the chars
 * Level 1 - "Calibration Mode":
 * Ask every char once in the GUI
 * Showing the average answering time for a char
 * Storing the answering time for each char individually
 * Level 2 - "Drill Mode":
 * Ask only the 10 currently slowest answered chars for a minute
 * Permanently updating the top 10 list of the slowest chars
 * End Screen:
 * Display the 5 slowest chars in red and declare them as weak spots.
 * ********************************************************************************
 * This code has been generated using eclipse WindowBuilder.
 * In eclipse it can be copy-pasted in a newly created WindowBuilder>SwingDesigner>
 * Jframe sketch to make the graphical design view available
 * ********************************************************************************
 */

package maincode;

public class TypingTrainer extends javax.swing.JFrame {

  // VARIABLES:
  private static final long serialVersionUID = 1L;

  static boolean answeredCorrect;
  static char questionChar;

  static int questionNo = 0;
  static int qChar;
  static int numberOfQuestions = RandomQuestionGenerator.numberOfQuestions;

  static String[] questionStringArray = new String[numberOfQuestions];
  static String questionString;
  static String typedChar;
  static String level = "0.0";

  static long startTime;
  static long charStopwatch;

  // ARRAYS:
  static long[] answeringTimeArray = new long[numberOfQuestions];

  public TypingTrainer() {
    initComponents();
  }

  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    charsPerMinuteLabel = new javax.swing.JLabel();
    questionField = new javax.swing.JLabel();
    charsPerMinuteField = new javax.swing.JLabel();
    charsToGoLabel = new javax.swing.JLabel();
    charsToGoField = new javax.swing.JLabel();
    answerField = new javax.swing.JTextField();
    infoTextField = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setAlwaysOnTop(true);
    setBackground(javax.swing.UIManager.getDefaults().getColor("Button.focus"));
    setPreferredSize(new java.awt.Dimension(700, 500));
    setResizable(false);
    getContentPane().setLayout(new java.awt.GridBagLayout());

    charsPerMinuteLabel.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
    charsPerMinuteLabel
        .setForeground(javax.swing.UIManager.getDefaults().getColor("Button.disabledText"));
    charsPerMinuteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    charsPerMinuteLabel.setText("chars per minute");
    charsPerMinuteLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    charsPerMinuteLabel.setPreferredSize(new java.awt.Dimension(220, 35));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 4;
    getContentPane().add(charsPerMinuteLabel, gridBagConstraints);

    questionField.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));
    questionField.setFont(new java.awt.Font("Ubuntu Mono", 1, 192)); // NOI18N
    questionField.setForeground(java.awt.Color.black);
    questionField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    questionField.setAlignmentY(0.0F);
    questionField.setBorder(
        javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    questionField.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    questionField.setPreferredSize(new java.awt.Dimension(220, 220));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    getContentPane().add(questionField, gridBagConstraints);

    charsPerMinuteField.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
    charsPerMinuteField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    charsPerMinuteField.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    charsPerMinuteField.setPreferredSize(new java.awt.Dimension(200, 50));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
    gridBagConstraints.insets = new java.awt.Insets(64, 0, 0, 0);
    getContentPane().add(charsPerMinuteField, gridBagConstraints);

    charsToGoLabel.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
    charsToGoLabel
        .setForeground(javax.swing.UIManager.getDefaults().getColor("Button.disabledText"));
    charsToGoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    charsToGoLabel.setText("chars to go");
    charsToGoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    charsToGoLabel.setPreferredSize(new java.awt.Dimension(220, 35));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    getContentPane().add(charsToGoLabel, gridBagConstraints);

    charsToGoField.setFont(new java.awt.Font("Dialog", 1, 40)); // NOI18N
    charsToGoField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    charsToGoField.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    charsToGoField.setPreferredSize(new java.awt.Dimension(200, 50));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
    gridBagConstraints.insets = new java.awt.Insets(64, 0, 0, 0);
    getContentPane().add(charsToGoField, gridBagConstraints);

    answerField.setFont(new java.awt.Font("Ubuntu Mono", 1, 90)); // NOI18N
    answerField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    answerField.setPreferredSize(new java.awt.Dimension(130, 130));
    answerField.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyReleased(java.awt.event.KeyEvent evt) {
        answerFieldKeyReleased(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
    getContentPane().add(answerField, gridBagConstraints);
    infoTextField.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));
    infoTextField.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
    infoTextField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    infoTextField.setText(" press any key to start");
    infoTextField.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    infoTextField.setPreferredSize(new java.awt.Dimension(600, 60));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridwidth = 3;
    getContentPane().add(infoTextField, gridBagConstraints);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void answerFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_answerFieldKeyReleased

    typedChar = answerField.getText();

    switch (level) {

    case "0.0":// SHOW FIRST QUESTION
      infoTextField.setText("");
      level = "1.0";
      questionString = questionStringArray[questionNo];
      questionField.setText(questionString);
      answerField.setText("");
      String charsToGoString = Integer.toString(numberOfQuestions - questionNo);
      charsToGoField.setText(charsToGoString);
      startTime = System.currentTimeMillis();
      charStopwatch = System.currentTimeMillis();
      break;

    case "1.0":// CALIBRATION LEVEL, TYPE EVERY CHAR ONCE
      questionString = questionStringArray[questionNo];
      questionField.setText(questionString); // show question char in question field

      // ANSWERED CORRECT:
      if (typedChar.equals(questionStringArray[questionNo])) {
        setQuestionFieldColor("black");

        // display chars to go:
        charsToGoString = Integer.toString(numberOfQuestions - (questionNo + 1));
        charsToGoField.setText(charsToGoString);

        // display typing speed:
        long timeElapsed = System.currentTimeMillis() - startTime;
        float timeElapsedSeconds = timeElapsed / 1000f;
        float timePerChar = timeElapsedSeconds / (questionNo + 1);
        float charsPerMinute = 60 / timePerChar;
        int charsPerMinuteInt = Math.round(charsPerMinute);
        String charsPerMinuteString = String.valueOf(charsPerMinuteInt);
        charsPerMinuteField.setText(charsPerMinuteString);

        // store answering speed
        long charAnsweringTime = System.currentTimeMillis() - charStopwatch;
        answeringTimeArray[questionNo] = charAnsweringTime;

        System.out.println("Question number " + questionNo + " took "
            + answeringTimeArray[questionNo] + " ms to answer");


        if (numberOfQuestions - (questionNo+1) == 0) {
          level = "1.1";
          break;
        }
        // display next question:
        charStopwatch = System.currentTimeMillis();
        questionString = questionStringArray[questionNo];
        questionField.setText(questionString);
        charsToGoString = Integer.toString(numberOfQuestions - questionNo);
        answerField.setText("");
        questionNo++;
        break;

        // ANSWERED WRONG:
      } else if (!typedChar.equals("")) {// = answered wrong
        setQuestionFieldColor("red");
      }
      break;

    case "1.1":// CALIBRATION LEVEL FINISHED
      long nextLevelTimer = System.currentTimeMillis() + 2000;
      while (System.currentTimeMillis() < nextLevelTimer) {
        // wait
      }
      infoTextField.setText("press any key to enter Drill Mode");
      level = "2.0";
      break;

    case "2.0": // DRILL MODE:
      break;

    }// GEN-LAST:event_answerFieldKeyReleased
  }

  public static void main(String args[]) {

    RandomQuestionGenerator.createCharArray();
    questionStringArray = RandomQuestionGenerator.generateQuestionStringArray();

    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
          .getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(TypingTrainer.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(TypingTrainer.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(TypingTrainer.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(TypingTrainer.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    }

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new TypingTrainer().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  public javax.swing.JTextField answerField;
  public javax.swing.JLabel charsPerMinuteField;
  public javax.swing.JLabel charsPerMinuteLabel;
  public javax.swing.JLabel charsToGoField;
  public javax.swing.JLabel charsToGoLabel;
  public javax.swing.JLabel infoTextField;
  public javax.swing.JLabel questionField;
  // End of variables declaration//GEN-END:variables

  private void setQuestionFieldColor(String color) {
    if (color.equals("red")) {
      questionField.setForeground(java.awt.Color.red);

    }
    if (color.equals("black")) {
      questionField.setForeground(java.awt.Color.black);
    }
  }
}