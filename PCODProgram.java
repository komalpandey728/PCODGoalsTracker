import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class PCODProgram {
    private ArrayList<String> goalsList = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PCODProgram().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("PCOD Goals Tracker");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel header = new JLabel("PCOD Tracker", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setForeground(new Color(75, 75, 200));

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addGoalBtn = new JButton("Add a Goal");
        JButton viewGoalsBtn = new JButton("View My Goals");
        JButton setReminderBtn = new JButton("Set a Reminder");
        JButton motivationBtn = new JButton("Get Motivation");
        JButton resourcesBtn = new JButton("PCOD Resources");

        addGoalBtn.addActionListener(e -> promptForGoal());
        viewGoalsBtn.addActionListener(e -> displayGoals());
        setReminderBtn.addActionListener(e -> setReminder());
        motivationBtn.addActionListener(e -> showMotivation());
        resourcesBtn.addActionListener(e -> accessResources());

        buttonPanel.add(header);
        buttonPanel.add(addGoalBtn);
        buttonPanel.add(viewGoalsBtn);
        buttonPanel.add(setReminderBtn);
        buttonPanel.add(motivationBtn);
        buttonPanel.add(resourcesBtn);

        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    private void promptForGoal() {
        String goal = JOptionPane.showInputDialog("What's your goal?");
        if (goal != null && !goal.trim().isEmpty()) {
            goalsList.add(goal);
            JOptionPane.showMessageDialog(null, "Goal added!");
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a goal.");
        }
    }

    private void displayGoals() {
        if (goalsList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You haven't set any goals yet.");
        } else {
            StringBuilder goals = new StringBuilder("Your Goals:\n");
            for (String g : goalsList) {
                goals.append("- ").append(g).append("\n");
            }
            JOptionPane.showMessageDialog(null, goals.toString());
        }
    }

    private void setReminder() {
        String message = JOptionPane.showInputDialog("What do you want to remember?");
        String timeStr = JOptionPane.showInputDialog("In how many seconds?");

        try {
            int time = Integer.parseInt(timeStr) * 1000;
            Timer timer = new Timer(time, e -> JOptionPane.showMessageDialog(null, "Reminder: " + message));
            timer.setRepeats(false);
            timer.start();
            JOptionPane.showMessageDialog(null, "Your reminder is set!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a number for the time.");
        }
    }

    private void showMotivation() {
        JOptionPane.showMessageDialog(null, "Keep going! YOUU GOOO GIRLLLL!");
        openLink("https://youtu.be/CJhA1x2U9bc?si=17ZNklBJgklN7jSJ");
    }

    private void accessResources() {
        String[] options = {"Yoga Video", "Get to Know More About PCOD"};
        int choice = JOptionPane.showOptionDialog(null, "Pick a resource to check out:", 
                "PCOD Resources", JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            openLink("https://youtu.be/GTVvhMPSoE8?si=dkB2TnaSzJNQn7rr");
        } else if (choice == 1) {
            openLink("https://www.carehospitals.com/blog-detail/pcod-polycystic-ovarian-disease-causes-symptoms-and-treatments/");
        }
    }

    private void openLink(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                JOptionPane.showMessageDialog(null, "Check this link: " + url);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can't open the link.");
        }
    }
}
