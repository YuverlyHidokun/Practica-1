import java.util.Random;

public class Carta {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Carta(String nombre) {
        this.nombre = nombre;
    }

    public void mostrarDescripcion() {
        System.out.println("Esta es la carta que sacaste: " + getNombre());
    }

    public static Carta[] jugar() {
        Carta[] cartas = new Carta[3];
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int numeroCarta = i + 1;
            if (random.nextBoolean()) {
                cartas[i] = new CartaNumerica("Carta Numérica " + numeroCarta,numeroCarta);
            } else {
                cartas[i] = new CartaEspecial("Carta Especial " + numeroCarta);
            }
        }

        return cartas;
    }
}

class CartaNumerica extends Carta {
    private int numero;

    public CartaNumerica(String nombre, int numero) {
        super(nombre);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double realizarCalculo() {
        double numeroConvertido = (double) numero;
        return Math.sqrt(numeroConvertido);
    }
}

class CartaEspecial extends Carta {
    public CartaEspecial(String nombre) {
        super(nombre);
    }

    public void activarEfectoEspecial() throws SinEfectoEspecial {
        if (getNombre().startsWith("Carta Especial")) {
            System.out.println("Efecto de la carta " + getNombre());
        } else {
            throw new SinEfectoEspecial("La carta " + getNombre() + " no tiene un efecto JAJAJAJA.");
        }
    }

    @Override
    public void mostrarDescripcion() {
        super.mostrarDescripcion();
        System.out.println("Número especial: " + getNombre());
    }
}

class SinEfectoEspecial extends Exception {
    public SinEfectoEspecial(String mensaje) {
        super(mensaje);
    }
}

class Main {
    public static void main(String[] args) {
        try {
            Carta[] cartas = Carta.jugar();

            for (Carta carta : cartas) {
                carta.mostrarDescripcion();

                if (carta instanceof CartaNumerica) {
                    double resultadoCalculo = ((CartaNumerica) carta).realizarCalculo();
                    System.out.println("Resultado del cálculo: " + resultadoCalculo);
                }

                if (carta instanceof CartaEspecial) {
                    ((CartaEspecial) carta).activarEfectoEspecial();
                }
            }
        } catch (Exception e) {
            System.out.println("Se produjo un error muy SUS: " + e.getMessage());
        }
    }
}


