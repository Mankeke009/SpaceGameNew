package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Nave4 extends ObjetosEspaciales implements Colision {
    private boolean destruida = false;
    private int vidas = 3;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;
    private Sound sonidoHerido;
    private Sound soundBala;
    private Texture txBala;

    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
        super(tx, x, y);
        this.sonidoHerido = soundChoque;
        this.soundBala = soundBala;
        this.txBala = txBala;
    }

    @Override
    public void mover() {
        // Movimiento de la nave
        if (!herido) {
            manejarMovimiento();
        } else {
            comportamientoHerido();
        }
    }

    private void manejarMovimiento() {
        // Cambiar velocidad basado en entradas del teclado
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) Vel_x--;
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) Vel_x++;
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) Vel_y--;
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) Vel_y++;

        float x = spr.getX() + Vel_x;
        float y = spr.getY() + Vel_y;

        // Control de límites de la nave
        if (x < 0 || x + spr.getWidth() > Gdx.graphics.getWidth()) {
            Vel_x *= -1; // Rebote horizontal
        }
        if (y < 0 || y + spr.getHeight() > Gdx.graphics.getHeight()) {
            Vel_y *= -1; // Rebote vertical
        }
        spr.setPosition(x, y);

        // Rotación de la nave hacia el cursor
        rotarHaciaCursor();
    }

    private void rotarHaciaCursor() {
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        float deltaX = mouseX - (spr.getX() + spr.getWidth() / 2);
        float deltaY = mouseY - (spr.getY() + spr.getHeight() / 2);
        float angle = MathUtils.atan2(deltaY, deltaX) * MathUtils.radiansToDegrees - 90;
        spr.setRotation(angle);
    }

    private void comportamientoHerido() {
        // Comportamiento cuando la nave está herida
        spr.setX(spr.getX() + MathUtils.random(-2, 2));
        tiempoHerido--;
        if (tiempoHerido <= 0) {
            herido = false; // Termina el estado herido
        }
    }

    public void draw(SpriteBatch batch, PantallaJuego juego) {
    // Disparo de balas antes de mover la nave
    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
        dispararBala(juego);
    }

    mover(); // Mueve la nave después de procesar la entrada de disparo
    spr.draw(batch);
}


    private void dispararBala(PantallaJuego juego) {
        float angle = spr.getRotation();
        Bullet bala = new Bullet(
            spr.getX() + spr.getWidth() / 2 - 5,
            spr.getY() + spr.getHeight() - 5,
            angle,
            txBala
        );
        juego.agregarBala(bala);
        soundBala.play();
    }

    // Implementación de la interfaz Colisionable
    @Override
    public boolean detectarColision(ObjetosEspaciales otro) {
        if (otro instanceof Ball2) {
            Ball2 ball = (Ball2) otro;
            return ball.getArea().overlaps(spr.getBoundingRectangle());
        }
        return false;
    }

    @Override
    public void alColisionar(ObjetosEspaciales otro) {
        if (otro instanceof Ball2) {
            manejarColisionConBall((Ball2) otro);
        }
    }

    private void manejarColisionConBall(Ball2 ball) {
        vidas--;
        herido = true; 
        tiempoHerido = tiempoHeridoMax; 
        sonidoHerido.play(); 
        if (vidas <= 0) {
            destruida = true; 
        }
    }

    public boolean estaDestruido() {
        return !herido && destruida; 
    }

    public boolean estaHerido() {
        return herido; 
    }

    public int getVidas() {
        return vidas; 
    }

    public void setVidas(int vidas2) {
        vidas = vidas2; 
    }
}
