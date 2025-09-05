package org.accountinglib.gui;

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

public class AccountingLibApp extends JFrame {

    private Context context = new Context();
    private DefaultTableModel ledgerTableModel;
    private final JTabbedPane tabs;

    public AccountingLibApp() {
        super("Accounting Library");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 650));
        setLocationRelativeTo(null);

        tabs = new JTabbedPane(SwingConstants.LEFT); // Initialize tabs here
        setJMenuBar(createMenuBar());
        add(createContent(), BorderLayout.CENTER);
    }

    private JComponent createContent() {
        tabs.addChangeListener(e -> {
            int selectedIndex = tabs.getSelectedIndex();
            if (selectedIndex == 0) {
                // Implement re-query logic via AccountingLib
            }
        });

        tabs.addTab("Ledger", createLedgerPanel());
        tabs.addTab("Accounts", createPlaceholderPanel("Accounts"));
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

    private JPanel createLedgerPanel() {
        ledgerTableModel = new DefaultTableModel(
                new Object[]{"Date", "Account", "Description", "Debit", "Credit"}, 0) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override public Class<?> getColumnClass(int columnIndex) {
                return switch (columnIndex) {
                    case 3, 4 -> Double.class;
                    default -> String.class;
                };
            }
        };

        JTable ledgerTable = new JTable(ledgerTableModel);
        ledgerTable.setAutoCreateRowSorter(true);
        ledgerTable.setFillsViewportHeight(true);
        ledgerTable.setRowHeight(24);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(ledgerTable), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSAFTImportPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(new JButton(new AbstractAction("Import SAF-T") {
            @Override public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(AccountingLibApp.this) == JFileChooser.APPROVE_OPTION) {
                    String filePath = chooser.getSelectedFile().getAbsolutePath();
                    try {
                        File file = new File(filePath);
                        Ledger ledger = SAFTImport.importSAFT(file);
                        Context.setLedger(ledger);

                        JOptionPane.showMessageDialog(AccountingLibApp.this, "SAF-T file imported successfully.", "Import", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(AccountingLibApp.this, "Failed to import SAF-T file.", "Error", JOptionPane.ERROR_MESSAGE);
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

        JMenuItem openItem = new JMenuItem(new AbstractAction("Open…") {
            @Override public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(AccountingLibApp.this) == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(AccountingLibApp.this,
                            "Selected: " + chooser.getSelectedFile().getAbsolutePath(),
                            "Open", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JMenuItem exitItem = new JMenuItem(new AbstractAction("Exit") {
            @Override public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exitItem.setAccelerator(KeyStroke.getKeyStroke("control Q"));

        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');
        JMenuItem refreshItem = new JMenuItem(new AbstractAction("Refresh") {
            @Override public void actionPerformed(ActionEvent e) {
                // For now, refresh the current tab
                int idx = tabs.getSelectedIndex();
                if (idx == 0) { // Ledger
                    JOptionPane.showMessageDialog(AccountingLibApp.this, "Ledger refreshed.");
                } else {
                    JOptionPane.showMessageDialog(AccountingLibApp.this, "Nothing to refresh here yet.");
                }
            }
        });
        editMenu.add(refreshItem);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        JMenuItem aboutItem = new JMenuItem(new AbstractAction("About") {
            @Override public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AccountingLibApp.this,
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
            Logger.getLogger(AccountingLibApp.class.getName()).log(Level.WARNING, "Failed to set look and feel", ex);
        }

        SwingUtilities.invokeLater(() -> {
            AccountingLibApp app = new AccountingLibApp();
            app.setVisible(true);
        });
    }
}
