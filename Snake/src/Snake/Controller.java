package Snake;
public class Controller {

	private View v;
	private Model m;
	
	public Controller(View v, Model m) {
		this.v = v;
		this.m = m;
	}
	
	public static void main(String[] args) {
		Controller c = new Controller(null, new Model());
		
		c.v = new View(c);
		
	}
 
}
