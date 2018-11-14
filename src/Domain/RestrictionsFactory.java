package Domain;

import java.util.ArrayList;
import java.util.Set;

public class RestrictionsFactory {

	public static boolean produce() {

		boolean ok = true;

		Set<String> available = Blocks.getInstance().getNames();

		for (String name : available) {

			Restriction restriction = new Restriction(name);

			if (ok) ok = restriction.add(name);

			if (ok) ok = Restrictions.getInstance().addAvailable(restriction);
		}

		return ok;
	}

	public static boolean producePrueba() {

		boolean ok = true;

		//Restriction subclass_restr = new Restriction("No overlapping between class and subclass");
		//subclass_restr.add("No group and subgroup overlapped");
		//Restrictions.getInstance().addAvailable(subclass_restr);
		//Restrictions.getInstance().addApplied(subclass_restr.getName(), new ArrayList<Object[]>());

		return ok;
	}
}
