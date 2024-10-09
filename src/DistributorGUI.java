import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistributorGUI extends JFrame {

    private Distributor distributor;

    public DistributorGUI(Distributor distributor) {
        this.distributor = distributor;

        setTitle("Distributor System");

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Subscription", createAddSubscriptionPanel());
        tabbedPane.addTab("List Subscriptions", createListSubscriptionsPanel());
        tabbedPane.addTab("Accept Payment", createAcceptPaymentPanel());
        tabbedPane.addTab("Generate Report", createReportPanel());

        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        setJMenuBar(createMenuBar());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createAddSubscriptionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField issnField = new JTextField();
        JTextField subscriberNameField = new JTextField();

        JButton addSubscriptionButton = new JButton("Add Subscription");
        addSubscriptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String issn = issnField.getText();
                    String subscriberName = subscriberNameField.getText();

                    if (issn.isEmpty() || subscriberName.isEmpty()) {
                        JOptionPane.showMessageDialog(DistributorGUI.this, "Please fill in all fields.");
                        return;
                    }

                    Journal journal = distributor.searchJournal(issn);
                    Subscriber subscriber = distributor.searchSubscriber(subscriberName);

                    if (journal != null && subscriber != null) {
                        Subscription subscription = new Subscription(
                                new DateInfo(1, 2023, 12), 1, journal, subscriber);
                        distributor.addSubscription(issn, subscriber, subscription);
                        JOptionPane.showMessageDialog(DistributorGUI.this, "Subscription added successfully!");
                        clearFields(issnField, subscriberNameField);
                    } else {
                        JOptionPane.showMessageDialog(DistributorGUI.this, "Journal or subscriber not found!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DistributorGUI.this, "Invalid input for subscription details!");
                }
            }
        });

        panel.add(new JLabel("ISSN: "));
        panel.add(issnField);
        panel.add(new JLabel("Subscriber Name: "));
        panel.add(subscriberNameField);
        panel.add(addSubscriptionButton);

        return panel;
    }

    private JPanel createListSubscriptionsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextField subscriberNameField = new JTextField();
        JTextField issnField = new JTextField();
        JButton listSubscriptionsButton = new JButton("List Subscriptions");

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        listSubscriptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String subscriberName = subscriberNameField.getText();
                    String issn = issnField.getText();

                    if (subscriberName.isEmpty() && issn.isEmpty()) {
                        JOptionPane.showMessageDialog(DistributorGUI.this, "Please enter a subscriber name or journal ISSN.");
                        return;
                    }

                    if (!subscriberName.isEmpty() && issn.isEmpty()) {
                        
                        distributor.listSubscriptions(subscriberName, resultArea);
                    } else if (subscriberName.isEmpty() && !issn.isEmpty()) {

                        distributor.listSubscriptionsByISSN(issn, resultArea);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DistributorGUI.this, "Error while listing subscriptions!");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Subscriber Name: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(subscriberNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Journal ISSN: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(issnField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(listSubscriptionsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(resultArea), gbc);

        return panel;
    }
    
    private JPanel createReportPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton generateReportButton = new JButton("Generate Report");
        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);

        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String report = distributor.report();
                reportArea.setText(report);
            }
        });

        panel.add(generateReportButton, BorderLayout.NORTH);
        panel.add(new JScrollPane(reportArea), BorderLayout.CENTER);

        return panel;
    }


    private JPanel createAcceptPaymentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField issnField = new JTextField();
        JTextField subscriberNameField = new JTextField();
        JTextField paymentAmountField = new JTextField();

        JButton acceptPaymentButton = new JButton("Accept Payment");
        acceptPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String issn = issnField.getText();
                    String subscriberName = subscriberNameField.getText();
                    double paymentAmount = Double.parseDouble(paymentAmountField.getText());

                    if (issn.isEmpty() || subscriberName.isEmpty() || paymentAmount <= 0) {
                        JOptionPane.showMessageDialog(DistributorGUI.this, "Please fill in all fields with valid values.");
                        return;
                    }

                    Journal journal = distributor.searchJournal(issn);
                    Subscriber subscriber = distributor.searchSubscriber(subscriberName);

                    if (journal != null && subscriber != null) {
                        Subscription subscription = journal.getSubscription(subscriber);

                        if (subscription != null) {
                            subscription.acceptPayment(paymentAmount);
                            JOptionPane.showMessageDialog(DistributorGUI.this, "Payment accepted successfully!");
                            clearFields(issnField, subscriberNameField, paymentAmountField);
                        } else {
                            JOptionPane.showMessageDialog(DistributorGUI.this, "Subscription not found!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(DistributorGUI.this, "Journal or subscriber not found!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DistributorGUI.this, "Invalid input for payment details!");
                }
            }
        });

        panel.add(new JLabel("ISSN: "));
        panel.add(issnField);
        panel.add(new JLabel("Subscriber Name: "));
        panel.add(subscriberNameField);
        panel.add(new JLabel("Payment Amount: "));
        panel.add(paymentAmountField);
        panel.add(acceptPaymentButton);

        return panel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        return menuBar;
    }

    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        Distributor distributor = new Distributor();
        Journal journal = new Journal("Dergi1", "1234-5678", 12, 10.0);
        Journal journal2 = new Journal("Dergi2", "1234-56789", 12, 10.0);
        Journal journal3 = new Journal("Dergi3", "1234-567810", 12, 10.0);
        Subscriber subscriber = new Individual("Merve Erdoğan", "Huzurevleri Mahallesi", "1234-5678-9101-1121", 12, 2023, 123);
        Subscriber subscriber2 = new Individual("Kerem Kayacı", "Gürselpaşa Mahallesi", "1234-5678-9101-1122", 12, 2023, 123);
        Subscriber subscriber3 = new Individual("Ali Emir İbici", "Toros Mahallesi", "1234-5678-9101-1123", 12, 2023, 123);
        distributor.addJournal(journal);
        distributor.addJournal(journal2);
        distributor.addJournal(journal3);
        distributor.addSubscriber(subscriber);
        distributor.addSubscriber(subscriber2);
        distributor.addSubscriber(subscriber3);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DistributorGUI(distributor);
            }
        });
    }
}
