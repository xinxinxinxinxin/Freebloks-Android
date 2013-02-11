package de.saschahlusiak.freebloks.view.effects;

import javax.microedition.khronos.opengles.GL10;

import de.saschahlusiak.freebloks.model.Spiel;
import de.saschahlusiak.freebloks.model.Stone;
import de.saschahlusiak.freebloks.view.BoardRenderer;

public class StoneFadeEffect extends AbsStoneEffect {
	private static final float TIME_PER_PERIOD = 1.2f;
	private float NUMBER_OF_PERIODS;
	
	public StoneFadeEffect(Stone stone, int player, int x, int y, float cycles) {
		super(stone, player, x, y);
		this.NUMBER_OF_PERIODS = cycles;
	}
	
	@Override
	public boolean isDone() {
		return time > TIME_PER_PERIOD * NUMBER_OF_PERIODS;
	}

	@Override
	public void render(GL10 gl, Spiel spiel, BoardRenderer renderer) {
		float alpha;
		/* every TIME_PER_PERIOD needs to match 2 * PI */
		alpha = (float)Math.cos(time / TIME_PER_PERIOD * (float)Math.PI * 2.0f) / 2.0f + 0.5f;
		
		gl.glPushMatrix();
		
	    gl.glTranslatef(
	    		-BoardRenderer.stone_size * (float)(spiel.m_field_size_x - 1) + BoardRenderer.stone_size * 2.0f * (float)x,
	    		0,
	    		-BoardRenderer.stone_size * (float)(spiel.m_field_size_x - 1) + BoardRenderer.stone_size * 2.0f * (float)y);
		
		renderer.renderPlayerStone(gl, player, stone, alpha * BoardRenderer.DEFAULT_ALPHA);
		gl.glPopMatrix();
	}
}