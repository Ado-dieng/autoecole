package controller;

import service.DisplayService;
import service.console.ConsoleDisplayService;
import service.console.ScannerMenuService;

public class PersonneController {
	private final ScannerMenuService menuService;
	private final DisplayService displayService;

	public PersonneController() {
		displayService=new ConsoleDisplayService();
		menuService=new ScannerMenuService(displayService);
	}
	
	public void Index()
	{
		displayService.displayBienvenu();
		displayService.displayMenuPrincipal();
		menuService.displayMenu();
	}
}
