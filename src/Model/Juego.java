package Model;

import GUI.MainWindow;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.stream.Collectors.toList;
import javax.swing.ImageIcon;
import org.paukov.combinatorics3.Generator;

/**
 *
 * @author Charlie
 */
public class Juego {

    private final Baraja baraja;
    private final MainWindow mw;
    private final Jugador jugArr;
    private final Jugador jugArrDcha;
    private final Jugador jugArrIzda;
    private final Jugador jugAb;
    private final Jugador jugAbDcha;
    private final Jugador jugAbIzda;
    private double numCombinaciones;
    private Card flop1;
    private Card flop2;
    private Card flop3;
    private Card turn;
    private Card river;
    private int numJugadores;

    public double getNumCombinaciones() {
        return numCombinaciones;
    }

    public Juego(MainWindow mw) {
        baraja = new Baraja();
        jugArr = new Jugador(mw.getjLabelArriba1(), mw.getjLabelArriba2());
        jugArrDcha = new Jugador(mw.getjLabelArribaDcha1(), mw.getjLabelArribaDcha2());
        jugArrIzda = new Jugador(mw.getjLabelArribaIzda1(), mw.getjLabelArribaIzda2());
        jugAb = new Jugador(mw.getjLabelAbajo1(), mw.getjLabelAbajo2());
        jugAbDcha = new Jugador(mw.getjLabelAbajoDcha1(), mw.getjLabelAbajoDcha2());
        jugAbIzda = new Jugador(mw.getjLabelAbajoIzda1(), mw.getjLabelAbajoIzda2());
        this.mw = mw;
        numJugadores = 6;
        flop1 = null;
        flop2 = null;
        flop3 = null;
        turn = null;
        river = null;

    }

    public void setFlop1(Card flop1) {
        this.flop1 = flop1;
    }

    public void setFlop2(Card flop2) {
        this.flop2 = flop2;
    }

    public void setFlop3(Card flop3) {
        this.flop3 = flop3;
    }

    public void setTurn(Card turn) {
        this.turn = turn;
    }

    public void setRiver(Card river) {
        this.river = river;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public Baraja getBaraja() {
        return baraja;
    }

    public Jugador getJugArr() {
        return jugArr;
    }

    public Jugador getJugArrDcha() {
        return jugArrDcha;
    }

    public Jugador getJugArrIzda() {
        return jugArrIzda;
    }

    public Jugador getJugAb() {
        return jugAb;
    }

    public Jugador getJugAbDcha() {
        return jugAbDcha;
    }

    public Jugador getJugAbIzda() {
        return jugAbIzda;
    }

    public void preflop() {
        Random rnd = new Random();

        Object[] barajaArr = this.baraja.getBaraja().toArray();
        String[] barajaToString = new String[40];
        for (int i = 0; i < 40; i++) {
            barajaToString[i] = barajaArr[i].toString();
        }
        List<List<String>> combinations
                = Generator.combination(barajaToString)
                        .simple(5)
                        .stream()
                        .collect(toList());

        this.numCombinaciones = combinations.size();

        for (int i = 0; i < combinations.size(); ++i) {
            String mano = "";
            for (int x = 0; x < 5; ++x) {
                mano += combinations.get(i).get(x);
            }

            if (!jugArr.isFold()) {
                jugArr.setManoLarga(mano + jugArr.getCarta1().toString() + jugArr.getCarta2().toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugArr.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations6
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos = new Mano[combinations6.size()];
                for (int x = 0; x < combinations6.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations6.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos[x] = m;
                }
                jugArr.setMejorMano(ordenarManos(manos));
            }

            if (!jugAb.isFold()) {
                jugAb.setManoLarga(mano + jugAb.getCarta1().toString() + jugAb.getCarta2().toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugAb.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations1
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos1 = new Mano[combinations1.size()];

                for (int x = 0; x < combinations1.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations1.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos1[x] = m;
                }

                jugAb.setMejorMano(ordenarManos(manos1));
            }

            if (!jugArrDcha.isFold()) {
                jugArrDcha.setManoLarga(mano + jugArrDcha.getCarta1().toString() + jugArrDcha.getCarta2().toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugArrDcha.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations2
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos2 = new Mano[combinations2.size()];

                for (int x = 0; x < combinations2.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations2.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos2[x] = m;
                }

                jugArrDcha.setMejorMano(ordenarManos(manos2));
            }

            if (!jugArrIzda.isFold()) {
                jugArrIzda.setManoLarga(mano + jugArrIzda.getCarta1().toString() + jugArrIzda.getCarta2().toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugArrIzda.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations3
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos3 = new Mano[combinations3.size()];

                for (int x = 0; x < combinations3.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations3.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos3[x] = m;
                }

                jugArrIzda.setMejorMano(ordenarManos(manos3));
            }

            if (!jugAbDcha.isFold()) {
                jugAbDcha.setManoLarga(mano + jugAbDcha.getCarta1().toString() + jugAbDcha.getCarta2().toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugAbDcha.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations4
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos4 = new Mano[combinations4.size()];

                for (int x = 0; x < combinations4.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations4.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos4[x] = m;
                }

                jugAbDcha.setMejorMano(ordenarManos(manos4));
            }

            if (!jugAbIzda.isFold()) {
                jugAbIzda.setManoLarga(mano + jugAbIzda.getCarta1().toString() + jugAbIzda.getCarta2().toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugAbIzda.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations5
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos5 = new Mano[combinations5.size()];

                for (int x = 0; x < combinations5.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations5.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos5[x] = m;
                }

                jugAbIzda.setMejorMano(ordenarManos(manos5));
            }

            /*Jugador[] jugadores = new Jugador[6];
            jugadores[0] = jugArr;
            jugadores[1] = jugArrDcha;
            jugadores[2] = jugArrIzda;
            jugadores[3] = jugAb;
            jugadores[4] = jugAbDcha;
            jugadores[5] = jugAbIzda;*/
            ArrayList<Jugador> arrPrueba = new ArrayList<>();
            if (!jugArr.isFold()) {
                arrPrueba.add(jugArr);
            }
            if (!jugArrDcha.isFold()) {
                arrPrueba.add(jugArrDcha);
            }
            if (!jugArrIzda.isFold()) {
                arrPrueba.add(jugArrIzda);
            }
            if (!jugAb.isFold()) {
                arrPrueba.add(jugAb);
            }
            if (!jugAbDcha.isFold()) {
                arrPrueba.add(jugAbDcha);
            }
            if (!jugAbIzda.isFold()) {
                arrPrueba.add(jugAbIzda);
            }

            Jugador[] jugadores = new Jugador[numJugadores];
            for (int x = 0; x < arrPrueba.size(); ++x) {
                jugadores[x] = arrPrueba.get(x);
            }

            ordenarJugadores(jugadores);
            //System.out.println(i);
            
            if (jugadores[0].getMejorMano().comprobarMejorMano().equalsIgnoreCase(jugadores[1].getMejorMano().comprobarMejorMano())
                        && jugadores[0].getMejorMano().getValorCartasMano() == jugadores[1].getMejorMano().getValorCartasMano()
                        && jugadores[0].getMejorMano().getValorCartaKicker() == jugadores[1].getMejorMano().getValorCartaKicker()
                        && jugadores[0].getMejorMano().getValorCartaSig() == jugadores[1].getMejorMano().getValorCartaSig()) {

                    ArrayList<Jugador> noSeKHago = new ArrayList<>();

                    if (numJugadores > 2) {
                        boolean ok = true;
                        for (int auxSim = 2; auxSim < 6 && ok; auxSim++) {
                            if (jugadores[auxSim].getMejorMano().comprobarMejorMano().equalsIgnoreCase(jugadores[auxSim - 1].getMejorMano().comprobarMejorMano())
                                    && jugadores[auxSim].getMejorMano().getValorCartasMano() == jugadores[auxSim - 1].getMejorMano().getValorCartasMano()
                                    && jugadores[auxSim].getMejorMano().getValorCartaKicker() == jugadores[auxSim - 1].getMejorMano().getValorCartaKicker()
                                    && jugadores[auxSim].getMejorMano().getValorCartaSig() == jugadores[auxSim].getMejorMano().getValorCartaSig()) {
                                if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                                    noSeKHago.add(jugArr);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                                    noSeKHago.add(jugAb);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                                    noSeKHago.add(jugArrDcha);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                                    noSeKHago.add(jugArrIzda);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                                    noSeKHago.add(jugAbDcha);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                                    noSeKHago.add(jugAbIzda);
                                }
                            } else {
                                ok = false;

                            }
                        }

                    }
                    if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                        noSeKHago.add(jugArr);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                        noSeKHago.add(jugAb);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                        noSeKHago.add(jugArrDcha);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                        noSeKHago.add(jugArrIzda);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                        noSeKHago.add(jugAbDcha);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                        noSeKHago.add(jugAbIzda);
                    }

                    if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                        noSeKHago.add(jugArr);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                        noSeKHago.add(jugAb);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                        noSeKHago.add(jugArrDcha);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                        noSeKHago.add(jugArrIzda);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                        noSeKHago.add(jugAbDcha);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                        noSeKHago.add(jugAbIzda);
                    }

                    for (Jugador j : noSeKHago) {
                        j.setJugadasGanadas(j.getJugadasGanadas() + (1.0 / noSeKHago.size()));
                    }
                } 
             else {
                if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                    jugArr.setJugadasGanadas(jugArr.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                    jugAb.setJugadasGanadas(jugAb.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                    jugArrDcha.setJugadasGanadas(jugArrDcha.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                    jugArrIzda.setJugadasGanadas(jugArrIzda.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                    jugAbDcha.setJugadasGanadas(jugAbDcha.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                    jugAbIzda.setJugadasGanadas(jugAbIzda.getJugadasGanadas() + 1.0);
                }
            }

//            if (jugadores[0].getMejorMano().getValorMano() == jugadores[1].getMejorMano().getValorMano()
//                    && jugadores[0].getMejorMano().getValorCartasMano() == jugadores[1].getMejorMano().getValorCartasMano()
//                    && jugadores[0].getMejorMano().getValorCartaKicker() == jugadores[1].getMejorMano().getValorCartaKicker()
//                    && jugadores[0].getMejorMano().getValorCartaSig() == jugadores[1].getMejorMano().getValorCartaSig()) {
//
//                ArrayList<Jugador> noSeKHago = new ArrayList<>();
//
//                boolean ok = true;
//                for (int auxSim = 2; auxSim < jugadores.length && ok; auxSim++) {
//                    if (jugadores[auxSim].getMejorMano().comprobarMejorMano().equalsIgnoreCase(jugadores[auxSim - 1].getMejorMano().comprobarMejorMano())
//                            && jugadores[auxSim].getMejorMano().getValorCartasMano() == jugadores[auxSim - 1].getMejorMano().getValorCartasMano()
//                            && jugadores[auxSim].getMejorMano().getValorCartaKicker() == jugadores[auxSim - 1].getMejorMano().getValorCartaKicker()
//                            && jugadores[auxSim].getMejorMano().getValorCartaSig() == jugadores[auxSim].getMejorMano().getValorCartaSig()) {
//
//                        if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
//                            noSeKHago.add(jugArr);
//                        } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
//                            noSeKHago.add(jugAb);
//                        } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
//                            noSeKHago.add(jugArrDcha);
//                        } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
//                            noSeKHago.add(jugArrIzda);
//                        } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
//                            noSeKHago.add(jugAbDcha);
//                        } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
//                            noSeKHago.add(jugAbIzda);
//                        }
//                    } else {
//                        ok = false;
//                        if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
//                            noSeKHago.add(jugArr);
//                        } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
//                            noSeKHago.add(jugAb);
//                        } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
//                            noSeKHago.add(jugArrDcha);
//                        } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
//                            noSeKHago.add(jugArrIzda);
//                        } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
//                            noSeKHago.add(jugAbDcha);
//                        } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
//                            noSeKHago.add(jugAbIzda);
//                        }
//
//                        if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
//                            noSeKHago.add(jugArr);
//                        } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
//                            noSeKHago.add(jugAb);
//                        } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
//                            noSeKHago.add(jugArrDcha);
//                        } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
//                            noSeKHago.add(jugArrIzda);
//                        } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
//                            noSeKHago.add(jugAbDcha);
//                        } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
//                            noSeKHago.add(jugAbIzda);
//                        }
//                    }
//                }
//
//                for (Jugador j : noSeKHago) {
//                    j.setJugadasGanadas(j.getJugadasGanadas() + (1.0 / noSeKHago.size()));
//                }
//
//            } else {
//
//                if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
//                    jugArr.setJugadasGanadas(jugArr.getJugadasGanadas() + 1.0);
//                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
//                    jugAb.setJugadasGanadas(jugAb.getJugadasGanadas() + 1.0);
//                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
//                    jugArrDcha.setJugadasGanadas(jugArrDcha.getJugadasGanadas() + 1.0);
//                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
//                    jugArrIzda.setJugadasGanadas(jugArrIzda.getJugadasGanadas() + 1.0);
//                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
//                    jugAbDcha.setJugadasGanadas(jugAbDcha.getJugadasGanadas() + 1.0);
//                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
//                    jugAbIzda.setJugadasGanadas(jugAbIzda.getJugadasGanadas() + 1.0);
//                }
//
//            }
        }
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public void flop() {
        jugArr.setJugadasGanadas(0.0);
        jugAb.setJugadasGanadas(0.0);
        jugArrDcha.setJugadasGanadas(0.0);
        jugArrIzda.setJugadasGanadas(0.0);
        jugAbDcha.setJugadasGanadas(0.0);
        jugAbIzda.setJugadasGanadas(0.0);

        if (flop1 == null) {
            Random rnd = new Random();
            int[] aux = new int[3];
            int contAux = 0;
            while (contAux < 3) {
                int ale = rnd.nextInt(baraja.getBaraja().size());
                if (contAux == 0) {
                    aux[0] = ale;
                    flop1 = baraja.getBaraja().get(aux[0]);
                    baraja.getBaraja().remove(aux[0]);
                    contAux++;
                } else if (ale != aux[contAux - 1]) {
                    aux[contAux] = ale;
                    if (contAux == 1) {
                        flop2 = baraja.getBaraja().get(ale);
                        baraja.getBaraja().remove(ale);
                    } else {
                        flop3 = baraja.getBaraja().get(ale);
                        baraja.getBaraja().remove(ale);
                    }
                    contAux++;
                }
            }

            mw.getjLabelFlop1().setIcon(new ImageIcon(flop1.getRutaRep()));
            mw.getjLabelFlop2().setIcon(new ImageIcon(flop2.getRutaRep()));
            mw.getjLabelFlop3().setIcon(new ImageIcon(flop3.getRutaRep()));
        }

        Object[] barajaArr = this.baraja.getBaraja().toArray();
        String[] barajaToString = new String[this.baraja.getBaraja().size()];
        for (int i = 0; i < this.baraja.getBaraja().size(); i++) {
            barajaToString[i] = barajaArr[i].toString();
        }
        List<List<String>> combinations
                = Generator.combination(barajaToString)
                        .simple(2)
                        .stream()
                        .collect(toList());

        this.numCombinaciones = combinations.size();

        for (int i = 0; i < combinations.size(); ++i) {
            String mano = "";
            for (int x = 0; x < 2; ++x) {
                mano += combinations.get(i).get(x);
            }

            if (!jugArr.isFold()) {
                jugArr.setManoLarga(mano + jugArr.getCarta1().toString() + jugArr.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugArr.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations6
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos = new Mano[combinations6.size()];
                for (int x = 0; x < combinations6.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations6.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos[x] = m;
                }
                jugArr.setMejorMano(ordenarManos(manos));
            }

            if (!jugAb.isFold()) {
                jugAb.setManoLarga(mano + jugAb.getCarta1().toString() + jugAb.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugAb.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations1
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos1 = new Mano[combinations1.size()];

                for (int x = 0; x < combinations1.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations1.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos1[x] = m;
                }

                jugAb.setMejorMano(ordenarManos(manos1));
            }

            if (!jugArrDcha.isFold()) {
                jugArrDcha.setManoLarga(mano + jugArrDcha.getCarta1().toString() + jugArrDcha.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugArrDcha.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations2
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos2 = new Mano[combinations2.size()];

                for (int x = 0; x < combinations2.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations2.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos2[x] = m;
                }

                jugArrDcha.setMejorMano(ordenarManos(manos2));
            }

            if (!jugArrIzda.isFold()) {
                jugArrIzda.setManoLarga(mano + jugArrIzda.getCarta1().toString() + jugArrIzda.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugArrIzda.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations3
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos3 = new Mano[combinations3.size()];

                for (int x = 0; x < combinations3.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations3.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos3[x] = m;
                }

                jugArrIzda.setMejorMano(ordenarManos(manos3));
            }

            if (!jugAbDcha.isFold()) {
                jugAbDcha.setManoLarga(mano + jugAbDcha.getCarta1().toString() + jugAbDcha.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugAbDcha.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations4
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos4 = new Mano[combinations4.size()];

                for (int x = 0; x < combinations4.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations4.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos4[x] = m;
                }

                jugAbDcha.setMejorMano(ordenarManos(manos4));
            }

            if (!jugAbIzda.isFold()) {
                jugAbIzda.setManoLarga(mano + jugAbIzda.getCarta1().toString() + jugAbIzda.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugAbIzda.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations5
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos5 = new Mano[combinations5.size()];

                for (int x = 0; x < combinations5.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations5.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos5[x] = m;
                }

                jugAbIzda.setMejorMano(ordenarManos(manos5));
            }

            ArrayList<Jugador> arrPrueba = new ArrayList<>();
            if (!jugArr.isFold()) {
                arrPrueba.add(jugArr);
            }
            if (!jugArrDcha.isFold()) {
                arrPrueba.add(jugArrDcha);
            }
            if (!jugArrIzda.isFold()) {
                arrPrueba.add(jugArrIzda);
            }
            if (!jugAb.isFold()) {
                arrPrueba.add(jugAb);
            }
            if (!jugAbDcha.isFold()) {
                arrPrueba.add(jugAbDcha);
            }
            if (!jugAbIzda.isFold()) {
                arrPrueba.add(jugAbIzda);
            }

            Jugador[] jugadores = new Jugador[numJugadores];
            for (int x = 0; x < arrPrueba.size(); ++x) {
                jugadores[x] = arrPrueba.get(x);
            }

            ordenarJugadores(jugadores);

                if (jugadores[0].getMejorMano().comprobarMejorMano().equalsIgnoreCase(jugadores[1].getMejorMano().comprobarMejorMano())
                        && jugadores[0].getMejorMano().getValorCartasMano() == jugadores[1].getMejorMano().getValorCartasMano()
                        && jugadores[0].getMejorMano().getValorCartaKicker() == jugadores[1].getMejorMano().getValorCartaKicker()
                        && jugadores[0].getMejorMano().getValorCartaSig() == jugadores[1].getMejorMano().getValorCartaSig()) {

                    ArrayList<Jugador> noSeKHago = new ArrayList<>();

                    if (numJugadores > 2) {
                        boolean ok = true;
                        for (int auxSim = 2; auxSim < 6 && ok; auxSim++) {
                            if (jugadores[auxSim].getMejorMano().comprobarMejorMano().equalsIgnoreCase(jugadores[auxSim - 1].getMejorMano().comprobarMejorMano())
                                    && jugadores[auxSim].getMejorMano().getValorCartasMano() == jugadores[auxSim - 1].getMejorMano().getValorCartasMano()
                                    && jugadores[auxSim].getMejorMano().getValorCartaKicker() == jugadores[auxSim - 1].getMejorMano().getValorCartaKicker()
                                    && jugadores[auxSim].getMejorMano().getValorCartaSig() == jugadores[auxSim].getMejorMano().getValorCartaSig()) {
                                if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                                    noSeKHago.add(jugArr);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                                    noSeKHago.add(jugAb);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                                    noSeKHago.add(jugArrDcha);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                                    noSeKHago.add(jugArrIzda);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                                    noSeKHago.add(jugAbDcha);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                                    noSeKHago.add(jugAbIzda);
                                }
                            } else {
                                ok = false;

                            }
                        }

                    }
                    if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                        noSeKHago.add(jugArr);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                        noSeKHago.add(jugAb);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                        noSeKHago.add(jugArrDcha);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                        noSeKHago.add(jugArrIzda);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                        noSeKHago.add(jugAbDcha);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                        noSeKHago.add(jugAbIzda);
                    }

                    if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                        noSeKHago.add(jugArr);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                        noSeKHago.add(jugAb);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                        noSeKHago.add(jugArrDcha);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                        noSeKHago.add(jugArrIzda);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                        noSeKHago.add(jugAbDcha);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                        noSeKHago.add(jugAbIzda);
                    }

                    for (Jugador j : noSeKHago) {
                        j.setJugadasGanadas(j.getJugadasGanadas() + (1.0 / noSeKHago.size()));
                    }
                } 
             else {
                if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                    jugArr.setJugadasGanadas(jugArr.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                    jugAb.setJugadasGanadas(jugAb.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                    jugArrDcha.setJugadasGanadas(jugArrDcha.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                    jugArrIzda.setJugadasGanadas(jugArrIzda.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                    jugAbDcha.setJugadasGanadas(jugAbDcha.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                    jugAbIzda.setJugadasGanadas(jugAbIzda.getJugadasGanadas() + 1.0);
                }
            }
        }
    }

    public void funcion() {

    }

    public void turn() {
        jugArr.setJugadasGanadas(0.0);
        jugAb.setJugadasGanadas(0.0);
        jugArrDcha.setJugadasGanadas(0.0);
        jugArrIzda.setJugadasGanadas(0.0);
        jugAbDcha.setJugadasGanadas(0.0);
        jugAbIzda.setJugadasGanadas(0.0);

        if (turn == null) {
            Random rnd = new Random();
            int ale = rnd.nextInt(baraja.getBaraja().size());
            this.turn = baraja.getBaraja().get(ale);
            mw.getjLabelTurn().setIcon(new ImageIcon(baraja.getBaraja().get(ale).getRutaRep()));
            baraja.getBaraja().remove(ale);
        }

        this.numCombinaciones = baraja.getBaraja().size();

        for (int i = 0; i < this.numCombinaciones; i++) {
            String mano = baraja.getBaraja().get(i).toString();

            if (!jugArr.isFold()) {
                jugArr.setManoLarga(mano + turn.toString() + jugArr.getCarta1().toString() + jugArr.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugArr.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations6
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos = new Mano[combinations6.size()];
                for (int x = 0; x < combinations6.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations6.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos[x] = m;
                }
                jugArr.setMejorMano(ordenarManos(manos));
            }

            if (!jugAb.isFold()) {
                jugAb.setManoLarga(mano + turn.toString() + jugAb.getCarta1().toString() + jugAb.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugAb.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations1
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos1 = new Mano[combinations1.size()];

                for (int x = 0; x < combinations1.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations1.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos1[x] = m;
                }

                jugAb.setMejorMano(ordenarManos(manos1));
            }

            if (!jugArrDcha.isFold()) {
                jugArrDcha.setManoLarga(mano + turn.toString() + jugArrDcha.getCarta1().toString() + jugArrDcha.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugArrDcha.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations2
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos2 = new Mano[combinations2.size()];

                for (int x = 0; x < combinations2.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations2.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos2[x] = m;
                }

                jugArrDcha.setMejorMano(ordenarManos(manos2));
            }

            if (!jugArrIzda.isFold()) {
                jugArrIzda.setManoLarga(mano + turn.toString() + jugArrIzda.getCarta1().toString() + jugArrIzda.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugArrIzda.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations3
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos3 = new Mano[combinations3.size()];

                for (int x = 0; x < combinations3.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations3.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos3[x] = m;
                }

                jugArrIzda.setMejorMano(ordenarManos(manos3));
            }

            if (!jugAbDcha.isFold()) {
                jugAbDcha.setManoLarga(mano + turn.toString() + jugAbDcha.getCarta1().toString() + jugAbDcha.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugAbDcha.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations4
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos4 = new Mano[combinations4.size()];

                for (int x = 0; x < combinations4.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations4.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos4[x] = m;
                }

                jugAbDcha.setMejorMano(ordenarManos(manos4));
            }

            if (!jugAbIzda.isFold()) {
                jugAbIzda.setManoLarga(mano + turn.toString() + jugAbIzda.getCarta1().toString() + jugAbIzda.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

                String[] auxi = new String[7];
                int tam = 0;

                for (int k = 0; k < 14; k = k + 2) {
                    auxi[tam] = jugAbIzda.getManoLarga().substring(k, k + 2);
                    tam++;
                }

                List<List<String>> combinations5
                        = Generator.combination(auxi)
                                .simple(5)
                                .stream()
                                .collect(toList());

                Mano[] manos5 = new Mano[combinations5.size()];

                for (int x = 0; x < combinations5.size(); x++) {
                    String cadenaMano = "";
                    for (int k = 0; k < 5; k++) {
                        cadenaMano += combinations5.get(x).get(k);
                    }
                    Mano m = new Mano(cadenaMano);
                    m.comprobarMejorMano();
                    manos5[x] = m;
                }

                jugAbIzda.setMejorMano(ordenarManos(manos5));
            }

            ArrayList<Jugador> arrPrueba = new ArrayList<>();
            if (!jugArr.isFold()) {
                arrPrueba.add(jugArr);
            }
            if (!jugArrDcha.isFold()) {
                arrPrueba.add(jugArrDcha);
            }
            if (!jugArrIzda.isFold()) {
                arrPrueba.add(jugArrIzda);
            }
            if (!jugAb.isFold()) {
                arrPrueba.add(jugAb);
            }
            if (!jugAbDcha.isFold()) {
                arrPrueba.add(jugAbDcha);
            }
            if (!jugAbIzda.isFold()) {
                arrPrueba.add(jugAbIzda);
            }

            Jugador[] jugadores = new Jugador[numJugadores];
            for (int x = 0; x < arrPrueba.size(); ++x) {
                jugadores[x] = arrPrueba.get(x);
            }

            ordenarJugadores(jugadores);

            if (jugadores[0].getMejorMano().comprobarMejorMano().equalsIgnoreCase(jugadores[1].getMejorMano().comprobarMejorMano())
                        && jugadores[0].getMejorMano().getValorCartasMano() == jugadores[1].getMejorMano().getValorCartasMano()
                        && jugadores[0].getMejorMano().getValorCartaKicker() == jugadores[1].getMejorMano().getValorCartaKicker()
                        && jugadores[0].getMejorMano().getValorCartaSig() == jugadores[1].getMejorMano().getValorCartaSig()) {

                    ArrayList<Jugador> noSeKHago = new ArrayList<>();

                    if (numJugadores > 2) {
                        boolean ok = true;
                        for (int auxSim = 2; auxSim < 6 && ok; auxSim++) {
                            if (jugadores[auxSim].getMejorMano().comprobarMejorMano().equalsIgnoreCase(jugadores[auxSim - 1].getMejorMano().comprobarMejorMano())
                                    && jugadores[auxSim].getMejorMano().getValorCartasMano() == jugadores[auxSim - 1].getMejorMano().getValorCartasMano()
                                    && jugadores[auxSim].getMejorMano().getValorCartaKicker() == jugadores[auxSim - 1].getMejorMano().getValorCartaKicker()
                                    && jugadores[auxSim].getMejorMano().getValorCartaSig() == jugadores[auxSim].getMejorMano().getValorCartaSig()) {
                                if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                                    noSeKHago.add(jugArr);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                                    noSeKHago.add(jugAb);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                                    noSeKHago.add(jugArrDcha);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                                    noSeKHago.add(jugArrIzda);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                                    noSeKHago.add(jugAbDcha);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                                    noSeKHago.add(jugAbIzda);
                                }
                            } else {
                                ok = false;

                            }
                        }

                    }
                    if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                        noSeKHago.add(jugArr);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                        noSeKHago.add(jugAb);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                        noSeKHago.add(jugArrDcha);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                        noSeKHago.add(jugArrIzda);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                        noSeKHago.add(jugAbDcha);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                        noSeKHago.add(jugAbIzda);
                    }

                    if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                        noSeKHago.add(jugArr);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                        noSeKHago.add(jugAb);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                        noSeKHago.add(jugArrDcha);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                        noSeKHago.add(jugArrIzda);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                        noSeKHago.add(jugAbDcha);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                        noSeKHago.add(jugAbIzda);
                    }

                    for (Jugador j : noSeKHago) {
                        j.setJugadasGanadas(j.getJugadasGanadas() + (1.0 / noSeKHago.size()));
                    }
                } 
             else {
                if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                    jugArr.setJugadasGanadas(jugArr.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                    jugAb.setJugadasGanadas(jugAb.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                    jugArrDcha.setJugadasGanadas(jugArrDcha.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                    jugArrIzda.setJugadasGanadas(jugArrIzda.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                    jugAbDcha.setJugadasGanadas(jugAbDcha.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                    jugAbIzda.setJugadasGanadas(jugAbIzda.getJugadasGanadas() + 1.0);
                }
            }
        }
    }

    public void river() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        jugArr.setJugadasGanadas(0.0);
        jugAb.setJugadasGanadas(0.0);
        jugArrDcha.setJugadasGanadas(0.0);
        jugArrIzda.setJugadasGanadas(0.0);
        jugAbDcha.setJugadasGanadas(0.0);
        jugAbIzda.setJugadasGanadas(0.0);

        if (river == null) {
            int ale = new Random().nextInt(baraja.getBaraja().size());
            mw.getjLabelRiver().setIcon(new ImageIcon(baraja.getBaraja().get(ale).getRutaRep()));
            this.river = baraja.getBaraja().get(ale);
            baraja.getBaraja().remove(ale);
        }

        String mano = river.toString();

        this.numCombinaciones = 1;

        if (!jugArr.isFold()) {
            jugArr.setManoLarga(mano + turn.toString() + jugArr.getCarta1().toString() + jugArr.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

            String[] auxi = new String[7];
            int tam = 0;

            for (int k = 0; k < 14; k = k + 2) {
                auxi[tam] = jugArr.getManoLarga().substring(k, k + 2);
                tam++;
            }

            List<List<String>> combinations6
                    = Generator.combination(auxi)
                            .simple(5)
                            .stream()
                            .collect(toList());

            Mano[] manos = new Mano[combinations6.size()];
            for (int x = 0; x < combinations6.size(); x++) {
                String cadenaMano = "";
                for (int k = 0; k < 5; k++) {
                    cadenaMano += combinations6.get(x).get(k);
                }
                Mano m = new Mano(cadenaMano);
                m.comprobarMejorMano();
                manos[x] = m;
            }
            jugArr.setMejorMano(ordenarManos(manos));
        }

        if (!jugAb.isFold()) {
            jugAb.setManoLarga(mano + turn.toString() + jugAb.getCarta1().toString() + jugAb.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

            String[] auxi = new String[7];
            int tam = 0;

            for (int k = 0; k < 14; k = k + 2) {
                auxi[tam] = jugAb.getManoLarga().substring(k, k + 2);
                tam++;
            }

            List<List<String>> combinations1
                    = Generator.combination(auxi)
                            .simple(5)
                            .stream()
                            .collect(toList());

            Mano[] manos1 = new Mano[combinations1.size()];

            for (int x = 0; x < combinations1.size(); x++) {
                String cadenaMano = "";
                for (int k = 0; k < 5; k++) {
                    cadenaMano += combinations1.get(x).get(k);
                }
                Mano m = new Mano(cadenaMano);
                m.comprobarMejorMano();
                manos1[x] = m;
            }

            jugAb.setMejorMano(ordenarManos(manos1));
        }

        if (!jugArrDcha.isFold()) {
            jugArrDcha.setManoLarga(mano + turn.toString() + jugArrDcha.getCarta1().toString() + jugArrDcha.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

            String[] auxi = new String[7];
            int tam = 0;

            for (int k = 0; k < 14; k = k + 2) {
                auxi[tam] = jugArrDcha.getManoLarga().substring(k, k + 2);
                tam++;
            }

            List<List<String>> combinations2
                    = Generator.combination(auxi)
                            .simple(5)
                            .stream()
                            .collect(toList());

            Mano[] manos2 = new Mano[combinations2.size()];

            for (int x = 0; x < combinations2.size(); x++) {
                String cadenaMano = "";
                for (int k = 0; k < 5; k++) {
                    cadenaMano += combinations2.get(x).get(k);
                }
                Mano m = new Mano(cadenaMano);
                m.comprobarMejorMano();
                manos2[x] = m;
            }

            jugArrDcha.setMejorMano(ordenarManos(manos2));
        }

        if (!jugArrIzda.isFold()) {
            jugArrIzda.setManoLarga(mano + turn.toString() + jugArrIzda.getCarta1().toString() + jugArrIzda.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

            String[] auxi = new String[7];
            int tam = 0;

            for (int k = 0; k < 14; k = k + 2) {
                auxi[tam] = jugArrIzda.getManoLarga().substring(k, k + 2);
                tam++;
            }

            List<List<String>> combinations3
                    = Generator.combination(auxi)
                            .simple(5)
                            .stream()
                            .collect(toList());

            Mano[] manos3 = new Mano[combinations3.size()];

            for (int x = 0; x < combinations3.size(); x++) {
                String cadenaMano = "";
                for (int k = 0; k < 5; k++) {
                    cadenaMano += combinations3.get(x).get(k);
                }
                Mano m = new Mano(cadenaMano);
                m.comprobarMejorMano();
                manos3[x] = m;
            }

            jugArrIzda.setMejorMano(ordenarManos(manos3));
        }

        if (!jugAbDcha.isFold()) {
            jugAbDcha.setManoLarga(mano + turn.toString() + jugAbDcha.getCarta1().toString() + jugAbDcha.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

            String[] auxi = new String[7];
            int tam = 0;

            for (int k = 0; k < 14; k = k + 2) {
                auxi[tam] = jugAbDcha.getManoLarga().substring(k, k + 2);
                tam++;
            }

            List<List<String>> combinations4
                    = Generator.combination(auxi)
                            .simple(5)
                            .stream()
                            .collect(toList());

            Mano[] manos4 = new Mano[combinations4.size()];

            for (int x = 0; x < combinations4.size(); x++) {
                String cadenaMano = "";
                for (int k = 0; k < 5; k++) {
                    cadenaMano += combinations4.get(x).get(k);
                }
                Mano m = new Mano(cadenaMano);
                m.comprobarMejorMano();
                manos4[x] = m;
            }

            jugAbDcha.setMejorMano(ordenarManos(manos4));
        }

        if (!jugAbIzda.isFold()) {
            jugAbIzda.setManoLarga(mano + turn.toString() + jugAbIzda.getCarta1().toString() + jugAbIzda.getCarta2().toString() + flop1.toString() + flop2.toString() + flop3.toString());

            String[] auxi = new String[7];
            int tam = 0;

            for (int k = 0; k < 14; k = k + 2) {
                auxi[tam] = jugAbIzda.getManoLarga().substring(k, k + 2);
                tam++;
            }

            List<List<String>> combinations5
                    = Generator.combination(auxi)
                            .simple(5)
                            .stream()
                            .collect(toList());

            Mano[] manos5 = new Mano[combinations5.size()];

            for (int x = 0; x < combinations5.size(); x++) {
                String cadenaMano = "";
                for (int k = 0; k < 5; k++) {
                    cadenaMano += combinations5.get(x).get(k);
                }
                Mano m = new Mano(cadenaMano);
                m.comprobarMejorMano();
                manos5[x] = m;
            }

            jugAbIzda.setMejorMano(ordenarManos(manos5));
        }

        ArrayList<Jugador> arrPrueba = new ArrayList<>();
        if (!jugArr.isFold()) {
            arrPrueba.add(jugArr);
        }
        if (!jugArrDcha.isFold()) {
            arrPrueba.add(jugArrDcha);
        }
        if (!jugArrIzda.isFold()) {
            arrPrueba.add(jugArrIzda);
        }
        if (!jugAb.isFold()) {
            arrPrueba.add(jugAb);
        }
        if (!jugAbDcha.isFold()) {
            arrPrueba.add(jugAbDcha);
        }
        if (!jugAbIzda.isFold()) {
            arrPrueba.add(jugAbIzda);
        }

        Jugador[] jugadores = new Jugador[numJugadores];
        for (int x = 0; x < arrPrueba.size(); ++x) {
            jugadores[x] = arrPrueba.get(x);
        }

        ordenarJugadores(jugadores);

        if (jugadores[0].getMejorMano().comprobarMejorMano().equalsIgnoreCase(jugadores[1].getMejorMano().comprobarMejorMano())
                        && jugadores[0].getMejorMano().getValorCartasMano() == jugadores[1].getMejorMano().getValorCartasMano()
                        && jugadores[0].getMejorMano().getValorCartaKicker() == jugadores[1].getMejorMano().getValorCartaKicker()
                        && jugadores[0].getMejorMano().getValorCartaSig() == jugadores[1].getMejorMano().getValorCartaSig()) {

                    ArrayList<Jugador> noSeKHago = new ArrayList<>();

                    if (numJugadores > 2) {
                        boolean ok = true;
                        for (int auxSim = 2; auxSim < 6 && ok; auxSim++) {
                            if (jugadores[auxSim].getMejorMano().comprobarMejorMano().equalsIgnoreCase(jugadores[auxSim - 1].getMejorMano().comprobarMejorMano())
                                    && jugadores[auxSim].getMejorMano().getValorCartasMano() == jugadores[auxSim - 1].getMejorMano().getValorCartasMano()
                                    && jugadores[auxSim].getMejorMano().getValorCartaKicker() == jugadores[auxSim - 1].getMejorMano().getValorCartaKicker()
                                    && jugadores[auxSim].getMejorMano().getValorCartaSig() == jugadores[auxSim].getMejorMano().getValorCartaSig()) {
                                if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                                    noSeKHago.add(jugArr);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                                    noSeKHago.add(jugAb);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                                    noSeKHago.add(jugArrDcha);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                                    noSeKHago.add(jugArrIzda);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                                    noSeKHago.add(jugAbDcha);
                                } else if (jugadores[auxSim].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                                    noSeKHago.add(jugAbIzda);
                                }
                            } else {
                                ok = false;

                            }
                        }

                    }
                    if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                        noSeKHago.add(jugArr);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                        noSeKHago.add(jugAb);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                        noSeKHago.add(jugArrDcha);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                        noSeKHago.add(jugArrIzda);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                        noSeKHago.add(jugAbDcha);
                    } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                        noSeKHago.add(jugAbIzda);
                    }

                    if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                        noSeKHago.add(jugArr);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                        noSeKHago.add(jugAb);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                        noSeKHago.add(jugArrDcha);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                        noSeKHago.add(jugArrIzda);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                        noSeKHago.add(jugAbDcha);
                    } else if (jugadores[1].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                        noSeKHago.add(jugAbIzda);
                    }

                    for (Jugador j : noSeKHago) {
                        j.setJugadasGanadas(j.getJugadasGanadas() + (1.0 / noSeKHago.size()));
                    }
                } 
             else {
                if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArr.getCarta1().toString())) {
                    jugArr.setJugadasGanadas(jugArr.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAb.getCarta1().toString())) {
                    jugAb.setJugadasGanadas(jugAb.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrDcha.getCarta1().toString())) {
                    jugArrDcha.setJugadasGanadas(jugArrDcha.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugArrIzda.getCarta1().toString())) {
                    jugArrIzda.setJugadasGanadas(jugArrIzda.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbDcha.getCarta1().toString())) {
                    jugAbDcha.setJugadasGanadas(jugAbDcha.getJugadasGanadas() + 1.0);
                } else if (jugadores[0].getCarta1().toString().equalsIgnoreCase(jugAbIzda.getCarta1().toString())) {
                    jugAbIzda.setJugadasGanadas(jugAbIzda.getJugadasGanadas() + 1.0);
                }
            }
    }

    public Mano ordenarManos(Mano[] aux) {
        Mano auxi;

        for (int i = 0; i < aux.length - 1; i++) {
            for (int j = 0; j < aux.length - i - 1; j++) {
                if (aux[j + 1].getValorMano() < aux[j].getValorMano()) {
                    auxi = aux[j + 1];
                    aux[j + 1] = aux[j];
                    aux[j] = auxi;
                } else if (aux[j + 1].getValorMano() == aux[j].getValorMano() && aux[j + 1].getValorCartasMano() == aux[j].getValorCartasMano() && aux[j + 1].getValorCartaKicker() == aux[j].getValorCartaKicker()) {
                    if (aux[j + 1].getValorCartaSig() > aux[j].getValorCartaSig()) {
                        auxi = aux[j + 1];
                        aux[j + 1] = aux[j];
                        aux[j] = auxi;
                    }
                } else if (aux[j + 1].getValorMano() == aux[j].getValorMano() && aux[j + 1].getValorCartasMano() == aux[j].getValorCartasMano()) {
                    if (aux[j + 1].getValorCartaKicker() > aux[j].getValorCartaKicker()) {
                        auxi = aux[j + 1];
                        aux[j + 1] = aux[j];
                        aux[j] = auxi;
                    }
                } else if (aux[j + 1].getValorMano() == aux[j].getValorMano()) {
                    if (aux[j + 1].getValorCartasMano() > aux[j].getValorCartasMano()) {
                        auxi = aux[j + 1];
                        aux[j + 1] = aux[j];
                        aux[j] = auxi;
                    }

                }
            }
        }

        return aux[0];
    }

    public void ordenarJugadores(Jugador[] aux) {
        Jugador auxi;
        for (int i = 0; i < aux.length - 1; i++) {
            for (int j = 0; j < aux.length - i - 1; j++) {
                if (aux[j + 1].getMejorMano().getValorMano() < aux[j].getMejorMano().getValorMano()) {
                    auxi = aux[j + 1];
                    aux[j + 1] = aux[j];
                    aux[j] = auxi;
                } else if (aux[j + 1].getMejorMano().getValorMano() == aux[j].getMejorMano().getValorMano() && aux[j + 1].getMejorMano().getValorCartasMano() == aux[j].getMejorMano().getValorCartasMano()
                        && aux[j + 1].getMejorMano().getValorCartaKicker() == aux[j].getMejorMano().getValorCartaKicker()) {
                    if (aux[j + 1].getMejorMano().getValorCartaSig() > aux[j].getMejorMano().getValorCartaSig()) {
                        auxi = aux[j + 1];
                        aux[j + 1] = aux[j];
                        aux[j] = auxi;
                    }
                } else if (aux[j + 1].getMejorMano().getValorMano() == aux[j].getMejorMano().getValorMano() && aux[j + 1].getMejorMano().getValorCartasMano() == aux[j].getMejorMano().getValorCartasMano()) {
                    if (aux[j + 1].getMejorMano().getValorCartaKicker() > aux[j].getMejorMano().getValorCartaKicker()) {
                        auxi = aux[j + 1];
                        aux[j + 1] = aux[j];
                        aux[j] = auxi;
                    }
                } else if (aux[j + 1].getMejorMano().getValorMano() == aux[j].getMejorMano().getValorMano()) {
                    if (aux[j + 1].getMejorMano().getValorCartasMano() > aux[j].getMejorMano().getValorCartasMano()) {
                        auxi = aux[j + 1];
                        aux[j + 1] = aux[j];
                        aux[j] = auxi;
                    }
                }
            }
        }
    }

}
