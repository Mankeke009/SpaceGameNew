package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceNavigation extends Game {

    private static SpaceNavigation instanciaUnica; // Instancia única Singleton
    private String nombreJuego = "Space Navigation";
    private SpriteBatch batch;
    private BitmapFont font;
    private int highScore;

    // Constructor privado: evita instanciación directa
    private SpaceNavigation() {
        highScore = 0;
    }

    // Método público estático para obtener la instancia única
    public static SpaceNavigation getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new SpaceNavigation();
        }
        return instanciaUnica;
    }

    // Inicialización de recursos
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        setScreen(new PantallaMenu(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
