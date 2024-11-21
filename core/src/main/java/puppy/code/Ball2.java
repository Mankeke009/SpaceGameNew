package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ball2 extends ObjetosEspaciales implements Colision {
    private int size;
    private boolean isDestroyed;

    public Ball2(int x, int y, int size, int xV, int yV, Texture tx) {
        super(tx, x, y);
        this.size = size;
        this.Vel_x = xV;
        this.Vel_y = yV;
        this.isDestroyed = false; // Inicialmente, el meteorito no está destruido

        // Control de límites iniciales
        ajustarPosicion(x, y);
    }

    private void ajustarPosicion(int x, int y) {
        if (x - size < 0) spr.setX(x + size);
        if (x + size > Gdx.graphics.getWidth()) spr.setX(x - size);
        if (y - size < 0) spr.setY(y + size);
        if (y + size > Gdx.graphics.getHeight()) spr.setY(y - size);
    }

    @Override
    public void mover() {
        if (!isDestroyed) {
            update();
        }
    }

    public void update() {
        float velocidadReducida = 0.7f;
        float newX = spr.getX() + Vel_x * velocidadReducida;
        float newY = spr.getY() + Vel_y * velocidadReducida;

        // Rebote en los bordes de la pantalla
        if (newX < 0 || newX + spr.getWidth() > Gdx.graphics.getWidth()) {
            Vel_x *= -1;
        }
        if (newY < 0 || newY + spr.getHeight() > Gdx.graphics.getHeight()) {
            Vel_y *= -1;
        }

        spr.setPosition(spr.getX() + Vel_x * velocidadReducida, spr.getY() + Vel_y * velocidadReducida);
    }

    public void draw(SpriteBatch batch) {
        if (!isDestroyed) {
            spr.draw(batch);
        }
    }

    // Implementación de la interfaz Colisionable
    @Override
    public boolean detectarColision(ObjetosEspaciales otro) {
        if (otro instanceof Nave4) { // Detectar colisión con la nave
            return spr.getBoundingRectangle().overlaps(otro.getArea());
        }
        return false;
    }

    @Override
    public void alColisionar(ObjetosEspaciales otro) {
        if (otro instanceof Nave4) {
            // Aquí puedes manejar la colisión con la nave
            ((Nave4) otro).alColisionar(this); // Llama al método de colisión de la nave

            // Marca el meteorito como destruido
            destruir();
        }
    }

    public void destruir() {
        isDestroyed = true; // Marca el meteorito como destruido
        // Aquí puedes agregar lógica para efectos de sonido o visuales
        System.out.println("Meteorito destruido!");
    }

    public boolean isDestroyed() {
        return isDestroyed; // Devuelve el estado de destrucción del meteorito
    }

    public int getSize() {
        return size; // Devuelve el tamaño del meteorito
    }

    public float getXSpeed() {
        return Vel_x; // Devuelve la velocidad en X
    }

    public float getYSpeed() {
        return Vel_y; // Devuelve la velocidad en Y
    }

    public void setXSpeed(float xSpeed) {
        this.Vel_x = xSpeed; // Establece la velocidad en X
    }

    public void setYSpeed(float ySpeed) {
        this.Vel_y = ySpeed; // Establece la velocidad en Y
    }
}


