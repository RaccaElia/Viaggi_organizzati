package viaggi_organizzati;

import java.util.Comparator;

public class ComparatorPartenzeDataPrezzo implements Comparator<Partenza>{

	@Override
	public int compare(Partenza o1, Partenza o2) {
		if(o1.data.equals(o2.data))	{
			return (int) (o1.prezzoPersona-o2.prezzoPersona);
		}
		return o1.data.compareTo(o2.data);
	}
	
}
