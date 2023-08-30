/*
import java.util.Random;
import java.util.Scanner;

public class NumberGuessGame
{

    public static void main(String[] args)
    {
       Scanner sc = new Scanner(System.in);
       Random num = new Random();
       int number = num.nextInt(101);
       int count=1;
	System.out.println("\n\n\t\t\t\t**********************************");
        System.out.println("\t\t\t\t  Welcome To Number Guessing Game");
	System.out.println("\t\t\t\t**********************************");
        System.out.println("\n\nEnter your Guess(upto 100):::");
        int guess = sc.nextInt();
        while(guess!=number)
        {
            
            if(guess<number)
            {
                System.out.println("GUESS HIGHER");
                 guess=sc.nextInt();
            }
            else
            {
                System.out.println("GUESS LOWER");
                 guess=sc.nextInt();
            }
            count++;
        }
        System.out.println("\t\t\t!!!!!!!!CONGRATULATIONS YOU HAVE GUESSED CORRECTLY!!!!!!!");
        System.out.println("\t\t\t\tIt Took You "+count+" tries to correctly guess");
    }
}
*/


/***Updated Version******/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.table.*;

public class NumberGuessGame {
    private JFrame mainFrame;
	    int guessCount = 1;
	    String playerName = null;
	    String mode = "";

    public NumberGuessGame() {
        initializeMainFrame();
    }

    private void initializeMainFrame() {
        mainFrame = new JFrame("Number Guess Game");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setLayout(new GridBagLayout());

        JButton startBtn = new JButton("Start Game");
        JButton exitBtn = new JButton("Exit");
        JButton showHistory = new JButton("Show History");

        Dimension buttonSize = new Dimension(150, 50);
        startBtn.setPreferredSize(buttonSize);
        exitBtn.setPreferredSize(buttonSize);
        showHistory.setPreferredSize(buttonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        mainFrame.add(startBtn, gbc);

        gbc.gridy = 1;
        mainFrame.add(showHistory , gbc);

        gbc.gridy = 2;
        mainFrame.add(exitBtn, gbc);
        
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = getPlayerName();
                if (playerName != null && !playerName.isEmpty()) {
                    openGameFrame(playerName);
                    mainFrame.setVisible(false);
                }
            }
        });
        
        showHistory.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		displayData();
        	}
        	
        });
        
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }

    private void openGameFrame(String playerName) {
        JFrame gameMenuFrame = new JFrame("Number Guess Game");
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        gameMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameMenuFrame.setSize(800, 600);
        gameMenuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameMenuFrame.setLayout(new BorderLayout());

        JLabel gameLabel = new JLabel("Welcome, " + playerName + "!");
        JButton backButton = new JButton("Back to Main Menu");

        JLabel chooseLevel = new JLabel("Choose Your Level:");
        JButton lvl1 = new JButton("Easy");
        JButton lvl2 = new JButton("Medium");
        JButton lvl3 = new JButton("Hard");

        lvl1.addActionListener(e -> mainGame(101));
        lvl2.addActionListener(e -> mainGame(501));
        lvl3.addActionListener(e -> mainGame(1001));

        panel2.add(chooseLevel);
        panel2.add(lvl1);
        panel2.add(lvl2);
        panel2.add(lvl3);
        panel1.add(gameLabel);
        panel3.add(backButton);

        gameMenuFrame.add(panel1, BorderLayout.NORTH);
        gameMenuFrame.add(panel2, BorderLayout.CENTER);
        gameMenuFrame.add(panel3, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMenuFrame.dispose();
                mainFrame.setVisible(true);
            }
        });

        gameMenuFrame.setVisible(true);
    }

    private void mainGame(int level) {
    	 JFrame gameFrame = new JFrame("Number Guessing Game");
 	    gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 	    gameFrame.setSize(600, 400);
 	    gameFrame.setLocationRelativeTo(null);
 	    gameFrame.setLayout(new FlowLayout());

 	    Random num = new Random();
 	    int numberToGuess = num.nextInt(level);

 	    JLabel titleLabel = new JLabel("Enter Your Guess (upto " + (level - 1) + "):");
 	    JTextField guessField = new JTextField(10);
 	    JButton guessButton = new JButton("Guess");
 	    JLabel resultLabel = new JLabel();
 	    JButton replayButton = new JButton("Replay");
 	    replayButton.setVisible(false);

 	    guessButton.addActionListener(new ActionListener() {
 	        @Override
 	        public void actionPerformed(ActionEvent e) {
 	        	try {

 	            int guess = Integer.parseInt(guessField.getText());

 	            if (guess == numberToGuess) {
 	                resultLabel.setText("Congratulations! "+playerName+", You guessed it correctly in " + guessCount + " tries.");
 	                guessButton.setEnabled(false);
 	                replayButton.setVisible(true);
 	                saveToDB(level);
 	            } else if (guess < numberToGuess) {
 	                resultLabel.setText("Guess Higher.");
 	            } else {
 	                resultLabel.setText("Guess Lower.");
 	            }

 	            guessCount++;
 	            guessField.setText("");
 	        	}
 	        	catch(NumberFormatException ex)
 	        	{
 	        		JOptionPane.showMessageDialog(gameFrame, "Please Enter Valid Number","Invalid Number",JOptionPane.WARNING_MESSAGE);		
 	        	}
 	        }
 	    });
 	    
 	    replayButton.addActionListener(e -> 
 	    {
 	    	gameFrame.dispose();
            guessCount = 1;

 	    	mainGame(level);
 	    });

 	    JPanel panel = new JPanel(new GridLayout(6, 1));
 	    panel.add(titleLabel);
 	    panel.add(guessField);
 	    panel.add(guessButton);
 	    panel.add(resultLabel);
 	    panel.add(replayButton);

 	    gameFrame.add(panel);
 	    gameFrame.setVisible(true);
 	}
    

    private String getPlayerName() {
        return JOptionPane.showInputDialog(mainFrame, "Enter Your Name:", "Player Name", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void saveToDB(int level)
    {
    	if(level == 101) {
    		mode = "Easy";
    	}
    	else if(level == 501) {
    		mode = "Medium";
    	}
    	else {
    		mode = "Hard";
    	}
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost/NumberGuessGame","root","");
    		Statement stm = cnn.createStatement();
    		String sql = "INSERT INTO Players(player_name,mode,no_of_tries) VALUES('"+playerName+"','"+mode+"',"+guessCount+")";
    		stm.executeUpdate(sql);
    		stm.close();
    		cnn.close();
    	}
    	catch(ClassNotFoundException|SQLException ex) {
    		JOptionPane.showMessageDialog(mainFrame,ex.getMessage(),"Failed",JOptionPane.ERROR_MESSAGE);
    	}
    }
    private void displayData() {
    	JFrame tbl = new JFrame("Players Record");
    	JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        tbl.add(scrollPane, BorderLayout.CENTER);
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("S.N");
        model.addColumn("Player's Name");
        model.addColumn("Mode");
        model.addColumn("No.of tries");

        try {
    		Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost/NumberGuessGame","root","");
            Statement stmt = cnn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Players");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("S_N"),
                        rs.getString("player_name"),
                        rs.getString("mode"),
                        rs.getInt("no_of_tries")
                });
            }

            stmt.close();
            cnn.close();
        } catch (SQLException ex) {
    		JOptionPane.showMessageDialog(tbl,ex.getMessage(),"Failed",JOptionPane.ERROR_MESSAGE);
        }
        
        table.setModel(model);
        tbl.pack();
        tbl.setLocationRelativeTo(null);
        tbl.setVisible(true);
    }

    public static void main(String[] args) {
    	new NumberGuessGame();
    }
}
