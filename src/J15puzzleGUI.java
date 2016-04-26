/*
 * Sceleton was taken from https://github.com/TideSofDarK/tutorial-basics/tree/master/src/org/tides/tutorial
 * Original tutorial was read from https://habrahabr.ru/post/145433/
 *
 */

package ua.kiev.agrit.java.j15puzzle;

class J15puzzleGUI extends Canvas implements Runnable {
    
    private static final long serialVersionUID = 1L;
    public static int WIDTH = 640; //ширина
    public static int HEIGHT = 480; //высота 
    public static String NAME = "15 PUZZLE"; //заголовок окна

    public void run() {
    	long lastTime = System.currentTimeMillis();
    	long delta;
    	
    	init();
    		
    	while(running) {
    		delta = System.currentTimeMillis() - lastTime;
    		lastTime = System.currentTimeMillis();	
    		update(delta);
    		render();
    	}
    }
    	
    public void init() {
    	//TODO
    }
    	
    public void render() {
    	BufferStrategy bs = getBufferStrategy(); 
    	if (bs == null) {
    		createBufferStrategy(2); //создаем BufferStrategy для нашего холста
    		requestFocus();
    		return;
    	}
    		
    	Graphics g = bs.getDrawGraphics(); //получаем Graphics из созданной нами BufferStrategy
    	g.setColor(Color.black); //выбрать цвет
    	g.fillRect(0, 0, getWidth(), getHeight()); //заполнить прямоугольник 
    	g.dispose();
    	bs.show(); //показать
    }

    public void update(long delta) {
    	//TODO
    }
    
    public void start() {
	    running = true;
	    new Thread(this).start();
    }

    public static void main(String[] args) {
    	J15puzzleGUI game = new J15puzzleGUI();
    	game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    
    	JFrame frame = new JFrame(J15puzzleGUI.NAME);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //выход из приложения по нажатию клавиши ESC
    	frame.setLayout(new BorderLayout());
    	frame.add(game, BorderLayout.CENTER); //добавляем холст на наш фрейм
    	frame.pack();
    	frame.setResizable(false);
    	frame.setVisible(true);
    
    	game.start();
    }

    /*
    public static void main(String[] args) {

        J15puzzleGUI j15 = new J15puzzleGUI();

        JFrame f = new JFrame();
        f.setSize(640,480);
        f.add(j15);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    */
    
}
