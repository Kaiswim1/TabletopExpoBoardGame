public class Game {
    private char[][] gridRep;
    private Player p;
    private int size;
    private Dice dice;

    public Game(int gridSize){
        this.size = gridSize;
        gridRep = new char[this.size][this.size];
        p = new Player(2, 2);
        fillGrid();
        placePlayer();
        dice = new Dice(6);
    }

    public Dice getDice(){
        return this.dice;
    }
    public void placePlayer(){
        gridRep[p.getX()][p.getY()] = 'P';
    }

    /**
     * Move the player forward if legal move
     * @param direction
     */
    public String movePlayer(int direction, int distance){
        for(int i=0; i<distance; i++) {
            gridRep[p.getX()][p.getY()] = '.';
            p.moveDir(direction);
            try {
                if (gridRep[p.getX()][p.getY()] == 'W') {
                    p.moveDir(direction - 180);
                }
                placePlayer();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You win");
                return "win";
            }
        }
        return "";
    }
    public void printGrid(){
        for(int i=0; i<gridRep.length; i++){
            for(int j=0; j<gridRep.length; j++){
                System.out.print(gridRep[j][i]+" ");
            }
            System.out.println();
        }
    }

    private void fillWall(int x, int y){
        gridRep[x][y] = 'W';
    }

    private void fillWalls(){
        for(int i=1; i<3; i++){
            fillWall(0, i);
            fillWall(i-1,4);

        }
        for(int i=1; i<5; i++){
            fillWall(i, 0);
            fillWall(i, 5);
        }
        fillWall(5, 4);
        fillWall(5, 1);
        fillWall(3, 2);
        fillWall(5, 0);
        fillWall(5, 3);
        fillWall(1, 2);
    }

    public void fillGrid(){
        for(int i=0; i<gridRep.length; i++){
            for(int j=0; j<gridRep.length; j++){
                gridRep[j][i] = '.';
            }
            System.out.println();
        }
        fillWalls();
    }

    public char[][] getGrid(){
        return this.gridRep;
    }



}
