package Model;

import java.util.ArrayList;
import java.util.Arrays;


public class Mano {

    private Card[] mano;
    private int tamMano;
    //private String mejorMano;

    private int valorMano; //Para comparar manos
    private int valorCartasMano; //Para comparar entre manos iguales
    private int valorCartaKicker; //Comparar manos iguales y del mismo valor
    private int valorCartaSig;

    public Mano(String cartas){
       // mejorMano = "";
        this.mano = new Card[5];
        tamMano = 0;
        valorCartaKicker = 0;
        valorCartaSig = 0;
        for (int i = 0; i < 10; i = i + 2) {
            Card c = new Card(cartas.substring(i, i + 2));
            mano[tamMano] = c;
            tamMano++;
        }
        ordenarMano();
        //Arrays.sort(mano);
    }

    @Override
    public String toString() { //Imprime la mano completa
        StringBuilder s = new StringBuilder("");
        for (Card c : mano) {
            s.append(c.toString());
        }
        return s.toString();
    }

    public void ordenarMano() {
        Card aux;

        for (int i = 0; i < mano.length - 1; i++) {
            for (int j = 0; j < mano.length - i - 1; j++) {
                if (mano[j + 1].getValue() > mano[j].getValue()) {
                    aux = mano[j + 1];
                    mano[j + 1] = mano[j];
                    mano[j] = aux;

                }
            }
        }
    }

    public Card[] getMano() {
        return this.mano;
    }
    public int getValorMano(){
        return this.valorMano;
    }
    public int getValorCartasMano(){
        return this.valorCartasMano;
    }

    public int getTamMano() {
        return this.tamMano;
    }

    public int getValorCartaKicker() {
        return valorCartaKicker;
    }

    public int getValorCartaSig() {
        return valorCartaSig;
    }
    

    public boolean comprobarPoker() { //Mano de 5 cartas ya ordenadas. Devuelve el valor de las cartas.
        this.valorMano = 3;
        this.valorCartasMano = mano[2].getValue();
        if (mano[0].getValue() == mano[3].getValue()) {
            //this.mejorMano = "";
            valorCartaKicker = mano[4].getValue();
            for (int i = 0; i < 4; i++) {
                //mejorMano += mano[i].toString();
            }
            return true;
        } else if (mano[1].getValue() == mano[4].getValue()) {
            //this.mejorMano = "";
            valorCartaKicker = mano[0].getValue();
            for (int i = 1; i < 5; i++) {
                //mejorMano += mano[i].toString();
            }
            return true;
        }
        return false;
    }

    public boolean comprobarFull() {
        this.valorMano = 4;
        this.valorCartasMano = mano[2].getValue();
        if (mano[0].getValue() == mano[2].getValue() && mano[3].getValue() == mano[4].getValue()) {
            //this.mejorMano = toString();
            this.valorCartaKicker = mano[3].getValue();
            return true;
        } else if (mano[0].getValue() == mano[1].getValue() && mano[2].getValue() == mano[4].getValue()) {
            //mejorMano = toString();
            this.valorCartaKicker = mano[0].getValue();
            return true;
        }
        return false;
    }

    public boolean comprobarTrio() {
        this.valorMano = 7;
        this.valorCartasMano = mano[2].getValue();

        boolean encontrado = false;
        for (int i = 0; i < 3 && !encontrado; i++) {
            if (mano[i].getValue() == mano[i + 1].getValue() && mano[i + 1].getValue() == mano[i + 2].getValue()) {
                encontrado = true;
                if(i == 0){
                    valorCartaKicker = mano[3].getValue();
                    valorCartaSig = mano[4].getValue();
                } else 
                {
                    valorCartaKicker = mano[0].getValue();
                    if(i == 1) valorCartaSig = mano[4].getValue();
                    else if(i == 2) valorCartaSig = mano[1].getValue();
                }
                //mejorMano = mano[i].toString() + mano[i + 1].toString() + mano[i + 2].toString();
            }
        }

        return encontrado;
    }

    public boolean comprobarColor() {
        boolean parar = false;
        for (int i = 0; i < 4 && !parar; i++) {
            if (mano[i].getSuit() != mano[i + 1].getSuit()) {
                parar = true;
            }
        }
        if (!parar) {
            //this.mejorMano = toString();
        }
        
        this.valorMano = 5;
        this.valorCartasMano = mano[0].getValue();

        return !parar;
    }

//    public String getMejorMano() {
//        return mejorMano;
//    }

    public boolean comprobarEscalera() {
        this.valorMano = 6;
        this.valorCartasMano = mano[0].getValue();
        boolean seguir = true;
        for (int i = 0; i < 4 && seguir; i++) {
            if (mano[i].getValue() - mano[i + 1].getValue() != 1) {
                seguir = false;
            }
        }
        if (seguir) {
            //this.mejorMano = toString();
            return true;
        } else {
            seguir = comprobarEscaleraAsCinco();
            if (seguir) {
                return true;
            }
        }
        return false;
    }

    public boolean comprobarEscaleraAsCinco() {
        boolean seguir = true;
        if (mano[0].getValue() == 14 && mano[1].getValue() == 5) {
            for (int i = 2; i < 4 && seguir; i++) {
                if (mano[i].getValue() - mano[i + 1].getValue() != 1) {
                    seguir = false;
                }
            }
            if (seguir) {
                //mejorMano = toString();
                //this.valorMano = 6;
                this.valorCartasMano = mano[1].getValue();
                return true;
            }
        }
        return false;
    }

    public boolean comprobarStraightFlush() {
        if (comprobarColor()) {
            if (comprobarEscalera()) {
                //mejorMano = toString();
                this.valorMano = 2;
                this.valorCartasMano = mano[0].getValue();
                return true;
            }
        }
        this.valorMano = 2;
        this.valorCartasMano = mano[0].getValue();
        return false;
    }

    public boolean comprobarRoyalFlush() {
        if (comprobarStraightFlush() && mano[4].getValue() == 10) {
            this.valorMano = 1;
            this.valorCartasMano = mano[0].getValue();
            //mejorMano = toString();
            return true;
        }
        return false;
    }

    public boolean comprobarDoblePareja() {
        
        this.valorMano = 8;
        //this.valorCartasMano = mano[0].getValue();
        if (mano[0].getValue() == mano[1].getValue()) {
            if (mano[2].getValue() == mano[3].getValue() && mano[3].getValue() != mano[0].getValue()) {
                //mejorMano = mano[0].toString() + mano[1].toString() + mano[2].toString() + mano[3].toString();
                this.valorCartasMano = mano[0].getValue();
                valorCartaKicker = mano[2].getValue();
                valorCartaSig = mano[4].getValue();
                return true;
            } else if (mano[3].getValue() == mano[4].getValue() && mano[3].getValue() != mano[0].getValue()) {
                //mejorMano = mano[0].toString() + mano[1].toString() + mano[3].toString() + mano[4].toString();
                this.valorCartasMano = mano[0].getValue();
                valorCartaKicker = mano[3].getValue();
                valorCartaSig = mano[2].getValue();
                return true;
            }
        } else if (mano[1].getValue() == mano[2].getValue() && mano[3].getValue() == mano[4].getValue()) {
            //mejorMano = mano[1].toString() + mano[2].toString() + mano[3].toString() + mano[4].toString();
            this.valorCartasMano = mano[1].getValue();
            valorCartaKicker = mano[3].getValue();
            valorCartaSig = mano[0].getValue();
            return true;
        }

        return false;
    }

    public boolean comprobarPareja() {
        
        this.valorMano = 9;
        
        boolean encontrado = false;
        for (int i = 0; i < 4 && !encontrado; i++) {
            if (mano[i].getValue() == mano[i + 1].getValue()) {
                if(i == 0){
                    valorCartaKicker = mano[2].getValue();
                    valorCartaSig = mano[3].getValue();
                } else {
                    valorCartaKicker = mano[0].getValue();
                    if(i == 1) valorCartaSig = mano[3].getValue();
                    else valorCartaSig = mano[1].getValue();
                }
                encontrado = true;
                //mejorMano = mano[i].toString() + mano[i + 1].toString();
                this.valorCartasMano = mano[i].getValue();
                return true;
            }
        }
        return false;
    }

    public void comprobarHighCard() {
        //this.mejorMano = mano[0].toString();
        this.valorMano = 10;
        this.valorCartasMano = mano[0].getValue();
        this.valorCartaKicker = mano[1].getValue();
        this.valorCartaSig = mano[2].getValue();
    }

    public boolean comprobarFlushDraw() {
        if (comprobarColor()) {
            return false;
        }

        int c = 0;
        int s = 0;
        int d = 0;
        int h = 0;

        for (int i = 0; i < tamMano; i++) {
            switch (mano[i].getSuit()) {
                case 'c':
                    c++;
                    break;
                case 's':
                    s++;
                    break;
                case 'd':
                    d++;
                    break;
                case 'h':
                    h++;
                    break;
            }
        }

        if (c == 4 || s == 4 || d == 4 || h == 4) {
            return true;
        }

        return false;
    }

    public boolean comprobarStraightGutshot() {
        if (comprobarEscalera()) {
            return false;
        }
        for (int i = 0; i < tamMano; i++) {
            ArrayList<Card> aux = new ArrayList<>();
            for (int j = 0; j < tamMano; j++) {
                if (j != i) {
                    aux.add(mano[j]);
                }
            }

            Card[] auxi = new Card[4];

            for (int j = 0; j < 4; j++) {
                auxi[j] = aux.get(j);
            }

            boolean seguir = true;
            int n1 = 0;
            int n2 = 0;

            if (auxi[0].getValue() == 14 && (auxi[1].getValue() == 5 || auxi[1].getValue() == 4)) {
                for (int j = 1; j < 3; j++) {
                    switch (auxi[j].getValue() - auxi[j + 1].getValue()) {
                        case 1:

                            n1++;

                            break;
                        case 2:

                            n2++;

                            break;

                    }
                }
                
                if((n2 == 1 && n1 == 1) || (n2 == 0 && n1 == 2)){
                    return true;
                }

            }

            for (int j = 0; j < 3 && seguir; j++) {
                switch (auxi[j].getValue() - auxi[j + 1].getValue()) {
                    case 1:
                        if (n1 < 2) {
                            n1++;
                        } else {
                            seguir = false;
                        }
                        break;
                    case 2:
                        if (n2 < 1) {
                            n2++;
                        } else {
                            seguir = false;
                        }
                        break;
                    default:
                        seguir = false;
                        break;
                }

                if (j == 2 && n2 == 1 && n1 == 2) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean comprobarStraightOpenEnded() {
        if (comprobarEscalera()) {
            return false;
        }
        boolean seguir = true;
        for (int i = 0; i < 3 && seguir; i++) {
            if (mano[i].getValue() - mano[i + 1].getValue() != 1) {
                seguir = false;
            }
        }
        if (seguir) {
            return true;
        } else {
            seguir = true;
            for (int i = 1; i < 4 && seguir; i++) {
                if (mano[i].getValue() - mano[i + 1].getValue() != 1) {
                    seguir = false;
                }
            }
            if (seguir) {
                return true;
            }
        }
        return false;
    }

    public String comprobarMejorMano() {
        if (comprobarRoyalFlush()) {
            return "ROYAL FLUSH \n";
        }
        if (comprobarStraightFlush()) {
            return "STRAIGHT FLUSH \n";
        }
        if (comprobarPoker()) {
            return "POKER \n";
        }
        if (comprobarFull()) {
            return "FULL HOUSE \n";
        }
        if (comprobarColor()) {
            return "FLUSH \n";
        }
        if (comprobarEscalera()) {
            return "STRAIGHT \n";
        }
        if (comprobarTrio()) {
            return "THREE OF A KIND \n";
        }
        if (comprobarDoblePareja()) {
            return "DOUBLE PAIR \n";
        }
        if (comprobarPareja()) {
            return "PAIR \n";
        }
        comprobarHighCard();
        return "HIGH CARD \n";
    }

    /*public String comprobarDrawsEscalera() {
        if (comprobarStraightGutshot()) {
            return "DRAW: STRAIGHT GUTSHOT \n";
        }
        if (comprobarStraightOpenEnded()) {
            return "DRAW: OPEN-ENDED STRAIGHT \n";
        }
        return "";
    }

    public String comprobarDrawColor() {
        if (comprobarFlushDraw()) {
            return "DRAW: FLUSH DRAW \n";
        }
        return "";
    }*/
    
    /*public boolean equals(Mano m){
        for(int i = 0; i < 5; i++){
            if(!this.mano[i].toString().equalsIgnoreCase(m.mano[i].toString())){
                return false;
            }
        }
        return true;
    }*/


}
