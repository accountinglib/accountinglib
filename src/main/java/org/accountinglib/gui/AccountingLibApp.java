package org.accountinglib.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AccountingLibApp extends JFrame {

    private JTable ledgerTable;
    private DefaultTableModel ledgerTableModel;
    private JLabel status;
    private JTabbedPane tabs;

    public AccountingLibApp() {
        super("Accounting Library");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 650));
        setLocationRelativeTo(null);

        setJMenuBar(createMenuBar());
        add(createContent(), BorderLayout.CENTER);
    }

    private JComponent createContent() {
        tabs = new JTabbedPane(JTabbedPane.LEFT);
        tabs.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

        tabs.addTab("Ledger", createLedgerPanel());
        tabs.addTab("Accounts", createPlaceholderPanel("Accounts"));
        tabs.addTab("Reports", createPlaceholderPanel("Reports"));
        tabs.addTab("Settings", createPlaceholderPanel("Settings"));

        status = new JLabel(" Ledger ready");
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.add(status, BorderLayout.WEST);

        tabs.addChangeListener(new ChangeListener() {
            @Override public void stateChanged(ChangeEvent e) {
                status.setText(" " + tabs.getTitleAt(tabs.getSelectedIndex()) + " ready");
            }
        });

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

        ledgerTable = new JTable(ledgerTableModel);
        ledgerTable.setAutoCreateRowSorter(true);
        ledgerTable.setFillsViewportHeight(true);
        ledgerTable.setRowHeight(24);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(ledgerTableModel);
        ledgerTable.setRowSorter(sorter);

        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(new JButton(new AbstractAction("Refresh") {
            @Override public void actionPerformed(ActionEvent e) {
                // TODO: re-query via AccountingLib
                JOptionPane.showMessageDialog(AccountingLibApp.this, "Ledger refreshed.");
            }
        }));
        toolbar.addSeparator();
        toolbar.add(new JButton(new AbstractAction("Import…") {
            @Override public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(AccountingLibApp.this) == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(AccountingLibApp.this,
                            "Selected: " + chooser.getSelectedFile().getAbsolutePath(),
                            "Import", JOptionPane.INFORMATION_MESSAGE);
                    // TODO: load into ledgerTableModel via AccountingLib services
                }
            }
        }));

        loadSampleLedgerData();

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(toolbar, BorderLayout.NORTH);
        panel.add(new JScrollPane(ledgerTable), BorderLayout.CENTER);
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
                        "Accounting Library App (Ledger)\nGNU Lesser General Public License.",
                        "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void loadSampleLedgerData() {
        Object[][] rows = new Object[][]{
                {"2025-09-01", "3000 Sales", "Invoice #1001", 0.00, 12500.00},
                {"2025-09-01", "1920 Bank", "Payment received", 12500.00, 0.00},
                {"2025-09-02", "4000 Purchases", "Supplier bill", 5400.00, 0.00},
                {"2025-09-03", "2700 VAT", "VAT settlement", 0.00, 1350.00}
        };
        for (Object[] r : rows) {
            ledgerTableModel.addRow(r);
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            AccountingLibApp app = new AccountingLibApp();
            app.setVisible(true);
        });
    }
}
