package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Bullet extends ObjetosEspaciales implements Colision {
    private float xSpeed;
    private float ySpeed;
    private boolean destroyed;

    public Bullet(float x, float y, float angle, Texture texture) {
        super(texture, (int)x, (int)y);
        spr.setRotation(angle);

        float speed = 5f; // Ajusta esta velocidad según lo necesites
        xSpeed = speed * MathUtils.cosDeg(angle + 90); // +90 para ajustar la dirección
        ySpeed = speed * MathUtils.sinDeg(angle + 90);
        destroyed = false; // Inicializa como no destruido
    }

    @Override
    public void mover() {
        // Actualiza la posición de la bala
        spr.setPosition(spr.getX() + xSpeed, spr.getY() + ySpeed);

        // Verifica si la bala salió de la pantalla
        if (isOutOfScreen()) {
            destroyed = true; // Marca como destruida si sale de la pantalla
        }
    }

    private boolean isOutOfScreen() {
        return spr.getX() < 0 || spr.getX() + spr.getWidth() > Gdx.graphics.getWidth() ||
               spr.getY() < 0 || spr.getY() + spr.getHeight() > Gdx.graphics.getHeight();
    }

    // Método que debe ser llamado en actualizarBalas()
    public void update() {
        mover(); // Llama al método mover() para actualizar la posición
    }

    public void draw(SpriteBatch batch) {
        spr.draw(batch);
    }

    // Implementación de la interfaz Colisionable
    @Override
    public boolean detectarColision(ObjetosEspaciales otro) {
        if (otro instanceof Ball2) {
            Ball2 asteroide = (Ball2) otro;
            return spr.getBoundingRectangle().overlaps(asteroide.getArea());
        }
        return false;
    }

    // Asegúrate de que este método esté correctamente colocado dentro de la clase
    public void alColisionar(ObjetosEspaciales otro) {
        // Implementación de lo que sucede al colisionar con otro objeto
    }

    public boolean isDestroyed() {
        return destroyed; // Método para verificar si la bala está destruida
    }
}
