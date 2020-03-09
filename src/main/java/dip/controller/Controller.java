package dip.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dip.Command;
import dip.Factory;
import dip.InputException;
import dip.console.Console;
import dip.console.ConsoleException;
import dip.data_model.Contact;
import dip.data_model.ContactDefault;
import dip.eventLoop.EventLoop;
import dip.reading.ContactFromFileLoader;
import dip.reading.ContactLoader;
import dip.reading.LoadingError;

public class Controller
{
	private static final String MY_CONTACTS_TXT = "/my_contacts.txt";
	
	private final List<Contact> list;
	private final Console console;

	private EventLoop eventLoop;

	public Controller()
	{
		list = new ArrayList<>();
		console = new Console();
		eventLoop = new EventLoop();
	}

	public void process()
	{
		try
		{
			loadContacts();
			runEventLoop();
		}
		catch(LoadingError e)
		{
			console.writeLine("Program terminated: " + e.getLocalizedMessage());
		}
	}

	private void runEventLoop()
	{
		eventLoop.start();
		
		while(eventLoop.isRunning())
		{
			try
			{
				printContacts();
				Command command = waitForCommand();
				handleCommand(command);
			}
			catch(InputException e)
			{
				console.writeLine(e.getLocalizedMessage());
			}
		}
	}

	private void handleCommand(Command command)
	{
		switch(command)
		{
			case Delete:
				onCommandDelete();
				break;
			case Exit:
				onCommandExit();
				break;
			case New:
				onCommandNew();
				break;
			case Save:
				onCommandSave();
				break;
		}
	}

	private void onCommandSave()
	{
		try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(Controller.class.getResource(MY_CONTACTS_TXT).toURI())))
		{
			for(Contact contact : list)
			{
				writeContactToFile(bw, contact);
			}
			console.writeLine("Contacts saved");
		}
		catch(IOException | URISyntaxException e)
		{
			throw new RuntimeException(e);
		}

	}

	private void writeContactToFile(BufferedWriter bw, Contact contact) throws IOException
	{
		String out = contact.getName() + "\t" + contact.getEMail();
		bw.write(out);
		bw.newLine();
	}

	private void onCommandNew()
	{
		String name = readInput("Enter name: ");
		String email = readInput("Enter e-mail: ");

		if(name != null && email != null)
		{
			Contact contact = Factory.get().newContact(name, email);
			list.add(contact);
		}
	}

	private void onCommandExit()
	{
		endProgram();
	}

	private void onCommandDelete()
	{
		try
		{
			int index = askUserForNumberToDelete();
			checkEnteredIndexValid(index);
			index--; // index still 1 based, but list is 0 base, so decrease index
			list.remove(index);
		}
		catch(InputException e)
		{
			console.writeLine(e.getLocalizedMessage());
		}

	}

	private void checkEnteredIndexValid(int index) throws InputException
	{
		if(index < 1 || index > list.size())
			throw new InputException("Input error: Contact does not exist");
	}

	private int askUserForNumberToDelete() throws InputException
	{
		int index;

		String indexString = readInput("Enter number of contact to be deleted: ");

		try
		{
			index = Integer.valueOf(indexString);
		}
		catch(NumberFormatException e)
		{
			throw new InputException("Input error: Number was expected", e);
		}

		return index;
	}

	private Command waitForCommand() throws InputException
	{
		String commandInput = readInput("[N]ew record, [D]elete, [S]ave, [E]xit: ");
		Command command = evaluateCommandInput(commandInput);
		return command;
	}

	private Command evaluateCommandInput(String commandInput) throws InputException
	{
		if(Objects.equals(commandInput, "D"))
			return Command.Delete;
		else if(Objects.equals(commandInput, "E"))
			return Command.Exit;
		else if(Objects.equals(commandInput, "N"))
			return Command.New;
		else if(Objects.equals(commandInput, "S"))
			return Command.Save;
		else
			throw new InputException("Input error: Unsupported Command");
	}

	private void endProgram()
	{
		console.writeLine("Have a nice day");
		eventLoop.stop();
	}

	private void printContacts()
	{
		for(int i = 0; i < list.size(); i++)
		{
			console.writeLine(i + 1 + ") " + list.get(i).getName() + ": " + list.get(i).getEMail());
		}
	}

	private void loadContacts() throws LoadingError
	{
		ContactLoader contactLoader = new ContactFromFileLoader(MY_CONTACTS_TXT);
		var contacts = contactLoader.load();
		list.addAll(contacts);
	}

	private String readInput(String prompt)
	{
		try
		{
			String line = console.readLine(prompt);
			return line;
		}
		catch(ConsoleException e)
		{
			throw new RuntimeException(e);
		}
	}

}
