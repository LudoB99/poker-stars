package cegepst.Entities;

public enum Name {
    ROYALFLUSH("une quinte flush royale",10000),
    STRAIGHTFLUSH("une quinte flush",9000),
    FOUROFAKIND("un carré",8000),
    FULLHOUSE("un full",7000),
    FLUSH("une couleur",6000),
    STRAIGHT("une quinte",5000),
    THREEOFAKIND("un brelan",4000),
    TWOPAIR("deux paires",3000),
    PAIR("une paire",2000),
    HIGHCARD("un carte haute",1000);

    private String name;
    private int rank;

    Name(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }
}
