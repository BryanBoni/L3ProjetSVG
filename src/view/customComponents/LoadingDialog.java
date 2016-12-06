package view.customComponents;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import static view.MainWindow.currentWindow;
import view.ThemeEditor;

/**
 *
 * @author Bryanboni
 */
public class LoadingDialog extends javax.swing.JDialog {
    
    Thread loadingthread;

    /**
     * Creates new form LoadingDialog
     */
    public LoadingDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadingBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Loading file");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loadingBar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loadingBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Used to change the value of the progress bar.
     *
     * @param value a positive or negative value.
     */
    public void modifLoadingBar(int value) {
        loadingBar.setValue(value);
    }

    public void loadingDone(){
        this.setVisible(false);
        loadingthread.interrupt();
    }
    
    public void startLoading(){
       // java.awt.EventQueue.invokeLater(new Runnable() {
       Runnable Loading = new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace(System.out);
                }
                LoadingDialog loadindDialog = new LoadingDialog(currentWindow, true);

                loadindDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                });
                loadindDialog.setVisible(true);
            }
        };
       
       loadingthread = new Thread(Loading);
       loadingthread.start();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JProgressBar loadingBar;
    // End of variables declaration//GEN-END:variables
}
