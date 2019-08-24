/**
 * ********************************************************************************
 * TYPING TRAINER
 * ********************************************************************************
 * @file TypingTrainerSwing.java
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

import javax.sound.midi.MidiDevice.Info;

public class TypingTrainerSwing extends javax.swing.JFrame {

  private static final long serialVersionUID = 1L;
  
  static int numberOfQuestions = RandomCharGenerator.numberOfQuestions;
  
  public static String[] questionStringArray = new String[numberOfQuestions];
  public static int qChar;
  
  public static boolean answeredCorrect;
  
  static char questionChar;
  
  static String questionString;
  static String typedChar;
  String level = "0.0";
  long timeForAllStart;

  static int questionNo = 0;

  /**
   * Creates new form JFrame_GUI
   */
  public TypingTrainerSwing() {
    initComponents();
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
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
    answerField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        answerFieldActionPerformed(evt);
      }
    });
    answerField.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        answerFieldKeyTyped(evt);
      }

      public void keyPressed(java.awt.event.KeyEvent evt) {
        answerFieldKeyPressed(evt);
      }

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

  private void answerFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_answerFieldActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_answerFieldActionPerformed

  private void answerFieldKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_answerFieldKeyPressed
    // infoTextField.setText("");
  }// GEN-LAST:event_answerFieldKeyPressed

  private void answerFieldKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_answerFieldKeyReleased

    typedChar = answerField.getText();

    switch (level) {

    case "0.0":// START SCREEN
      infoTextField.setText("");
      level = "1.0";
      // show question char in question field
      String questionString = questionStringArray[questionNo];
      questionField.setText(questionString);
      answerField.setText("");
      timeForAllStart =  System.currentTimeMillis();
      break;

    case "1.0":// CALIBRATION LEVEL, TYPE EVERY CHAR ONCE
      // show question char in question field
      questionString = questionStringArray[questionNo];
      questionField.setText(questionString);

      String charsToGoString = Integer.toString(numberOfQuestions - questionNo);
      charsToGoField.setText(charsToGoString);

      if (typedChar.equals(questionStringArray[questionNo])) {// = answered correct // correct
        setQuestionFieldColor("black");
        questionNo++;
        if (numberOfQuestions - (questionNo) == 0) {
          level = "1.1";
          break;
        }
        questionString = questionStringArray[questionNo];
        questionField.setText(questionString);
        // charsToGoString=Integer.toString(numberOfQuestions-questionNo);
        answerField.setText("");
      } else if (!typedChar.equals("")) {// = answered wrong
        setQuestionFieldColor("red");
      }
      break;

      
    case "1.1":// CALIBRATION LEVEL FINISHED
      infoTextField.setText("...finished!");
      long timeForAllFinish =  System.currentTimeMillis();
      long timeElapsed = timeForAllFinish - timeForAllStart;
      String timeElapsedString = new Long(timeElapsed/1000).toString();
      charsPerMinuteField.setText(timeElapsedString);
      break;
      
    }// GEN-LAST:event_answerFieldKeyReleased
  }

  private void answerFieldKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_answerFieldKeyTyped
    // TODO add your handling code here:

  }// GEN-LAST:event_answerFieldKeyTyped

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {

    RandomCharGenerator.createCharArray();
    questionStringArray = RandomCharGenerator.generateQuestionStringArray();

    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
          .getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(TypingTrainerSwing.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(TypingTrainerSwing.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(TypingTrainerSwing.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(TypingTrainerSwing.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    }

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new TypingTrainerSwing().setVisible(true);
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