package application; 
import java.util.Date;
import java.text.*;


public class Faecher implements Comparable<Faecher> {
	
	  private String name;
	    private String level;
	    private Date klausurDatum;
	    private boolean schwierig;
	    private int maximalerBetrag;
            private String uhrzeit;

	    public Faecher(String name, String vorname, Date geburtstag, boolean geschenk, String uhrzeit ) {
	        this.name = name;
	        this.level = vorname;
	        this.klausurDatum = geburtstag;
	        this.schwierig = geschenk;
                this.uhrzeit = uhrzeit;
	        
	    }

	    public String getName() {
	        return name;
	    }

	    public String getLevel() {
	        return level;
	    }

	    public Date getKlausurdatum() {
	        return klausurDatum;
	        } 

	
	    public boolean istSchwierig() {
	        return schwierig;
	    }

	    public String getUhrzeit() {
	        return uhrzeit;
	    }

	    @Override
	    public int compareTo(Faecher other) {
	        int dateComparison = klausurDatum.compareTo(other.getKlausurdatum());
	        if (dateComparison != 0) {
	            return dateComparison;
	        }

	        int lastNameComparison = name.compareTo(other.getName());
	        if (lastNameComparison != 0) {
	            return lastNameComparison;
	        }

	        return level.compareTo(other.getLevel());
	    }

	    @Override
	    public String toString() {
	        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	        String formattedKlausurDate = dateFormat.format(klausurDatum);
                

	        StringBuilder sb = new StringBuilder();
                
	        sb.append(formattedKlausurDate).append(" ");
                sb.append(" um "+uhrzeit+" ");
	        sb.append(name).append(", ").append(level+" ");
                
	        if (schwierig) {
	            sb.append(getName()).append(" --> ist Schwierig fuer dich ");
	        } else {
	            sb.append(getName()).append(" --> ist nicht Schwierig");
	        }
	        return sb.toString();
	    }


}
