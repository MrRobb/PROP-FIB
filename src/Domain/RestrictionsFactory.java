package Domain;

import java.util.Set;

public class RestrictionsFactory {

	public static boolean produce() {

		boolean ok = true;

		Set<String> available = Blocks.getInstance().getNames();

		for (String name : available) {

			Restriction restriction = new Restriction(name);

			if (ok) ok = restriction.add(name);

			if (ok) ok = Restrictions.getInstance().addAvailable(name, restriction);
		}

		return ok;
	}
}
