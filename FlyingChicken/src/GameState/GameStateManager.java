package GameState;

import java.util.ArrayList;

public class GameStateManager
{
    private ArrayList<GameState> gameStates;
    private int currentState;
    
    public static final int MENUSTATE = 0;
    public static final int LEVEL1 = 1;
    public static final int LEVEL2 = 2;
    public static final int LEVEL3 = 3;
    public static final int CUTSCENE = 4;
    public static final int ENDSCENE = 5;
    public static final int FAILSCENE = 6;
    public static final int HOWTOPLAY = 7;
    public static final int CREDITS = 8;
    
    
    
    public GameStateManager()
    {
        gameStates = new ArrayList<GameState>();
        
        currentState = MENUSTATE;

        gameStates.add(new MenuState(this));
        gameStates.add(new Level1(this));
        gameStates.add(new Level2(this));
        gameStates.add(new Level3(this));
        gameStates.add(new CutScene(this));
        gameStates.add(new EndScene(this));
        gameStates.add(new FailScene(this));
        gameStates.add(new HowToPlay(this));
        gameStates.add(new Credits(this));
    }
    
    public void setState(int state)
    {
        currentState = state;
        gameStates.get(currentState).init();
    }
    
    public void update()
    {
        gameStates.get(currentState).update();
    }
    
    public void draw(java.awt.Graphics2D g)
    {
        gameStates.get(currentState).draw(g);
    }
    
    public void keyPressed(int k)
    {
        gameStates.get(currentState).keyPressed(k);
    }
    
    public void keyReleased(int k)
    {
        gameStates.get(currentState).keyReleased(k);
    }
}
