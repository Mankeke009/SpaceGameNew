package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

// Clase abstracta que representa un objeto espacial
public abstract class ObjetosEspaciales {
    protected Sprite spr;
    protected float Vel_x; 
    protected float Vel_y; 

    // Constructor que permite inicializar la textura y la posición del objeto
    public ObjetosEspaciales(Texture texture, int x, int y) {
        this.spr = new Sprite(texture);
        this.spr.setPosition(x, y);
        // Opcional: se puede ajustar el tamaño según sea necesario
        this.spr.setBounds(x, y, texture.getWidth(), texture.getHeight());
    }

    // Método abstracto que debe ser implementado para mover el objeto
    public abstract void mover();

    // Método para actualizar la posición del sprite
    public void actualizarPosicion() {
        spr.setX(spr.getX() + Vel_x);
        spr.setY(spr.getY() + Vel_y);
    }

    // Método para verificar si hay colisión con otro objeto
    public boolean verificarColision(ObjetosEspaciales otro) {
        return this.getArea().overlaps(otro.getArea());
    }

    public Sprite getSprite() {
        return spr;
    }

    public float getX() {
        return spr.getX();
    }

    public float getY() {
        return spr.getY();
    }

    public Rectangle getArea() {
        return spr.getBoundingRectangle();
    }

    public void setVel_x(float xVel) {
        this.Vel_x = xVel;
    }

    public void setVel_y(float yVel) {
        this.Vel_y = yVel;
    }

    public float getVel_x() {
        return Vel_x;
    }

    public float getVel_y() {
        return Vel_y;
    }
}

