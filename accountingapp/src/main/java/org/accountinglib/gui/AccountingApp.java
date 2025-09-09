package org.accountinglib.gui;

import org.accountinglib.data.AccountingCompany;
import org.accountinglib.data.Context;
import org.accountinglib.data.Ledger;
import org.accountinglib.saft.SAFTImport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountingApp extends JFrame {

    private static Context context = new Context();
    private DefaultTableModel ledgerTableModel;
    private final JTabbedPane tabs;
    private DefaultTableModel accountsTableModel;
    private AccountsPanel accountsPanel;
    private NewVoucherPanel voucherPanel;
    private LedgerPanel ledgerPanel;
    private CompanyInformationPanel companyInfoPanel;

    public AccountingApp() {
        super("Accounting Library");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 650));
        setLocationRelativeTo(null);

        tabs = new JTabbedPane(SwingConstants.LEFT); // Initialize tabs here
        setJMenuBar(createMenuBar());
        add(createContent(), BorderLayout.CENTER);
    }

    private void updateAccountsTable() {
        accountsPanel.updateAccountsTable();
    }

    private void updateLedgerTable() {
        ledgerPanel.updateLedgerTable(Context.getLedger());
    }

    private JComponent createContent() {
        tabs.addChangeListener(e -> {
            int selectedIndex = tabs.getSelectedIndex();
            if (selectedIndex == 2) { // Accounts tab
                updateAccountsTable();
            }
            if (selectedIndex == 3) {
                voucherPanel.updateNewVoucerPanel();
            }
            if (selectedIndex == 0) { // Company information tab
                companyInfoPanel.updateInfo();
            }
            if (selectedIndex == 1) {
                // Implement re-query logic via AccountingLib
            }
        });

        companyInfoPanel = new CompanyInformationPanel();
        tabs.addTab("Company information", companyInfoPanel);
        ledgerPanel = new LedgerPanel();
        tabs.addTab("Ledger", ledgerPanel);
        accountsPanel = new AccountsPanel();
        tabs.addTab("Accounts", accountsPanel);
        voucherPanel = new NewVoucherPanel(AccountingApp.this);
        tabs.addTab("New Voucher", voucherPanel);
        tabs.addTab("Reports", createPlaceholderPanel("Reports"));
        tabs.addTab("Settings", createPlaceholderPanel("Settings"));
        tabs.addTab("SAF-T Import", createSAFTImportPanel()); // Added a new tab for SAF-T Import

        JLabel status = new JLabel(" Ledger ready");
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.add(status, BorderLayout.WEST);

        tabs.addChangeListener(e -> status.setText(" " + tabs.getTitleAt(tabs.getSelectedIndex()) + " ready"));

        JPanel root = new JPanel(new BorderLayout());
        root.add(tabs, BorderLayout.CENTER);
        root.add(statusBar, BorderLayout.SOUTH);
        return root;
    }

    private JPanel createCompanyInfoPanel() {
        return companyInfoPanel;
    }

    private JPanel createSAFTImportPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(new JButton(new AbstractAction("Import SAF-T") {
            @Override public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(AccountingApp.this) == JFileChooser.APPROVE_OPTION) {
                    String filePath = chooser.getSelectedFile().getAbsolutePath();
                    try {
                        File file = new File(filePath);
                        Ledger ledger = SAFTImport.importSAFT(file);
                        AccountingCompany accountingCompany = new AccountingCompany(ledger.getCompany(), ledger);
                        Context.setAccountingCompany(accountingCompany);
                        Context.setLedger(ledger);

                        updateAccountsTable();
                        updateLedgerTable();
                        companyInfoPanel.updateInfo();

                        JOptionPane.showMessageDialog(AccountingApp.this, "SAF-T file imported successfully.", "Import", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(AccountingApp.this, "Failed to import SAF-T file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }));

        panel.add(toolbar, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createPlaceholderPanel(String name) {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel label = new JLabel(name + " — coming soon");
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 16f));
        panel.add(label);
        return panel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');

        JMenuItem newCompanyItem = new JMenuItem(new AbstractAction("New company") {
            @Override public void actionPerformed(ActionEvent e) {
                NewCompanyDialog dialog = new NewCompanyDialog(AccountingApp.this);
                dialog.setVisible(true);
                if (dialog.isConfirmed()) {
                    JOptionPane.showMessageDialog(AccountingApp.this, "Company created: " + dialog.getCompany().name(), "New Company", JOptionPane.INFORMATION_MESSAGE);

                    AccountingCompany accountingCompany = new AccountingCompany(dialog.getCompany(), new Ledger());
                    accountingCompany.initializeChartOfAccounts();
                    Context.setAccountingCompany(accountingCompany);
                    companyInfoPanel.updateInfo();
                }
            }
        });
        fileMenu.add(newCompanyItem);

        JMenuItem exitItem = new JMenuItem(new AbstractAction("Exit") {
            @Override public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exitItem.setAccelerator(KeyStroke.getKeyStroke("control Q"));

        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
        JMenuItem refreshItem = new JMenuItem(new AbstractAction("Refresh") {
            @Override public void actionPerformed(ActionEvent e) {
                // For now, refresh the current tab
                int idx = tabs.getSelectedIndex();
                if (idx == 0) { // Ledger
                    JOptionPane.showMessageDialog(AccountingApp.this, "Ledger refreshed.");
                } else {
                    JOptionPane.showMessageDialog(AccountingApp.this, "Nothing to refresh here yet.");
                }
            }
        });
        editMenu.add(refreshItem);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        JMenuItem aboutItem = new JMenuItem(new AbstractAction("About") {
            @Override public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AccountingApp.this,
                        "Accounting Library App\nGNU Lesser General Public License.",
                        "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountingApp.class.getName()).log(Level.WARNING, "Failed to set look and feel", ex);
        }

        SwingUtilities.invokeLater(() -> {
            AccountingApp app = new AccountingApp();
            app.setVisible(true);
        });
    }
}
