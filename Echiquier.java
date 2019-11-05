import java.util.*;

public class Echiquier {
    private Cellule[][] echiquier;
    private int taille;
    private int nombreCasesLibres;

    public Echiquier(int taille) {
        this.taille = taille;
        echiquier = new Cellule[taille][taille];
        nombreCasesLibres = taille * taille;
        initEchiquier();
    }
    
    private void initEchiquier() {
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                echiquier[y][x] = new Cellule(x, y);
            }
        }
    }

    public void modifierCellule(int x, int y, int valeur) {
        if(echiquier[y][x].getTypeOccupation() != valeur) {
            echiquier[y][x].setTypeOccupation(valeur);
            if (valeur != 0)
                nombreCasesLibres--;
        }
    }

    public Cellule placerReine(int _x, int _y) {
        if(echiquier[_y][_x].getTypeOccupation() == Cellule.LIBRE) {
            modifierCellule(_x, _y, Cellule.REINE);

            // Ligne
            for (int x = 0; x < taille; x++) {
                if(x != _x)
                    modifierCellule(x, _y, Cellule.MENACEE);
            }   

            //Colonne
            for (int y = 0; y < taille; y++) {
                if(y != _y)
                    modifierCellule(_x, y, Cellule.MENACEE);
            }

            int yHaut = _y;
            int yBas  = _y;
            // Diagonales gauches
            for(int x = _x - 1; x >= 0; x--) {
                if(yHaut > 0) {
                    yHaut--;
                    modifierCellule(x, yHaut, Cellule.MENACEE);
                }
                if (yBas < taille - 1) {
                    yBas++;
                    modifierCellule(x, yBas, Cellule.MENACEE);
                }
            }

            yHaut = _y;
            yBas  = _y;
            // Diagonales droites
            for (int x = _x + 1; x < taille; x++) {
                if (yHaut > 0) {
                    yHaut--;
                    modifierCellule(x, yHaut, Cellule.MENACEE);
                }
                if (yBas < taille - 1) {
                    yBas++;
                    modifierCellule(x, yBas, Cellule.MENACEE);
                }
            }

            return getCellue(_x, _y);
        }

        return null;
    }

    public Cellule placerReineOpti() {
        int minCase = taille * taille;
        List<Cellule> cellulesOpti = new ArrayList<>();

        for(int y = 0; y < taille; y++) {
            for(int x = 0; x < taille; x++) {
                if(echiquier[y][x].getTypeOccupation() == Cellule.LIBRE) {
                    int nbCellulesMenacees = cellulesMenacees(x, y);
                    if(nbCellulesMenacees == minCase) {
                        cellulesOpti.add(getCellue(x, y));
                    }
                    else if(nbCellulesMenacees < minCase) {
                        cellulesOpti.clear();
                        cellulesOpti.add(getCellue(x, y));
                        minCase = nbCellulesMenacees;
                    }
                }
            }
        }
        Cellule choix = cellulesOpti.get((int)(Math.random() * cellulesOpti.size()));

        return placerReine(choix.getX(), choix.getY());
    }

    private int cellulesMenacees(int _x, int _y) {
        int nbCelluleMenacee = 0;

        for (int x = 0; x < taille; x++) {
            if (x != _x && echiquier[_y][x].getTypeOccupation() == Cellule.LIBRE)
                nbCelluleMenacee++;
        }

        // Colonne
        for (int y = 0; y < taille; y++) {
            if (y != _y && echiquier[y][_x].getTypeOccupation() == Cellule.LIBRE)
                nbCelluleMenacee++;
        }

        int yHaut = _y;
        int yBas = _y;
        // Diagonales gauches
        for (int x = _x - 1; x >= 0; x--) {
            if (yHaut > 0) {
                yHaut--;
                if (echiquier[yHaut][x].getTypeOccupation() == Cellule.LIBRE)
                    nbCelluleMenacee++;
            }
            if (yBas < taille - 1) {
                yBas++;
                if (echiquier[yBas][x].getTypeOccupation() == Cellule.LIBRE)
                    nbCelluleMenacee++;
            }
        }

        yHaut = _y;
        yBas = _y;
        // Diagonales droites
        for (int x = _x + 1; x < taille; x++) {
            if (yHaut > 0) {
                yHaut--;
                if (echiquier[yHaut][x].getTypeOccupation() == Cellule.LIBRE)
                    nbCelluleMenacee++;
            }
            if (yBas < taille - 1) {
                yBas++;
                if (echiquier[yBas][x].getTypeOccupation() == Cellule.LIBRE)
                    nbCelluleMenacee++;
            }
        }

        return nbCelluleMenacee;
    }

    public Cellule getCellue(int x, int y) {
        return echiquier[y][x];
    }

    public int getNombreCasesLibres() {
        return this.nombreCasesLibres;
    }

    public String toString() {
        String res = "";
        for(int y = 0; y < taille; y ++) {
            for(int x = 0; x < taille; x++) {
                res += echiquier[y][x] + " ";
            }
            res += "\n";
        }

        return res;
    }
}