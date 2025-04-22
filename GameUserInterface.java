import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUserInterface extends JFrame {
    private Game game;
    private final int cellSize = 120;

    private int tries;

    private final Font playerFont;

    public GameUserInterface(Game game) {
        this.playerFont = new Font("Arial", Font.BOLD, 95);
        this.tries = 0;
        this.game = game;
        setTitle("Game Window");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Use a layout manager
        setVisible(true);

        // Create a panel for the arrow buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create movement buttons
        JButton leftButton = new JButton("Left");
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton rightButton = new JButton("Right");

        // Assign action listeners
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(180); // Move left
                game.printGrid();
            }
        });
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(-90); // Move up
                game.printGrid();
            }
        });
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(90); // Move down
                game.printGrid();
            }
        });
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movePlayer(0); // Move right
                game.printGrid();
            }
        });

        // Arrange buttons in arrow key formation
        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonPanel.add(upButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(leftButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonPanel.add(rightButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonPanel.add(downButton, gbc);

        // Add the button panel to the bottom or side of the window
        add(buttonPanel, BorderLayout.EAST); // or BorderLayout.SOUTH if you prefer


    }

    // Handles moving the player
    private void movePlayer(int direction) {
        int roll = game.getDice().roll();
        int option = JOptionPane.showConfirmDialog(this, "You rolled a " + roll,
                "Roll Result", JOptionPane.DEFAULT_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String result = game.movePlayer(direction, roll);
            if ("win".equals(result)) {
                JOptionPane.showMessageDialog(this, "You win!");
            }
            repaint(); // Update the grid
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        char[][] grid = game.getGrid();

        Graphics2D g2d = (Graphics2D) g; // Cast to Graphics2D for advanced control
        Stroke oldStroke = g2d.getStroke(); // Save the original stroke
        g2d.setStroke(new BasicStroke(4)); // Set line thickness (e.g., 4 pixels)

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int y = j * cellSize + 50;
                int x = i * cellSize + 50;

                // Background
                if (grid[i][j] == 'W') {
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.fillRect(x, y, cellSize, cellSize);
                } else {
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(x, y, cellSize, cellSize);
                }
                if (i==2&&j==2) {
                    g2d.setColor(new Color(145, 255, 145));
                    g2d.fillRect(x, y, cellSize, cellSize);
                }

                // Thicker border
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, cellSize, cellSize);

                // Player
                if (grid[i][j] == 'P') {
                    g2d.setColor(Color.RED);
                    g2d.setFont(playerFont);
                    //.drawString("P", x + cellSize / 2 - 25, y + cellSize / 2 + 30);
                }
            }
        }

        g2d.setStroke(oldStroke); // Restore original stroke
    }

}
