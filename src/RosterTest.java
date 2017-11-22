import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


	
public class RosterTest {
	
	// INTERFACE DEL TESTER
	public interface CheckPerson {
		boolean test(Person p);
	}
	
	// MÈTODES DEL ROSTERTEST
	public static void printPersonsOlderThan(List<Person> roster, int age) {
		for (Person p : roster) {
			if (p.getAge() >= age) {
				p.printPerson();
			}
		}
	}
	
	public static void printPersonsByGender(List<Person> roster, Person.Sex gender) {
		for (Person p : roster) {
			if (p.getGender().equals(gender)) {
				p.printPerson();
			}
		}
	}
	
	public static void printPersonsWithinAgeRange (List<Person> roster, int low, int high) {
		for (Person p : roster) {
			if (low <= p.getAge() && p.getAge() < high) {
				p.printPerson();
			}
		}
	}
	
	// Mètode genèric on usar un tester
	public static void printPersons(List<Person> roster, CheckPerson tester) { 
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}
	
	// Passo una lambda
	public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) { // Mètode genèric
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}
	
	// Passo dues lambdes
	public static void processPersons(List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {
		for (Person p : roster) {
			if (tester.test(p)) {
				block.accept(p);
			}
		}
	}
	
	
	/// MAIN LOCAL ///
	public static void main(String[] args) {
		
		List<Person> roster = Person.createRoster();
		
		RosterTest.printPersonsOlderThan(roster, 30);
		System.out.println("================");
		
		RosterTest.printPersonsByGender(roster, Person.Sex.FEMALE);
		System.out.println("================");
		
		RosterTest.printPersonsWithinAgeRange(roster, 27, 30);
		System.out.println();
		
		
		System.out.println("=== TERCERA APROXIMACIÓ ===");
		System.out.println("Implementar interfaces al Main (classe local) per ser més flexible i ràpid");
		
		// TESTER
		class CheckPersonEligibleForSelectiveService implements CheckPerson {
			public boolean test(Person p) {
				return p.getGender() == Person.Sex.MALE
						&& p.getAge() >= 18
						&& p.getAge() <= 35;
			}
		}
		
		printPersons(roster, new CheckPersonEligibleForSelectiveService()); // els param són roster i tester
		System.out.println();
		
		System.out.println("=== CINQUENA APROXIMACIÓ ===");
		System.out.println("Les lambdes implementen una interface **d'un sol mètode** a saco");
		
		printPersons(roster,
				// Aquest és el "tester" fet a base de lambdes. Estem passant una funció com a param: primer el param i després la "programació" de la funció
				// hint: mira la implementació anterior per veure que són quasi bé idèntics
				p -> p.getGender() == Person.Sex.MALE
					&& p.getAge() >= 18
					&& p.getAge() <= 35
				);
		
		System.out.println();
		
		System.out.println("=== SISENA APROXIMACIÓ ===");
		System.out.println("El java ja ofereix interfaces prefetes");
		
		printPersonsWithPredicate(
				roster, 								// param1
				p -> p.getGender() == Person.Sex.MALE	// param2: lambda
					&& p.getAge() >= 18
					&& p.getAge() <= 35
				);
		
		System.out.println();
		
		System.out.println("=== SISENA APROXIMACIÓ ===");
		System.out.println("Podem passar més d'una lambda");
		
		processPersons(
				roster, 								// param1
				p -> p.getGender() == Person.Sex.MALE	// param2: lambda
					&& p.getAge() >= 18
					&& p.getAge() <= 35,
				p -> p.printPerson()					// param3: lambda
				);

	}
	
	

}






