package Model;

import javax.swing.JLabel;


public class Jugador {
    
    private String manoLarga;
    private Mano mejorMano;
    private Card carta1;
    private Card carta2;
    private JLabel jLabelCarta1;
    private JLabel jLabelCarta2;
    private double jugadasGanadas;
    private double porcentaje;
    private boolean fold;
    
    public Jugador(JLabel carta1, JLabel carta2){
        this.jLabelCarta1 = carta1;
        this.jLabelCarta2 = carta2;
        this.carta1 = null;
        this.carta2 = null;
        this.jugadasGanadas = 0.0000;
        this.porcentaje = 0.00000;
        fold = false;
        manoLarga = "";
    }

    public void setFold(boolean fold) {
        this.fold = fold;
    }

    public boolean isFold() {
        return fold;
    }

    public void setJugadasGanadas(double jugadasGanadas) {
        this.jugadasGanadas = jugadasGanadas;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getJugadasGanadas() {
        return jugadasGanadas;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setManoLarga(String manoLarga) {
        this.manoLarga = manoLarga;
    }
    
    public String getManoLarga(){
        return this.manoLarga;
    }
    
    public Mano getMejorMano(){
        return this.mejorMano;
    }
    
    public void setMejorMano(Mano mejorMano){
        this.mejorMano = mejorMano;
    }

    public JLabel getjLabelCarta1() {
        return jLabelCarta1;
    }

    public JLabel getjLabelCarta2() {
        return jLabelCarta2;
    }

    public void setCarta1(Card carta1) {
        this.carta1 = carta1;
    }

    public void setCarta2(Card carta2) {
        this.carta2 = carta2;
    }

    public Card getCarta1() {
        return carta1;
    }

    public Card getCarta2() {
        return carta2;
    }

    

    
}
