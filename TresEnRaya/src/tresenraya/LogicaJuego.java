package tresenraya;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.*;

public class LogicaJuego {
    int turno, pX, pO; 
    boolean habilitado;
    boolean gX = false;
    boolean gO = false;

    /**
     * Inicializaremos el juego con las siguientes variables_
     * @param turno (Nos indicará el turno del jugador: 0 - X, 1 - O)
     * @param pX (Contiene el valor total de las victorias de este jugador)
     * @param pO (Contiene el valor total de las victorias de este jugador)
     */
    public LogicaJuego(int turno, int pX, int pO) {
        this.turno = turno;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Obtener turno
     * @return 
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Insertar turno
     * @param turno 
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }
    
    /**
     * Llamaremos a este método para cambiar de turno
     */
    public void cambioTurno(){
        
        if (turno == 0) {
            turno = 1;
        } else if (turno == 1) {
            turno = 0;
        }
        
    }
    
    /**
     * Comprobar si se ha conseguido un tres en raya, 
     * en caso que se haya conseguido devolverá 1, en caso contrario retorna 0 y continúa el juego
     * @param matriz (Tablero de juego)
     * @return 
     */
    public int comprobarJuego(int matriz[][]){
        
        boolean compr = false;
        gX = false;
        gO = false;
        
        for (int i = 0; i < 3; i++) {
            int j = 0;
            if (matriz[i][j] == 0 && matriz[i][j+1] == 0 && matriz[i][j+2] == 0) {
               compr = true;
               gX = true;
            } else if (matriz[i][j] == 1 && matriz[i][j+1] == 1 && matriz[i][j+2] == 1) {
               compr = true;
               gO = true;
            }
        }
        
        for (int j = 0; j < 3; j++) {
            int i = 0;
            if (matriz[i][j] == 0 && matriz[i+1][j] == 0 && matriz[i+2][j] == 0) {
               compr = true;
               gX = true;
            } else if (matriz[i][j] == 1 && matriz[i+1][j] == 1 && matriz[i+2][j] == 1) {
               compr = true;
               gO = true;
            }
        }
        
        if (matriz[2][0] == 0 && matriz[1][1] == 0 && matriz[0][2] == 0) {
            compr = true;
            gX = true;
        } else if (matriz[0][0] == 0 && matriz[1][1] == 0 && matriz[2][2] == 0) {
            compr = true;                 
            gX = true;
        } else if (matriz[2][0] == 1 && matriz[1][1] == 1 && matriz[0][2] == 1) {
            compr = true;                 
            gO = true;
        } else if (matriz[0][0] == 1 && matriz[1][1] == 1 && matriz[2][2] == 1) {
            compr = true;                 
            gO = true;
        }
        
        
        if (compr == true) {
        return 1;
        } else {
        return 0;
        }
    }
    
    /**
     * Deshabilitará el botón para evitar que se vuelva a posicionar una ficha en ese hueco
     * @param bt (Botón seleccionado)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     * @return 
     */
    public int tiradaJugador(javax.swing.JButton bt, int x, int y, int matriz[][], javax.swing.JPanel jp, javax.swing.JLabel lX, javax.swing.JLabel lO){
        
        bt.setEnabled(false);
                 
        ponerFicha(matriz, x, y, bt);
        cambioTurno();
        
        
         if (comprobarJuego(matriz) == 1) {
         
         for (Component c : jp.getComponents()){
            c.setEnabled(false);
         
         }
         ganador(lX, lO);
         }
         return 0;
    }
    
    /**
     * Actualizar la puntuación de cada jugador al ganar la partida
     * Al finalizar el incremento de puntuación es necesario cambiar de turno
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     */
    public void ganador(javax.swing.JLabel lX, javax.swing.JLabel lO){
        
        if (gX == true) {
        pX = pX + 1;
        lX.setText(getpX()+"");
        } else if (gO == true) {
        pO = pO + 1;
        lO.setText(getpO() + "");
        }
            
 
    }
    
    /**
     * Habilitará o deshabilitará el tablero dependiendo del estado de la variable habilitado
     * @param jp  (Panel dónde se sitúa el tablero de juego)
     */
    public void habilitarTablero( javax.swing.JPanel jp){
        
        for (Component c : jp.getComponents()){
            c.setEnabled(true);
        }
        
        
    }
    
    /**
     * Insertaremos la ficha en la posición correspondiente de la matriz
     * Llamaremos al método pintarFicha
     * @param matriz (Tablero de juego)
     * @param t (Turno)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param bt (Botón pulsado)
     */
    public void ponerFicha(int matriz[][], int x, int y, javax.swing.JButton bt){
        
        
        if (turno == 0) {
            matriz[x][y] = 0;
            } else if (turno == 1) {
            matriz[x][y] = 1;
            } 
            pintarFicha(bt);
        
        
    }
    
    /**
     * Pintará la ficha en el tablero de juego visual, es decir, en el botón
     * @param bt (Botón pulsado)
     */
    private void pintarFicha(javax.swing.JButton bt){
              
        if (turno == 0) {
        bt.setText("X");
        bt.setForeground(Color.red);
        } else if (turno == 1) {
        bt.setText("O");
        bt.setForeground(Color.blue);
        }
        

    }
    
    /**
     * Inicializa una nueva partida, reinicia la matriz (Tablero de juego) y habilita el tablero
     * 
     *  
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void iniciarPartida(int matriz[][], javax.swing.JPanel jp){
        
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                matriz[x][y]=(x+10)*(y+10);
            }
        }

        
         habilitado = true;
         habilitarTablero(jp);
    }
}
