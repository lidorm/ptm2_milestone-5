package test;

import interpeter.Lexer;
import interpeter.Parser;

public class MyInterpreter {

	public static  int interpret(String[] lines){
		String[] args = {
				"controls/flight/speedbrake",
				"controls/engines/current-engine/throttle",
				"instrumentation/heading-indicator/offset-deg",
				"instrumentation/airspeed-indicator/indicated-speed-kt",
				"instrumentation/attitude-indicator/indicated-roll-deg",
				"instrumentation/attitude-indicator/internal-pitch-deg",
				"controls/flight/rudder",
				"controls/flight/aileron",
				"controls/flight/elevator",
				"instrumentation/altimeter/indicated-altitude-ft"
				};
		
		Lexer lexer = new Lexer();
		Parser parser = new Parser(args);
		
		return (int) parser.parse(lexer.getLineAndAdd(lines));
	}
}
