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
			if (ok) ok = restriction.setScore(1);
			if (ok) ok = Restrictions.getInstance().addAvailable(restriction);
		}
		Restrictions.getInstance().getAvailableRestriction("No overlapping at the same hour and classroom").setEditable(false);
		Restrictions.getInstance().addApplied(Restrictions.getInstance().getAvailableRestriction("No overlapping at the same hour and classroom"));

		Restrictions.getInstance().getAvailableRestriction("All groups must fit in a classroom").setEditable(false);
		Restrictions.getInstance().addApplied(Restrictions.getInstance().getAvailableRestriction("All groups must fit in a classroom"));
		return ok;
	}

}
