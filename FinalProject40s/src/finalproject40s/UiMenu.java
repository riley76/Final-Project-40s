/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject40s;

import static finalproject40s.PropertiesManager.accountsCreated;
import gameTools.Constants;
import gameTools.FileHandler;
import gameTools.LinkedList;

/**
 *
 * @author r.wiggins
 */
public class UiMenu extends javax.swing.JFrame {
    
    PropertiesManager manager;

    /**
     * Creates new form UiMenu
     * @param manager
     */
    public UiMenu(PropertiesManager manager) {
        initComponents();
        this.manager = manager;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jMenu1 = new javax.swing.JMenu();
        buttonAdmin = new javax.swing.JButton();
        button1 = new javax.swing.JButton();
        labNeverEndingMode = new javax.swing.JLabel();
        labControlType = new javax.swing.JLabel();
        labTitle = new javax.swing.JLabel();
        labelTitle = new javax.swing.JLabel();
        button2 = new javax.swing.JButton();
        button3 = new javax.swing.JButton();
        bttExit = new javax.swing.JButton();
        button4 = new javax.swing.JButton();
        button6 = new javax.swing.JButton();
        button5 = new javax.swing.JButton();
        labLoggedInPoints = new javax.swing.JLabel();
        labName5 = new javax.swing.JLabel();
        labName6 = new javax.swing.JLabel();
        labName7 = new javax.swing.JLabel();
        labTotalAccounts = new javax.swing.JLabel();
        labLoggedInGames = new javax.swing.JLabel();
        labLoggedInLives = new javax.swing.JLabel();
        labLoggedInName = new javax.swing.JLabel();
        labTotalAccounts1 = new javax.swing.JLabel();
        labTotalAccounts2 = new javax.swing.JLabel();
        labTotalAccounts3 = new javax.swing.JLabel();
        labName8 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(725, 600));
        getContentPane().setLayout(null);

        buttonAdmin.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        buttonAdmin.setText("Admin");
        buttonAdmin.setMargin(new java.awt.Insets(0, 0, 0, 0));
        buttonAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdminActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAdmin);
        buttonAdmin.setBounds(903, 506, 30, 15);

        button1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        button1.setText("Error");
        button1.setVerifyInputWhenFocusTarget(false);
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        getContentPane().add(button1);
        button1.setBounds(40, 130, 250, 80);

        labNeverEndingMode.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        labNeverEndingMode.setText("Error");
        getContentPane().add(labNeverEndingMode);
        labNeverEndingMode.setBounds(70, 80, 220, 40);

        labControlType.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        labControlType.setText("Error");
        getContentPane().add(labControlType);
        labControlType.setBounds(680, 80, 180, 40);

        labTitle.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        labTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labTitle.setText("Error");
        labTitle.setInheritsPopupMenu(false);
        getContentPane().add(labTitle);
        labTitle.setBounds(350, 80, 240, 40);

        labelTitle.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        labelTitle.setText("Welcome to Rileys Grand Game system, my Final Project for Computer Science 40s, 2019.");
        getContentPane().add(labelTitle);
        labelTitle.setBounds(100, 0, 730, 80);

        button2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        button2.setText("Error");
        button2.setVerifyInputWhenFocusTarget(false);
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        getContentPane().add(button2);
        button2.setBounds(350, 130, 250, 80);

        button3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        button3.setText("Error");
        button3.setVerifyInputWhenFocusTarget(false);
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        getContentPane().add(button3);
        button3.setBounds(650, 130, 250, 80);

        bttExit.setBackground(new java.awt.Color(255, 153, 153));
        bttExit.setText("Exit");
        bttExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttExitActionPerformed(evt);
            }
        });
        getContentPane().add(bttExit);
        bttExit.setBounds(840, 0, 90, 70);

        button4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        button4.setText("Error");
        button4.setVerifyInputWhenFocusTarget(false);
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button4ActionPerformed(evt);
            }
        });
        getContentPane().add(button4);
        button4.setBounds(40, 250, 250, 80);

        button6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        button6.setText("Error");
        button6.setVerifyInputWhenFocusTarget(false);
        button6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button6ActionPerformed(evt);
            }
        });
        getContentPane().add(button6);
        button6.setBounds(650, 250, 250, 80);

        button5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        button5.setText("Error");
        button5.setVerifyInputWhenFocusTarget(false);
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });
        getContentPane().add(button5);
        button5.setBounds(350, 250, 250, 80);

        labLoggedInPoints.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labLoggedInPoints.setText("Error");
        getContentPane().add(labLoggedInPoints);
        labLoggedInPoints.setBounds(260, 390, 350, 40);

        labName5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labName5.setText("Account Logged In: Games Played   = ");
        getContentPane().add(labName5);
        labName5.setBounds(40, 430, 230, 40);

        labName6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labName6.setText("Account Logged In: Lives Lost         = ");
        getContentPane().add(labName6);
        labName6.setBounds(40, 470, 230, 40);

        labName7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labName7.setText("Account Logged In: Total Points     = ");
        getContentPane().add(labName7);
        labName7.setBounds(40, 390, 230, 40);

        labTotalAccounts.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labTotalAccounts.setText("Total Games Played = ");
        getContentPane().add(labTotalAccounts);
        labTotalAccounts.setBounds(620, 440, 230, 40);

        labLoggedInGames.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labLoggedInGames.setText("Error");
        getContentPane().add(labLoggedInGames);
        labLoggedInGames.setBounds(260, 430, 350, 40);

        labLoggedInLives.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labLoggedInLives.setText("Error");
        getContentPane().add(labLoggedInLives);
        labLoggedInLives.setBounds(260, 470, 350, 40);

        labLoggedInName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labLoggedInName.setText("Error");
        getContentPane().add(labLoggedInName);
        labLoggedInName.setBounds(260, 350, 350, 40);

        labTotalAccounts1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labTotalAccounts1.setText("Total Accounts.       = ");
        getContentPane().add(labTotalAccounts1);
        labTotalAccounts1.setBounds(620, 350, 230, 40);

        labTotalAccounts2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labTotalAccounts2.setText("Total Points.            = ");
        getContentPane().add(labTotalAccounts2);
        labTotalAccounts2.setBounds(620, 400, 230, 40);

        labTotalAccounts3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labTotalAccounts3.setText("Total Lives Lost       = ");
        getContentPane().add(labTotalAccounts3);
        labTotalAccounts3.setBounds(620, 480, 230, 40);

        labName8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labName8.setText("Account Logged In: Name             = ");
        getContentPane().add(labName8);
        labName8.setBounds(40, 350, 230, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button4ActionPerformed
        manager.clickedButton4();
    }//GEN-LAST:event_button4ActionPerformed

    private void button6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button6ActionPerformed
        manager.clickedButton6();
    }//GEN-LAST:event_button6ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        manager.clickedButton1();
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        manager.clickedButton2();
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        manager.clickedButton3();
    }//GEN-LAST:event_button3ActionPerformed

    private void bttExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttExitActionPerformed
        manager.keepPlaying(Constants.EXITED_GAME);
    }//GEN-LAST:event_bttExitActionPerformed

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        manager.clickedButton5();
    }//GEN-LAST:event_button5ActionPerformed

    private void buttonAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdminActionPerformed
        manager.toggleAdmin();
    }//GEN-LAST:event_buttonAdminActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bttExit;
    public javax.swing.JButton button1;
    public javax.swing.JButton button2;
    public javax.swing.JButton button3;
    public javax.swing.JButton button4;
    public javax.swing.JButton button5;
    public javax.swing.JButton button6;
    public javax.swing.JButton buttonAdmin;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JSpinner jSpinner1;
    public javax.swing.JLabel labControlType;
    public javax.swing.JLabel labLoggedInGames;
    public javax.swing.JLabel labLoggedInLives;
    public javax.swing.JLabel labLoggedInName;
    public javax.swing.JLabel labLoggedInPoints;
    public javax.swing.JLabel labName5;
    public javax.swing.JLabel labName6;
    public javax.swing.JLabel labName7;
    public javax.swing.JLabel labName8;
    public javax.swing.JLabel labNeverEndingMode;
    public javax.swing.JLabel labTitle;
    public javax.swing.JLabel labTotalAccounts;
    public javax.swing.JLabel labTotalAccounts1;
    public javax.swing.JLabel labTotalAccounts2;
    public javax.swing.JLabel labTotalAccounts3;
    private javax.swing.JLabel labelTitle;
    // End of variables declaration//GEN-END:variables

    
     
}
