package uo.ri.ui.mechanic;

import alb.util.console.Printer;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.business.impl.BusinessFactory;
import uo.ri.conf.Factory;
import uo.ri.persistence.jpa.JpaRepositoryFactory;
import uo.ri.persistence.jpa.executor.JpaExecutorFactory;

public class MechanicMain {

    private static class MainMenu extends BaseMenu {
	MainMenu() {
	    menuOptions = new Object[][] { { "Mecánico", null },
		    { "Listar reparaciones asignadas",
			    NotYetImplementedAction.class },
		    { "Añadir repuestos a reparación",
			    NotYetImplementedAction.class },
		    { "Eliminar repuestos a reparación",
			    NotYetImplementedAction.class },
		    { "Cerrar una reparación",
			    NotYetImplementedAction.class }, };
	}
    }

    private MainMenu menu = new MainMenu();

    public static void main(String[] args) {
	new MechanicMain().config().run().close();
    }

    private MechanicMain config() {
	Factory.service = new BusinessFactory();
	Factory.repository = new JpaRepositoryFactory();
	Factory.executor = new JpaExecutorFactory();

	return this;
    }

    public MechanicMain run() {
	try {
	    menu.execute();

	} catch (RuntimeException rte) {
	    Printer.printRuntimeException(rte);
	}
	return this;
    }

    private void close() {
    }

}
