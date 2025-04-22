public class Player {
    private int x;
    private int y;
    public Player(int start_x, int start_y){
        this.x = start_x;
        this.y = start_y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void moveDir(int direction){
        int rx = (int) Math.round(Math.cos(Math.toRadians(direction)));
        int ry = (int) Math.round(Math.sin(Math.toRadians(direction)));
        this.x+=rx;
        this.y+=ry;
    }
}
