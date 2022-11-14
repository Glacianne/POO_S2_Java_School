import java.util.ArrayList;
// from Xavier
class Devoir
{
    String matiere;
    Integer note;
    String date;

    public Devoir( String mat, Integer n, String d)
    {
        this.matiere = mat;
        this.note = n;
        this.date = d;
    }
    public Integer getNote()
    {
        return this.note;
    }

    @Override
    public String toString() {
        return this.matiere + ": " + this.note + "(" + this.date + ")";
    } 
}

class CarnetDevoirs
{
    ArrayList<Devoir> devoirs = new ArrayList<>();

    public CarnetDevoirs(){}

    public void ajouter( Devoir n )
    {
        this.devoirs.add( n );
    }
    //ajouterDevoir("matiere", -1, null, k1);
    public ArrayList<Devoir> filtrerDevoirs()
    {
        for (Devoir devoir : devoirs){
            if (devoir.date.equals("DD/MM/YYYY")){
                //devoirsFaits.add(devoir);
            }
        }
        return devoirs;
    }

    public String toString()
    {
        String res = "";
        for (Devoir n : this.devoirs) {
            res += n;
            res += ", ";
        }
        return res;
    }
}

class Klasse{
    String nom;
    String niveau;

    ArrayList<Eleve> eleves = new ArrayList<>();
    CarnetDevoirs carnetDevoir = new CarnetDevoirs();
    Prof instituteur;

    public Klasse( String nom, String niv, Prof inst )
    {
        this.nom = nom;
        this.niveau = niv;
        this.instituteur = inst;
    } 

    public void ajouterDevoir( String matiere, int val, String date )
    {
        this.carnetDevoir.ajouter( new Devoir( matiere, val, date )  );
        for (Eleve e : eleves){
            e.ajouterDevoir( matiere, val, date );
        }
    } 

    public void ajouterEleve( Eleve e )
    {
        this.eleves.add( e );
    }

    public void affiche()
    {
        System.out.println( String.format("Classe        : %s", this.nom)  );
        System.out.println( String.format("Niveau        : %s", this.niveau)  );
        System.out.println( String.format("Instituteur   : %s", this.instituteur)  );
        for (Eleve eleve : this.eleves ) {
            System.out.println( String.format("    - %s", eleve)  );
        }
        System.out.println();
    }
}

class Ecole {
    
    private ArrayList<Individus> individus = new ArrayList<>();
    private ArrayList<Klasse> klasses = new ArrayList<>();

    public void ajouterPersonne(Individus a) {
        this.individus.add(a);
    }

    public void ajouterKlasse(Klasse k) {
        this.klasses.add(k);
    }

    // 
    public Individus chercherIndividus (String nomATrouver) {
        for (Individus personne : this.individus) {
            if (personne.nom.equals(nomATrouver))
                return personne;
        }
        return null;
    }

    public Klasse chercherKlasse(String nomATrouver) {
        for (Klasse klasse : this.klasses) {
            if (klasse.nom.equals(nomATrouver))
                return klasse;
        }
        return null;
    }

    public ArrayList<Eleve> listerEleve() {
        ArrayList<Eleve> listRetour = new ArrayList<>();

        for (Individus personne : this.individus) {
            if (personne.getClass().getSimpleName().equals("Eleve")) {
                listRetour.add((Eleve) personne);
            }
        }
        return listRetour;
    }

    public void sonnerOuverture()
    {
        
    }

    public void sonnerFermeture()
    {
        
    }

    public ArrayList<Klasse> getKlasses() {
        return klasses;
    }
}

class Individus {
    String nom;

    public Individus(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return nom;
    }
}

class Staff extends Individus {
    int salaire;

    public Staff(String nom) {
        super(nom);
        this.salaire = 0;
    }

    public Staff(String nom, int salaire) {
        super(nom);
        this.salaire = salaire;
    }
}

class Eleve extends Individus {
    CarnetDevoirs carnetDevoir = new CarnetDevoirs();

    public Eleve(String nom) {
        super(nom);
    }

    public void ajouterDevoir( String matiere, int val, String date )
    {
        this.carnetDevoir.ajouter( new Devoir( matiere, val, date )  );
    } 

    public String toString() {
        return String.format("Eleve(%s, %s)", this.nom, this.carnetDevoir);
    }

    public void ouverture()
    {
        System.out.println( "sort ses cahiers" );
        System.out.println( "répond à l'appel" );
    }
}

class Prof extends Staff {
    CarnetDevoirs devoir = new CarnetDevoirs();

    public Prof(String nom, int salaire) {
        super(nom, salaire);
    }

    public void ajouterDevoir( String matiere, int val, String date )
    {
        this.devoir.ajouter( new Devoir( matiere, val, date )  );
    } 

    public void ouverture()
    {
        System.out.println( "sort sont cahier d'appel" );
        System.out.println( "fait l'appel" );
    }
}

class Cuisinier extends Staff {
    public Cuisinier(String nom, int salaire) {
        super(nom, salaire);
    }

    public void ouverture()
    {
        System.out.println( "regarder menu" );
        System.out.println( "sortir les ingrédients" );
        System.out.println( "allumer les fourneaux" );
    }

}

class Pion extends Staff {
    public Pion(String nom, int salaire) {
        super(nom, salaire);
    }

    public void ouverture()
    {
        System.out.println( "prends cahier appel" );
        System.out.println( "surveiller la cour" );

    }
}

class POOEcoleOne {
    public static void ajouterEcoleSaintMartin(){
        Ecole saintMartin = new Ecole();
        saintMartin.ajouterPersonne(new Cuisinier("Ratatouille", 1200));
        saintMartin.ajouterPersonne(new Eleve("toubib"));
        saintMartin.ajouterPersonne(new Eleve("toubob"));
        saintMartin.ajouterPersonne(new Eleve("toubub"));
        saintMartin.ajouterPersonne(new Prof("Marcel Conche", 1250));
        saintMartin.ajouterPersonne(new Prof("Louis Jouvet", 1251));
        saintMartin.ajouterPersonne(new Prof("Guy Carcassonne", 1252));
        saintMartin.ajouterKlasse(new Klasse("Camus", "quatrieme", (Prof)saintMartin.chercherIndividus("Marcel Conche"))); // J'ajoute un prof d'une ecole differente ><
        saintMartin.ajouterKlasse(new Klasse("Pharell", "cinquieme", (Prof)saintMartin.chercherIndividus("Louis Jouvet"))); // J'ajoute un prof d'une ecole differente ><
        saintMartin.ajouterKlasse(new Klasse("Pharell", "cinquieme", (Prof)saintMartin.chercherIndividus("Guy Carcassonne"))); // J'ajoute un prof d'une ecole differente ><
        Klasse handleK;
        handleK = saintMartin.chercherKlasse("Camus");
        handleK.ajouterEleve( (Eleve)saintMartin.chercherIndividus("toubib")  );
        handleK.ajouterEleve( (Eleve)saintMartin.chercherIndividus("toubob")  );
        handleK = saintMartin.chercherKlasse("Pharell");
        handleK.ajouterEleve( (Eleve)saintMartin.chercherIndividus("toubub")  );
        System.out.println(  "==================================");

        //saintMartin.chercherKlasse( "Camus").affiche();  
        //saintMartin.chercherKlasse( "Pharell").affiche();  
        //System.out.println(  "==================================");

        Eleve e1 = (Eleve)saintMartin.chercherIndividus( "toubub" ); 
        Prof p1 = (Prof)saintMartin.chercherIndividus( "Marcel Conche" ); 
        Klasse k1 = (Klasse)saintMartin.chercherKlasse( "Camus" );
        k1.ajouterDevoir("matiere-1", -1, null);

        k1 = (Klasse)saintMartin.chercherKlasse( "Pharell" );
        k1.ajouterDevoir("matiere-2", 15, "DD/MM/YYYY");

        //e1.ajouterDevoir( "Arts Plastique", -1, null); 
        //p1.ajouterDevoir( "Arts Plastique", -1, null);
        System.out.println( e1 );
        System.out.println(  "==================================");

        saintMartin.chercherKlasse( "Pharell").affiche();  
        System.out.println(  "==================================");

        ArrayList<Eleve> lesEleves = saintMartin.listerEleve();
        for (Eleve eleve : lesEleves) {
            System.out.println(eleve);
        }
        System.out.println(  "==================================");
    }
    
    public static void main(String[] args) {

        ajouterEcoleSaintMartin();
        

    }
}