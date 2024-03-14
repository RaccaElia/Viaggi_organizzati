package viaggi_organizzati;

import java.util.Comparator;

public class ComparatorViaggiDurata implements Comparator<Viaggio>{

	@Override
	public int compare(Viaggio o1, Viaggio o2) {
		if(o1.getDurata()==o2.getDurata())	{
			return o1.getIdentificativo().compareTo(o2.getIdentificativo());
		}
		return o1.getDurata()-o2.getDurata();
	}

}
