import java.util.*;

public class Main {
    static int TAILLE = 8;
    public static void main(String[] args) {
        TAILLE = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        
        // algoNaif();

        algoOpti(TAILLE, true);

        // testAlgoOpti();
    }

    public static void algoNaif() {
        Echiquier echiquier = new Echiquier(TAILLE);
        List<Cellule> reines = new ArrayList<>();

        for (int x = 0; x < TAILLE; x++) {
            for (int y = 0; y < TAILLE; y++) {
                Cellule cel = echiquier.placerReine(y, x);
                if (cel != null)
                    reines.add(cel);
            }
        }

        System.out.println(echiquier);
        System.out.println("Nombre de reines : " + reines.size());
        for (Cellule cel : reines)
            System.out.printf("x: %d, y: %d%n", cel.getX(), cel.getY());
    }

    public static int algoOpti(int taille, boolean print) {
        Echiquier echiquier = new Echiquier(taille);
        List<Cellule> reines = new ArrayList<>();

        while(echiquier.getNombreCasesLibres() > 0)
            reines.add(echiquier.placerReineOpti());

        if(print) {
            System.out.println(echiquier);
            System.out.println("Nombre de reines : " + reines.size());
            for (Cellule cel : reines)
                System.out.printf("x: %d, y: %d%n", cel.getX(), cel.getY());
        }
        
        return reines.size();
    }

    public static void testAlgoOpti() {
        for (int i = 4; i < 1000; i++) {
            int res = 0;
            int j = 0;
            while(res != i && j < 70) {
                res = algoOpti(i, false);
                j++;
            }
            System.out.println("Taille échiquier = " + i + ", tentatives = " + j);
        }
    }
}