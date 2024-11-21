package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaMenu implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;

	public PantallaMenu(SpaceNavigation game) {
		this.game = SpaceNavigation.getInstance();
        
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
	}

	@Override
	public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        
        // Dibujar el fondo
        //game.getBatch().draw(fondo, 0, 0, 1200, 800);
        
        // Mostrar el texto de bienvenida
        game.getFont().draw(game.getBatch(), "Bienvenido a Space Navigation!", 100, 400);
        game.getFont().draw(game.getBatch(), "Cómo jugar:", 100, 350);
        game.getFont().draw(game.getBatch(), "WASD para moverte y apuntar.", 100, 300);
        game.getFont().draw(game.getBatch(), "Dispara con el clic izquierdo del ratón.", 100, 250);
        game.getFont().draw(game.getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 100, 200);
        
        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            Screen newScreen = new PantallaJuego(game, 1, 3, 0, 1, 1);
            newScreen.resize(1200, 800);
            game.setScreen(newScreen);
            // Optionally, dispose of the current screen if necessary
            // dispose(); 
        }
    }

	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
   
}