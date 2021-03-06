package view;

import java.awt.Color;
import java.awt.Frame;

/**
 *
 * @author BryanBoni
 */
public class ThemeEditor extends javax.swing.JDialog{

    private static String curTheme;

    /**
     * Creates new form ThemeEditor
     *
     * @param parent
     * @param modal
     * @param curTheme
     */
    public ThemeEditor(Frame parent, boolean modal, String curTheme) {
        super(parent, modal);
        ThemeEditor.curTheme = curTheme;
        initComponents();
        setTitle("Theme Preferences Editor");
        setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCurTheme = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        classicBtn = new javax.swing.JButton();
        darkBtn = new javax.swing.JButton();
        customBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Current Theme :");

        jCurTheme.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jCurTheme.setText(curTheme);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jCurTheme)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCurTheme))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Themes Available"));

        classicBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        classicBtn.setText("Classic");
        classicBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classicBtnActionPerformed(evt);
            }
        });

        darkBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        darkBtn.setText("Dark");
        darkBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darkBtnActionPerformed(evt);
            }
        });

        customBtn.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        customBtn.setText("Custom it !");
        customBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classicBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(darkBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(classicBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(darkBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(customBtn)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Define the current theme to default dark.
     * 
     * @param evt event catch when the user click on the dark button.
     */
    private void darkBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darkBtnActionPerformed
        WindowPreferences.changePref(new Color(52, 52, 52), new Color(255, 255, 255), Color.BLACK, new Color(255, 255, 255), new Color(52, 52, 52));
        MainWindow.currentWindow.applyPreferences();
        ThemeEditor.curTheme = "Dark";
        jCurTheme.setText(curTheme);
    }//GEN-LAST:event_darkBtnActionPerformed

    /**
     * Define the current theme to default classic.
     * 
     * @param evt event catch when the user click on the classic button.
     */
    private void classicBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classicBtnActionPerformed
        WindowPreferences.changePref(new Color(240, 240, 240), new Color(52, 52, 52), Color.WHITE, new Color(52, 52, 52), new Color(240, 240, 240));
        MainWindow.currentWindow.applyPreferences();
        ThemeEditor.curTheme = "Classic";
        jCurTheme.setText(curTheme);
    }//GEN-LAST:event_classicBtnActionPerformed

    /**
     * Define a custom theme, unused here.
     * 
     * @param evt event catch when the user click on the custom button.
     */
    private void customBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton classicBtn;
    private javax.swing.JButton customBtn;
    private javax.swing.JButton darkBtn;
    private javax.swing.JLabel jCurTheme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
