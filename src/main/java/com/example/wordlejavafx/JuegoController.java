package com.example.wordlejavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class JuegoController {

    @FXML
    private TextField palabra;

    @FXML private Rectangle r00,r01,r02,r03,r04;
    @FXML private Rectangle r10,r11,r12,r13,r14;
    @FXML private Rectangle r20,r21,r22,r23,r24;
    @FXML private Rectangle r30,r31,r32,r33,r34;
    @FXML private Rectangle r40,r41,r42,r43,r44;
    @FXML private Rectangle r50,r51,r52,r53,r54;

    @FXML private Label l00,l01,l02,l03,l04;
    @FXML private Label l10,l11,l12,l13,l14;
    @FXML private Label l20,l21,l22,l23,l24;
    @FXML private Label l30,l31,l32,l33,l34;
    @FXML private Label l40,l41,l42,l43,l44;
    @FXML private Label l50,l51,l52,l53,l54;

    @FXML private Button btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ,btnK, btnL, btnM, btnN, btnÑ, btnO, btnP, btnQ, btnR, btnS,btnT, btnU, btnV, btnW, btnX, btnY, btnZ;

    private final String[] palabras5Letras = {
            "CASAS", "PERRO", "GATOS", "LIMON", "NIEVE", "CAMPO", "TIGRE", "RATON", "SILLA", "MANGO",
            "PLUMA", "LIBRO", "SUELO", "AVION", "ARCOI", "FLORE", "CUERO", "TRIGO", "SALTA", "FIESTA",
            "DULCE", "LLAVE", "PIANO", "RELOJ", "MONTA", "VALOR", "JUEGO", "OJOSO", "BOLSA", "CARRO",
            "VIAJE", "SOLAR", "BARCO", "CASCO", "HUESO", "FRUTA", "BEBER", "CAMPO", "CIELO", "PERLA",
            "ARENA", "PLANO", "GRUPO", "MOTOR", "TARDE", "FONDO", "GENTE", "RUIDO", "VENTA", "GLOBO",
            "SALSA", "CAUSA", "NACER", "MUEVE", "PARED", "LARGO", "LUZAR", "PEZON", "LLORO", "TIEMO",
            "NINOS", "MUNDO", "FIRMA", "CRUZS", "SOLAR", "MONTE", "HABER", "GRADO", "LIMPI", "PLAZA",
            "BAILE", "ROMPE", "CANTO", "BRISA", "CRONO", "POLLO", "JUGAR", "SALIR", "SUEÑO", "NIEVE",
            "LECHE", "PESAR", "TRABA", "BOMBA", "FUEGO", "RISAS", "VOLAR", "NUBES", "COCHE", "LLAMA",
            "SALTO", "MIRAR", "PUNTO", "BRAVO", "RAPTO", "TRECE", "LUGAR", "BEBES", "PASTA", "VISTA"
    };

    private String palabraSecreta;
    private int filaActual = 0;
    private Rectangle[][] tablero;
    private Label[][] tableroLetras;
    public int record;

    public void initialize(){

    tablero = new Rectangle[][]{
        {r00,r01,r02,r03,r04},
        {r10,r11,r12,r13,r14},
        {r20,r21,r22,r23,r24},
        {r30,r31,r32,r33,r34},
        {r40,r41,r42,r43,r44},
        {r50,r51,r52,r53,r54}
    };

    tableroLetras = new Label[][]{
        {l00,l01,l02,l03,l04},
        {l10,l11,l12,l13,l14},
        {l20,l21,l22,l23,l24},
        {l30,l31,l32,l33,l34},
        {l40,l41,l42,l43,l44},
        {l50,l51,l52,l53,l54}
    };

    elegirPalabraSecreta();

    }

    public void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void reiniciarJuego() {
        filaActual = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                tablero[i][j].setFill(Color.LIGHTGRAY);
                tableroLetras[i][j].setText("");
            }
        }
        elegirPalabraSecreta();
    }

    public void elegirPalabraSecreta() {
        Random rand = new Random();
        palabraSecreta = palabras5Letras[rand.nextInt(palabras5Letras.length)];
        System.out.println("Palabra secreta: " + palabraSecreta);
    }

    public void agregarPalabra(){
        String palabraTexto = palabra.getText().toUpperCase();
        palabra.setText("");

        if (palabraTexto.length()!=5){
            mostrarAlerta("La palabra debe tener 5 caracteres");
            return;
        }

        for (int i = 0; i < 5; i++) {
            char letraUsuario = palabraTexto.charAt(i);
            char letraSecreta = palabraSecreta.charAt(i);

            tableroLetras[filaActual][i].setText(String.valueOf(letraUsuario));

            if(letraSecreta == letraUsuario){
                tablero[filaActual][i].setFill(Color.GREEN);

            } else if (palabraSecreta.contains(String.valueOf(letraUsuario))) {
                tablero[filaActual][i].setFill(Color.GOLD);

            } else if (letraSecreta != letraUsuario) {
                tablero[filaActual][i].setFill(Color.GREY);
            }
        }

        filaActual++;

        if (palabraTexto.equals(palabraSecreta)) {
            record++;
            finJuego(true);
        } else if (filaActual >= 6) {
            finJuego(false);
        }


    }

    private void coloresTeclado(char letra, Color colorLetra){

        Button[] botones = {
                btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ,
                btnK, btnL, btnM, btnN, btnÑ, btnO, btnP, btnQ, btnR, btnS,
                btnT, btnU, btnV, btnW, btnX, btnY, btnZ
        };

        for (Button btn : botones) {
            if (btn.getText().equalsIgnoreCase(String.valueOf(letra))) {
                String estilo = btn.getStyle();
                if(!estilo.contains("green")){

                    if(colorLetra == Color.GREEN) {
                        btn.setStyle("-fx-background-color: green; -fx-border-color: black;");
                    } else if(colorLetra == Color.GOLD) {
                            btn.setStyle("-fx-background-color: gold; -fx-border-color: black;");
                    } else {
                            btn.setStyle("-fx-background-color: grey; -fx-border-color: black;");
                    }
                };
            }
        }
    }

    public void borrarPalabra(){
        palabra.setText("");
    }

    public void finJuego(boolean win){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Fin del juego");

        if (win) {
            alerta.setHeaderText("¡Ganaste!");
            alerta.setContentText("Has adivinado la palabra: " + palabraSecreta + "\nTienes un record de: " + record);
        } else {
            alerta.setHeaderText("Se acabaron los intentos");
            alerta.setContentText("La palabra era: " + palabraSecreta + "\nTienes un record de: " + record);
            record = 0;
        }

        ButtonType botonSalir = new ButtonType("Salir");
        ButtonType botonJugar = new ButtonType("Seguir jugando");
        alerta.getButtonTypes().setAll(botonSalir, botonJugar);

        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == botonJugar) {
            reiniciarJuego();
        } else {
            System.exit(0);
        }
    }
}
