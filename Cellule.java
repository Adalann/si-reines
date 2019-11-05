public class Cellule {
    final static int LIBRE = 0;
    final static int REINE = 1;
    final static int MENACEE = 2;

    private int x;
    private int y;
    private int typeOccupation;    

    public Cellule(int x, int y) {
        this.x = x;
        this.y = y;
        this.typeOccupation = LIBRE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTypeOccupation() {
        return typeOccupation;
    }

    public void setTypeOccupation(int typeOccupation) {
        this.typeOccupation = typeOccupation;
    }

    @Override
    public String toString() {
        String res = "";
        switch(typeOccupation) {
            case LIBRE:
                res = " ";
                break;
            case REINE:
                res = "*";
                break;
            case MENACEE:
                res = "-";
                break;
        }

        return res;
    }
}