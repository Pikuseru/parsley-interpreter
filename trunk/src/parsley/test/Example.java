package parsley.test;

import parsley.lexing.Scanner;
import parsley.model.*;
import parsley.pool.Pool;
import parsley.runtime.*;
import parsley.values.*;

import java.io.*;
import java.util.*;

public class Example {
	public static void main(String[] args) {
		
		try {
			Engine engine = new Engine();
			engine.registerCoreCommands();		
			
			final Interrupts interrupts = new Interrupts();
			Scanner scanner = Scanner.newScanner().initWithReader(readerForFilename(args[0]));
			Program program = new Program(engine, scanner);
			Context context = new Context(engine, program, interrupts);
			
			Thread thread = new Thread(context);
			thread.start();
			
			// schedule an interrupt after an interval
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				public void run() {
					Table table = Table.newTable();
					Stack param = new Stack();
					
					// procedure call and parameter
					String proc = "interrupt_me_and_halt_with";
					param.push("#message:");
					Signature signature = new Signature(proc, param);
					
					// symbol table
					Symbol symbol = new Symbol("#message");
					Value value = new StringValue("\"stop running please!\"");
					table.save(symbol, value);
					
					// post the interrupt
					Interrupt interrupt = new Interrupt(signature, table);
					interrupts.post(interrupt);
				}
			}, 5000);
			
			// wait for the context to finish
			thread.join();
			System.out.println("main thread quitting...");
			for (Enumeration e = Pool.pools() ; e.hasMoreElements() ; ) {
				Object next = e.nextElement();
				System.out.println(next);
			}
			System.out.println(Scanner.new_scanners());

			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Reader readerForFilename(String filename) throws IOException {
		return new BufferedReader(new FileReader(filename));
	}
}
